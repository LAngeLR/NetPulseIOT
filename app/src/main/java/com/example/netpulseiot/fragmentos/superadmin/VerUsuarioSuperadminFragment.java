package com.example.netpulseiot.fragmentos.superadmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentVerUsuarioSuperadminBinding;

public class VerUsuarioSuperadminFragment extends Fragment {

    FragmentVerUsuarioSuperadminBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentVerUsuarioSuperadminBinding.inflate(inflater,container,false);





        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ver_usuario_superadmin, container, false);
    }
}