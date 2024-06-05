package com.example.netpulseiot.fragmentos.superadmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.ActivityNuevoUsuarioSuperadminBinding;
import com.example.netpulseiot.databinding.ActivitySuperadminBinding;
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

public class NuevoUsuarioSuperadminActivity extends AppCompatActivity {

    /** Para generar un código aleatorio **/
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 20;
    FirebaseFirestore db;
    FirebaseFirestore db1;


    ActivityNuevoUsuarioSuperadminBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNuevoUsuarioSuperadminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.cancelar.setOnClickListener(view -> {
            Intent intent = new Intent(NuevoUsuarioSuperadminActivity.this, SuperadminActivity.class);
            intent.putExtra("fragmentToLoad", "usuariosSuperadmin");
            Toast.makeText(this, "Accion cancelada", Toast.LENGTH_LONG).show();
            startActivity(intent);
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
            Intent intent = new Intent(NuevoUsuarioSuperadminActivity.this, SuperadminActivity.class);
            intent.putExtra("fragmentToLoad", "usuariosSuperadmin");
            Toast.makeText(this, "Usuario creado exitosamente", Toast.LENGTH_LONG).show();
            startActivity(intent);
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