package com.example.netpulseiot.fragmentos.superadmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.Superadmin.SuperadminLogsAdapter;
import com.example.netpulseiot.databinding.FragmentHistorialSuperadminBinding;
import com.example.netpulseiot.entity.SuperadminLogsItem;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class HistorialSuperadminFragment extends Fragment {

    FragmentHistorialSuperadminBinding binding;
    SuperadminLogsAdapter adapter;
    List<SuperadminLogsItem> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistorialSuperadminBinding.inflate(inflater,container,false);
        list = new ArrayList<>();
        adapter = new SuperadminLogsAdapter(getContext(), list);

        binding.superadminLogsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.superadminLogsRecyclerView.setAdapter(adapter);

        /** Instancia de Firestore **/
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        /** Carga los datos desde Firestore **/
        db.collection("logs")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            SuperadminLogsItem logItem = document.toObject(SuperadminLogsItem.class);
                            list.add(logItem);
                        }
                        /** Notifica al adaptador que los datos han cambiado **/
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.d("msg-test", "Error getting documents: ", task.getException());
                    }
                });


        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}