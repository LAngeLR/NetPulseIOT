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
import com.example.netpulseiot.entity.SuperadminLogsItem;

import java.util.List;

public class SuperadminLogsAdapter extends RecyclerView.Adapter<SuperadminLogsAdapter.SuperadminLogViewHolder>{

    Context context;
    List<SuperadminLogsItem> list;

    public SuperadminLogsAdapter(Context context, List<SuperadminLogsItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SuperadminLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_superadmin_logs,parent,false);
        return new SuperadminLogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperadminLogViewHolder holder, int position) {
//        holder.codigoItem.setText(list.get(position).getCodigo());
//        holder.descripcionItem.setText(list.get(position).getDescripcion());
//        holder.fechaItem.setText(list.get(position).getFecha());
//        holder.fotoItem.setImageResource(list.get(position).getImage());
//        holder.horaItem.setText(list.get(position).getHora());

        /** CAMBIO **/
        holder.codigoItem.setText(list.get(position).getNombre());
        holder.descripcionItem.setText(list.get(position).getDireccion());
        holder.fechaItem.setText("hoy");
//        holder.fotoItem.setImageResource(list.get(position).getImage());
        holder.horaItem.setText(list.get(position).getApellido());
    }

    @Override
    public int getItemCount() {
        return list.size();    }

    public class SuperadminLogViewHolder extends RecyclerView.ViewHolder{
        ImageView fotoItem;
        TextView codigoItem, fechaItem,descripcionItem, horaItem;
        public SuperadminLogViewHolder(@NonNull View itemView) {
            super(itemView);

//            fotoItem = itemView.findViewById(R.id.fotoItem);
            codigoItem = itemView.findViewById(R.id.codigoItem);
            fechaItem = itemView.findViewById(R.id.fechaItem);
            descripcionItem = itemView.findViewById(R.id.descripcionItem);
            horaItem = itemView.findViewById(R.id.horaItem);
        }
    }
}
