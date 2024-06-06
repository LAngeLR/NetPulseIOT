package com.example.netpulseiot.fragmentos.supervisor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentAdminVerEquipoBinding;
import com.example.netpulseiot.databinding.FragmentSupervisorVerEquipoBinding;


public class SupervisorVerEquipoFragment extends Fragment {


    FragmentSupervisorVerEquipoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSupervisorVerEquipoBinding.inflate(inflater, container, false);

        Bundle args = getArguments();
        if (args != null) {
            String des = args.getString("des");
            String mod = args.getString("mod");
            String mar = args.getString("mar");
            String num = args.getString("num");
            String sku = args.getString("sku");
            String tip = args.getString("tip");

            // Actualiza las vistas con los datos del usuario, o "No definido" si el valor es null
            binding.descripcion.setText(des != null ? des : "No definido");
            binding.modelo.setText(mod != null ? mod : "No definido");
            binding.marca.setText(mar != null ? mar : "No definido");
            binding.numSeriee.setText(num != null ? num : "No definido");
            binding.sku.setText(sku != null ? sku : "No definido");
            binding.tipoEquipo.setText(tip != null ? tip : "No definido");
        }



        return binding.getRoot();
    }
}