package com.example.netpulseiot;

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

import com.example.netpulseiot.databinding.ActivityAdmin1Binding;
import com.example.netpulseiot.databinding.ActivitySuperadminBinding;
import com.example.netpulseiot.fragmentos.UsersFragment;
import com.example.netpulseiot.fragmentos.admin.AdminEquiposFragment;
import com.example.netpulseiot.fragmentos.admin.AdminInicioFragment;
import com.example.netpulseiot.fragmentos.admin.AdminListaMensajesFragmenmt;
import com.example.netpulseiot.fragmentos.admin.AdminSitiosFragment;
import com.example.netpulseiot.fragmentos.admin.AdminUsuariosFragment;
import com.example.netpulseiot.fragmentos.superadmin.HistorialSuperadminFragment;
import com.example.netpulseiot.fragmentos.superadmin.InicioSuperadminFragment;
import com.example.netpulseiot.fragmentos.superadmin.SuperadminActivity;
import com.example.netpulseiot.fragmentos.superadmin.UsuariosSuperadminFragment;
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

public class AdminActivity extends AppCompatActivity {

    ActivityAdmin1Binding binding;

    FirebaseUser firebaseUser;
    DatabaseReference reference;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdmin1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("NetPulse");

        /** CREAR INSTANCIA DE BD FIREBASE **/
        db = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());


        replaceFragment(new AdminInicioFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.inicioAdmin) {
                replaceFragment(new AdminInicioFragment());
            } else if (item.getItemId() == R.id.mensajesAdmin) {
                replaceFragment(new AdminListaMensajesFragmenmt());
            } else if (item.getItemId() == R.id.usuariosAdmin) {
                replaceFragment(new AdminUsuariosFragment());
            } else if (item.getItemId() == R.id.sitiosAdmin) {
                replaceFragment(new AdminSitiosFragment());
            } else if (item.getItemId() == R.id.equiposAdmin) {
                replaceFragment(new AdminEquiposFragment());
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
        fragmentTransaction.replace(R.id.fragment_admin, fragment);
        fragmentTransaction.commit();
    }

    public void listaSitio(View view){
        Intent intent = new Intent(this, AdminListaSitiosActivity.class);
        startActivity(intent);
    }

    public void listaEquipo(View view){
        Intent intent = new Intent(this, AdminListaEquiposActivity.class);
        startActivity(intent);
    }

    public void listaMensaje(View view){
        Intent intent = new Intent(this, AdminListaMensajeActivity.class);
        startActivity(intent);
    }

    public void admin(View view){
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

    public void listaUsuarios(View view){
        Intent intent = new Intent(this, AdminListaUsuarioActivity.class);
        startActivity(intent);
    }


    public void perfil1(View view){
        Intent intent = new Intent(this, AdminPerfilActivity.class);
        startActivity(intent);
    }

    public void mensaje(View view){
        Intent intent = new Intent(this, AdminMensajeActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.tercero) { // ID del ítem "Cerrar sesión"
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(AdminActivity.this, MainActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}