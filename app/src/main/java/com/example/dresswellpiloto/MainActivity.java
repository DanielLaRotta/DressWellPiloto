package com.example.dresswellpiloto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dresswellpiloto.R;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {
    EditText usuario, contrasena;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.namelogin);
        contrasena = findViewById(R.id.contralogin);

        SharedPreferences credenciales = getSharedPreferences("datos", Context.MODE_PRIVATE);
        usuario.setText(credenciales.getString("email", ""));
        contrasena.setText(credenciales.getString("contra", ""));
        login = findViewById(R.id.btnlogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciosesion(
                        usuario.getText().toString(),
                        contrasena.getText().toString()
                );
            }
        });
    }

    public void irPrimeraPag(View view) {
        Intent i = new Intent(this, PrimeraPag.class);
        startActivity(i);
    }

    public void irRegistrar(View view) {
        Intent i = new Intent(this, RegistrarseAc.class);
        startActivity(i);
    }

    private void iniciosesion(String correo, String contra) {
        db.collection("Usuario").document(correo).get().addOnSuccessListener(tarea -> {
            String nombre1 = (String) tarea.get("nombre");
            String apellido1 = (String) tarea.get("apellido");
            String contra1 = (String) tarea.get("contra");


            if (contra.equals(contra1)) {
                irPrimeraPag(null);
                Toast.makeText(this, "Aprobado", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Usuario y/o contraseña incorrecta", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(error -> {
            Toast.makeText(this, "Usuario y/o contraseña incorrecta", Toast.LENGTH_LONG).show();
        });
    }
}

