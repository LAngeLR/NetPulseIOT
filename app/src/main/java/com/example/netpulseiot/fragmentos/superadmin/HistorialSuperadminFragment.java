package com.example.netpulseiot.fragmentos.superadmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.AdminMensajeAdapter;
import com.example.netpulseiot.Adapter.SuperadminLogsAdapter;
import com.example.netpulseiot.Adapter.SupervisorMensajeAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentHistorialSuperadminBinding;
import com.example.netpulseiot.entity.AdminMensajeItem;
import com.example.netpulseiot.entity.SuperadminLogsItem;

import java.util.ArrayList;
import java.util.List;


public class HistorialSuperadminFragment extends Fragment {

    FragmentHistorialSuperadminBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistorialSuperadminBinding.inflate(inflater,container,false);
        List<SuperadminLogsItem> list = new ArrayList<SuperadminLogsItem>();
        for (int i=0; i<=12;i++){
            list.add(new SuperadminLogsItem("Oscar Diaz","Ingreso detalles del usuario Fabian Bustos", R.drawable.fotoperfil_u2,"08/11/2023", "05:15:02"));
        }

        binding.superadminLogsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.superadminLogsRecyclerView.setAdapter(new SuperadminLogsAdapter(getContext(),list));

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}