package com.example.netpulseiot.fragmentos.superadmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentVerUsuarioSuperadminBinding;
import com.example.netpulseiot.dto.UsuarioDTO;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class VerUsuarioSuperadminFragment extends Fragment {

    FragmentVerUsuarioSuperadminBinding binding;
    String idUsuario;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentVerUsuarioSuperadminBinding.inflate(inflater,container,false);

        Bundle args = getArguments();
        if (args != null) {
            String nombre = args.getString("nombre");
            String apellido = args.getString("apellido");
            String cargo = args.getString("cargo");
            String correo = args.getString("correo");
            String telefono = args.getString("telefono");
            String direccion = args.getString("direccion");
            // Actualiza las vistas con los datos del usuario
            binding.nombreUser.setText(nombre);
            binding.apellidoUser.setText(apellido);
            binding.rolUser.setText(cargo);
            binding.correoUser.setText(correo);
            binding.telefonoUser.setText(telefono);
            binding.direccionUser.setText(direccion);
        }

//        Log.d("msg-test", "ID ACCEDIDO: " + idUsuario);





        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ver_usuario_superadmin, container, false);
    }
}