package com.example.netpulseiot;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.netpulseiot.databinding.ActivityMain2Binding;
import com.example.netpulseiot.fragmentos.superadmin.SuperadminActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthActivity2 extends AppCompatActivity {

    TextInputEditText email, password;
    Button btn_login;
    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        btn_login = findViewById(R.id.Ingresar);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString().trim();
                String txt_password = password.getText().toString().trim();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(AuthActivity2.this, "Todos los campos son necesarios", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(txt_email, txt_password)
                            .addOnCompleteListener(AuthActivity2.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = auth.getCurrentUser();
                                        if (user != null) {
                                            db.collection("usuarios")
                                                    .document(user.getUid())
                                                    .get()
                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                            if (task.isSuccessful()) {
                                                                DocumentSnapshot document = task.getResult();
                                                                if (document != null && document.exists()) {
                                                                    String rol = document.getString("rol");
                                                                    if (rol != null) {
                                                                        switch (rol) {
                                                                            case "Administrador":
                                                                                startActivityWithClearTask(AdminActivity.class);
                                                                                break;
                                                                            case "Supervisor":
                                                                                startActivityWithClearTask(SupervisorActivity.class);
                                                                                break;
                                                                            case "Superadministrador":
                                                                                startActivityWithClearTask(SuperadminActivity.class);
                                                                                break;
                                                                            default:
                                                                                Toast.makeText(AuthActivity2.this, "Rol no reconocido.", Toast.LENGTH_SHORT).show();
                                                                                break;
                                                                        }
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
                                        Toast.makeText(AuthActivity2.this, "Autenticación Fallida", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void startActivityWithClearTask(Class<?> cls) {
        Intent intent = new Intent(AuthActivity2.this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}

