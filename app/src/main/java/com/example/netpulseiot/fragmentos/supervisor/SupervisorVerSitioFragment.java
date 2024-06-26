package com.example.netpulseiot.fragmentos.supervisor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.netpulseiot.SupervisorActivity;
import com.example.netpulseiot.databinding.FragmentAdminVerSitioBinding;
import com.example.netpulseiot.databinding.FragmentSupervisorVerSitioBinding;

import java.util.ArrayList;


public class SupervisorVerSitioFragment extends Fragment {
    FragmentSupervisorVerSitioBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSupervisorVerSitioBinding.inflate(inflater, container, false);

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
            ArrayList<String> equipos = args.getStringArrayList("equipos");

            binding.nombreSitioItem.setText(nombre != null ? nombre : "No definido");
            String ubicacion = (departamento + ", " + provincia + ", " + distrito);
            binding.ubicacionSitioItem.setText(distrito != null ? ubicacion : "No definido");
            binding.tipoSitioItem.setText(tipoSitio != null ? tipoSitio : "No definido");
            binding.tipoZonaItem.setText(tipoZona != null ? tipoZona : "No definido");
            String geolocalizacion = ("Lat: " + latitud + ", Lon: " + longitud);
            binding.geolocalizacionItem.setText(geolocalizacion);

            //agregado dinámico de equipos a la vista
            LinearLayout equiposContainer = binding.equiposContainer;
            if (equipos != null && !equipos.isEmpty()){
                for( String equipo: equipos){
                    TextView equipoTextView = new TextView(getContext());
                    equipoTextView.setText(equipo);
                    equipoTextView.setTextSize(20);
                    equipoTextView.setPadding(60, 10, 0, 10);
                    equiposContainer.addView(equipoTextView);
                }
            }


            //mandar a vista de agregar equipo
            binding.agregarEquipo.setOnClickListener(v -> {
                Fragment supervisorAgregarEquipoFragment = new SupervisorAgregarEquipoFragment();

                Bundle argsSitio = new Bundle();
                argsSitio.putString("sitioId", sitioId);
                supervisorAgregarEquipoFragment.setArguments(argsSitio);

                //si no funciona ver primero acá uu
                if (getContext() instanceof SupervisorActivity){
                    ((SupervisorActivity) getContext()).replaceFragment(supervisorAgregarEquipoFragment);
                }
            });

        }





        return  binding.getRoot();
    }
}