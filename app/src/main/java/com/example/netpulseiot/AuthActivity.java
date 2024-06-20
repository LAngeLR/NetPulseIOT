package com.example.netpulseiot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.netpulseiot.databinding.ActivityMain3Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AuthActivity extends AppCompatActivity {

    ActivityMain3Binding binding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.editTextText100.getText().toString().trim();
                String apellido = binding.editTextText101.getText().toString().trim();
                int dni = Integer.parseInt(binding.editTextText102.getText().toString().trim());
                String correo = binding.editTextText103.getText().toString().trim();
                int celular = Integer.parseInt(binding.editTextText104.getText().toString().trim());
                String direccion = binding.editTextText.getText().toString().trim();
                String rol = binding.editTextTextad.getText().toString().trim();
                String contraseña = binding.editTextTextads.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(correo, contraseña)
                        .addOnCompleteListener(AuthActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();


                                    Map<String, Object> userData = new HashMap<>();
                                    userData.put("nombre", nombre);
                                    userData.put("apellido", apellido);
                                    userData.put("dni", dni);
                                    userData.put("correo", correo);
                                    userData.put("celular", celular);
                                    userData.put("direccion", direccion);
                                    userData.put("rol", rol);
                                    userData.put("habilitado", true);
                                    userData.put("id", user.getUid());
                                    userData.put("foto","No hay, no disponible");

                                    // Añadir el documento a la colección "usuarios" en Firestore
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    db.collection("usuarios")
                                            .document(user.getUid())
                                            .set(userData)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        // Documento agregado correctamente a Firestore
                                                        Toast.makeText(AuthActivity.this, "Usuario registrado correctamente.", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        // Error al agregar el documento a Firestore
                                                        Toast.makeText(AuthActivity.this, "Error al registrar usuario en Firestore.", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                } else {
                                    Toast.makeText(AuthActivity.this, "Error al registrar usuario en Firebase Authentication.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }


    /* Para regresar a la vista anterior con el OnClick */
    public void cancela(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
