package com.example.netpulseiot.fragmentos.supervisor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.Supervisor.SupervisorSitioAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentSupervisorSitiosBinding;
import com.example.netpulseiot.entity.SupervisorSitioItem;

import java.util.ArrayList;
import java.util.List;

public class SupervisorSitiosFragment extends Fragment {

    FragmentSupervisorSitiosBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSupervisorSitiosBinding.inflate(inflater,container,false);

        //hardoceo de la lista (se cambiará cuando tengamos BD o API para extraer los dto
        List<SupervisorSitioItem> list = new ArrayList<SupervisorSitioItem>();
        for (int i=0; i<=12;i++){
            list.add(new SupervisorSitioItem("Lima","Lima","Surco", "Tipo1", R.drawable.fotoperfil_u));
        }

        binding.supervisorSitiosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.supervisorSitiosRecyclerView.setAdapter(new SupervisorSitioAdapter(getContext(),list));

        return binding.getRoot();
    }
}