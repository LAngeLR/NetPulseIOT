package com.example.netpulseiot.Adapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.AdminVerUsuarioFragment;
import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.SuperadminUsuarioItem;
import com.example.netpulseiot.fragmentos.superadmin.ListaUsuariosSuperadminFragment;
import com.example.netpulseiot.fragmentos.superadmin.SuperadminActivity;
import com.example.netpulseiot.fragmentos.superadmin.VerUsuarioSuperadminFragment;

import java.util.List;

public class SuperadminUsuarioAdapter extends RecyclerView.Adapter<SuperadminUsuarioAdapter.SuperadminUsuarioViewHolder> {

    Context context; // Cambio de tipo de Context a Fragment
    List<SuperadminUsuarioItem> list;
    List<String> documentIds;

    public SuperadminUsuarioAdapter(Context context, List<SuperadminUsuarioItem> list, List<String> documentIds) {
        this.context = context;
        this.list = list;
        this.documentIds = documentIds;
    }

    /**
    public SuperadminUsuarioAdapter(Context context, List<SuperadminUsuarioItem> list) {
        this.context = context;
        this.list = list;
    }
    **/




    @NonNull
    @Override
    public SuperadminUsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_superadmin_usuarios,parent,false);
        return new SuperadminUsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperadminUsuarioViewHolder holder, int position) {
        SuperadminUsuarioItem currentItem = list.get(position);

        String nombreCompleto = list.get(position).getNombre() + " " + list.get(position).getApellido();
        holder.nombreItem.setText(nombreCompleto);
        holder.cargoItem.setText(currentItem.getRol());

//        String idDocumento = documentIds.get(position);
//        String idPasar = currentItem.setIdDocumento(idDocumento);


//        currentItem.setIdDocumento(getSnapshots().getSnapshot(position).getId());
//        holder.switchButton.setOnCheckedChangeListener(null);
//        holder.switchButton.setChecked(currentItem.isChecked());

        // Verificar si documentIds no está vacío y si tiene el tamaño suficiente para la posición actual
        if (documentIds != null && !documentIds.isEmpty() && position < documentIds.size()) {
            String idDocumento = documentIds.get(position);
            currentItem.setIdDocumento(idDocumento);
            Log.d("msg-test", "ID del documento: " + idDocumento);
        } else {
            Log.e("msg-test", "La lista de documentIds está vacía o no tiene el tamaño suficiente en la posición " + position);
        }

        /**
        holder.switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentItem.setChecked(isChecked);
            notifyDataSetChanged(); // Notificar al adaptador de cambios

            if (isChecked) {
                // Llamar al método para mostrar la notificación
//                context.notificarImportanceDefault();
            }
        });
         **/

        holder.itemView.setOnClickListener(v -> {
            // Llamar al método replaceFragment y pasar el fragmento de destino
            Fragment verUsuarioSuperadminFragment = new VerUsuarioSuperadminFragment();

            SuperadminUsuarioItem usuario = list.get(position);
            String idDocumento = documentIds.get(position);


            // Opcional: pasar datos al nuevo fragmento
            Bundle args = new Bundle();
            args.putString("nombre", currentItem.getNombre());
            args.putString("rol", currentItem.getRol());
//            args.putString("idUsuario", currentItem.setIdDocumento(documentIds.get(position)));
            args.putString("idUsuario", currentItem.setIdDocumento(idDocumento));
            verUsuarioSuperadminFragment.setArguments(args);
            Log.d("msg-test", currentItem.setIdDocumento(idDocumento));

            // Verificar que context es una instancia de la actividad para llamar el método replaceFragment
            if (context instanceof SuperadminActivity) {
                ((SuperadminActivity) context).replaceFragment(verUsuarioSuperadminFragment);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SuperadminUsuarioViewHolder extends RecyclerView.ViewHolder{
        ImageView perfilFotoItem;
        TextView nombreItem, cargoItem;
        Switch switchButton;
        public SuperadminUsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
//            perfilFotoItem = itemView.findViewById(R.id.perfilFotoItem);
            nombreItem = itemView.findViewById(R.id.nombreItem);
            cargoItem = itemView.findViewById(R.id.cargoItem);
//            switchButton = itemView.findViewById(R.id.switch3);
        }
    }
}
