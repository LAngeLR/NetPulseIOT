package com.example.netpulseiot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.netpulseiot.fragmentos.superadmin.SuperadminActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    public void admin(View view){
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

    public void superadmin(View view){
        Intent intent = new Intent(this, SuperadminActivity.class);
        startActivity(intent);
    }

    public void supervisor(View view){
        Intent intent = new Intent(this, SupervisorActivity.class);
        startActivity(intent);
    }

}