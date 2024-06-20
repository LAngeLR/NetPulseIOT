package com.example.netpulseiot.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.netpulseiot.databinding.ActivityAdminNuevoSitioBinding;
import com.example.netpulseiot.entity.SitioItem;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class AdminNuevoSitio extends AppCompatActivity {

    /** Para generar un código aleatorio **/
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 20;
    FirebaseFirestore db;
    ActivityAdminNuevoSitioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminNuevoSitioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //cancelar Guardado
        binding.cancelar.setOnClickListener(view -> {
            Intent intent = new Intent(AdminNuevoSitio.this, AdminActivity.class);
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
            String nombre = binding.nombre.getEditText().getText().toString().trim();
            String departamento = binding.departamento.getEditText().getText().toString().trim();
            String provincia = binding.provincia.getEditText().getText().toString().trim();
            String distrito = binding.distrito.getEditText().getText().toString().trim();
            String tipoZona = binding.tipoZona.getEditText().getText().toString().trim();
            String tipoSitio = binding.tipoSitio.getEditText().getText().toString().trim();
            String ubigeo = binding.ubigeo.getEditText().getText().toString().trim();
            String latitudStr = binding.latitud.getEditText().getText().toString().trim();
            String longitudStr = binding.longitud.getEditText().getText().toString().trim();

            /** CREAR INSTANCIA DE BD FIREBASE **/
            db = FirebaseFirestore.getInstance();
            /** Usuario **/
            SitioItem sitioItem = new SitioItem();
            SecureRandom random = new SecureRandom();
            String randomCode = generateRandomCode(random, CODE_LENGTH);
            sitioItem.setId(randomCode);
            sitioItem.setNombre(nombre);
            sitioItem.setDepartamento(departamento);
            sitioItem.setProvincia(provincia);
            sitioItem.setDistrito(distrito);
            sitioItem.setTipoZona(tipoZona);
            sitioItem.setTipoSitio(tipoSitio);
            sitioItem.setUbigeo(ubigeo);
            // Convertir las cadenas a double
            double latitud = Double.parseDouble(latitudStr);
            double longitud = Double.parseDouble(longitudStr);
            // Crear una instancia de GeoPoint con las coordenadas
            GeoPoint geolocalizacion = new GeoPoint(latitud, longitud);
            sitioItem.setGeolocalizacion(geolocalizacion);
            sitioItem.setFoto("no hay foto XD");
            sitioItem.setSupervisor("Sin asignar");
            List<String> equipos = new ArrayList<>();
            sitioItem.setEquipos(equipos);

//            /** Log **/
//            SuperadminLogsItem logsItem = new SuperadminLogsItem();
//            logsItem.setUsuario("ADMIN");
//            logsItem.setAccion("Creación del usuario: " + usuario.getNombre() + usuario.getApellido());
//            logsItem.setFecha(fechaActual.format(formatoFecha));
//            logsItem.setHora(horaActual.format(formatoHora));
//            logsItem.setFechaCreacion(fechaHoraActualPeruDate);

            db.collection("sitios")
                    .document(sitioItem.getId())
                    .set(sitioItem)
                    .addOnSuccessListener( unused -> {
                        Log.d("msg-test","sitio " + sitioItem.getNombre() + " guardada exitosamente");
                    })
                    .addOnFailureListener(e -> e.printStackTrace());

//            db.collection("logs")
//                    .add(logsItem)
//                    .addOnSuccessListener( unused -> {
//                        Log.d("msg-test","LOG DATA guardada exitosamente");
//                    })
//                    .addOnFailureListener(e -> e.printStackTrace());

            // Luego redirige de vuelta a SuperadminActivity con el fragmento UsuariosSuperadminFragment
            Intent intent = new Intent(AdminNuevoSitio.this, AdminActivity.class);
            intent.putExtra("fragmentToLoad", "usuariosSuperadmin");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            Toast.makeText(this, "Sitio creado exitosamente", Toast.LENGTH_LONG).show();
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