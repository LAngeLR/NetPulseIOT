package com.example.netpulseiot.fragmentos.superadmin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.netpulseiot.Activity.MainActivity;
import com.example.netpulseiot.databinding.ActivityNuevoUsuarioSuperadminBinding;
import com.example.netpulseiot.dto.UsuarioDTO;
import com.example.netpulseiot.entity.SuperadminLogsItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class NuevoUsuarioSuperadminActivity extends AppCompatActivity {

    /** Para generar un código aleatorio **/
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 20;
    FirebaseAuth mAuth;


    ActivityNuevoUsuarioSuperadminBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNuevoUsuarioSuperadminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.cancelar.setOnClickListener(view -> {
            Intent intent = new Intent(NuevoUsuarioSuperadminActivity.this, SuperadminActivity.class);
            intent.putExtra("fragmentToLoad", "usuariosSuperadmin");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            Toast.makeText(this, "Accion cancelada", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        });

        binding.crear.setOnClickListener(view -> {

            /** Fecha **/
            ZoneId zonaPeru = ZoneId.of("America/Lima");
            ZonedDateTime fechaHoraActualPeru = ZonedDateTime.now(zonaPeru);

            LocalDate fechaActual = fechaHoraActualPeru.toLocalDate();
            LocalTime horaActual = LocalTime.now(zonaPeru);
            Instant instant = fechaHoraActualPeru.toInstant();
            Date fechaHoraActualPeruDate = Date.from(instant);

            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");


            /** DATOS RECOLECTADOS **/


//            String nombres = binding.nombres.getEditText().getText().toString().trim();
//            String apellidos = binding.apellidos.getEditText().getText().toString().trim();
//            String dni = binding.dni.getEditText().getText().toString().trim();
//          String correo = binding.correo.getEditText().getText().toString().trim();
//            String celular = binding.celular.getEditText().getText().toString().trim();
//            String domicilio = binding.domicilio.getEditText().getText().toString().trim();
//          String contrasenia = "hola";


            String correo = "barry.allen@example.com";
            String contrasenia = "SuperSecretPassword!";

            String nombres = "hola";
            String apellidos = "hola";
            Integer dni = 128;
            Integer celular = 98;
            String domicilio = "hola";


            /** CREAR EN AUTH **/

            Log.d("msg-test","Tengo los parametros");
            mAuth = FirebaseAuth.getInstance();

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo, contrasenia)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            Log.d("msg-test","Tengo los parametros AHORA SÍ");


                            if (task.isSuccessful()) {

                                Log.d("msg-test","Ingrese aqui??? aver");


                                /** Usuario **/



                                FirebaseUser user = mAuth.getCurrentUser();
//                                Log.d(msg-message, "createUserWithEmail:success");
                                /** Usuario **/
                                UsuarioDTO usuario = new UsuarioDTO();
                                SecureRandom random = new SecureRandom();
                                String randomCode = generateRandomCode(random, CODE_LENGTH);
                                usuario.setId(randomCode);
                                usuario.setNombre(nombres);
                                usuario.setApellido(apellidos);
                                usuario.setCorreo(correo);
                                usuario.setDni(Integer.parseInt(String.valueOf(dni)));
                                usuario.setCelular(Integer.parseInt(String.valueOf(celular)));
                                usuario.setDireccion(domicilio);
                                usuario.setFoto("De pocas palabras xd");
                                usuario.setRol("Admin");
                                usuario.setHabilitado(true);

                                /** Log **/
                                SuperadminLogsItem logsItem = new SuperadminLogsItem();
                                logsItem.setUsuario("SUPERADMIN");
                                logsItem.setAccion("Creación del usuario: " + usuario.getNombre() + usuario.getApellido());
                                logsItem.setFecha(fechaActual.format(formatoFecha));
                                logsItem.setHora(horaActual.format(formatoHora));
                                logsItem.setFechaCreacion(fechaHoraActualPeruDate);


                                // Añadir el documento a la colección "usuarios" en Firestore
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("usuarios")
                                        .document(usuario.getId())
                                        .set(usuario)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    // Documento agregado correctamente a Firestore
                                                    Toast.makeText(NuevoUsuarioSuperadminActivity.this, "Usuario registrado correctamente SIUUU.", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(NuevoUsuarioSuperadminActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    // Error al agregar el documento a Firestore
                                                    Toast.makeText(NuevoUsuarioSuperadminActivity.this, "Error al registrar usuario en Firestore.", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                                db.collection("logs")
                                        .add(logsItem)
                                        .addOnSuccessListener( unused -> {
                                            Log.d("msg-test","LOG DATA guardada exitosamente");
                                        })
                                        .addOnFailureListener(e -> e.printStackTrace());



                            } else {
                                Toast.makeText(NuevoUsuarioSuperadminActivity.this, "Error al registrar usuario en Firebase Authentication.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

// ESTO IBA ANTES
//             db.collection("usuarios")
//                 .document(usuario.getId())
//                 .set(usuario)
//                 .addOnSuccessListener( unused -> {
//                 Log.d("msg-test","Usuario " + usuario.getNombre() + " " + usuario.getApellido() + " guardada exitosamente");
//                 })
//                 .addOnFailureListener(e -> e.printStackTrace());


            // Luego redirige de vuelta a SuperadminActivity con el fragmento UsuariosSuperadminFragment
            Intent intent = new Intent(NuevoUsuarioSuperadminActivity.this, SuperadminActivity.class);
            intent.putExtra("fragmentToLoad", "usuariosSuperadmin");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            Toast.makeText(this, "MENSJA EDE MENTIRA", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        });

    }

    /** Generar código aleatorio - Se debe usar en crear usuario **/
    private static String generateRandomCode(SecureRandom random, int length) {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }
}