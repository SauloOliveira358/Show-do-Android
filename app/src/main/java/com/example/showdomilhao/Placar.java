package com.example.showdomilhao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

        SharedPreferences preferences = getSharedPreferences(Dados.ARQUIVO_USUARIO, MODE_PRIVATE);
        String nome = preferences.getString(Dados.NOME, "Sem nome");
        int pontuacao = preferences.getInt(Dados.PONTUACAO, 0);

        placar = new String[]{
                 nome + pontuacao


        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                placar
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
}