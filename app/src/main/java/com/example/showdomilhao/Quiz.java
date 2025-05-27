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
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Random;


public class Quiz extends AppCompatActivity {
private TextView bemVindo,pergunta,numerodaPergunta;
private CheckBox resposta1,resposta2,resposta3,resposta4;
private Button proxima_Pergunta;
private boolean checkBoxTexto;
private int pontuação = 0,questoes = 1;

    private String [] Perguntas = {
            "Qual arquivo é responsável por declarar permissões e configurações básicas de um app Android?",      // Pergunta 1
            "Para alterar o layout visual de uma activity, qual tipo de arquivo é normalmente utilizado?",        // Pergunta 2
            "Qual linguagem é oficialmente suportada pelo Google para desenvolvimento Android desde 2019?",       // Pergunta 3
            "Qual classe é usada para criar uma nova tela (interface) em Android?",                               // Pergunta 4
            "Qual é o propósito do arquivo build.gradle (Module: app)?",                                          // Pergunta 5
            "Qual método do ciclo de vida de uma Activity é chamado quando ela se torna visível ao usuário?",     // Pergunta 6
            "O que é um Intent no Android?",                                                                      // Pergunta 7
            "Qual método usamos para detectar cliques em um botão no Android?",                                   // Pergunta 8
            "O que faz o método apply() do SharedPreferences.Editor?",                                            // Pergunta 9
            "Como verificamos se um CheckBox está marcado?",                                                      // Pergunta 10
            "Como iniciamos o uso do SharedPreferences?",                                                         // Pergunta 11
            "Qual interface é implementada para usar um OnClickListener?",                                        // Pergunta 12
            "Qual destes componentes permite ao usuário digitar texto?",                                          // Pergunta 13
            "Qual a primeira coisa que um progamador Android faz ao abrir o Android Studio?",                     // Pergunta 14
            "Para que serve o botão \"Forçar Parada\" em um app?"                                                 // Pergunta 15
    };

    private String[][] Respostas = {
            {"MainActivity.java","AndroidManifest.xml","strings.xml","styles.xml"},                              // Pergunta 1
            {".java",".gradle",".xml",".json"},                                                                   // Pergunta 2
            {"JavaScript","C++","Kotlin", "Python"},                                                              // Pergunta 3
            {"Intent", "Fragment", "View", "Activity"},                                                           // Pergunta 4
            {"Armazenar strings do app","Navegação entre activities","Dependências e configurações de build", "Elementos gráficos"}, // Pergunta 5
            {"onDestroy()", "onStart()", "onCreate()", "onPause()"},                                              // Pergunta 6
            {"Uma mensagem para ativar componentes","Um tipo de layout", "Uma permissão especial", "Um componente visual"}, // Pergunta 7
            {"setOnClickListener()","onClick()", "setOnClick()", "detectClick()"},                                // Pergunta 8
            {"Salva dados de forma síncrona", "Apaga os dados", "Aplica mudanças de forma assíncrona", "Inicia o SharedPreferences"}, // Pergunta 9
            {"checkbox.isChecked()", "checkbox.isMarked()", "checkbox.getCheck()", "checkbox.isSelected()"},     // Pergunta 10
            {"loadPreferences()", "startPreferences()", "openFileInput()", "getSharedPreferences()"},             // Pergunta 11
            {"Runnable", "OnClickListener", "ClickEvent", "ViewManager"},                                         // Pergunta 12
            {"TextView","Button","EditText","ImageView"},                                                         // Pergunta 13
            {"Programa um app","Vai tomar um café ate carregar","Reza pro Gradle nao quebrar","Fecha e tenta de novo"}, // Pergunta 14
            {"Desabafar","Dizer: Chega,já deu por hoje!","Fazer o app pensar sobre seus erros","Todas as anteriores"}, // Pergunta 15
    };

    private int [] respostaCerta = {
            1,  // Pergunta 1
            2,  // Pergunta 2
            2,  // Pergunta 3
            3,  // Pergunta 4
            2,  // Pergunta 5
            1,  // Pergunta 6
            0,  // Pergunta 7
            0,  // Pergunta 8
            2,  // Pergunta 9
            0,  // Pergunta 10
            3,  // Pergunta 11
            1,  // Pergunta 12
            2,  // Pergunta 13
            0,  // Pergunta 14
            3   // Pergunta 15
    };
private int respostaSelecionada;
private int perguntaAtual = 0;
private int numeroPerguntas = 10;


    private ArrayList<Integer> indicesDisponiveis = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove a barra de título (ActionBar)
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_quiz);
        bemVindo = findViewById(R.id.IdTextViewShowdoAndroid);
        pergunta = findViewById(R.id.IdTextViewPergunta);
        resposta1 = findViewById(R.id.idCheckboxResposta1);
        resposta2 = findViewById(R.id.IdCheckboxResposta2);
        resposta3 = findViewById(R.id.IdCheckboxResposta3);
        resposta4 = findViewById(R.id.IdCheckboxResposta4);
        numerodaPergunta = findViewById(R.id.IdTextViewNumeroPergunta);
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
                if (!checkBoxTexto) {
                    Toast.makeText(getApplicationContext(), "Selecione uma resposta!", Toast.LENGTH_SHORT).show();
                    return;
                }

                acertoErro();
                perguntaAtual++;
                numeroPerguntas--;
                respostaSelecionada = -1;
                checkBoxTexto = false;

                if (numeroPerguntas > 0) {
                    carregar_Perguntas();
                    if (numeroPerguntas == 1) {
                        proxima_Pergunta.setText("Finalizar");
                    }
                } else {
                    SharedPreferences preferences = getSharedPreferences(ARQUIVO_USUARIO, 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt(PONTUACAO, pontuação);
                    editor.apply();
                    Intent intent2 = new Intent(getApplicationContext(), Resultado.class);
                    startActivity(intent2);
                }
            }
        }); // fim do botao proxima pergunta


    }
        //Carregar as perguntas de forma altomatica
    public void carregar_Perguntas(){
        resposta1.setChecked(false);
        resposta2.setChecked(false);
        resposta3.setChecked(false);
        resposta4.setChecked(false);
        checkBoxTexto =false;
        numerodaPergunta.setText("Pergunta "+ questoes + " de 10");
        questoes++;
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



    }//fim metodo carregar_Perguntas

    //metodo de acerto e erro
    public void acertoErro(){
        if(respostaSelecionada == respostaCerta[perguntaAtual]){
            pontuação++;
            String Stringpontuação = String.valueOf(pontuação);
            //ao pra testes
            bemVindo.setText(bemVindo.getText().toString());
        }
    }


}