package com.example.netpulseiot.fragmentos.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.SupervisorSitioAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentAdminSitiosBinding;
import com.example.netpulseiot.databinding.FragmentSupervisorSitiosBinding;
import com.example.netpulseiot.entity.SupervisorSitioItem;

import java.util.ArrayList;
import java.util.List;


public class AdminSitiosFragment extends Fragment {

    FragmentAdminSitiosBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminSitiosBinding.inflate(inflater,container,false);

        //hardoceo de la lista (se cambiará cuando tengamos BD o API para extraer los dto
        List<SupervisorSitioItem> list = new ArrayList<SupervisorSitioItem>();
        for (int i=0; i<=12;i++){
            list.add(new SupervisorSitioItem("Lima","Lima","Surco", "Tipo1", R.drawable.fotoperfil_u));
        }

        binding.adminSitiosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.adminSitiosRecyclerView.setAdapter(new SupervisorSitioAdapter(getContext(),list));

        return binding.getRoot();
    }
}