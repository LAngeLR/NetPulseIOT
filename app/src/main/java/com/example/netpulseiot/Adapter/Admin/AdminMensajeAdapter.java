package com.example.netpulseiot.Adapter.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.AdminMensajeItem;

import java.util.List;

public class AdminMensajeAdapter extends RecyclerView.Adapter<AdminMensajeAdapter.AdminMensajeViewHolder>{

    Context context;
    List<AdminMensajeItem> lista;
//    NavController navController;  //importado para usar nav controller en el botón

    public AdminMensajeAdapter(Context context, List<AdminMensajeItem> lista) {
        this.context = context;
        this.lista = lista;
//        this.navController = navController; //agregado apra nav controller
    }

    @NonNull
    @Override
    public AdminMensajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_mensaje,parent,false);
        return new AdminMensajeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminMensajeViewHolder holder, int position) {

        holder.nombreItem.setText(lista.get(position).getName());
        holder.mensajeItem.setText(lista.get(position).getMensaje());
        holder.fotoItem.setImageResource(lista.get(position).getImage());
        holder.contadorItem.setText(lista.get(position).getContador());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class AdminMensajeViewHolder extends RecyclerView.ViewHolder{
        ImageView fotoItem;
        TextView nombreItem, mensajeItem;
        Button contadorItem;
        public AdminMensajeViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoItem = itemView.findViewById(R.id.fotoItem);
            nombreItem = itemView.findViewById(R.id.nombreItem);
            mensajeItem = itemView.findViewById(R.id.mensajeItem);
            contadorItem = itemView.findViewById(R.id.contadorItem);

        }
    }

    public void bind(NavController navController){

        //hola
    }

}
