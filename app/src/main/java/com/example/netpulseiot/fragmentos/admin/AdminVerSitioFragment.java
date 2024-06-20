package com.example.netpulseiot.fragmentos.admin;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Activity.AdminActivity;
import com.example.netpulseiot.databinding.FragmentAdminVerSitioBinding;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminVerSitioFragment extends Fragment {
    FragmentAdminVerSitioBinding binding;
    FirebaseFirestore db;

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
            String supervisorId = args.getString("supervisor");
            Double latitud = args.getDouble("latitud");
            Double longitud = args.getDouble("longitud");

            binding.nombreSitioItem.setText(nombre != null ? nombre : "No definido");
            String ubicacion = (departamento + ", " + provincia + ", " + distrito);
            binding.ubicacionSitioItem.setText(distrito != null ? ubicacion : "No definido");
            binding.tipoSitioItem.setText(tipoSitio != null ? tipoSitio : "No definido");
            binding.tipoZonaItem.setText(tipoZona != null ? tipoZona : "No definido");
//            binding.supervisorAsignado.setText(supervisor != null ? supervisor : "No asignado");
            String geolocalizacion = ("Lat: " + latitud + ", Lon: " + longitud);
            binding.geolocalizacionItem.setText(geolocalizacion);

            //llamafo a la BD para obtener el nombre según el id
            db = FirebaseFirestore.getInstance();
            if (supervisorId != null && !supervisorId.isEmpty()) {
                DocumentReference supervisorRef = db.collection("usuarios").document(supervisorId);
                supervisorRef.get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String supervisorNombre = documentSnapshot.getString("nombre");
                        String supervisorApellido = documentSnapshot.getString("apellido");
                        String nombreCompleto = supervisorNombre + " " + supervisorApellido;
                        binding.supervisorAsignado.setText(supervisorNombre != null ? nombreCompleto : "No asignado");
                    } else {
                        binding.supervisorAsignado.setText("No asignado");
                    }
                }).addOnFailureListener(e -> {
                    binding.supervisorAsignado.setText("No asignado");
                });
            } else {
                binding.supervisorAsignado.setText("No asignado");
            }

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