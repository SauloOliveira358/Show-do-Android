package com.example.showdomilhao;

import static com.example.showdomilhao.Dados.*;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Random;


public class Quiz extends AppCompatActivity {
private TextView bemVindo,pergunta;
private CheckBox resposta1,resposta2,resposta3,resposta4;
private Button proxima_Pergunta;
private boolean checkBoxTexto;
private int pontuação = 0;

private String []  Perguntas = {
        "Qual arquivo é responsável por declarar permissões e configurações básicas de um app Android?",
        "Para alterar o layout visual de uma activity, qual tipo de arquivo é normalmente utilizado?",
        "Pergunta3",
};

private String[][] Respostas = {
        {"MainActivity.java","AndroidManifest.xml","strings.xml","styles.xml"},
        {".java",".gradle",".xml",".json"},
        {"Resposta1","Resposta2","Resposta3","Resposta4"},
};

private int [] respostaCerta = {1,2,0}; //aqui coloca as respostas correta em cada pergunta na ordem certa
private int respostaSelecionada;
private int perguntaAtual = 0;
private int numeroPerguntas = 2;


    private ArrayList<Integer> indicesDisponiveis = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        bemVindo = findViewById(R.id.IdTextViewShowdoAndroid);
        pergunta = findViewById(R.id.IdTextViewPergunta);
        resposta1 = findViewById(R.id.idCheckboxResposta1);
        resposta2 = findViewById(R.id.IdCheckboxResposta2);
        resposta3 = findViewById(R.id.IdCheckboxResposta3);
        resposta4 = findViewById(R.id.IdCheckboxResposta4);
        //adicionar os indices disponiveis
        for (int i = 0; i < Perguntas.length; i++) {
            indicesDisponiveis.add(i);
        }
        carregar_Perguntas();
        proxima_Pergunta = findViewById(R.id.IdBtnProximaPergunta);



        View.OnClickListener clique = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox clicou = (CheckBox) v;
                //os ifs pega  cada resposta a qual clicou
                // e atribu um valor a variavel resposta Selecionada
                if(v == resposta1)respostaSelecionada = 0;
                if(v == resposta2)respostaSelecionada = 1;
                if(v == resposta3)respostaSelecionada = 2;
                if(v == resposta4)respostaSelecionada = 3;
                resposta1.setChecked(false);
                resposta2.setChecked(false);
                resposta3.setChecked(false);
                resposta4.setChecked(false);

                clicou.setChecked(true);
                checkBoxTexto = true;       //pra pegar se selecionao e nao pular
            }
        };

        resposta1.setOnClickListener(clique);
        resposta2.setOnClickListener(clique);
        resposta3.setOnClickListener(clique);
        resposta4.setOnClickListener(clique);




        //botao proxima pergunta
        proxima_Pergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acertoErro();  // o metodo pra saber se acertou ou errou
                if(numeroPerguntas > 0){
                    if(numeroPerguntas == 0){
                        proxima_Pergunta.setText("Finalizar");
                    }
                    if(checkBoxTexto == true){
                        perguntaAtual++;
                        numeroPerguntas--;
                        carregar_Perguntas();
                        //bloco para tirar o clique que fica no check box


                        //fim do bloco
                }
                }else{
                    SharedPreferences preferences = getSharedPreferences(ARQUIVO_USUARIO, 0);
                    SharedPreferences.Editor editor = preferences.edit();  //editamos
                    editor.putInt(PONTUACAO,pontuação);
                    editor.commit();//Salva o arquivo no banco
                    editor.apply();
                    Intent intent2 = new Intent(getApplicationContext(),Resultado.class);
                    startActivity(intent2);

                }
            }
        }); // fim do botao proxima pergunta


    }
        //Carregar as perguntas de forma altomatica
    public void carregar_Perguntas(){

        if (indicesDisponiveis.size() == 0) return;

        Random random = new Random();
        int posicao = random.nextInt(indicesDisponiveis.size());
        perguntaAtual = indicesDisponiveis.get(posicao);

        // Remove o índice sorteado da lista
        indicesDisponiveis.remove(posicao);

        //aqui pega as perguntas pela perguntaAtual ai pega certinho de todas é o indice de controle
        pergunta.setText(Perguntas[perguntaAtual]);
        //as resposta sao matriz porque ai cada linha tem 4 alternativas ai o indice delas e que vai pra cada alternativa
        resposta1.setText(Respostas[perguntaAtual][0]);
        resposta2.setText(Respostas[perguntaAtual][1]);
        resposta3.setText(Respostas[perguntaAtual][2]);
        resposta4.setText(Respostas[perguntaAtual][3]);


        resposta1.setChecked(false);
        resposta2.setChecked(false);
        resposta3.setChecked(false);
        resposta4.setChecked(false);
        checkBoxTexto =false;
    }//fim metodo carregar_Perguntas

    //metodo de acerto e erro
    public void acertoErro(){
        if(respostaSelecionada == respostaCerta[perguntaAtual]){
            pontuação++;
            String Stringpontuação = String.valueOf(pontuação);
            //ao pra testes
            bemVindo.setText(bemVindo.getText().toString() + " " + numeroPerguntas);
        }
    }


}