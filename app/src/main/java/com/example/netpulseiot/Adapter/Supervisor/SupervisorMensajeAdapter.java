package com.example.netpulseiot.Adapter.Supervisor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.MensajeItem;

import java.util.List;

public class SupervisorMensajeAdapter extends RecyclerView.Adapter<SupervisorMensajeAdapter.SupervisorMensajeViewHolder>{

    Context context;
    List<MensajeItem> lista;

    public SupervisorMensajeAdapter(Context context, List<MensajeItem> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public SupervisorMensajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_superadmin_mensaje, parent,false);

        return new SupervisorMensajeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupervisorMensajeViewHolder holder, int position) {
        holder.nombreItem.setText(lista.get(position).getName());
        holder.mensajeItem.setText(lista.get(position).getMensaje());
        holder.fotoItem.setImageResource(lista.get(position).getImage());
        holder.contadorItem.setText(lista.get(position).getContador());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class SupervisorMensajeViewHolder extends RecyclerView.ViewHolder{
        ImageView fotoItem;
        TextView nombreItem, mensajeItem;
        Button contadorItem;
        public SupervisorMensajeViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoItem = itemView.findViewById(R.id.fotoItem);
            nombreItem = itemView.findViewById(R.id.nombreItem);
            mensajeItem = itemView.findViewById(R.id.mensajeItem);
            contadorItem = itemView.findViewById(R.id.contadorItem);
        }
    }

}
