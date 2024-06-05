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
import com.example.netpulseiot.entity.SupervisorSitioItem;

import java.util.List;

public class SupervisorSitioAdapter extends RecyclerView.Adapter<SupervisorSitioAdapter.supervisorSitioViewHolder>{

    private Context context;
    List<SupervisorSitioItem> listaSitios;
    //cree un constructor de estas 2 instancias para poder usarlo en el onBindViewHolder
    public SupervisorSitioAdapter(Context context, List<SupervisorSitioItem> listaSitios) {
        this.context = context;
        this.listaSitios = listaSitios;
    }

    @NonNull
    @Override
    public SupervisorSitioAdapter.supervisorSitioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_supervisor_sitio,parent,false);
        return new SupervisorSitioAdapter.supervisorSitioViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SupervisorSitioAdapter.supervisorSitioViewHolder holder, int position) {
        holder.nombreItem.setText(listaSitios.get(position).getNombre());
        holder.provinciaItem.setText(listaSitios.get(position).getProvincia());
        holder.distritoItem.setText(listaSitios.get(position).getDistrito());
        holder.tipoItem.setText(listaSitios.get(position).getTipo());
        holder.imageItem.setImageResource(listaSitios.get(position).getImage());
    }
    @Override
    public int getItemCount() {
        return listaSitios.size();
    }
    //View Holder
    public class supervisorSitioViewHolder extends RecyclerView.ViewHolder{
        ImageView imageItem;
        TextView nombreItem, provinciaItem, distritoItem, tipoItem ;

        public supervisorSitioViewHolder(@NonNull View itemView) {
            super(itemView);

            imageItem = itemView.findViewById(R.id.imageItem);
            nombreItem = itemView.findViewById(R.id.nombreItem);
            provinciaItem = itemView.findViewById(R.id.provinciaItem);
            distritoItem = itemView.findViewById(R.id.distritoItem);
            tipoItem = itemView.findViewById(R.id.tipoItem);
        }
    }
}
