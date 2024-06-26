package com.example.netpulseiot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.netpulseiot.databinding.ActivityAdminNuevoUsuarioBinding;
import com.example.netpulseiot.dto.UsuarioDTO;
import com.example.netpulseiot.entity.SuperadminLogsItem;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AdminNUevoUsuario extends AppCompatActivity {

    /** Para generar un código aleatorio **/
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 20;
    FirebaseFirestore db;
    ActivityAdminNuevoUsuarioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminNuevoUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //cancelar Guardado
        binding.cancelar.setOnClickListener(view -> {
            Intent intent = new Intent(AdminNUevoUsuario.this, AdminActivity.class);
            intent.putExtra("fragmentToLoad", "usuariosSuperadmin");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            Toast.makeText(this, "Accion cancelada", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        });

        //crear Guardado
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

            /** Usuario **/
            String nombres = binding.nombres.getEditText().getText().toString().trim();
            String apellidos = binding.apellidos.getEditText().getText().toString().trim();
            String dni = binding.dni.getEditText().getText().toString().trim();
            String correo = binding.correo.getEditText().getText().toString().trim();
            String celular = binding.celular.getEditText().getText().toString().trim();
            String domicilio = binding.domicilio.getEditText().getText().toString().trim();


            /** CREAR INSTANCIA DE BD FIREBASE **/
            db = FirebaseFirestore.getInstance();
            /** Usuario **/
            UsuarioDTO usuario = new UsuarioDTO();
            SecureRandom random = new SecureRandom();
            String randomCode = generateRandomCode(random, CODE_LENGTH);
            usuario.setId(randomCode);
            usuario.setNombre(nombres);
            usuario.setApellido(apellidos);
            usuario.setCorreo(correo);
            usuario.setDni(Integer.parseInt(dni));
            usuario.setCelular(Integer.parseInt(celular));
            usuario.setDireccion(domicilio);
            usuario.setFoto("Muy guapo para poner la foto");
            usuario.setRol("Supervisor");
            usuario.setHabilitado(true);

            /** Log **/
            SuperadminLogsItem logsItem = new SuperadminLogsItem();
            logsItem.setUsuario("ADMIN");
            logsItem.setAccion("Creación del usuario: " + usuario.getNombre() + usuario.getApellido());
            logsItem.setFecha(fechaActual.format(formatoFecha));
            logsItem.setHora(horaActual.format(formatoHora));
            logsItem.setFechaCreacion(fechaHoraActualPeruDate);


            db.collection("usuarios")
                    .document(usuario.getId())
                    .set(usuario)
                    .addOnSuccessListener( unused -> {
                        Log.d("msg-test","Usuario " + usuario.getNombre() + " " + usuario.getApellido() + " guardada exitosamente");
                    })
                    .addOnFailureListener(e -> e.printStackTrace());

            db.collection("logs")
                    .add(logsItem)
                    .addOnSuccessListener( unused -> {
                        Log.d("msg-test","LOG DATA guardada exitosamente");
                    })
                    .addOnFailureListener(e -> e.printStackTrace());

            // Luego redirige de vuelta a SuperadminActivity con el fragmento UsuariosSuperadminFragment
            Intent intent = new Intent(AdminNUevoUsuario.this, AdminActivity.class);
            intent.putExtra("fragmentToLoad", "usuariosSuperadmin");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            Toast.makeText(this, "Usuario creado exitosamente", Toast.LENGTH_LONG).show();
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