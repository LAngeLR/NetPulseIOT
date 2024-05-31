package com.example.netpulseiot.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.ActivityAdmin1Binding;
import com.example.netpulseiot.fragmentos.admin.AdminEquiposFragment;
import com.example.netpulseiot.fragmentos.admin.AdminInicioFragment;
import com.example.netpulseiot.fragmentos.admin.AdminListaMensajesFragmenmt;
import com.example.netpulseiot.fragmentos.admin.AdminSitiosFragment;
import com.example.netpulseiot.fragmentos.admin.AdminUsuariosFragment;

public class AdminActivity extends AppCompatActivity {

    ActivityAdmin1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdmin1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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

            return true;

        });

    }

    private void replaceFragment(Fragment fragment){
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

}