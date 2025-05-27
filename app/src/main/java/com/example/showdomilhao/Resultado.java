package com.example.showdomilhao;

// Importação das bibliotecas necessárias
import static com.example.showdomilhao.Dados.ARQUIVO_USUARIO;
import static com.example.showdomilhao.Dados.NOME;
import static com.example.showdomilhao.Dados.PONTUACAO;

import android.content.Intent; // Para navegação entre telas (Activities)
import android.content.SharedPreferences;
import android.os.Bundle; // Para salvar e recuperar o estado da Activity
import android.support.v7.app.AppCompatActivity; // Classe base para Activities
import android.view.View; // Para lidar com eventos de clique
import android.widget.Button; // Botão na interface
import android.widget.TextView; // Texto na interface

// Declaração da classe Resultado, que herda de AppCompatActivity
public class Resultado extends AppCompatActivity {

    // Declaração dos componentes da interface
    private TextView textoPontuacao; // Exibe a pontuação final
    private Button botaoReiniciar;   // Botão para reiniciar o quiz
    private int resultado_Quiz;
    // Método onCreate é chamado quando a Activity é criada
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Chama o método da classe pai
        setContentView(R.layout.activity_resultado); // Define qual layout será usado (activity_resultado.xml)

        // Faz ligação entre os componentes do XML e as variáveis no código
        textoPontuacao = findViewById(R.id.idTextoValorPontuacao);
        botaoReiniciar = findViewById(R.id.idBotaoReiniciar);

        // Recupera a pontuação enviada pela Activity Quiz
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_USUARIO, 0);
        int pontuacao = preferences.getInt(PONTUACAO, 0);
        String nome = preferences.getString(NOME, "Sem nome");

        Pontuacao.salvarPontuacao(Resultado.this, nome, pontuacao);
        // Se não receber nada, assume 0 como valor padrão




        // Exibe a pontuação na tela
        textoPontuacao.setText(String.valueOf(pontuacao));

        // Define ação ao clicar no botão "Reiniciar Quiz"
        botaoReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria uma Intent para iniciar novamente a tela do Quiz
                Intent intent = new Intent(Resultado.this, MainActivity.class);
                startActivity(intent); // Inicia o Quiz
                finish(); // Encerra a tela de Resultado para não ficar acumulando Activities
            }
        });
    }



}
