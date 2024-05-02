package com.example.netpulseiot.fragmentos.supervisor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.SupervisorEquipoAdapter;
import com.example.netpulseiot.Adapter.SupervisorSitioAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentSupervisorEquiposBinding;
import com.example.netpulseiot.databinding.FragmentSupervisorSitiosBinding;
import com.example.netpulseiot.entity.SupervisorEquipoItem;
import com.example.netpulseiot.entity.SupervisorSitioItem;

import java.util.ArrayList;
import java.util.List;


public class SupervisorEquiposFragment extends Fragment {


    FragmentSupervisorEquiposBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSupervisorEquiposBinding.inflate(inflater,container,false);

        //hardoceo de la lista (se cambiará cuando tengamos BD o API para extraer los dto
        List<SupervisorEquipoItem> list = new ArrayList<SupervisorEquipoItem>();
        for (int i=0; i<=12;i++){
            list.add(new SupervisorEquipoItem("Equipo1", R.drawable.fotoperfil_u));
        }

        binding.supervisorEquiposRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.supervisorEquiposRecyclerView.setAdapter(new SupervisorEquipoAdapter(getContext(),list));

        return binding.getRoot();
    }
}