package com.example.netpulseiot.fragmentos.superadmin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.netpulseiot.R;
import com.example.netpulseiot.SuperAdminListaUsarios;
import com.example.netpulseiot.databinding.ActivitySuperadminBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class SuperadminActivity extends AppCompatActivity {

    ActivitySuperadminBinding binding;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySuperadminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /** CREAR INSTANCIA DE BD FIREBASE **/
        db = FirebaseFirestore.getInstance();

        /** GUARDAR LOG **/

        ZoneId zonaPeru = ZoneId.of("America/Lima");
        ZonedDateTime fechaHoraActualPeru = ZonedDateTime.now(zonaPeru);

        LocalDate fechaActual = fechaHoraActualPeru.toLocalDate();
        LocalTime horaActual = LocalTime.now(zonaPeru);
        Instant instant = fechaHoraActualPeru.toInstant();
        Date fechaHoraActualPeruDate = Date.from(instant);

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

        Log.d("msg-test","Fecha actual: " + fechaActual.format(formatoFecha));
        Log.d("msg-test","Hora actual: " + horaActual.format(formatoHora));
        Log.d("msg-test","Timestamp: " + fechaHoraActualPeruDate);
/**
        SuperadminLogsItem logsItem = new SuperadminLogsItem();
        logsItem.setUsuario("Prueba");
        logsItem.setAccion("Creacion de LA AITEL");
        logsItem.setFecha(fechaActual.format(formatoFecha));
        logsItem.setHora(horaActual.format(formatoHora));
        logsItem.setFechaCreacion(fechaHoraActualPeruDate);

        db.collection("logs")
                .add(logsItem)
                .addOnSuccessListener( unused -> {
                    Log.d("msg-test","LOG DATA guardada exitosamente");
                })
                .addOnFailureListener(e -> e.printStackTrace());
         **/


        // Check if an extra is passed to load a specific fragment
        String fragmentToLoad = getIntent().getStringExtra("fragmentToLoad");
        if (fragmentToLoad != null && fragmentToLoad.equals("usuariosSuperadmin")) {
            replaceFragment(new UsuariosSuperadminFragment());
        } else {
            replaceFragment(new InicioSuperadminFragment());
        }


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.inicioSuperadmin) {
                replaceFragment(new InicioSuperadminFragment());
            } else if (item.getItemId() == R.id.mensajesSuperadmin) {
                replaceFragment(new MensajesSuperadminFragment());
            } else if (item.getItemId() == R.id.usuariosSuperadmin) {
                replaceFragment(new UsuariosSuperadminFragment());
            }else if (item.getItemId() == R.id.logsSuperadmin) {
                replaceFragment(new HistorialSuperadminFragment());
            }
            return true;
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_superadmin, fragment);
        fragmentTransaction.commit();
    }




    public void superAdmin(View view){
        Intent intent = new Intent(this, SuperadminActivity.class);
        startActivity(intent);
    }

    public void listaUsuarios(View view){
        Intent intent = new Intent(this, SuperAdminListaUsarios.class);
        startActivity(intent);
    }

//Nuevo usuario
    public void nuevoUsuario(View view){
        Intent intent = new Intent(this, NuevoUsuarioSuperadminActivity.class);
        startActivity(intent);
    }
}