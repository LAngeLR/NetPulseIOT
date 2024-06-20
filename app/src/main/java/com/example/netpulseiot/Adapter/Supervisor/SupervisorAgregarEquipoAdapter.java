package com.example.netpulseiot.Adapter.Supervisor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.Adapter.Admin.AdminAsignarSupervisorAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.EquipoItem;
import com.example.netpulseiot.entity.SuperadminUsuarioItem;

import java.util.List;

public class SupervisorAgregarEquipoAdapter extends RecyclerView.Adapter<SupervisorAgregarEquipoAdapter.supervisorAgregarEquipoViewHolder>{
    
    Context context;
    List<EquipoItem> list;
    private OnItemClickListener listener;

    public SupervisorAgregarEquipoAdapter(Context context, List<EquipoItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public supervisorAgregarEquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_agregar_equipo_sitio,parent,false);
        return new SupervisorAgregarEquipoAdapter.supervisorAgregarEquipoViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull supervisorAgregarEquipoViewHolder holder, int position) {
        EquipoItem currentItem = list.get(position);
        holder.modeloItem.setText(currentItem.getModelo());
        holder.tipoEquipoItem.setText(currentItem.getTipoEquipo());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(currentItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class supervisorAgregarEquipoViewHolder extends RecyclerView.ViewHolder{
        TextView modeloItem, tipoEquipoItem;

        public supervisorAgregarEquipoViewHolder(@NonNull View itemView) {
            super(itemView);
            modeloItem = itemView.findViewById(R.id.modeloItem);
            tipoEquipoItem = itemView.findViewById(R.id.tipoEquipoItem);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(EquipoItem item);
    }

    public void setOnItemClickListener(SupervisorAgregarEquipoAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
