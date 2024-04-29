package com.example.netpulseiot.fragmentos.superadmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.netpulseiot.R;
import com.example.netpulseiot.SuperAdminListaUsarios;
import com.example.netpulseiot.SuperadminListaMensajes;
import com.example.netpulseiot.databinding.ActivitySuperadminBinding;

public class SuperadminActivity extends AppCompatActivity {

    ActivitySuperadminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySuperadminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new InicioSuperadminFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

//            switch (item.getItemId()){
//
//                case R.id.inicioSuperadmin:
//                    replaceFragment(new InicioSuperadminFragment());
//                    break;
//
//                case R.id.mensajesSuperadmin:
//                    replaceFragment(new MensajesSuperadminFragment());
//                    break;
//
//                case R.id.usuariosSuperadmin:
//                    replaceFragment(new UsuariosSuperadminFragment());
//                    break;
//
//            }

            if (item.getItemId() == R.id.inicioSuperadmin) {
                replaceFragment(new InicioSuperadminFragment());
            } else if (item.getItemId() == R.id.mensajesSuperadmin) {
                replaceFragment(new MensajesSuperadminFragment());
            } else if (item.getItemId() == R.id.usuariosSuperadmin) {
                replaceFragment(new InicioSuperadminFragment());
            }

            return true;

        });

    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_superadmin, fragment);
        fragmentTransaction.commit();
    }



    public void listaMensajes(View view){
        Intent intent = new Intent(this, SuperadminListaMensajes.class);
        startActivity(intent);
    }

    public void superAdmin(View view){
        Intent intent = new Intent(this, SuperadminActivity.class);
        startActivity(intent);
    }

    public void listaUsuarios(View view){
        Intent intent = new Intent(this, SuperAdminListaUsarios.class);
        startActivity(intent);
    }
}