package com.example.showdomilhao;

import android.content.SharedPreferences;

public class Pontuacao {

    public static void salvarPontuacao(android.content.Context context, String nome, int pontos) {
        SharedPreferences prefs = context.getSharedPreferences("PLACAR", android.content.Context.MODE_PRIVATE);
        String placarStr = prefs.getString("placar", "");
        placarStr += nome + ":" + pontos + ";";
        prefs.edit().putString("placar", placarStr).apply();
    }

}
