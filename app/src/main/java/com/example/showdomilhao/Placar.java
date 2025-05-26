package com.example.showdomilhao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Placar extends AppCompatActivity {

    private ListView listaPlacar;
    private Button voltaPrincipal;
    private String[] placar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placar);

        listaPlacar = findViewById(R.id.idListaPlacar);
        voltaPrincipal = findViewById(R.id.idbtnVoltarP);

        SharedPreferences prefs = getSharedPreferences("PLACAR", MODE_PRIVATE);
        String placarStr = prefs.getString("placar", "");

        String[] entradas = placarStr.split(";");
        List<String> listaFormatada = new ArrayList<>();
        List<Integer> pontos = new ArrayList<>();
        List<String> nomes = new ArrayList<>();

        for (String entrada : entradas) {
            if (entrada.trim().isEmpty()) continue;

            String[] partes = entrada.split(":");
            if (partes.length == 2) {
                nomes.add(partes[0]);
                pontos.add(Integer.parseInt(partes[1]));
            }
        }

// Ordenar as pontuações em ordem decrescente
        for (int i = 0; i < pontos.size(); i++) {
            for (int j = i + 1; j < pontos.size(); j++) {
                if (pontos.get(j) > pontos.get(i)) {
                    // Troca pontuação
                    int tempPonto = pontos.get(i);
                    pontos.set(i, pontos.get(j));
                    pontos.set(j, tempPonto);

                    // Troca nome correspondente
                    String tempNome = nomes.get(i);
                    nomes.set(i, nomes.get(j));
                    nomes.set(j, tempNome);
                }
            }
        }

// Montar lista final formatada
        for (int i = 0; i < nomes.size(); i++) {
            listaFormatada.add(nomes.get(i) + " - " + pontos.get(i));
        }

// Exibir no ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaFormatada
        );

        listaPlacar.setAdapter(adapter);



        voltaPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Placar.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
    public void salvarPontuacao(String nome, int pontos) {
        SharedPreferences prefs = getSharedPreferences("PLACAR", MODE_PRIVATE);
        String placarStr = prefs.getString("placar", "");

        // Adiciona nova pontuação
        placarStr += nome + ":" + pontos + ";";

        prefs.edit().putString("placar", placarStr).apply();
    }

}