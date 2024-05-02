package com.example.netpulseiot.fragmentos.supervisor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.SuperadminMensajeAdapter;
import com.example.netpulseiot.Adapter.SupervisorMensajeAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentAdminListaMensajesFragmenmtBinding;
import com.example.netpulseiot.databinding.FragmentSupervisorMensajesBinding;
import com.example.netpulseiot.entity.SupervisorMensajeItem;
import com.example.netpulseiot.entity.SupervisorMensajeItem;

import java.util.ArrayList;
import java.util.List;

public class SupervisorMensajesFragment extends Fragment {
    FragmentSupervisorMensajesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSupervisorMensajesBinding.inflate(inflater,container,false);

        //hardoceo de la lista (se cambiará cuando tengamos BD o API para extraer los dto
        List<SupervisorMensajeItem> list = new ArrayList<SupervisorMensajeItem>();
        for (int i=0; i<=12;i++){
            list.add(new SupervisorMensajeItem("Alex Valera","La reunión será a las 11:00 am",R.drawable.fotoperfil_u2,"1"));
        }
        binding.supervisorMensajesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.supervisorMensajesRecyclerView.setAdapter(new SupervisorMensajeAdapter(getContext(),list));
        
        // Inflate the layout for this fragment
        return binding.getRoot();    }
}