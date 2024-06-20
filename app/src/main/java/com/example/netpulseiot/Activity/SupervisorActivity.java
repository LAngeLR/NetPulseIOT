package com.example.netpulseiot.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.ActivitySupervisorBinding;
import com.example.netpulseiot.fragmentos.supervisor.SupervisorEquiposFragment;
import com.example.netpulseiot.fragmentos.supervisor.SupervisorInicioFragment;
import com.example.netpulseiot.fragmentos.supervisor.SupervisorMensajesFragment;
import com.example.netpulseiot.fragmentos.supervisor.SupervisorSitiosFragment;

public class SupervisorActivity extends AppCompatActivity {

    ActivitySupervisorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySupervisorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new SupervisorInicioFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.inicioSupervisor) {
                replaceFragment(new SupervisorInicioFragment());
            } else if (item.getItemId() == R.id.mensajesSupervisor) {
                replaceFragment(new SupervisorMensajesFragment());
            } else if (item.getItemId() == R.id.sitiosSupervisor) {
                replaceFragment(new SupervisorSitiosFragment());
            } else if (item.getItemId() == R.id.equiposSupervisor) {
                replaceFragment(new SupervisorEquiposFragment());
            }

            return true;

        });

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_supervisor, fragment);
        fragmentTransaction.commit();
    }

    public void listaSitio(View view){
        Intent intent = new Intent(this, SupervisorListaSitiosActivity.class);
        startActivity(intent);
    }

    public void listaEquipo(View view){
        Intent intent = new Intent(this, SupervisorListaEquiposActivity.class);
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