package com.example.netpulseiot.Adapter.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.EquipoItem;

import java.util.List;

public class AdminEquipoAdapter extends RecyclerView.Adapter<AdminEquipoAdapter.adminEquipoViewHolder>{

    Context context;
    List<EquipoItem> list;

    public AdminEquipoAdapter(Context context, List<EquipoItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public adminEquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //verificar si está con el layout con nombre item equipo o item ssupervisor equipo pq le planee cambiar el nombre para uniformizar
        View view = LayoutInflater.from(context).inflate(R.layout.item_equipo,parent,false);
        return new adminEquipoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adminEquipoViewHolder holder, int position) {
        EquipoItem item = list.get(position);
        holder.modeloItem.setText(item.getModelo()); //cambiar lugeo nombre x modelo
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class adminEquipoViewHolder extends RecyclerView.ViewHolder{
        TextView modeloItem;

        public adminEquipoViewHolder(@NonNull View itemView) {
            super(itemView);
            modeloItem = itemView.findViewById(R.id.nombreItem); //va el modelo pero en el layout el id se llama nombre
        }
    }
}
