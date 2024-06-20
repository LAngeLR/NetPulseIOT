package com.example.netpulseiot.fragmentos.supervisor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.netpulseiot.Activity.AdminActivity;
import com.example.netpulseiot.Activity.SupervisorActivity;
import com.example.netpulseiot.Adapter.Admin.AdminAsignarSupervisorAdapter;
import com.example.netpulseiot.Adapter.Supervisor.SupervisorAgregarEquipoAdapter;
import com.example.netpulseiot.databinding.FragmentSupervisorAgregarEquipoBinding;
import com.example.netpulseiot.entity.EquipoItem;
import com.example.netpulseiot.entity.SuperadminUsuarioItem;
import com.example.netpulseiot.fragmentos.admin.AdminAsignarSupervisorFragment;
import com.example.netpulseiot.fragmentos.admin.AdminSitiosFragment;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class SupervisorAgregarEquipoFragment extends Fragment {
    FragmentSupervisorAgregarEquipoBinding binding;
    List<EquipoItem> list;
    String sitioId;
    SupervisorAgregarEquipoAdapter adapter;
    FirebaseFirestore db;
    private EquipoItem equipoItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSupervisorAgregarEquipoBinding.inflate(inflater,container,false);
        //llenar de info de equipos al adapter 
        list = new ArrayList<>();
        adapter = new SupervisorAgregarEquipoAdapter(getContext(),list);
        binding.supervisorAgregarEquiposRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.supervisorAgregarEquiposRecyclerView.setAdapter(adapter);

        //instanciar Firestore
        db = FirebaseFirestore.getInstance();
        db.collection("equipos")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            EquipoItem logEquipo = document.toObject(EquipoItem.class);
                            if (logEquipo!=null){
                                //importante para evitar null pointer exception
                                logEquipo.setId(document.getId());
                                list.add(logEquipo);
                            }
                        }
                        Log.d("msg-test", "Se mandó la lista");
                        /** Notifica al adaptador que los datos han cambiado **/
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.d("msg-test", "Error getting documents: ", task.getException());
                    }
                });
        // Configurar el listener para el adaptador
        adapter.setOnItemClickListener(item -> {
            binding.equiposElegidoTextView.setText(item.getModelo());
            equipoItem = item;
        });
        
        
        //recepeción del sitio al que se le asignará el supervisor
        Bundle args = getArguments();
        if (args != null){
            sitioId = args.getString("sitioId");
        }
        binding.textView.setText(sitioId);

        //cancelar Guardado
        binding.cancelar.setOnClickListener(view -> {
            Fragment supervisorSitiosFragment =new SupervisorSitiosFragment();
            if (getContext() instanceof SupervisorActivity){
                ((SupervisorActivity) getContext()).replaceFragment(supervisorSitiosFragment);
            }
        });

        binding.guardar.setOnClickListener(v -> {
            db = FirebaseFirestore.getInstance();
            db.collection("sitios")
                    .document(sitioId)
                    .update("equipos", FieldValue.arrayUnion(equipoItem.getId()))
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(getContext(), "equipo agregado", Toast.LENGTH_SHORT).show();
                        Fragment supervisorSitiosFragment =new SupervisorSitiosFragment();
                        if (getContext() instanceof SupervisorActivity){
                            ((SupervisorActivity) getContext()).replaceFragment(supervisorSitiosFragment);
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Error al actualizar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });;


        });



        return binding.getRoot();
    }
}