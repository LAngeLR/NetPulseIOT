package com.example.netpulseiot.fragmentos.superadmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.SuperadminMensajeAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentMensajesSuperadminBinding;
import com.example.netpulseiot.entity.AdminMensajeItem;
import com.example.netpulseiot.entity.SuperadminMensajeItem;

import java.util.ArrayList;
import java.util.List;


public class MensajesSuperadminFragment extends Fragment {

    FragmentMensajesSuperadminBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMensajesSuperadminBinding.inflate(inflater,container,false);
        List<SuperadminMensajeItem> list = new ArrayList<SuperadminMensajeItem>();
        for (int i=0; i<=12;i++){
            list.add(new SuperadminMensajeItem("Alex Valera","La reunión será a las 11:00 am",R.drawable.fotoperfil_u2,"1"));
        }

        binding.superadminMensajesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.superadminMensajesRecyclerView.setAdapter(new SuperadminMensajeAdapter(getContext(),list));

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}