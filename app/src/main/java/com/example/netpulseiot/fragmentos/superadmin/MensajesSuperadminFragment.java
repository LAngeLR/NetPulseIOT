package com.example.netpulseiot.fragmentos.superadmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.Adapter.Superadmin.SuperadminMensajeAdapter;
import com.example.netpulseiot.databinding.FragmentMensajesSuperadminBinding;
import com.example.netpulseiot.entity.ChatItem;
import com.example.netpulseiot.entity.UserItem;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MensajesSuperadminFragment extends Fragment {

    private FragmentMensajesSuperadminBinding binding;
    private List<ChatItem> chatList;
    private SuperadminMensajeAdapter adapter;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMensajesSuperadminBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        // Inicializar RecyclerView
        RecyclerView recyclerView = binding.superadminMensajesRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatList = new ArrayList<>();
        adapter = new SuperadminMensajeAdapter(getContext(), chatList);
        recyclerView.setAdapter(adapter);

        // Cargar datos desde Firestore
        loadChatData();

        return root;
    }

    private void loadChatData() {
        // Consultar la colección de chats en Firestore
        db.collection("chats")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    chatList.clear(); // Limpiar la lista actual

                    // Iterar sobre los documentos de chats
                    for (ChatItem chatItem : queryDocumentSnapshots.toObjects(ChatItem.class)) {
                        // Agregar cada chat a la lista
                        chatList.add(chatItem);

                        // Obtener nombre del segundo participante si existen al menos dos participantes
                        if (chatItem.getParticipantes().size() > 1) {
                            String secondParticipantId = chatItem.getParticipantes().get(1); // Segundo participante en el array
                            loadUserNameForParticipant(chatItem, secondParticipantId);
                        }
                    }

                    // Notificar al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    // Manejar errores de consulta
                });
    }

    private void loadUserNameForParticipant(ChatItem chatItem, String userId) {
        // Consultar el usuario correspondiente al userId
        db.collection("usuarios").document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        UserItem user = documentSnapshot.toObject(UserItem.class);
                        if (user != null) {
                            // Actualizar el nombre del usuario en el chatItem
                            chatItem.setIdUsuarioUltimo(user.getNombre() + " " + user.getApellido());
                            // Notificar al adaptador que los datos han cambiado
                            adapter.notifyDataSetChanged();
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    // Manejar errores si la obtención del usuario falla
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
