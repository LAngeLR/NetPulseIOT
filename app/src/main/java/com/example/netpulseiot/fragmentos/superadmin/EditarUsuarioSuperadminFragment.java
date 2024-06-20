package com.example.netpulseiot.fragmentos.superadmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.netpulseiot.databinding.FragmentEditarUsuarioSuperadminBinding;


import com.example.netpulseiot.R;
import com.example.netpulseiot.dto.UsuarioDTO;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditarUsuarioSuperadminFragment extends Fragment {

    FragmentEditarUsuarioSuperadminBinding binding;

    String nombre = null, apellido = null, rol = null, correo = null, telefono = null, direccion = null, idUsuario = null;

//    Integer celularInt = Integer.parseInt(telefono);
    String idUsuarioEditar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentEditarUsuarioSuperadminBinding.inflate(inflater,container,false);
        View rootView = binding.getRoot();

        Bundle args = getArguments();
        if (args != null) {
             nombre = args.getString("nombre");
             apellido = args.getString("apellido");
             rol = args.getString("rol");
             correo = args.getString("correo");
             telefono = args.getString("telefono");
             direccion = args.getString("direccion");
             idUsuario = args.getString("idUsuario");
            Log.d("msg-test","El ID obtenido fue: " + idUsuario);

            idUsuarioEditar = idUsuario;


            // Actualiza las vistas con los datos del usuario
            binding.nombreUser.setText(nombre != null ? nombre : "No definido");
            binding.apellidoUser.setText(apellido != null ? apellido : "No definido");
            binding.rolUser.setText(rol != null ? rol : "No definido");
            binding.correoUser.setText(correo != null ? correo : "No definido");
            binding.telefonoUser.setText(telefono != null ? telefono : "No definido");
            binding.direccionUser.setText(direccion != null ? direccion : "No definido");
        } else {
            idUsuarioEditar = null;
        }

        binding.guardarEditar.setOnClickListener(view -> {

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            int MandarCelar = Integer.parseInt(binding.telefonoUser.getText().toString());

            db.collection("usuarios")
                    .document(idUsuarioEditar)
                    .update("celular", MandarCelar)
                    .addOnSuccessListener( unused -> {
                        Log.d("msg-test","LA DATA SE ACTULIZO CORRECTAMENTE WIIII");
                        Toast.makeText(requireContext(), "Usuario '" + nombre + " " + apellido + "' se actualizo", Toast.LENGTH_SHORT).show();

                        // Retroceder al fragmento anterior
                        if (getFragmentManager() != null) {
                            getFragmentManager().popBackStack();
                        }

                    })
                    .addOnFailureListener(e -> e.printStackTrace());


        });

        binding.cancelarEditar.setOnClickListener(view -> {
            // Retroceder al fragmento anterior
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
                Toast.makeText(requireContext(), "Acción de editar cancelada", Toast.LENGTH_SHORT).show();

            }
        });


        return rootView;

    }
}