package com.example.dresswellpiloto;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegistrarseAc extends AppCompatActivity {

    EditText nombre, apellido, correo, contra;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button registro;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        correo = findViewById(R.id.correo);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        contra = findViewById(R.id.contra);
        registro = findViewById(R.id.button);

        SharedPreferences credenciales = getSharedPreferences("datos", Context.MODE_PRIVATE);
        correo.setText(credenciales.getString("email", ""));
        nombre.setText(credenciales.getString("nombre", ""));
        apellido.setText(credenciales.getString("apellido", ""));
        contra.setText(credenciales.getString("contra", ""));

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciosesion(correo.getText().toString(), nombre.getText().toString(), apellido.getText().toString(), contra.getText().toString());
            }
        });
    }

    private void iniciosesion(String correo, String nombre, String apellido, String contra) {
        registro.setEnabled(false);

        HashMap<String, Object> map = new HashMap<>();
        map.put("correo", correo);
        map.put("nombre", nombre);
        map.put("apellido", apellido);
        map.put("contra", contra);

        db.collection("Usuario").document(correo).set(map)
                .addOnCompleteListener(t -> {
                    registro.setEnabled(true);
                })
                .addOnSuccessListener(t -> {
                    registro.setEnabled(true);
                    startActivity(new Intent(RegistrarseAc.this, PrimeraPag.class));
                    Toast.makeText(this, "Aprobado", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(t -> {
                    registro.setEnabled(true);
                    Toast.makeText(this, "Usuario y/o contraseña incorrecta", Toast.LENGTH_LONG).show();
                });
    }
}


