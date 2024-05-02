package com.example.netpulseiot.fragmentos.superadmin;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

//        binding = FragmentUsuariosSuperadminBinding.inflate(inflater,container,false);
//
//        /**
//        View v = inflater.inflate(R.layout.fragment_usuarios_superadmin, container, false);
//        Button tv = v.findViewById(R.id.NuevoUsuario);
//        Intent intent = new Intent(getActivity(), NuevoUsuarioSuperadminActivity.class);
//        startActivity(intent);
//
//         **/
//
//        NavController navController = NavHostFragment.findNavController(UsuariosSuperadminFragment.this);
//        binding.ListaUsuariosSuperadmin.setOnClickListener(view -> {
//            navController.navigate(R.id.action_usuariosSuperadminFragment_to_superadminListaUsuariosActivity);
//        });
//
//
//
//
//        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_usuarios_superadmin, container, false);
//        return v;
//        return binding.getRoot();

    }

//    public void nuevoUsuario(View view){
//        Intent intent = new Intent(this, NuevoUsuarioSuperadminActivity);
//        startActivity(intent);
//    }
}