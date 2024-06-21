package com.example.netpulseiot.fragmentos.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.netpulseiot.AdminActivity;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentAdminVerUsuarioBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerUsuarioAdminFragment extends Fragment {
    FragmentAdminVerUsuarioBinding binding;
    String nombre = null, apellido = null, rol = null, correo = null, telefono = null, direccion = null, idUsuario = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminVerUsuarioBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        String idUsuarioEditar;

        Bundle args = getArguments();
        if (args != null) {
            nombre = args.getString("nombre");
            apellido = args.getString("apellido");
            rol = args.getString("rol");
            correo = args.getString("correo");
            telefono = args.getString("telefono");
            direccion = args.getString("direccion");
            idUsuario = args.getString("idUsuario");

            idUsuarioEditar = idUsuario;

            // Actualiza las vistas con los datos del usuario, o "No definido" si el valor es null
            binding.textView101.setText(nombre != null ? nombre : "No definido");
            binding.textView103.setText(apellido != null ? apellido : "No definido");
            binding.textView105.setText(rol != null ? rol : "No definido");
            binding.textView107.setText(correo != null ? correo : "No definido");
            binding.telefonoUserAdmin.setText(telefono != null ? telefono : "No definido");
            binding.textView111.setText(direccion != null ? direccion : "No definido");
        }

        FloatingActionButton fab = rootView.findViewById(R.id.floatingActionButton3);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("msg-test", "Funciona fab");

                EditarUsuarioAdminFragment editarFragment = new EditarUsuarioAdminFragment();
                Fragment editarUsuarioAdminFragment = new EditarUsuarioAdminFragment();

                Bundle args = new Bundle();
                args.putString("nombre", nombre);
                args.putString("apellido", apellido);
                args.putString("rol", rol);
                args.putString("correo", correo);
                args.putString("telefono", telefono);
                args.putString("direccion", direccion);
                args.putString("idUsuario", idUsuario);

                editarUsuarioAdminFragment.setArguments(args);

                if(getActivity() instanceof AdminActivity){
                    ((AdminActivity) getActivity()).replaceFragment(editarUsuarioAdminFragment);
                }

            }
        });


        return rootView;
    }
}


