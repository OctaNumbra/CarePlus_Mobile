package com.example.careplus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class TelaCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro); // Corrija para o layout correto


        // Elementos do layout
        EditText campoEmail = findViewById(R.id.campoEmail);
        EditText campoUsuario = findViewById(R.id.campoUsuario);
        EditText campoSenha = findViewById(R.id.campoSenha);
        Button btCadastro = findViewById(R.id.btCadastro);

        // Código do botão Cadastro
        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter valores dos campos
                String email = campoEmail.getText().toString().trim();
                String senha = campoSenha.getText().toString().trim();
                String usuario = campoUsuario.getText().toString().trim();

                // Verificar se os campos estão preenchidos
                if (email.isEmpty() || senha.isEmpty() || usuario.isEmpty()) {
                    // Exibir uma mensagem de erro se algum campo estiver vazio
                    Toast.makeText(TelaCadastro.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("email", email);
                editor.putString("senha", senha);
                editor.putString("usuario", usuario);
                editor.apply(); // ou editor.commit();

                    // Criar uma Intent para iniciar a TelaEscolha
                    Intent intent = new Intent(TelaCadastro.this, TelaEscolha.class);
                    startActivity(intent);
            }
        });
    }
}
