package com.example.showdomilhao;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button iniciar,placar,comoJogar,sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciar = findViewById(R.id.idBtnIniciar);
        placar = findViewById(R.id.idBtnPlacar);
        placar = findViewById(R.id.idBtnComoJogar);
        sair = findViewById(R.id.idBtnSair);

        iniciar.setOnClickListener(v ->{
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            LayoutInflater inflater =  getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.activity_tela_nome, null);
            builder.setView(dialogView);

            AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            EditText nome = dialogView.findViewById(R.id.idEditNomeUsuario);
            Button continuar = dialogView.findViewById(R.id.idBtnContinuar);


            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),Quiz.class);
                    startActivity(intent);
                }
            });
                });

    }




}