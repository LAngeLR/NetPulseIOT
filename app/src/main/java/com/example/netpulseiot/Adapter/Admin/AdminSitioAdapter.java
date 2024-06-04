package com.example.netpulseiot.Adapter.Admin;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.Adapter.Supervisor.SupervisorSitioAdapter;
import com.example.netpulseiot.AdminActivity;
import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.AdminSitioItem;
import com.example.netpulseiot.fragmentos.admin.AdminVerSitioFragment;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.List;

public class AdminSitioAdapter extends RecyclerView.Adapter<AdminSitioAdapter.adminSitioViewHolder>{

    Context context;
    List<AdminSitioItem> list;

    public AdminSitioAdapter(Context context, List<AdminSitioItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public adminSitioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //el layout se dejó con el de supervisor porque eso sí no cambia absolutamente nada
        View view = LayoutInflater.from(context).inflate(R.layout.item_supervisor_sitio,parent,false);
        return new AdminSitioAdapter.adminSitioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adminSitioViewHolder holder, int position) {
        AdminSitioItem currentItem = list.get(position);

        holder.nombreItem.setText(currentItem.getNombre());
        holder.provinciaItem.setText(currentItem.getProvincia());
        holder.distritoItem.setText(currentItem.getDistrito());
        holder.tipoItem.setText(currentItem.getTipoSitio());

        //linkeo a ver info
        holder.itemView.setOnClickListener(v -> {
            Fragment adminVerSitioFragment = new AdminVerSitioFragment();
            AdminSitioItem sitioItem = list.get(position);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("sitios")
                    .document(currentItem.getId())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if(documentSnapshot.exists()){
                                AdminSitioItem sitioItem1 = documentSnapshot.toObject(AdminSitioItem.class);
                                if (sitioItem1!=null){

                                    Bundle args = new Bundle();
                                    args.putString("id", currentItem.getId());
                                    args.putString("nombre",sitioItem1.getNombre());
                                    args.putString("departamento",sitioItem1.getDepartamento());
                                    args.putString("provincia",sitioItem1.getProvincia());
                                    args.putString("distrito",sitioItem1.getDistrito());
                                    args.putString("tipoSitio",sitioItem1.getTipoSitio());
                                    args.putString("tipoZona",sitioItem1.getTipoZona());
                                    args.putString("ubigeo",sitioItem1.getUbigeo());
                                    GeoPoint geoPoint = sitioItem1.getGeolocalizacion();
                                    args.putDouble("latitud",geoPoint.getLatitude());
                                    args.putDouble("longitud",geoPoint.getLongitude());

                                    adminVerSitioFragment.setArguments(args);

                                    if (context instanceof AdminActivity) {
                                        ((AdminActivity) context).replaceFragment(adminVerSitioFragment);
                                    }
                                }
                            } else {
                                Log.d("msg-test", "No such document");
                            }
                        }else {
                            Log.d("msg-test", "get failed with ", task.getException());
                        }
                    });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class adminSitioViewHolder extends RecyclerView.ViewHolder{
        TextView nombreItem, provinciaItem, distritoItem, tipoItem ;

        public adminSitioViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreItem = itemView.findViewById(R.id.nombreItem);
            provinciaItem = itemView.findViewById(R.id.provinciaItem);
            distritoItem = itemView.findViewById(R.id.distritoItem);
            tipoItem = itemView.findViewById(R.id.tipoItem);
        }
    }
}
