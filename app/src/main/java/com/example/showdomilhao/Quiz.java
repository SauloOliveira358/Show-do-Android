package com.example.showdomilhao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Quiz extends AppCompatActivity {
private TextView bemVindo,pergunta;
private CheckBox resposta1,resposta2,resposta3,resposta4;
private Button proxima_Pergunta;
private boolean checkBoxTexto;
private int pontuação;

private String []  Perguntas = {
        "Pergunta1",
        "Pergunta2",
        "Pergunta3",
};

private String[][] Respostas = {
        {"Resposta1","Resposta2","Resposta3","Resposta4"},
        {"Resposta1","Resposta2","Resposta3","Resposta4"},
        {"Resposta1","Resposta2","Resposta3","Resposta4"},
};

private int [] respostaCerta = {1,2,0}; //aqui coloca as respostas correta em cada pergunta na ordem certa
private int respostaSelecionada;
private int perguntaAtual = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        bemVindo = findViewById(R.id.IdTextViewShowdoAndroid);
        pergunta = findViewById(R.id.IdTextViewPergunta);
        resposta1 = findViewById(R.id.IdCheckboxResposta1);
        resposta2 = findViewById(R.id.IdCheckboxResposta2);
        resposta3 = findViewById(R.id.IdCheckboxResposta3);
        resposta4 = findViewById(R.id.IdCheckboxResposta4);

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
                if(perguntaAtual <= Perguntas.length-2){
                    if(perguntaAtual <= Perguntas.length-3){
                        proxima_Pergunta.setText("Finalizar");
                    }
                    if(checkBoxTexto == true){
                        perguntaAtual++;
                        carregar_Perguntas();
                        //bloco para tirar o clique que fica no check box
                        resposta1.setChecked(false);
                        resposta2.setChecked(false);
                        resposta3.setChecked(false);
                        resposta4.setChecked(false);
                        //fim do bloco
                }
                }else{

                    //Tela final de quantas acertou ou o que fazer
                    //
                    //
                    //

                }
            }
        }); // fim do botao proxima pergunta


    }
        //Carregar as perguntas de forma altomatica
    public void carregar_Perguntas(){
        //aqui pega as perguntas pela perguntaAtual ai pega certinho de todas é o indice de controle
        pergunta.setText(Perguntas[perguntaAtual]);
        //as resposta sao matriz porque ai cada linha tem 4 alternativas ai o indice delas e que vai pra cada alternativa
        resposta1.setText(Respostas[perguntaAtual][0]);
        resposta2.setText(Respostas[perguntaAtual][1]);
        resposta3.setText(Respostas[perguntaAtual][2]);
        resposta4.setText(Respostas[perguntaAtual][3]);

    }//fim metodo carregar_Perguntas

    //metodo de acerto e erro
    public void acertoErro(){
        if(respostaSelecionada == respostaCerta[perguntaAtual]){
            pontuação++;
            String Stringpontuação = String.valueOf(pontuação);
            //ao pra testes
            bemVindo.setText(bemVindo.getText().toString() + " " + Stringpontuação);
        }
    }


}