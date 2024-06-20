package com.example.netpulseiot.fragmentos.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.netpulseiot.AdminActivity;
import com.example.netpulseiot.Adapter.Admin.AdminAsignarSupervisorAdapter;
import com.example.netpulseiot.databinding.FragmentAdminAsignarSupervisorBinding;
import com.example.netpulseiot.entity.SuperadminUsuarioItem;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class AdminAsignarSupervisorFragment extends Fragment {
    FragmentAdminAsignarSupervisorBinding binding;
    List<SuperadminUsuarioItem> list;
    AdminAsignarSupervisorAdapter adapter;
    String sitioId;
    FirebaseFirestore db;
    private SuperadminUsuarioItem superadminUsuarioItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminAsignarSupervisorBinding.inflate(inflater,container,false);
        //llenar de info de supervisores al adapter
        list = new ArrayList<>();
        adapter = new AdminAsignarSupervisorAdapter(getContext(),list);
        binding.adminAsignarSupervisorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.adminAsignarSupervisorRecyclerView.setAdapter(adapter);

        //instanciar Firestore
        db = FirebaseFirestore.getInstance();
        db.collection("usuarios")
                .whereEqualTo("rol", "Supervisor") //filtrado para que solo muestre supervisores (U)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            SuperadminUsuarioItem logUser = document.toObject(SuperadminUsuarioItem.class);
                            if (logUser!=null){
                                //importante para evitar null pointer exception
                                logUser.setId(document.getId());
                                list.add(logUser);
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
            binding.supervisorElegidoTextView.setText(item.getNombre());
            superadminUsuarioItem = item;
        });

        //recepeción del sitio al que se le asignará el supervisor
        Bundle args = getArguments();
        if (args != null){
            sitioId = args.getString("sitioId");
        }


        //cancelar Guardado
        binding.cancelar.setOnClickListener(view -> {
            Fragment adminSitiosFragment =new AdminSitiosFragment();
            if (getContext() instanceof AdminActivity){
                ((AdminActivity) getContext()).replaceFragment(adminSitiosFragment);
            }
        });

        binding.guardar.setOnClickListener(v -> {
            db = FirebaseFirestore.getInstance();
            db.collection("sitios")
                    .document(sitioId)
                    .update("supervisor",superadminUsuarioItem.getId())
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(getContext(), "Supervisor asignado", Toast.LENGTH_SHORT).show();
                        Fragment adminSitiosFragment =new AdminSitiosFragment();
                        if (getContext() instanceof AdminActivity){
                            ((AdminActivity) getContext()).replaceFragment(adminSitiosFragment);
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Error al actualizar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });;


        });



        return binding.getRoot();
    }
}