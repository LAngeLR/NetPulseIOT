package com.example.netpulseiot.Adapter.Superadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.R;
import com.example.netpulseiot.entity.ChatItem;
import com.example.netpulseiot.entity.UserItem;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class SuperadminMensajeAdapter extends RecyclerView.Adapter<SuperadminMensajeAdapter.SuperadminMensajeViewHolder> {

    private Context context;
    private List<ChatItem> list;
    private FirebaseFirestore db;

    public SuperadminMensajeAdapter(Context context, List<ChatItem> list) {
        this.context = context;
        this.list = list;
        this.db = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public SuperadminMensajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_superadmin_mensaje, parent, false);
        return new SuperadminMensajeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperadminMensajeViewHolder holder, int position) {
        ChatItem currentItem = list.get(position);

        // Mostrar el nombre del segundo participante en el chat
        if (currentItem.getParticipantes().size() > 1) {
            String secondParticipantId = currentItem.getParticipantes().get(1); // Segundo participante en el array

            // Realizar la consulta para obtener el nombre del usuario
            db.collection("usuarios").document(secondParticipantId)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            UserItem user = documentSnapshot.toObject(UserItem.class);
                            if (user != null) {
                                // Actualizar el nombre del usuario en el ViewHolder
                                holder.nombreUsuario.setText(user.getNombre() + " " + user.getApellido());
                            } else {
                                holder.nombreUsuario.setText("Usuario Desconocido");
                            }
                        } else {
                            holder.nombreUsuario.setText("Usuario Desconocido");
                        }
                    })
                    .addOnFailureListener(e -> {
                        holder.nombreUsuario.setText("Error al cargar el nombre");
                        // Manejar errores si la obtención del usuario falla
                    });
        }

        // Mostrar la fecha del último mensaje
        if (currentItem.getTiempoMensaje() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            String formattedDate = dateFormat.format(currentItem.getTiempoMensaje().toDate());
            holder.fechaItem.setText(formattedDate);
        }

        // Mostrar el mensaje último
        holder.mensajeItem.setText(currentItem.getMensajeUltimo());
        holder.imageView.setImageResource(R.drawable.fotoperfil_u2);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SuperadminMensajeViewHolder extends RecyclerView.ViewHolder {
        TextView nombreUsuario, fechaItem, mensajeItem;
        ImageView imageView;

        public SuperadminMensajeViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreUsuario = itemView.findViewById(R.id.nombreItem);
            fechaItem = itemView.findViewById(R.id.fechaItem);
            mensajeItem = itemView.findViewById(R.id.mensajeItem);
            imageView = itemView.findViewById(R.id.fotoItem);
        }
    }
}
