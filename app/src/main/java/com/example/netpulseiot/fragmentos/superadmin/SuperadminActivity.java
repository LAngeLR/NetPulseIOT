package com.example.netpulseiot.fragmentos.superadmin;

//import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    private android.text.Html Html;

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

            int selectedItemId = item.getItemId();
            changeSelectedMenuItemTextAppearance(selectedItemId);

            return true;
        });


    }
    private void changeSelectedMenuItemTextAppearance(int selectedItemId) {
        Menu menu = binding.bottomNavigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            boolean isSelected = menuItem.getItemId() == selectedItemId;

            // Change text appearance based on selection (using SpannableString)
            if (isSelected) {
                menuItem.setTitle(menuItem.getTitle().toString().replaceFirst("[\\p{Cntrl}]", "").replaceFirst("[\\p{Punct}]", "")); // Remove special characters
                menuItem.setTitle(Html.fromHtml("<font color='#FFFFFF'>" + menuItem.getTitle() + "</font>")); // Set text content with color
            } else {
                // Set default text appearance (optional)
                menuItem.setTitle(menuItem.getTitle().toString()); // Reset text (optional)
            }
        }
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



    /* TOOLBAR - CERRAR SESIÓN */

    /**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu); // Asumiendo que el archivo XML del menú se llama toolbar_menu.xml
        return true;
    }
//    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tercero : // Este es el ID del ítem "Cerrar sesión"
                // Aquí es donde colocas el código para cerrar sesión
                cerrarSesion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void cerrarSesion() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(task -> {
                    Log.d("infoApp", "logout exitoso");
                    // Opcional: Redirigir al usuario a la pantalla de inicio de sesión o a otra actividad
                    Intent intent = new Intent(SuperadminActivity.this, AuthActivity2.class); // Asegúrate de que LoginActivity sea la actividad de inicio de sesión
                    startActivity(intent);
                    finish(); // Finaliza la actividad actual para que no puedan volver a ella presionando el botón "Atrás"
                });
    }

    **/

}