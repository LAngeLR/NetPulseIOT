package com.example.netpulseiot.fragmentos.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentAdminVerSitioBinding;
import com.google.firebase.firestore.GeoPoint;

public class AdminVerSitioFragment extends Fragment {
    FragmentAdminVerSitioBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminVerSitioBinding.inflate(inflater, container, false);

        Bundle args = getArguments();
        if (args!= null) {
            
            String nombre = args.getString("nombre");
            String departamento = args.getString("departamento");
            String provincia = args.getString("provincia");
            String distrito = args.getString("distrito");
            String tipoSitio = args.getString("tipoSitio");
            String tipoZona = args.getString("tipoZona");
            String ubigeo = args.getString("ubigeo");
            Double latitud = args.getDouble("latitud");
            Double longitud = args.getDouble("longitud");

            binding.nombreSitioItem.setText(nombre != null ? nombre : "No definido");
            String ubicacion = (departamento + ", " + provincia + ", " + distrito);
            binding.ubicacionSitioItem.setText(distrito != null ? ubicacion : "No definido");
            binding.tipoSitioItem.setText(tipoSitio != null ? tipoSitio : "No definido");
            binding.tipoZonaItem.setText(tipoZona != null ? tipoZona : "No definido");
            String geolocalizacion = ("Lat: " + latitud + ", Lon: " + longitud);
            binding.geolocalizacionItem.setText(geolocalizacion);
        }

        return  binding.getRoot();
    }
}