package com.example.netpulseiot.fragmentos.superadmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentVerUsuarioSuperadminBinding;
//import com.example.netpulseiot.databinding.FragmentSuperadminVerUsuarioBinding;
import com.example.netpulseiot.dto.UsuarioDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class VerUsuarioSuperadminFragment extends Fragment {

    FragmentVerUsuarioSuperadminBinding binding;
//    FragmentSuperadminVerUsuario binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentVerUsuarioSuperadminBinding.inflate(inflater,container,false);
        View rootView = binding.getRoot();

        Bundle args = getArguments();
        if (args != null) {
            String nombre = args.getString("nombre");
            String apellido = args.getString("apellido");
            String cargo = args.getString("cargo");
            String correo = args.getString("correo");
            String telefono = args.getString("telefono");
            String direccion = args.getString("direccion");

            // Actualiza las vistas con los datos del usuario
            binding.nombreUser.setText(nombre != null ? nombre : "No definido");
            binding.apellidoUser.setText(apellido != null ? apellido : "No definido");
            binding.rolUser.setText(cargo != null ? cargo : "No definido");
            binding.correoUser.setText(correo != null ? correo : "No definido");
            binding.telefonoUser.setText(telefono != null ? telefono : "No definido");
            binding.direccionUser.setText(direccion != null ? direccion : "No definido");
        }

        FloatingActionButton fab = rootView.findViewById(R.id.floating_action_button);
        /** Boton de editado **/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("msg-test", "FUNCIONA EL BOTON FLOTANTE");
            }
        });

        return rootView;
    }
}