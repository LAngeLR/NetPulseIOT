package com.example.netpulseiot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AdminPerfilEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_perfil_edit2);

    }

    public void perfil1(View view){
        Intent intent = new Intent(this, AdminPerfilActivity.class);
        startActivity(intent);
    }
}