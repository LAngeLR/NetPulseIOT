package com.example.netpulseiot.fragmentos.supervisor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.Admin.AdminSitioAdapter;
import com.example.netpulseiot.Adapter.Supervisor.SupervisorSitioAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentSupervisorSitiosBinding;
import com.example.netpulseiot.entity.AdminSitioItem;
import com.example.netpulseiot.entity.SupervisorSitioItem;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class SupervisorSitiosFragment extends Fragment {

    FragmentSupervisorSitiosBinding binding;
    List<AdminSitioItem> list;
    SupervisorSitioAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSupervisorSitiosBinding.inflate(inflater,container,false);
        list = new ArrayList<>();
        //se setea el adapter con una lista vacía, después se actualiza con lo obtenido de BD
        adapter = new SupervisorSitioAdapter(getContext(),list);
        binding.supervisorSitiosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.supervisorSitiosRecyclerView.setAdapter(adapter);

        //instanciar Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("sitios")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            AdminSitioItem logSitio = document.toObject(AdminSitioItem.class);
                            if (logSitio!=null){
                                //importante para evitar null pointer exception
                                logSitio.setId(document.getId());
                                list.add(logSitio);
                            }
                        }
                        Log.d("msg-test", "Se mandó la lista");
                        /** Notifica al adaptador que los datos han cambiado **/
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.d("msg-test", "Error getting documents: ", task.getException());
                    }
                });

        return binding.getRoot();
    }
}