package com.example.careplus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TelaEscolha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_escolha);

        // Referências aos ImageButtons
        ImageButton familia = findViewById(R.id.ibFamila);  // Corrija o ID para corresponder ao XML
        ImageButton cuidador = findViewById(R.id.ibCuidador);  // Corrija o ID para corresponder ao XML

        // Configurar o Listener do botão Familia
        familia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criar uma Intent para iniciar a TelaCadastro
                Intent intent = new Intent(TelaEscolha.this, TelaPrincipal.class);
                startActivity(intent);
            }
        });

        // Configurar o Listener do botão Cuidador
        cuidador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criar uma Intent para iniciar a TelaCadastro
                Intent intent = new Intent(TelaEscolha.this, TelaPrincipal.class);
                startActivity(intent);
            }
        });
    }
}
