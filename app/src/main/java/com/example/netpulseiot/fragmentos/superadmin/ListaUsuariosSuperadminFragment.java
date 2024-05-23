package com.example.netpulseiot.fragmentos.superadmin;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.SuperadminUsuarioAdapter;
import com.example.netpulseiot.MainActivity;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentListaUsuariosSuperadminBinding;
import com.example.netpulseiot.entity.SuperadminUsuarioItem;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuariosSuperadminFragment extends Fragment {

    String canal1 = "importanteDefault";
    FragmentListaUsuariosSuperadminBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListaUsuariosSuperadminBinding.inflate(inflater, container, false);
        List<SuperadminUsuarioItem> list = new ArrayList<>();
        for (int i = 0; i <= 12; i++) {
            list.add(new SuperadminUsuarioItem("Oscar Diaz", "Superadmin", R.drawable.fotoperfil_u2));
        }
        crearCanalesNotificacion();
        binding.superadminUsuariosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SuperadminUsuarioAdapter adapter = new SuperadminUsuarioAdapter(this, list); // Pasar el fragmento como contexto
        binding.superadminUsuariosRecyclerView.setAdapter(adapter);

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
