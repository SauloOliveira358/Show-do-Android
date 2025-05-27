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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Placar extends AppCompatActivity {

    private ListView listaPlacar;
    private Button voltaPrincipal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placar);

        listaPlacar = findViewById(R.id.idListaPlacar);
        voltaPrincipal = findViewById(R.id.idbtnVoltarP);

        SharedPreferences prefs = getSharedPreferences("PLACAR", MODE_PRIVATE);
        String placarStr = prefs.getString("placar", "");

        // Parsear dados
        List<RankingItem> rankingList = new ArrayList<>();

        if (!placarStr.isEmpty()) {
            String[] entradas = placarStr.split(";");
            for (String entrada : entradas) {
                if (!entrada.trim().isEmpty()) {
                    String[] partes = entrada.split(":");
                    if (partes.length == 2) {
                        String nome = partes[0];
                        int pontos = Integer.parseInt(partes[1]);
                        rankingList.add(new RankingItem(nome, pontos));
                    }
                }
            }
        }

        // Ordenar decrescente
        Collections.sort(rankingList, new Comparator<RankingItem>() {
            @Override
            public int compare(RankingItem o1, RankingItem o2) {
                return o2.pontos - o1.pontos;
            }
        });

        // Preparar para exibir
        List<String> listaFormatada = new ArrayList<>();
        for (RankingItem item : rankingList) {
            listaFormatada.add(item.nome + " - " + item.pontos + " pts");
        }

        // Adapter
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

    private static class RankingItem {
        String nome;
        int pontos;

        RankingItem(String nome, int pontos) {
            this.nome = nome;
            this.pontos = pontos;
        }
    }



}