package com.example.showdomilhao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TelaNome extends AppCompatActivity {
private Button continuar;
private EditText nome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_nome);

        continuar = findViewById(R.id.idBtnContinuar);
        nome = findViewById(R.id.idEditNomeUsuario);


            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!nome.getText().toString().trim().isEmpty()) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            });

    }

}