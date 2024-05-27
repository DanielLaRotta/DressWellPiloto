package com.example.dresswellpiloto;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PrimeraPag extends AppCompatActivity {

    ImageButton casual, formal, soleado, nublado, lluvioso;
    Button buscar;

    private boolean seleccionCasual = false;
    private boolean seleccionFormal = false;
    private boolean seleccionSoleado = false;
    private boolean seleccionNublado = false;
    private boolean seleccionLluvioso = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_pag);
        casual = findViewById(R.id.btn_casual);
        formal = findViewById(R.id.btn_formal);
        soleado = findViewById(R.id.btn_soleado);
        nublado = findViewById(R.id.btn_nublado);
        lluvioso = findViewById(R.id.btn_lluvioso);
        buscar = findViewById(R.id.btn_buscar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        casual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionCasual = true;
                seleccionFormal = false;
                Toast.makeText(PrimeraPag.this, "Seleccionaste Casual", Toast.LENGTH_LONG).show();
            }
        });
        formal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionCasual = false;
                seleccionFormal = true;
                Toast.makeText(PrimeraPag.this, "Seleccionaste Formal", Toast.LENGTH_LONG).show();
            }
        });
        soleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionSoleado = true;
                seleccionNublado = false;
                seleccionLluvioso = false;
                Toast.makeText(PrimeraPag.this, "Seleccionaste Soleado", Toast.LENGTH_LONG).show();
            }
        });
        nublado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionSoleado = false;
                seleccionNublado = true;
                seleccionLluvioso = false;
                Toast.makeText(PrimeraPag.this, "Seleccionaste Nublado", Toast.LENGTH_LONG).show();
            }
        });
        lluvioso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionSoleado = false;
                seleccionNublado = false;
                seleccionLluvioso = true;
                Toast.makeText(PrimeraPag.this, "Seleccionaste LLuvioso", Toast.LENGTH_LONG).show();
            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seleccionCasual){
                    if (seleccionSoleado){
                        startActivity(new Intent(PrimeraPag.this, PaginaCol.class));
                    } else if (seleccionNublado) {

                    } else if (seleccionLluvioso) {

                    } else {
                        Toast.makeText(PrimeraPag.this, "No has seleccionado el clima", Toast.LENGTH_LONG).show();
                    }
                } else if (seleccionFormal){
                    if (seleccionSoleado){

                    } else if (seleccionNublado) {

                    } else if (seleccionLluvioso) {

                    } else {
                        Toast.makeText(PrimeraPag.this, "No has seleccionado el clima", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(PrimeraPag.this, "No has seleccionado la ocasión", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
