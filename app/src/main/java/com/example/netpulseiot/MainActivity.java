package com.example.netpulseiot;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.netpulseiot.databinding.ActivityMainBinding;
import com.example.netpulseiot.fragmentos.superadmin.SuperadminActivity;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    String canal1 = "importanteDefault";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        crearCanalesNotificacion();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void crearCanalesNotificacion() {

        NotificationChannel channel = new NotificationChannel(canal1,
                "Canal notificaciones default",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Canal para notificaciones con prioridad default");
        channel.enableVibration(true);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        pedirPermisos();
    }

    public void pedirPermisos() {
        // TIRAMISU = 33
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{POST_NOTIFICATIONS}, 101);
        }
    }

    public void notificarImportanceDefault(String valor){
        //Crear notificación
        //Agregar información a la notificación que luego sea enviada a la actividad que se abre

        Intent intent = new Intent(this, MainActivity.class); // Declarar intent fuera del switch
        intent.putExtra("pid","4616");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        String mensajeBienvenida = "";

        Intent activityIntent = null;

        switch (valor) {
            case "admin":
                mensajeBienvenida = "Bienvenido Administrador";
                activityIntent = new Intent(this, AdminActivity.class);
                break;
            case "superadmin":
                mensajeBienvenida = "Bienvenido SuperAdministrador";
                activityIntent = new Intent(this, SuperadminActivity.class);
                break;
            case "supervisor":
                mensajeBienvenida = "Bienvenido Supervisor";
                activityIntent = new Intent(this, SupervisorActivity.class);
                break;
            default:
                mensajeBienvenida = "Bienvenido";
        }

        PendingIntent activityPendingIntent = PendingIntent.getActivity(this, 0, activityIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, canal1)
                .setSmallIcon(R.drawable.baseline_rocket_launch_24)
                .setContentTitle("Informe de Ingreso")
                .setContentText(mensajeBienvenida) // Utilizar el mensaje de bienvenida personalizado
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(activityPendingIntent) // Cambiar pendingIntent por activityPendingIntent
                .setAutoCancel(true);

        Notification notification = builder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        if (ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(1, notification);
        }
    }

    public void admin(View view){
        notificarImportanceDefault("admin");
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

    public void superadmin(View view){
        notificarImportanceDefault("superadmin");
        Intent intent = new Intent(this, SuperadminActivity.class);
        startActivity(intent);
    }

    public void supervisor(View view){
        notificarImportanceDefault("supervisor");
        Intent intent = new Intent(this, SupervisorActivity.class);
        startActivity(intent);
    }

}