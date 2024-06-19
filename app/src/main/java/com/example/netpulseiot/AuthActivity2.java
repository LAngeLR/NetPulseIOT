package com.example.netpulseiot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.netpulseiot.databinding.ActivityMain2Binding;
import com.example.netpulseiot.fragmentos.superadmin.SuperadminActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        binding.Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = binding.editTextTextEmailAddress.getText().toString().trim();
                String contraseña = binding.editTextTextPassword.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(correo, contraseña)
                        .addOnCompleteListener(AuthActivity2.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        db.collection("usuarios")
                                                .document(user.getUid())
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            DocumentSnapshot document = task.getResult();
                                                            if (document.exists()) {
                                                                String rol = document.getString("rol");
                                                                if (rol != null) {
                                                                    switch (rol) {
                                                                        case "Administrador":
                                                                            Intent adminIntent = new Intent(AuthActivity2.this, AdminActivity.class);
                                                                            startActivity(adminIntent);
                                                                            break;
                                                                        case "Supervisor":
                                                                            Intent supervisorIntent = new Intent(AuthActivity2.this, SupervisorActivity.class);
                                                                            startActivity(supervisorIntent);
                                                                            break;
                                                                        case "Superadministrador":

                                                                            Intent superadminIntent = new Intent(AuthActivity2.this, SuperadminActivity.class);
                                                                            startActivity(superadminIntent);
                                                                            break;
                                                                        default:

                                                                            Toast.makeText(AuthActivity2.this, "Rol no reconocido.", Toast.LENGTH_SHORT).show();
                                                                            break;
                                                                    }
                                                                    finish();
                                                                }
                                                            } else {
                                                                Toast.makeText(AuthActivity2.this, "Documento de usuario no encontrado.", Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(AuthActivity2.this, "Error al obtener el documento del usuario.", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    }
                                } else {
                                    Toast.makeText(AuthActivity2.this, "Error en el inicio de sesión.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
