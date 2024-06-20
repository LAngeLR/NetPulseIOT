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

import com.example.netpulseiot.R;
import com.example.netpulseiot.Activity.SupervisorActivity;
import com.example.netpulseiot.entity.SitioItem;
import com.example.netpulseiot.fragmentos.supervisor.SupervisorVerSitioFragment;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.List;

public class SupervisorSitioAdapter extends RecyclerView.Adapter<SupervisorSitioAdapter.supervisorSitioViewHolder>{

    private Context context;
    List<SitioItem> list;
    public SupervisorSitioAdapter(Context context, List<SitioItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SupervisorSitioAdapter.supervisorSitioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sitio,parent,false);
        return new SupervisorSitioAdapter.supervisorSitioViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SupervisorSitioAdapter.supervisorSitioViewHolder holder, int position) {
        SitioItem currentItem = list.get(position);
        
        holder.nombreItem.setText(currentItem.getNombre());
        holder.provinciaItem.setText(currentItem.getProvincia());
        holder.distritoItem.setText(currentItem.getDistrito());
        holder.tipoItem.setText(currentItem.getTipoSitio());

        //linkeo a ver info
        holder.itemView.setOnClickListener(v -> {
            Fragment supervisorVerSitioFragment = new SupervisorVerSitioFragment();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("sitios")
                    .document(currentItem.getId())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if(documentSnapshot.exists()){
                                SitioItem sitioItem1 = documentSnapshot.toObject(SitioItem.class);
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

                                    supervisorVerSitioFragment.setArguments(args);

                                    if (context instanceof SupervisorActivity) {
                                        ((SupervisorActivity) context).replaceFragment(supervisorVerSitioFragment);
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
    //View Holder
    public class supervisorSitioViewHolder extends RecyclerView.ViewHolder{
        TextView nombreItem, provinciaItem, distritoItem, tipoItem ;

        public supervisorSitioViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreItem = itemView.findViewById(R.id.nombreItem);
            provinciaItem = itemView.findViewById(R.id.provinciaItem);
            distritoItem = itemView.findViewById(R.id.distritoItem);
            tipoItem = itemView.findViewById(R.id.tipoItem);
        }
    }
}
