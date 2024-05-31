package com.example.netpulseiot.fragmentos.admin;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.AdminUsuarioAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentAdminUsuariosBinding;
import com.example.netpulseiot.entity.AdminUserItem;

import java.util.ArrayList;
import java.util.List;

public class AdminUsuariosFragment extends Fragment {

    FragmentAdminUsuariosBinding binding;
    private RecyclerView recyclerView;
    private AdminUsuarioAdapter adminUsuarioAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminUsuariosBinding.inflate(inflater,container,false);

        //hardoceo de la lista (se cambiará cuando tengamos BD o API para extraer los dto
        List<AdminUserItem> list = new ArrayList<AdminUserItem>();
        for (int i=0; i<=12;i++){
            list.add(new AdminUserItem("José Rivera","supervisor",R.drawable.fotoperfil_u));
        }

        //llamado al NavController para pasarlo al constructor de Adapter modificado
        NavController navController = NavHostFragment.findNavController(AdminUsuariosFragment.this);
        //error línea 45
        Log.d("AdminUsuariosFragment", navController.toString());

        binding.adminUsariosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //se le agregó el navcontroller porque ahora el construrctor pide 3 parametros por la modificación que hice para adapter
        binding.adminUsariosRecyclerView.setAdapter(new AdminUsuarioAdapter(getContext(),list));

        //uso del constructor
        adminUsuarioAdapter = new AdminUsuarioAdapter(getContext(),list);
        recyclerView.setAdapter(adminUsuarioAdapter);

        return binding.getRoot();
    }
}