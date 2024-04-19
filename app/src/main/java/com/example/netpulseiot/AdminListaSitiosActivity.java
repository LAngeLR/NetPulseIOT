package com.example.netpulseiot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminListaSitiosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lista_sitios);

    }

    //luego borrar y hacerlo con fragmento
    public void listaSitio(View view){
        Intent intent = new Intent(this, AdminListaSitiosActivity.class);
        startActivity(intent);
    }
    public void listaEquipo(View view){
        Intent intent = new Intent(this, AdminListaEquiposActivity.class);
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

}