package com.example.netpulseiot.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.Adapter.SupervisorSitioAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.SupervisorSitioItem;

import java.util.ArrayList;
import java.util.List;

public class SupervisorListaSitiosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_supervisor_equipos);

        //hardoceo de la lista (se cambiará cuando tengamos BD o API para extraer los dto
        List<SupervisorSitioItem> list = new ArrayList<SupervisorSitioItem>();
        for (int i=0; i<=12;i++){
            list.add(new SupervisorSitioItem("Lima","Lima","Surco", "Tipo1", R.drawable.fotoperfil_u));
        }
        //implementación del RecyclerViewer
        RecyclerView recyler = findViewById(R.id.supervisorSitiosRecyclerView);
        recyler.setLayoutManager(new LinearLayoutManager(this));
        recyler.setAdapter(new SupervisorSitioAdapter(getApplicationContext(),list));

    }


    public void listaSitio(View view){
        Intent intent = new Intent(this, SupervisorListaSitiosActivity.class);
        startActivity(intent);
    }
    public void listaEquipo(View view){
        Intent intent = new Intent(this, SupervisorListaEquiposActivity.class);
        startActivity(intent);
    }
    public void listaMensaje(View view){
        Intent intent = new Intent(this, AdminListaMensajeActivity.class);
        startActivity(intent);
    }
    public void admin(View view){
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }
    public void listaUsuarios(View view){
        Intent intent = new Intent(this, AdminListaUsuarioActivity.class);
        startActivity(intent);
    }

    public void perfil1(View view){
        Intent intent = new Intent(this, AdminPerfilActivity.class);
        startActivity(intent);
    }


    public void nuevoUsuario(View view){
        Intent intent = new Intent(this, AdminNUevoUsuario.class);
        startActivity(intent);
    }

    public void infoUsuario(View view){
        Intent intent = new Intent(this, AdminInfoUsuarioActivity.class);
        startActivity(intent);
    }
}
