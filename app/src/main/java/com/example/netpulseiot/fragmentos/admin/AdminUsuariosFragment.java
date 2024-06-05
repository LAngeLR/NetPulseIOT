package com.example.netpulseiot.fragmentos.admin;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.Admin.AdminUsuarioAdapter;
import com.example.netpulseiot.MainActivity;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentAdminUsuariosBinding;
import com.example.netpulseiot.entity.AdminUserItem;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdminUsuariosFragment extends Fragment {

    String canal1 = "importanteDefault";
    FragmentAdminUsuariosBinding binding;

    AdminUsuarioAdapter adapter;
    List<AdminUserItem> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminUsuariosBinding.inflate(inflater, container, false);
        list = new ArrayList<>();
        adapter = new AdminUsuarioAdapter(getContext(), list);

        crearCanalesNotificacion();
        binding.adminUsariosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        SuperadminUsuarioAdapter adapter = new SuperadminUsuarioAdapter(this, list); // Pasar el fragmento como contexto
        binding.adminUsariosRecyclerView.setAdapter(adapter);

        /** Instancia de Firestore **/
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        /** Carga los datos desde Firestore **/
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            AdminUserItem logUsuario = document.toObject(AdminUserItem.class);
                            list.add(logUsuario);
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

    public void crearCanalesNotificacion() {

        NotificationChannel channel = new NotificationChannel(canal1,
                "Canal notificaciones default",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Canal para notificaciones con prioridad default");
        channel.enableVibration(true);

        NotificationManager notificationManager = requireActivity().getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        pedirPermisos();
    }

    public void pedirPermisos() {
        // TIRAMISU = 33
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(requireContext(), POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(requireActivity(), new String[]{POST_NOTIFICATIONS}, 101);
        }
    }

    public void notificarImportanceDefault() {

        //Crear notificación
        //Agregar información a la notificación que luego sea enviada a la actividad que se abre
        Intent intent = new Intent(requireContext(), MainActivity.class);
        intent.putExtra("pid", "4616");
        PendingIntent pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
        //
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), canal1)
                .setSmallIcon(R.drawable.baseline_rocket_launch_24)
                .setContentTitle("Prohibicion de Acceso")
                .setContentText("El usuario Oscar Diaz ha sido deshabilitado")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        Notification notification = builder.build();

        //Lanzar notificación
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());

        if (ActivityCompat.checkSelfPermission(requireContext(), POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(1, notification);
        }
    }
}