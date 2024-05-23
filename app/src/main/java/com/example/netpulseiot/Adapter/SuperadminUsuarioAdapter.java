package com.example.netpulseiot.Adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.SuperadminUsuarioItem;
import com.example.netpulseiot.fragmentos.superadmin.ListaUsuariosSuperadminFragment;

import java.util.List;

public class SuperadminUsuarioAdapter extends RecyclerView.Adapter<SuperadminUsuarioAdapter.SuperadminUsuarioViewHolder> {

    ListaUsuariosSuperadminFragment context; // Cambio de tipo de Context a Fragment
    List<SuperadminUsuarioItem> list;

    public SuperadminUsuarioAdapter(ListaUsuariosSuperadminFragment context, List<SuperadminUsuarioItem> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SuperadminUsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getContext()).inflate(R.layout.item_superadmin_usuarios,parent,false);
        return new SuperadminUsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperadminUsuarioViewHolder holder, int position) {
        SuperadminUsuarioItem currentItem = list.get(position);

        holder.nombreItem.setText(currentItem.getName());
        holder.cargoItem.setText(currentItem.getCargo());
        holder.perfilFotoItem.setImageResource(currentItem.getImage());

        holder.switchButton.setOnCheckedChangeListener(null);
        holder.switchButton.setChecked(currentItem.isChecked());
        holder.switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentItem.setChecked(isChecked);
            notifyDataSetChanged(); // Notificar al adaptador de cambios

            if (isChecked) {
                // Llamar al método para mostrar la notificación
                context.notificarImportanceDefault();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SuperadminUsuarioViewHolder extends RecyclerView.ViewHolder{
        ImageView perfilFotoItem;
        TextView nombreItem, cargoItem;
        Switch switchButton;
        public SuperadminUsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            perfilFotoItem = itemView.findViewById(R.id.perfilFotoItem);
            nombreItem = itemView.findViewById(R.id.nombreItem);
            cargoItem = itemView.findViewById(R.id.cargoItem);
            switchButton = itemView.findViewById(R.id.switch3);
        }
    }
}
