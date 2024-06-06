package com.example.netpulseiot.Adapter.Superadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.MensajeItem;

import java.util.List;

public class SuperadminMensajeAdapter extends RecyclerView.Adapter<SuperadminMensajeAdapter.SuperadminMensajeViewHolder> {

    Context context;
    List<MensajeItem> lista;

    public SuperadminMensajeAdapter(Context context, List<MensajeItem> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public SuperadminMensajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_superadmin_mensaje,parent,false);
        return new SuperadminMensajeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperadminMensajeViewHolder holder, int position) {
        MensajeItem item = lista.get(position);
        holder.nombreItem.setText(lista.get(position).getName());
        holder.mensajeItem.setText(lista.get(position).getMensaje());
        holder.fotoItem.setImageResource(lista.get(position).getImage());
        holder.contadorItem.setText(lista.get(position).getContador());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    /** SE AÑADIO CON CHAT GPT **/
    public void updateList(List<MensajeItem> newList) {
        lista = newList;
        notifyDataSetChanged();
    }

    public class SuperadminMensajeViewHolder extends RecyclerView.ViewHolder{
        ImageView fotoItem;
        TextView nombreItem, mensajeItem;
        Button contadorItem;

        public SuperadminMensajeViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoItem = itemView.findViewById(R.id.fotoItem);
            nombreItem = itemView.findViewById(R.id.nombreItem);
            mensajeItem = itemView.findViewById(R.id.mensajeItem);
            contadorItem = itemView.findViewById(R.id.contadorItem);
        }
    }
}
