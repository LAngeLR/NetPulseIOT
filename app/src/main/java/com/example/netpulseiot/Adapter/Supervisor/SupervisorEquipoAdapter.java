package com.example.netpulseiot.Adapter.Supervisor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.SupervisorEquipoItem;

import java.util.List;

public class SupervisorEquipoAdapter extends RecyclerView.Adapter<SupervisorEquipoAdapter.supervisorEquipoViewHolder>{

    private Context context;
    List<SupervisorEquipoItem> listaEquipos;
    //cree un constructor de estas 2 instancias para poder usarlo en el onBindViewHolder
    public SupervisorEquipoAdapter(Context context, List<SupervisorEquipoItem> listaEquipos) {
        this.context = context;
        this.listaEquipos = listaEquipos;
    }

    @NonNull
    @Override
    public SupervisorEquipoAdapter.supervisorEquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_supervisor_equipo,parent,false);
        return new SupervisorEquipoAdapter.supervisorEquipoViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SupervisorEquipoAdapter.supervisorEquipoViewHolder holder, int position) {
        holder.nombreItem.setText(listaEquipos.get(position).getNombre());
        holder.imageItem.setImageResource(listaEquipos.get(position).getImage());
    }
    @Override
    public int getItemCount() {
        return listaEquipos.size();
    }
    //View Holder
    public class supervisorEquipoViewHolder extends RecyclerView.ViewHolder{
        ImageView imageItem;
        TextView nombreItem;

        public supervisorEquipoViewHolder(@NonNull View itemView) {
            super(itemView);

            imageItem = itemView.findViewById(R.id.imageItem);
            nombreItem = itemView.findViewById(R.id.nombreItem);
        }
    }
}
