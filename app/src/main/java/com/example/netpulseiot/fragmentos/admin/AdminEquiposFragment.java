package com.example.netpulseiot.fragmentos.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.Supervisor.SupervisorEquipoAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentAdminEquiposBinding;
import com.example.netpulseiot.entity.EquipoItem;

import java.util.ArrayList;
import java.util.List;

public class AdminEquiposFragment extends Fragment {

    FragmentAdminEquiposBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminEquiposBinding.inflate(inflater,container,false);

        //hardoceo de la lista (se cambiará cuando tengamos BD o API para extraer los dto
        List<EquipoItem> list = new ArrayList<EquipoItem>();
        for (int i=0; i<=12;i++){
            list.add(new EquipoItem("Equipo1", R.drawable.fotoperfil_u));
        }

        binding.adminEquiposRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.adminEquiposRecyclerView.setAdapter(new SupervisorEquipoAdapter(getContext(),list));

        return binding.getRoot();
    }
}