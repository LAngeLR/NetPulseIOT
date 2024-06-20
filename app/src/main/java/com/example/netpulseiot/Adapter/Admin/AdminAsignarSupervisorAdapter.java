package com.example.netpulseiot.Adapter.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.SuperadminUsuarioItem;

import java.util.List;

public class AdminAsignarSupervisorAdapter extends RecyclerView.Adapter<AdminAsignarSupervisorAdapter.adminAsignarSupervisorViewHolder>{

    Context context;
    List<SuperadminUsuarioItem> list;
    private OnItemClickListener listener;

    public AdminAsignarSupervisorAdapter(Context context, List<SuperadminUsuarioItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public adminAsignarSupervisorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_asignar_supervisor,parent,false);
        return new AdminAsignarSupervisorAdapter.adminAsignarSupervisorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adminAsignarSupervisorViewHolder holder, int position) {
        SuperadminUsuarioItem currentItem = list.get(position);
        holder.nombreItem.setText(currentItem.getNombre());

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

    public class adminAsignarSupervisorViewHolder extends RecyclerView.ViewHolder{
        TextView nombreItem;

        public adminAsignarSupervisorViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreItem = itemView.findViewById(R.id.nombreItem);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SuperadminUsuarioItem item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
