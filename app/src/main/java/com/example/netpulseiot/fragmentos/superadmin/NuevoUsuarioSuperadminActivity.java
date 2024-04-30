package com.example.netpulseiot.fragmentos.superadmin;

import android.app.Activity;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.ActivityNuevoUsuarioSuperadminBinding;
import com.example.netpulseiot.databinding.ActivitySuperadminBinding;

public class NuevoUsuarioSuperadminActivity extends AppCompatActivity {

    ActivityNuevoUsuarioSuperadminBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNuevoUsuarioSuperadminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}