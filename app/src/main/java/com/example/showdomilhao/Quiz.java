package com.example.showdomilhao;

import static com.example.showdomilhao.Dados.*;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Random;


public class Quiz extends AppCompatActivity {
private TextView bemVindo,pergunta,numerodaPergunta;
private CheckBox resposta1,resposta2,resposta3,resposta4;
private Button proxima_Pergunta, cartas;
private boolean checkBoxTexto,cartaSelecionou;
private int pontuação = 0,questoes = 1;

    private String [] Perguntas = {
            "Qual arquivo é responsável por declarar permissões e configurações básicas de um app Android?",      // Pergunta 1
            "Para alterar o layout visual de uma activity, qual tipo de arquivo é normalmente utilizado?",        // Pergunta 2
            "Qual linguagem é oficialmente suportada pelo Google para desenvolvimento Android desde 2019?",       // Pergunta 3
            "Qual classe é usada para criar uma nova tela (interface) em Android?",                               // Pergunta 4
            "Qual é o propósito do arquivo build.gradle (Module: app)?",                                          // Pergunta 5
            "Qual método do ciclo de vida de uma Activity é chamado quando ela se torna visível ao usuário?",     // Pergunta 6
            "O que é um Intent no Android?",                                                                      // Pergunta 7
            "Qual método usamos para detectar cliques em um botão no Android?",                                   // Pergunta 8
            "O que faz o método apply() do SharedPreferences.Editor?",                                            // Pergunta 9
            "Como verificamos se um CheckBox está marcado?",                                                      // Pergunta 10
            "Como iniciamos o uso do SharedPreferences?",                                                         // Pergunta 11
            "Qual interface é implementada para usar um OnClickListener?",                                        // Pergunta 12
            "Qual destes componentes permite ao usuário digitar texto?",                                          // Pergunta 13
            "Qual a primeira coisa que um progamador Android faz ao abrir o Android Studio?",                     // Pergunta 14
            "Para que serve o botão \"Forçar Parada\" em um app?",                                                 // Pergunta 15
            "Qual é o nome da ferramenta oficial para desenvolvimento Android?",                                     //pergunta16
            "Qual método é chamado quando uma Activity é criada?",                                                  //pergunta17
            "O que acontece se você colocar o celular no modo avião?",                                               //pergunta18
            " Quando o Android começa a travar, o que ele está tentando dizer?",                                        //pergunta19
            "O que é o LinearLayout no Android Studio?",                                                                //pergunta20
            "Qual método é usado para abrir outra Activity?",                                                            //pergunta21
            "Qual elemento visual exibe uma lista de itens na tela?",                                                      //pergunta22
            "Qual componente permite a navegação entre diferentes seções com abas?",                              // Pergunta 22
            "O que representa a pasta 'res' em um projeto Android?",                                              // Pergunta 23
            "Qual é a finalidade do arquivo strings.xml?",                                                        // Pergunta 24
            "Para qual propósito usamos o método findViewById()?",                                                // Pergunta 25
            "Qual diretório contém os arquivos de layout em um app Android?",                                     // Pergunta 26
            "O que acontece se não declararmos uma Activity no AndroidManifest.xml?",                             // Pergunta 27
            "Qual é o propósito da classe RecyclerView?",                                                         // Pergunta 28
            "Qual desses arquivos define o tema visual do aplicativo?",                                          // Pergunta 29
            "Qual comando mágico faz uma Activity desaparecer?",                              // Pergunta 30
            "Onde você deve colocar aquela imagem linda do seu botão?",        // Pergunta 31
            "Onde você organiza aquele arco-íris de cores?",               // Pergunta 32
            "Se quiser avisar o usuário com uma mensagem que desaparece sozinha?",             // Pergunta 33
            "O que é uma Activity no Activity no Android?",                                  //pergunta34
            "Para que serve o método setContentView()",                                          //pergunta35
            "Qual o uso do SharedPreferences?",                                         //pergunta 36
            "Qual componente é mais recomendado para exibir listas grandes?",          //pergunta37
            "O que é necessário para declarar uma nova Activity?",                      //pergunta38
            "Qual elemento XML define um botão?",                                       //pergunta39
            "O que é o Gradle no Android Studio?",                                          //pergunta40
            "Como se define uma string reutilizável?",                                      //pergunta41
            "Qual tipo de layout posiciona elementos em sequência vertical ou horizontal?",   //pergunta42
            "O que é um Toast no Android?",                                          //pergunta43
            "Como é representada uma interface gráfica no Android?",            //pergunta44
            "Qual foi o nome da primeira versão pública do Android?",           //pertunta 45
            "Qual desses nomes de doces nunca foi usado em uma versão oficial do Android?", //pergunta 46
            "O mascote do Android tem um nome oficial. Qual é?",                        //pergunta47
            "Qual dessas versões do Android veio primeiro?",                        //pergunta48
            "Qual é a diferença principal entre Android e iOS?",                        //pergunta49
            "O Android é baseado em qual núcleo de sistema operacional?",                  // pergunta50
            "Qual é a loja oficial de aplicativos do Android?"                                                       //pergunta51




    };

    private String[][] Respostas = {
            {"MainActivity.java","AndroidManifest.xml","strings.xml","styles.xml"},                              // Pergunta 1
            {".java",".gradle",".xml",".json"},                                                                   // Pergunta 2
            {"JavaScript","C++","Kotlin", "Python"},                                                              // Pergunta 3
            {"Intent", "Fragment", "View", "Activity"},                                                           // Pergunta 4
            {"Armazenar strings do app","Navegação entre activities","Dependências e configurações de build", "Elementos gráficos"}, // Pergunta 5
            {"onDestroy()", "onStart()", "onCreate()", "onPause()"},                                              // Pergunta 6
            {"Uma mensagem para ativar componentes","Um tipo de layout", "Uma permissão especial", "Um componente visual"}, // Pergunta 7
            {"setOnClickListener()","onClick()", "setOnClick()", "detectClick()"},                                // Pergunta 8
            {"Salva dados de forma síncrona", "Apaga os dados", "Aplica mudanças de forma assíncrona", "Inicia o SharedPreferences"}, // Pergunta 9
            {"checkbox.isChecked()", "checkbox.isMarked()", "checkbox.getCheck()", "checkbox.isSelected()"},     // Pergunta 10
            {"loadPreferences()", "startPreferences()", "openFileInput()", "getSharedPreferences()"},             // Pergunta 11
            {"Runnable", "OnClickListener", "ClickEvent", "ViewManager"},                                         // Pergunta 12
            {"TextView","Button","EditText","ImageView"},                                                         // Pergunta 13
            {"Programa um app","Vai tomar um café ate carregar","Reza pro Gradle nao quebrar","Fecha e tenta de novo"}, // Pergunta 14
            {"Desabafar","Dizer: Chega,já deu por hoje!","Fazer o app pensar sobre seus erros","Todas as anteriores"}, // Pergunta 15
            {"Eclipse","Android Studio","IntelliJ IDEA","Kotlin"},                                                  //Pergunta16
            {"onCreate()", "onStart()","onResume()","onDestroy()"},                                                  //pergunta17
            {"Ele começa a voar","Liga som de turbina","Desativa redes móveis e Wi-Fi","Mostra a janela do avião"}, //pergunta18
            {"Me dá férias!","Fecha esses 87 aplicativos, por favor!","Atualização chegando…","Você merece um novo celular"},  //pergunta19
            {"Um tipo de banco de dados","Layout dos elementos em linha vertical ou horizontal","Um componente para animação","Activity especial"},  //pergunta20
            {"setContentView","startActivity","findViewById","Button"},                                                         //pergunta21
            {"Intent","EditText","ListaLigada","ListView"},                                                                        //pergunta22
            {"ViewPager", "RecyclerView", "TabLayout", "NavigationDrawer"},                                       // Pergunta 22
            {"Contém arquivos executáveis", "Contém recursos como layouts e imagens", "Contém código Java", "Contém configurações do sistema"}, // Pergunta 23
            {"Armazenar temas", "Guardar imagens", "Centralizar textos usados no app", "Controlar permissões"},   // Pergunta 24
            {"Buscar arquivos XML", "Encontrar Views no layout", "Acessar banco de dados", "Ativar notificações"}, // Pergunta 25
            {"res/layout", "src/layout", "layout/res", "drawable/layout"},                                        // Pergunta 26
            {"Ela funciona normalmente", "Ela não poderá ser executada", "Ela consome mais bateria", "Ela se torna invisível"}, // Pergunta 27
            {"Mostrar uma imagem", "Exibir itens de forma eficiente em listas", "Executar animações", "Acessar sensores"}, // Pergunta 28
            {"colors.xml", "styles.xml", "themes.xml", "AndroidManifest.xml"},                                    // Pergunta 29
            {"vanish()", "dismiss()", "finish()", "abracadabra()"},                                                   // Pergunta 30
            {"res/raw", "res/drawable", "res/assets", "res/imagesThatIPromiseAreUseful"},                             // Pergunta 31
            {"colors.xml", "magic_colors.png", "styles.xml", "rainbow_config.xml"},                                   // Pergunta 32
            {"Toast", "PopUpSurpresa", "Snackbar", "Alarme de incêndio"},                                             // Pergunta 33
            {"Uma função do sistema Operacional","Um tipo de arquivo xml","Uma tela do aplicativo","Um serviço em segundo plano"},  //pergunta34
            {"Definir uma orientação da tela","Iniciar uma activity","Conectar o codigo a interface XML","Salvar a tela"},  //pergunta 35
            {"Armazenar grandes volumes de dados","Salvar configurações simples","Controlar permissão de internet","Renderizar elementos gráficos"},  // pergunta36
            {"ListView","ScrollView","RecyclerView","LinearLayout"},                                                                                //pergunta 37
            {"Criar arquivo Java e layout XML","Adicionar permissão no Manifest","Definir no AndroidManifest.xml","nstalar pacote externo"},        //pergunta38
            {"<TextView>","EditText>","<Button>","<ImageView>"},                                                    //pergunta39
            {"O que é o Gradle no Android Studio?","Sistema de build","erenciador de eventos","Ferramenta gráfica"},        //pergunta40
            {"No arquivo layout.xml","Na classe Java","Em AndroidManifest.xml","Em res/values/strings.xml"},   //pergunta41
            {"RelativeLayout","LinhaLayout","GridLayout","LinearLayout"},                           //pergunta42
            {"um toque na tela","Uma caixa de diálogo","Um componente visual fixo","Uma mensagem breve na tela"},     //pergunta43
            {"Com arquivos XML","Com arquivo JSON","RTX 3590","Usando JavaScript"} ,               //pergunta44
            {"Apple Pie","Cupcake","Donut","Beta"},                                             //pergunta 45
            {"KitKat","Jelly Bean","Pancake","Ice Cream Sandwich"},                         //pergunta46
            {"Bugdroid","Droidinho","Andy","Robozinho"},                        //pergunta47
            {"Lollipop","Marshmallow","Nougat","KitKat"},                       //pergunta48
            {"Um só funciona com internet","Um é de código aberto, o outro fechado","Android não roda apps","Nenhuma"},                //´pergunta49
            {"Windows","Linux","MacOS","Unix"},                                             //pergunta50
            {"Google Play Store","Android Store","Android Market","Galaxy Apps"},               //pergunta51

    };

    private int [] respostaCerta = {
            1,  // Pergunta 1
            2,  // Pergunta 2
            2,  // Pergunta 3
            3,  // Pergunta 4
            2,  // Pergunta 5
            1,  // Pergunta 6
            0,  // Pergunta 7
            0,  // Pergunta 8
            2,  // Pergunta 9
            0,  // Pergunta 10
            3,  // Pergunta 11
            1,  // Pergunta 12
            2,  // Pergunta 13
            0,  // Pergunta 14
            3,   // Pergunta 15
            1,   //Pergunta16
            0,  //pergunta17
            2,  //pergunta18
            1,  //pergunta19
            1,   //pergunta20
            1, //pergunta21
            3, //pergunta22
            2,  // Pergunta 22
            1,  // Pergunta 23
            2,  // Pergunta 24
            1,  // Pergunta 25
            0,  // Pergunta 26
            1,  // Pergunta 27
            1,  // Pergunta 28
            2,   // Pergunta 29
            2,  // Pergunta 30
            1,  // Pergunta 31
            0,  // Pergunta 32
            0,   // Pergunta 33
            2,   // pergunta34
            2, // pergunta 35
            1, //pergunta 36
            2,  //pergunta37
            2,  //pergunta38
            2, //pergunta39
            1, // pergunta 40
            3, //pergunta41
            3,   //pergunta42
            3, //pergunta43
            0,  //pergunta44
            1,  //pergunta45
            2,   //pergunta46
            0,   //pergunta47
            3,   //pergunta48
            1,     //pergunta49
            1,    //perguntas50
            0,   //pergunta51







    };
private int respostaSelecionada;
private int perguntaAtual = 0;
private int numeroPerguntas = 10;
private int cartasRecebidas;


    private ArrayList<Integer> indicesDisponiveis = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove a barra de título (ActionBar)
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_quiz);
        bemVindo = findViewById(R.id.IdTextViewShowdoAndroid);
        pergunta = findViewById(R.id.IdTextViewPergunta);
        resposta1 = findViewById(R.id.idCheckboxResposta1);
        resposta2 = findViewById(R.id.IdCheckboxResposta2);
        resposta3 = findViewById(R.id.IdCheckboxResposta3);
        resposta4 = findViewById(R.id.IdCheckboxResposta4);
        numerodaPergunta = findViewById(R.id.IdTextViewNumeroPergunta);

        cartas = findViewById(R.id.IdBtnCartas);
        //adicionar os indices disponiveis
        for (int i = 0; i < Perguntas.length; i++) {
            indicesDisponiveis.add(i);
        }
        carregar_Perguntas();
        proxima_Pergunta = findViewById(R.id.IdBtnProximaPergunta);



        View.OnClickListener clique = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox clicou = (CheckBox) v;
                //os ifs pega  cada resposta a qual clicou
                // e atribu um valor a variavel resposta Selecionada
                if(v == resposta1)respostaSelecionada = 0;
                if(v == resposta2)respostaSelecionada = 1;
                if(v == resposta3)respostaSelecionada = 2;
                if(v == resposta4)respostaSelecionada = 3;
                resposta1.setChecked(false);
                resposta2.setChecked(false);
                resposta3.setChecked(false);
                resposta4.setChecked(false);

                clicou.setChecked(true);
                checkBoxTexto = true;       //pra pegar se selecionao e nao pular
            }
        };

        resposta1.setOnClickListener(clique);
        resposta2.setOnClickListener(clique);
        resposta3.setOnClickListener(clique);
        resposta4.setOnClickListener(clique);




        //botao proxima pergunta
        proxima_Pergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBoxTexto) {
                    Toast.makeText(getApplicationContext(), "Selecione uma resposta!", Toast.LENGTH_SHORT).show();
                    return;
                }

                acertoErro();
                perguntaAtual++;
                numeroPerguntas--;
                respostaSelecionada = -1;
                checkBoxTexto = false;

                if (numeroPerguntas > 0) {
                    carregar_Perguntas();
                    if (numeroPerguntas == 1) {
                        proxima_Pergunta.setText("Finalizar");
                    }
                } else {
                    SharedPreferences preferences = getSharedPreferences(ARQUIVO_USUARIO, 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt(PONTUACAO, pontuação);
                    editor.apply();
                    Intent intent2 = new Intent(getApplicationContext(), Resultado.class);
                    startActivity(intent2);
                    finish();
                }
            }
        }); // fim do botao proxima pergunta

        //botao Cartas
        cartas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cartas.class);
                startActivityForResult(intent, 1);
                cartaSelecionou = true;

            }

        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
             cartasRecebidas = data.getIntExtra("Carta",-1);
            cartas.setClickable(false);
            cartas.setAlpha(0.5f);


            if (cartasRecebidas > 0) {
                int correta = respostaCerta[perguntaAtual];

                // Array para evitar desativar a mesma resposta mais de uma vez
                boolean[] desativadas = new boolean[4];
                desativadas[correta] = true; // não pode desativar a certa

                Random random = new Random();
                int eliminadas = 0;

                while (eliminadas < cartasRecebidas) {
                    int i = random.nextInt(4); // entre 0 e 3

                    if (!desativadas[i]) {
                        switch (i) {
                            case 0:
                                resposta1.setEnabled(false);
                                resposta1.setAlpha(0.5f); break;
                            case 1: resposta2.setEnabled(false);
                                resposta2.setAlpha(0.5f);break;
                            case 2: resposta3.setEnabled(false);
                                resposta3.setAlpha(0.5f);break;
                            case 3: resposta4.setEnabled(false);
                                resposta4.setAlpha(0.5f);break;
                        }
                        desativadas[i] = true;
                        eliminadas++;
                    }
                }
            }

        }
    }

    //Carregar as perguntas de forma altomatica
    public void carregar_Perguntas(){
        resposta1.setEnabled(true);
        resposta1.setAlpha(1f);
        resposta2.setEnabled(true);
        resposta2.setAlpha(1f);
        resposta3.setEnabled(true);
        resposta3.setAlpha(1f);
        resposta4.setEnabled(true);
        resposta4.setAlpha(1f);


        resposta1.setChecked(false);
        resposta2.setChecked(false);
        resposta3.setChecked(false);
        resposta4.setChecked(false);
        checkBoxTexto =false;
        numerodaPergunta.setText("Pergunta "+ questoes + " de 10");
        questoes++;
        if (indicesDisponiveis.size() == 0) return;

        Random random = new Random();
        int posicao = random.nextInt(indicesDisponiveis.size());
        perguntaAtual = indicesDisponiveis.get(posicao);

        // Remove o índice sorteado da lista
        indicesDisponiveis.remove(posicao);

        //aqui pega as perguntas pela perguntaAtual ai pega certinho de todas é o indice de controle
        pergunta.setText(Perguntas[perguntaAtual]);
        //as resposta sao matriz porque ai cada linha tem 4 alternativas ai o indice delas e que vai pra cada alternativa
        resposta1.setText(Respostas[perguntaAtual][0]);
        resposta2.setText(Respostas[perguntaAtual][1]);
        resposta3.setText(Respostas[perguntaAtual][2]);
        resposta4.setText(Respostas[perguntaAtual][3]);



    }//fim metodo carregar_Perguntas


    //metodo de acerto e erro
    public void acertoErro(){
        if(respostaSelecionada == respostaCerta[perguntaAtual]){
            pontuação++;
            String Stringpontuação = String.valueOf(pontuação);
            //ao pra testes
            bemVindo.setText(bemVindo.getText().toString());
        }
    }


}