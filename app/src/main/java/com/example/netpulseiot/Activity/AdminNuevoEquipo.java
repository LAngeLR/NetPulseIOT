package com.example.netpulseiot.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.netpulseiot.databinding.ActivityAdminNuevoEquipo2Binding;
import com.example.netpulseiot.entity.EquipoItem;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.SecureRandom;

public class AdminNuevoEquipo extends AppCompatActivity {

    /** Para generar un código aleatorio **/
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 20;
    FirebaseFirestore db;
    ActivityAdminNuevoEquipo2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminNuevoEquipo2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //cancelar Guardado
        binding.cancelar.setOnClickListener(view -> {
            Intent intent = new Intent(AdminNuevoEquipo.this, AdminActivity.class);
            intent.putExtra("fragmentToLoad", "usuariosSuperadmin");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            Toast.makeText(this, "Accion cancelada", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        });

        //crear Guardado
        binding.crear.setOnClickListener(view -> {

//            /** Fecha **/
//            ZoneId zonaPeru = ZoneId.of("America/Lima");
//            ZonedDateTime fechaHoraActualPeru = ZonedDateTime.now(zonaPeru);
//            LocalDate fechaActual = fechaHoraActualPeru.toLocalDate();
//            LocalTime horaActual = LocalTime.now(zonaPeru);
//            Instant instant = fechaHoraActualPeru.toInstant();
//            Date fechaHoraActualPeruDate = Date.from(instant);
//            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

            //Sitio
            String modelo = binding.modelo.getEditText().getText().toString().trim();
            String marca = binding.marca.getEditText().getText().toString().trim();
            String tipoEquipo = binding.tipoEquipo.getEditText().getText().toString().trim();
            String numSerie = binding.numSerie.getEditText().getText().toString().trim();
            String sku = binding.sku.getEditText().getText().toString().trim();
            String descripcion = binding.descripcion.getEditText().getText().toString().trim();

            /** CREAR INSTANCIA DE BD FIREBASE **/
            db = FirebaseFirestore.getInstance();
            /** Usuario **/
            EquipoItem equipoItem = new EquipoItem();
            SecureRandom random = new SecureRandom();
            String randomCode = generateRandomCode(random, CODE_LENGTH);
            equipoItem.setId(randomCode);
            equipoItem.setModelo(modelo);
            equipoItem.setMarca(marca);
            equipoItem.setTipoEquipo(tipoEquipo);
            equipoItem.setNumSerie(numSerie);
            equipoItem.setSku(sku);
            equipoItem.setDescripcion(descripcion);
            equipoItem.setFoto("no hay foto uu");

//            /** Log **/
//            SuperadminLogsItem logsItem = new SuperadminLogsItem();
//            logsItem.setUsuario("ADMIN");
//            logsItem.setAccion("Creación del usuario: " + usuario.getNombre() + usuario.getApellido());
//            logsItem.setFecha(fechaActual.format(formatoFecha));
//            logsItem.setHora(horaActual.format(formatoHora));
//            logsItem.setFechaCreacion(fechaHoraActualPeruDate);

            db.collection("equipos")
                    .document(equipoItem.getId())
                    .set(equipoItem)
                    .addOnSuccessListener( unused -> {
                        Log.d("msg-test","equipos " + equipoItem.getModelo() + " guardado exitosamente");
                    })
                    .addOnFailureListener(e -> e.printStackTrace());

//            db.collection("logs")
//                    .add(logsItem)
//                    .addOnSuccessListener( unused -> {
//                        Log.d("msg-test","LOG DATA guardada exitosamente");
//                    })
//                    .addOnFailureListener(e -> e.printStackTrace());

            // Luego redirige de vuelta a SuperadminActivity con el fragmento UsuariosSuperadminFragment
            Intent intent = new Intent(AdminNuevoEquipo.this, AdminActivity.class);
            intent.putExtra("fragmentToLoad", "usuariosSuperadmin");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            Toast.makeText(this, "Equipo creado exitosamente", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        });

    }

    private static String generateRandomCode(SecureRandom random, int length) {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }

}