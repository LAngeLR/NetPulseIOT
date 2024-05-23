package com.example.netpulseiot.fragmentos.superadmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentUsuariosSuperadminBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsuariosSuperadminFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsuariosSuperadminFragment extends Fragment {

    FragmentUsuariosSuperadminBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUsuariosSuperadminBinding.inflate(inflater, container, false);


        binding.ListaUsuariosSuperadmin.setOnClickListener(view -> {
            replaceFragment(new ListaUsuariosSuperadminFragment());
        });

        return binding.getRoot();



        /** SOLO ESTABA ESTA LINEA Y FUNCIONABA CORRECTAMENTE **/
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_usuarios_superadmin, container, false);

    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_superadmin, fragment);
        fragmentTransaction.addToBackStack(null); // Añadir a la pila de retroceso
        fragmentTransaction.commit();
    }

}