package com.example.netpulseiot.Adapter.Supervisor;

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

public class SupervisorEquipoAdapter extends RecyclerView.Adapter<SupervisorEquipoAdapter.supervisorEquipoViewHolder>{

    private Context context;
    List<EquipoItem> list;
    //cree un constructor de estas 2 instancias para poder usarlo en el onBindViewHolder
    public SupervisorEquipoAdapter(Context context, List<EquipoItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SupervisorEquipoAdapter.supervisorEquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_equipo,parent,false);
        return new SupervisorEquipoAdapter.supervisorEquipoViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SupervisorEquipoAdapter.supervisorEquipoViewHolder holder, int position) {
        EquipoItem item = list.get(position);
        holder.modeloItem.setText(item.getModelo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //View Holder
    public class supervisorEquipoViewHolder extends RecyclerView.ViewHolder{
        TextView modeloItem;

        public supervisorEquipoViewHolder(@NonNull View itemView) {
            super(itemView);
            modeloItem = itemView.findViewById(R.id.nombreItem); //va el modelo pero en el layout el id se llama nombre
        }
    }
}
