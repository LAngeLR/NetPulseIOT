package com.example.netpulseiot.fragmentos.superadmin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.netpulseiot.AuthActivity2;
import com.example.netpulseiot.MainActivity;
import com.example.netpulseiot.R;
import com.example.netpulseiot.SuperAdminListaUsarios;
import com.example.netpulseiot.databinding.ActivitySuperadminBinding;
import com.example.netpulseiot.fragmentos.UsersFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SuperadminActivity extends AppCompatActivity {

    ActivitySuperadminBinding binding;
    FirebaseUser firebaseUser;
    DatabaseReference reference;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySuperadminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("NetPulse");

        /** CREAR INSTANCIA DE BD FIREBASE **/
        db = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        /** GUARDAR LOG **/

        ZoneId zonaPeru = ZoneId.of("America/Lima");
        ZonedDateTime fechaHoraActualPeru = ZonedDateTime.now(zonaPeru);

        LocalTime horaActual = LocalTime.now(zonaPeru);
        Instant instant = fechaHoraActualPeru.toInstant();
        Date fechaHoraActualPeruDate = Date.from(instant);

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

        Log.d("msg-test","Hora actual: " + horaActual.format(formatoHora));
        Log.d("msg-test","Timestamp: " + fechaHoraActualPeruDate);


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
                replaceFragment(new UsersFragment());
            } else if (item.getItemId() == R.id.usuariosSuperadmin) {
                replaceFragment(new UsuariosSuperadminFragment());
            } else if (item.getItemId() == R.id.logsSuperadmin) {
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

    // Toolbar - Cerrar sesión
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.tercero) { // ID del ítem "Cerrar sesión"
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(SuperadminActivity.this, MainActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
