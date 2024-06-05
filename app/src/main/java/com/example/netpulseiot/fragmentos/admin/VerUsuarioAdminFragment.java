package com.example.netpulseiot.fragmentos.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentAdminVerUsuarioBinding;

public class VerUsuarioAdminFragment extends Fragment {
    FragmentAdminVerUsuarioBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminVerUsuarioBinding.inflate(inflater, container, false);

        Bundle args = getArguments();
        if (args != null) {
            String nombre = args.getString("nombre");
            String apellido = args.getString("apellido");
            String rol = args.getString("rol");
            String correo = args.getString("correo");
            String telefono = args.getString("telefono");
            String direccion = args.getString("direccion");

            // Actualiza las vistas con los datos del usuario, o "No definido" si el valor es null
            binding.textView101.setText(nombre != null ? nombre : "No definido");
            binding.textView103.setText(apellido != null ? apellido : "No definido");
            binding.textView105.setText(rol != null ? rol : "No definido");
            binding.textView107.setText(correo != null ? correo : "No definido");
            binding.textView109.setText(telefono != null ? telefono : "No definido");
            binding.textView111.setText(direccion != null ? direccion : "No definido");
        }

        return binding.getRoot(); // Devolver la vista inflada correctamente
    }
}


