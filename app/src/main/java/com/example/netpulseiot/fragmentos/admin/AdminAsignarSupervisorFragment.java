package com.example.netpulseiot.fragmentos.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.databinding.FragmentAdminAsignarSupervisorBinding;


public class AdminAsignarSupervisorFragment extends Fragment {
    FragmentAdminAsignarSupervisorBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminAsignarSupervisorBinding.inflate(inflater,container,false);


        Bundle args = getArguments();
        if (args != null){
            String sitioId = args.getString("sitioId");

            binding.textView.setText(sitioId);
        }

        return binding.getRoot();
    }
}