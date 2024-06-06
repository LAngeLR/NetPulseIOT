package com.example.netpulseiot.fragmentos.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.Admin.AdminEquipoAdapter;
import com.example.netpulseiot.Adapter.Admin.AdminSitioAdapter;
import com.example.netpulseiot.Adapter.Supervisor.SupervisorEquipoAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentAdminEquiposBinding;
import com.example.netpulseiot.entity.AdminSitioItem;
import com.example.netpulseiot.entity.EquipoItem;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdminEquiposFragment extends Fragment {

    FragmentAdminEquiposBinding binding;
    List<EquipoItem> list;
    AdminEquipoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminEquiposBinding.inflate(inflater,container,false);
        list = new ArrayList<>();
        //se setea el adapter con una lista vacía, después se actualiza con lo obtenido de BD
        adapter = new AdminEquipoAdapter(getContext(),list);

        binding.adminEquiposRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.adminEquiposRecyclerView.setAdapter(adapter);

        //instanciar Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("equipos")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            EquipoItem logSitio = document.toObject(EquipoItem.class);
                            list.add(logSitio);
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