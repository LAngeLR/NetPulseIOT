package com.example.netpulseiot.fragmentos.superadmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.SuperadminLogsAdapter;
import com.example.netpulseiot.Adapter.SuperadminUsuarioAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentHistorialSuperadminBinding;
import com.example.netpulseiot.databinding.FragmentListaUsuariosSuperadminBinding;
import com.example.netpulseiot.entity.SuperadminLogsItem;
import com.example.netpulseiot.entity.SuperadminUsuarioItem;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuariosSuperadminFragment extends Fragment {

    FragmentListaUsuariosSuperadminBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListaUsuariosSuperadminBinding.inflate(inflater,container,false);
        List<SuperadminUsuarioItem> list = new ArrayList<SuperadminUsuarioItem>();
        for (int i=0; i<=12;i++){
            list.add(new SuperadminUsuarioItem("Oscar Diaz","Superadmin", R.drawable.fotoperfil_u2));
        }

        binding.superadminUsuariosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.superadminUsuariosRecyclerView.setAdapter(new SuperadminUsuarioAdapter(getContext(),list));

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}