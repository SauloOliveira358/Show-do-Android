package com.example.showdomilhao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz extends AppCompatActivity {

    // Componentes da interface
    private TextView textoBemVindo, textoPergunta, textoNumeroPergunta;
    private RadioGroup grupoRespostas;
    private RadioButton resposta1, resposta2, resposta3, resposta4;
    private Button botaoProxima;

    // Controle do quiz
    private int perguntaAtual = 0;
    private int pontuacao = 0;

    // Perguntas
    private String[] perguntas = {
            "Onde você define permissões como acesso à internet no Android?",
            "Qual método do ciclo de vida de uma Activity é chamado quando ela é criada?",
            "Qual é a extensão dos arquivos de layout no Android Studio?",
            "Qual destes não é um componente de interface no Android?",
            "O que é um Intent no desenvolvimento Android?",
            "O que o arquivo AndroidManifest.xml NÃO faz?",
            "Qual linguagem de marcação é usada para criar layouts no Android Studio?",
            "Qual componente serve para exibir um texto fixo na tela?",
            "Onde você altera o nome do aplicativo?",
            "Qual destes componentes permite ao usuário digitar texto?"
    };


    // Alternativas
    private String[][] respostas = {
            {"build.gradle", "activity_main.xml", "AndroidManifest.xml", "MainActivity.java"}, // Pergunta 1
            {"onStart()", "onResume()", "onPause()", "onCreate()"}, // Pergunta 2
            {".html", ".xml", ".json", ".layout"}, // Pergunta 3
            {"TextView", "Button", "Intent", "EditText"}, // Pergunta 4
            {"Um botão na interface", "Um layout XML", "Um mecanismo para navegação entre componentes", "Um tipo de banco de dados"}, // Pergunta 5
            {"Declara atividades e serviços", "Configura permissões", "Define o layout da aplicação", "Define o nome do aplicativo"}, // Pergunta 6
            {"HTML", "CSS", "XML", "JSON"}, // Pergunta 7
            {"EditText", "Button", "TextView", "ImageView"}, // Pergunta 8
            {"No arquivo MainActivity.java", "No AndroidManifest.xml", "No arquivo strings.xml", "No build.gradle"}, // Pergunta 9
            {"TextView", "Button", "EditText", "ImageView"} // Pergunta 10
    };


    // Índice das respostas corretas
    private int[] respostasCorretas = {2, 3, 1, 2, 2, 2, 2, 2, 2, 2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Ligando os elementos do XML
        textoBemVindo = findViewById(R.id.IdTextViewShowdoAndroid);
        textoPergunta = findViewById(R.id.IdTextViewPergunta);
        textoNumeroPergunta = findViewById(R.id.IdTextViewNumeroPergunta);
        grupoRespostas = findViewById(R.id.IdGrupoRespostas);
        resposta1 = findViewById(R.id.IdRadioResposta1);
        resposta2 = findViewById(R.id.IdRadioResposta2);
        resposta3 = findViewById(R.id.IdRadioResposta3);
        resposta4 = findViewById(R.id.IdRadioResposta4);
        botaoProxima = findViewById(R.id.IdBtnProximaPergunta);

        // Carregar a primeira pergunta
        carregarPergunta();

        // Clique no botão Próxima
        botaoProxima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!respostaSelecionada()) {
                    Toast.makeText(Quiz.this, "Selecione uma resposta!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (respostaCorreta()) {
                    pontuacao++;
                    Toast.makeText(Quiz.this, "Acertou!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Quiz.this, "Errou!", Toast.LENGTH_SHORT).show();
                }

                perguntaAtual++;

                if (perguntaAtual < perguntas.length) {
                    carregarPergunta();
                } else {
                    mostrarResultado();
                }
            }
        });
    }

    // ✔️ Carrega pergunta e respostas na tela
    private void carregarPergunta() {
        textoPergunta.setText(perguntas[perguntaAtual]);
        textoNumeroPergunta.setText("Pergunta " + (perguntaAtual + 1) + " de " + perguntas.length);

        resposta1.setText(respostas[perguntaAtual][0]);
        resposta2.setText(respostas[perguntaAtual][1]);
        resposta3.setText(respostas[perguntaAtual][2]);
        resposta4.setText(respostas[perguntaAtual][3]);

        grupoRespostas.clearCheck();

        if (perguntaAtual == perguntas.length - 1) {
            botaoProxima.setText("Finalizar");
        } else {
            botaoProxima.setText("Próxima");
        }
    }

    // ✔️ Verifica se uma resposta foi selecionada
    private boolean respostaSelecionada() {
        return grupoRespostas.getCheckedRadioButtonId() != -1;
    }

    // ✔️ Verifica se a resposta selecionada é correta
    private boolean respostaCorreta() {
        int idSelecionado = grupoRespostas.getCheckedRadioButtonId();
        int respostaIndex = -1;

        if (idSelecionado == resposta1.getId()) respostaIndex = 0;
        else if (idSelecionado == resposta2.getId()) respostaIndex = 1;
        else if (idSelecionado == resposta3.getId()) respostaIndex = 2;
        else if (idSelecionado == resposta4.getId()) respostaIndex = 3;

        return respostaIndex == respostasCorretas[perguntaAtual];
    }

    // ✔️ Mostra resultado final
    private void mostrarResultado() {
        Intent intent = new Intent(Quiz.this, Resultado.class);
        intent.putExtra("pontuacao", pontuacao);
        startActivity(intent);
        finish();
    }

}

