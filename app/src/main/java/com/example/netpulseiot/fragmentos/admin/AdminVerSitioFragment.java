package com.example.netpulseiot.fragmentos.admin;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Activity.AdminActivity;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentAdminAsignarSupervisorBinding;
import com.example.netpulseiot.databinding.FragmentAdminVerSitioBinding;
import com.example.netpulseiot.entity.AdminSitioItem;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class AdminVerSitioFragment extends Fragment {
    FragmentAdminVerSitioBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminVerSitioBinding.inflate(inflater, container, false);
        Context context;



        Bundle args = getArguments();
        if (args!= null) {
            String sitioId = args.getString("id");
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

            binding.asignarSupervisores.setOnClickListener(v -> {
                //lógica para ir a la vista de agregar supervisor a sitio
                Fragment adminAsignarSupervisorFragment =new AdminAsignarSupervisorFragment();

                Bundle argsSupervisor = new Bundle();
                argsSupervisor.putString("sitioId", sitioId);

                adminAsignarSupervisorFragment.setArguments(argsSupervisor);

                //si no funciona ver primero acá uu
                if (getContext() instanceof AdminActivity){
                    ((AdminActivity) getContext()).replaceFragment(adminAsignarSupervisorFragment);
                }
            });
        }

        return  binding.getRoot();
    }
}