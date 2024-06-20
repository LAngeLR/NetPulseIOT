package com.example.netpulseiot.Adapter.Admin;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.AdminActivity;
import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.UserItem;
import com.example.netpulseiot.fragmentos.admin.EditarUsuarioAdminFragment;
import com.example.netpulseiot.fragmentos.admin.VerUsuarioAdminFragment;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;



public class AdminUsuarioAdapter extends RecyclerView.Adapter<AdminUsuarioAdapter.AdminUsuarioViewHolder> {

    Context context;
    List<UserItem> list;

    public AdminUsuarioAdapter(Context context, List<UserItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdminUsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_usuarios, parent, false);
        return new AdminUsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminUsuarioViewHolder holder, int position) {
        UserItem currentItem = list.get(position);

        String nombreCompleto = list.get(position).getNombre() + " " + list.get(position).getApellido();
        holder.nombreItem.setText(nombreCompleto);
        holder.cargoItem.setText(currentItem.getRol());

        holder.itemView.setOnClickListener(v -> {
            Fragment verUsuarioAdminFragment = new VerUsuarioAdminFragment();
            Fragment editarUsuarioAdminFragment = new EditarUsuarioAdminFragment();
            UserItem usuario = list.get(position);

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("usuarios")
                    .document(usuario.getId())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (((DocumentSnapshot) document).exists()) {
                                UserItem usuario1 = document.toObject(UserItem.class);
                                if (usuario != null) {
                                    /**
                                    Log.d("msg-test", "Nombre: " + usuario1.getNombre());
                                    Log.d("msg-test", "Apellido: " + usuario1.getApellido());
                                    Log.d("msg-test", "Rol: " + usuario1.getRol());
                                    Log.d("msg-test", "Correo: " + usuario1.getCorreo());
                                    Log.d("msg-test", "Celular: " + usuario1.getCelular());
                                    Log.d("msg-test", "Direccion: " + usuario1.getDireccion());
                                     **/

                                    Bundle args = new Bundle();
                                    args.putString("nombre", usuario1.getNombre());
                                    args.putString("apellido", usuario1.getApellido());
                                    args.putString("rol", usuario1.getRol());
                                    args.putString("correo", usuario1.getCorreo());
                                    args.putString("telefono", String.valueOf(usuario1.getCelular()));
                                    args.putString("direccion", usuario1.getDireccion());

                                    verUsuarioAdminFragment.setArguments(args);
                                    editarUsuarioAdminFragment.setArguments(args);

                                    if (context instanceof AdminActivity) {
                                        ((AdminActivity) context).replaceFragment(verUsuarioAdminFragment);
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

    public class AdminUsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView nombreItem, cargoItem;
        Switch switchButton;
        public AdminUsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreItem = itemView.findViewById(R.id.nombreItem);
            cargoItem = itemView.findViewById(R.id.cargoItem);
        }
    }
}
