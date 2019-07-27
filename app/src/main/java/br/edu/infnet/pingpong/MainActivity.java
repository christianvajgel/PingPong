/*
Ping-Pong - Make it Ping. Hope for the Pong.

PROJETO DE BLOCO - Desenvolvimento Android [18E2-18E3] - Instituto Infnet.
Christian Vajgel - 25/09/2018 - Android Studio - Conceito DML (10/10).
Classe MainActivity

Google Play -> https://play.google.com/store/apps/details?id=br.edu.infnet.pingpong
Android 6.0+

Christian Vajgel
christian.vajgel@al.infnet.edu.br
linkedin.com/in/christianvajgel/

All Rights Reserved.
*/

package br.edu.infnet.pingpong;

import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText mLinkUrl;
    private Button mBotaoAdicionar;
    private Handler mHandler;
    private Handler mHandlerLista;
    private FirebaseDatabase bancoDados;
    DatabaseReference referencia;
    private ListView mListaFavoritosSites;
    private ArrayList<String> arrayListSites;
    private TextView mTextViewStatus;
    String urlFirebase;
    private List<Site> mSites;
    private SiteAdapter mAdapter;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLinkUrl = findViewById(R.id.texto_linkurl);
        mBotaoAdicionar = findViewById(R.id.botao_Adicionar);
        mTextViewStatus = findViewById(R.id.website_status);
        mListaFavoritosSites = findViewById(R.id.listview_SitesFavoritos);
        mBotaoAdicionar.setOnClickListener(adicionarLista);
        bancoDados = FirebaseDatabase.getInstance();
        referencia = bancoDados.getReference("sites");
        arrayListSites = new ArrayList<>();
        mSites = new ArrayList<>();
        mAdapter = new SiteAdapter(this, mSites);
        carregarListaFirebase();
        mListaFavoritosSites.setAdapter(mAdapter);
        mHandler = new Handler();
        mHandlerLista = new Handler();
    }

    @Override
    protected void onStart() {
        super.onStart();
            TimerTask timer = new TimerTask() {
                @Override
                public void run() {
                    mHandlerLista.post(new Runnable() {
                        @Override
                        public void run() {
                            threadVerificacao();
                        }
                    });
                }
            };
            new Timer().scheduleAtFixedRate(timer, 5000,10000);
            // O 'delay (5000)' e/ou 'period (10000)' pode(m) ser(em) escolhido(s) em (milissegundos)
           // dependendo da velocidade da conexão.
    }

    public void threadVerificacao(){
        TarefaVerificacao verificacao = new TarefaVerificacao();
        verificacao.start();
    }

    class TarefaVerificacao extends Thread {
        int valorListaSites = mSites.size();
        @Override
        public void run() {
            for (position = 0; position < mSites.size(); position++) {
                if (position == valorListaSites) {
                    break;
                } else {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
                    StrictMode.setThreadPolicy(policy);
                    try {
                        if (position == valorListaSites){
                            break;
                        } else {
                            Site siteLista = mAdapter.getItem(position);
                            String url = siteLista.getSiteNome();
                            final OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder().url("http://" + url).build();
                                try (Response response = client.newCall(request).execute()) {
                                    if (response.isSuccessful()) {
                                        MainActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                mAdapter.setSiteStatus(position, true);
                                                } catch (IndexOutOfBoundsException exception){
                                                        Log.d("Excepetion On-line: ", exception.getMessage());
                                                }
                                            }
                                        });
                                    } else {
                                        MainActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    mAdapter.setSiteStatus(position, false);
                                                } catch (IndexOutOfBoundsException exception){
                                                    Log.d("Excepetion Off-line: ", exception.getMessage());
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void verificacaoLista() {
        OkHttpClient client = new OkHttpClient();
        String url = (mLinkUrl.getText().toString().trim());

        if (url.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Link em branco!", Toast.LENGTH_SHORT).show();
            mLinkUrl.requestFocus();
        } else if (url.contains("www") || url.contains("http") || url.contains("https")) {
            Toast.makeText(getApplicationContext(), "Prefixo já incluído!", Toast.LENGTH_SHORT).show();
            mLinkUrl.setText("");
            mLinkUrl.requestFocus();
        } else if (url.contains(" ")) {
            Toast.makeText(getApplicationContext(), "Link contém espaço!", Toast.LENGTH_SHORT).show();
            mLinkUrl.setText("");
            mLinkUrl.requestFocus();
        } else {
            try {
                final Request request = new Request.Builder().url("http://" + url).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        if (response.isSuccessful()) {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String urlValida = mLinkUrl.getText().toString().trim();
                                    if (!urlValida.isEmpty() && !arrayListSites.contains(urlValida)) {
                                        DatabaseReference sites = referencia;
                                        FirebaseDAO site = new FirebaseDAO();
                                        site.setSiteLink(urlValida);
                                        sites.push().setValue(site);
                                    }
                                }
                            });
                        } else {
                            String urlValida = mLinkUrl.getText().toString().trim();
                            if (!urlValida.isEmpty() && !arrayListSites.contains(urlValida)) {
                                DatabaseReference sites = referencia;
                                FirebaseDAO site = new FirebaseDAO();
                                site.setSiteLink(urlValida);
                                sites.push().setValue(site);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                websiteOffline();
                            }
                        });
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void carregarListaFirebase(){
        referencia.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FirebaseDAO site = dataSnapshot.getValue(FirebaseDAO.class);
                urlFirebase = site.getSiteLink();
                Site sitesFB = new Site();
                sitesFB.setSiteNome(urlFirebase);
                sitesFB.setSiteStatus(getString(R.string.bola_status_website));
                mSites.add(sitesFB);
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void websiteOffline(){
        Toast.makeText(this,R.string.text_website_invalido, Toast.LENGTH_SHORT).show();
        mLinkUrl.setText("");
    }

    private View.OnClickListener adicionarLista = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            verificacaoLista();
        }
    };
}