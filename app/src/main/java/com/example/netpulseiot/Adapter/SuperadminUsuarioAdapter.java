package com.example.netpulseiot.Adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.SuperadminLogsItem;
import com.example.netpulseiot.entity.SuperadminUsuarioItem;

import java.util.List;


public class SuperadminUsuarioAdapter extends RecyclerView.Adapter<SuperadminUsuarioAdapter.SuperadminUsuarioViewHolder> {

    Context context;
    List<SuperadminUsuarioItem> list;

    public SuperadminUsuarioAdapter(Context context, List<SuperadminUsuarioItem> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SuperadminUsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_superadmin_usuarios,parent,false);
        return new SuperadminUsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperadminUsuarioViewHolder holder, int position) {
        holder.nombreItem.setText(list.get(position).getName());
        holder.cargoItem.setText(list.get(position).getCargo());
        holder.perfilFotoItem.setImageResource(list.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SuperadminUsuarioViewHolder extends RecyclerView.ViewHolder{
        ImageView perfilFotoItem;
        TextView nombreItem, cargoItem;
        public SuperadminUsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            perfilFotoItem = itemView.findViewById(R.id.perfilFotoItem);
            nombreItem = itemView.findViewById(R.id.nombreItem);
            cargoItem = itemView.findViewById(R.id.cargoItem);
        }
    }
}
