package com.example.showdomilhao;

import static com.example.showdomilhao.Dados.ARQUIVO_USUARIO;

import static com.example.showdomilhao.Dados.PONTUACAO;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class Cartas extends AppCompatActivity {
private Button cartas;
private int sorte;
private ImageView imagemBaralho;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartas);
        cartas= findViewById(R.id.IdBtnCartasTrocar);
        imagemBaralho = findViewById(R.id.IdImagemViewCartas);


        cartas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartas.setEnabled(false);
                Intent intent = new Intent(getApplicationContext(), Quiz.class);

                Random random = new Random();
                sorte = random.nextInt(3) + 1;
                //Carta As 1 carta
                if(sorte == 1){
                    imagemBaralho.setImageResource(R.drawable.as_carta);

                }

                //Cartas 2 e 2 cartas
                else if(sorte == 2){
                    imagemBaralho.setImageResource(R.drawable.duas_cartas);


                }

                //Carta 3 e 3 cartas
                else if(sorte ==3){
                    imagemBaralho.setImageResource(R.drawable.tres_carta);


                }
                new Handler().postDelayed(() -> {
                    intent.putExtra("Carta", sorte);
                    setResult(RESULT_OK, intent);
                    finish(); // Fecha a activity
                }, 3000);



            }
        });
    }
}