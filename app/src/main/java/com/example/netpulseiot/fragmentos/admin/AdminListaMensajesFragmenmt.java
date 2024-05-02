package com.example.netpulseiot.fragmentos.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.AdminMensajeAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentAdminListaMensajesFragmenmtBinding;
import com.example.netpulseiot.entity.AdminMensajeItem;

import java.util.ArrayList;
import java.util.List;

public class AdminListaMensajesFragmenmt extends Fragment {

    FragmentAdminListaMensajesFragmenmtBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminListaMensajesFragmenmtBinding.inflate(inflater,container,false);
        //hardoceo de la lista (se cambiará cuando tengamos BD o API para extraer los dto
        List<AdminMensajeItem> list = new ArrayList<AdminMensajeItem>();
        for (int i=0; i<=12;i++){
            list.add(new AdminMensajeItem("Alex Valera","La reunión será a las 11:00 am",R.drawable.fotoperfil_u2,"1"));
        }

        binding.adminMensajesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.adminMensajesRecyclerView.setAdapter(new AdminMensajeAdapter(getContext(),list));

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}