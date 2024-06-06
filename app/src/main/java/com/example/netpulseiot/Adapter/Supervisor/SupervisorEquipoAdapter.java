package com.example.netpulseiot.Adapter.Supervisor;

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
import com.example.netpulseiot.SupervisorActivity;
import com.example.netpulseiot.entity.EquipoItem;
import com.example.netpulseiot.fragmentos.admin.AdminVerEquipoFragment;
import com.example.netpulseiot.fragmentos.supervisor.SupervisorVerEquipoFragment;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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

        holder.itemView.setOnClickListener(v -> {
            Fragment supervisorVerEquipoFragment = new SupervisorVerEquipoFragment();
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

                                    supervisorVerEquipoFragment.setArguments(args);

                                    if (context instanceof SupervisorActivity) {
                                        ((SupervisorActivity) context).replaceFragment(supervisorVerEquipoFragment);
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
    //View Holder
    public class supervisorEquipoViewHolder extends RecyclerView.ViewHolder{
        TextView modeloItem;

        public supervisorEquipoViewHolder(@NonNull View itemView) {
            super(itemView);
            modeloItem = itemView.findViewById(R.id.nombreItem); //va el modelo pero en el layout el id se llama nombre
        }
    }
}
