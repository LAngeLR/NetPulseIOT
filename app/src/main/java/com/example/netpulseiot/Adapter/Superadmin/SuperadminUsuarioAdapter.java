package com.example.netpulseiot.Adapter.Superadmin;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.netpulseiot.R;
import com.example.netpulseiot.dto.UsuarioDTO;
import com.example.netpulseiot.entity.AdminUserItem;
import com.example.netpulseiot.entity.SuperadminUsuarioItem;
import com.example.netpulseiot.fragmentos.superadmin.SuperadminActivity;
import com.example.netpulseiot.fragmentos.superadmin.VerUsuarioSuperadminFragment;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class SuperadminUsuarioAdapter extends RecyclerView.Adapter<SuperadminUsuarioAdapter.SuperadminUsuarioViewHolder> {

    Context context; // Cambio de tipo de Context a Fragment
    List<SuperadminUsuarioItem> list;

    public SuperadminUsuarioAdapter(Context context, List<SuperadminUsuarioItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SuperadminUsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_superadmin_usuarios,parent,false);
        return new SuperadminUsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperadminUsuarioViewHolder holder, int position) {
        SuperadminUsuarioItem currentItem = list.get(position);

        String nombreCompleto = list.get(position).getNombre() + " " + list.get(position).getApellido();
        holder.nombreItem.setText(nombreCompleto);
        holder.cargoItem.setText(currentItem.getRol());

//        holder.switchButton.setOnCheckedChangeListener(null);
//        holder.switchButton.setChecked(currentItem.isChecked());

        /**
        holder.switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentItem.setChecked(isChecked);
            notifyDataSetChanged(); // Notificar al adaptador de cambios

            if (isChecked) {
                // Llamar al método para mostrar la notificación
//                context.notificarImportanceDefault();
            }
        });
         **/

        holder.itemView.setOnClickListener(v -> {
            /* Llamar al método replaceFragment y pasar el fragmento de destino */
            Fragment verUsuarioSuperadminFragment = new VerUsuarioSuperadminFragment();
            SuperadminUsuarioItem usuario = list.get(position);

            /* Acceder a FireStore */

            /** Instancia de Firestore **/
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("usuarios")
                    .document(usuario.getId())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (((DocumentSnapshot) document).exists()) {
                                AdminUserItem usuario1 = document.toObject(AdminUserItem.class);
                                if(usuario != null){
                                    /**
                                    Log.d("msg-test", "Nombre: " + usuario1.getNombre());
                                    Log.d("msg-test", "Apellido: " + usuario1.getApellido());
                                    Log.d("msg-test", "rol: " + usuario1.getRol());
                                    Log.d("msg-test", "Correo: " + usuario1.getCorreo());
                                    Log.d("msg-test", "Nombre: " + usuario1.getCelular());
                                    Log.d("msg-test", "Nombre: " + usuario1.getDireccion());
                                    **/

                                    // Pasar los datos al fragmento destino
                                    Bundle args = new Bundle();
                                    args.putString("nombre", usuario1.getNombre());
                                    args.putString("apellido", usuario1.getApellido());
                                    args.putString("rol", usuario1.getRol());
                                    args.putString("correo", usuario1.getCorreo());
                                    args.putString("telefono", String.valueOf(usuario1.getCelular()));
                                    args.putString("direccion", usuario1.getDireccion());

                                    verUsuarioSuperadminFragment.setArguments(args);

                                    // Reemplazar el fragmento
                                    if (context instanceof SuperadminActivity) {
                                        ((SuperadminActivity) context).replaceFragment(verUsuarioSuperadminFragment);
                                    }

                                }
                            } else {
                                Log.d("nsg-test", "No such document");
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

    public class SuperadminUsuarioViewHolder extends RecyclerView.ViewHolder{
        ImageView perfilFotoItem;
        TextView nombreItem, cargoItem;
        Switch switchButton;
        public SuperadminUsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
//            perfilFotoItem = itemView.findViewById(R.id.perfilFotoItem);
            nombreItem = itemView.findViewById(R.id.nombreItem);
            cargoItem = itemView.findViewById(R.id.cargoItem);
//            switchButton = itemView.findViewById(R.id.switch3);
        }
    }
}
