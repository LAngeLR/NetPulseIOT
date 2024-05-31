package com.example.netpulseiot.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.netpulseiot.R;

public class AdminPerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_perfil);

    }

    public void perfil2(View view){
        Intent intent = new Intent(this, AdminPerfilEditActivity.class);
        startActivity(intent);
    }
}