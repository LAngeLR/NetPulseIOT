package com.example.netpulseiot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.AdminUserItem;

import java.util.List;

public class AdminUsuarioAdapter extends RecyclerView.Adapter<AdminUsuarioAdapter.adminUsuarioViewHolder>{



    private Context context;
    List<AdminUserItem> listaUsuarios;
    //cree un constructor de estas 2 instancias para poder usarlo en el onBindViewHolder
    public AdminUsuarioAdapter(Context context, List<AdminUserItem> listaUsuarios) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public adminUsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_usuario,parent,false);
        return new adminUsuarioViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull adminUsuarioViewHolder holder, int position) {
        holder.nombreItem.setText(listaUsuarios.get(position).getName());
        holder.cargoItem.setText(listaUsuarios.get(position).getCargo());
        holder.fotoItem.setImageResource(listaUsuarios.get(position).getImage());
    }
    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }
    //View Holder
    public class adminUsuarioViewHolder extends RecyclerView.ViewHolder{
        ImageView fotoItem;
        TextView nombreItem, cargoItem;

        public adminUsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            fotoItem = itemView.findViewById(R.id.fotoItem);
            nombreItem = itemView.findViewById(R.id.nombreItem);
            cargoItem = itemView.findViewById(R.id.cargoItem);
        }
    }

}
