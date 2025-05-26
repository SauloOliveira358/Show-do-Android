package com.example.showdomilhao;
import static com.example.showdomilhao.Dados.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


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
                        SharedPreferences preferences = getSharedPreferences(ARQUIVO_USUARIO, 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(NOME, nome.getText().toString());
                        editor.commit();//Salva o arquivo no banco
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), Quiz.class);
                        startActivity(intent);
                    }
                }
            });

    }

}