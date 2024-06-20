package com.example.netpulseiot.Adapter.Admin;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.AdminActivity;
import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.EquipoItem;
import com.example.netpulseiot.fragmentos.admin.AdminVerEquipoFragment;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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

        holder.itemView.setOnClickListener(v -> {
            Fragment adminVerEquipoFragment = new AdminVerEquipoFragment();
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            firestore.collection("equipos")
                    .document(item.getId())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                EquipoItem equipoItem1 = document.toObject(EquipoItem.class);
                                if (equipoItem1 != null) {

                                    Bundle args = new Bundle();
                                    args.putString("id", equipoItem1.getId());
                                    args.putString("des", equipoItem1.getDescripcion());
                                    args.putString("mod", equipoItem1.getModelo());
                                    args.putString("mar", equipoItem1.getMarca());
                                    args.putString("num", equipoItem1.getNumSerie());
                                    args.putString("sku", equipoItem1.getSku());
                                    args.putString("tip", equipoItem1.getTipoEquipo());

                                    adminVerEquipoFragment.setArguments(args);

                                    if (context instanceof AdminActivity) {
                                        ((AdminActivity) context).replaceFragment(adminVerEquipoFragment);
                                    }

                                }
                            } else {
                                Log.d("msg-test", "No such document");
                            }
                        } else {
                            Log.d("msg-test", "get failed with ", task.getException());
                        }
                    });
        });
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
