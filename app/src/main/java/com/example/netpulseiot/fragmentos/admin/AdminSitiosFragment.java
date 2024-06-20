package com.example.netpulseiot.fragmentos.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.Admin.AdminSitioAdapter;
import com.example.netpulseiot.Activity.AdminNuevoSitio;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentAdminSitiosBinding;
import com.example.netpulseiot.entity.AdminSitioItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class AdminSitiosFragment extends Fragment {

    FragmentAdminSitiosBinding binding;
    List<AdminSitioItem> list;
    AdminSitioAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminSitiosBinding.inflate(inflater,container,false);
        list = new ArrayList<>();
        //se setea el adapter con una lista vacía, después se actualiza con lo obtenido de BD
        adapter = new AdminSitioAdapter(getContext(),list);
        binding.adminSitiosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.adminSitiosRecyclerView.setAdapter(adapter);

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

        FloatingActionButton fabAddUser = binding.getRoot().findViewById(R.id.fabAddUser);
        fabAddUser.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AdminNuevoSitio.class); // Reemplaza con tu actividad
            startActivity(intent);
        });

        return binding.getRoot();
    }
}