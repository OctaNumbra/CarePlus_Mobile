package com.example.careplus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requisicao = Volley.newRequestQueue(this);
        String urlLogin = "http://127.0.0.1:4060/users/login";
        // Elementos do layout
        EditText campoEmail = findViewById(R.id.campoEmail);
        EditText campoSenha = findViewById(R.id.campoSenha);
        Button btLogin = findViewById(R.id.btLogin);
        Button btCadastro = findViewById(R.id.btCadastro);

        // Código do botão Login
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject dadosBody = new JSONObject();
                // Obter valores dos campos
                String email = campoEmail.getText().toString().trim();
                String senha = campoSenha.getText().toString().trim();

                // Verificar se os campos estão preenchidos
                if (email.isEmpty() || senha.isEmpty()) {
                    // Exibir uma mensagem de erro se algum campo estiver vazio
                    Toast.makeText(MainActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try{
                    dadosBody.put("email", email);
                    dadosBody.put("password", senha);
                }catch(JSONException exc){
                    exc.printStackTrace();
                }

                JsonObjectRequest enviarPost = new JsonObjectRequest(
                    Request.Method.POST,
                        urlLogin,
                        dadosBody,
                        new Response.Listener<JSONObject>() {
                            public void onResponse(JSONObject response) {
                                try {
                                    // Supondo que o ID do usuário está em um campo chamado "userId"
                                    if (response.has("userId")) {
                                        String userId = response.getString("userId");
                                        // Armazene o ID do usuário conforme necessário
                                        // Por exemplo, você pode armazená-lo em SharedPreferences ou usá-lo diretamente
                                        // Exemplo de armazenamento em SharedPreferences:
                                        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putString("userId", userId);
                                        editor.apply();

                                        // Exibir uma mensagem de sucesso e iniciar a próxima atividade
                                        Toast.makeText(MainActivity.this, "Cadastrado", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, TelaPrincipal.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(MainActivity.this, "Erro: resposta inválida", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity.this, "Erro ao processar a resposta", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                Toast.makeText(MainActivity.this, "Erro ao enviar", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                    // Criar uma Intent para iniciar a TelaPrincipalActivity
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(enviarPost);
            }
        });
        // Adicione a requisição à fila de requisições do Volley

        // Código do botão Cadastrar
        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criar uma Intent para iniciar a TelaCadastroActivity
                Intent intent = new Intent(MainActivity.this, TelaCadastro.class);
                startActivity(intent);
            }
        });

        ;



    }
}
