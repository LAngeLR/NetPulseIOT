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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.netpulseiot.databinding.ActivityMainBinding;
import com.example.netpulseiot.fragmentos.superadmin.SuperadminActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {

    /** Para generar un código aleatorio **/
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 20;


    ActivityMainBinding binding;
    String canal1 = "importanteDefault";
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
            case "Administrador":
                mensajeBienvenida = "Bienvenido Administrador";
                activityIntent = new Intent(this, AdminActivity.class);
                break;
            case "Superadministrador":
                mensajeBienvenida = "Bienvenido SuperAdministrador";
                activityIntent = new Intent(this, SuperadminActivity.class);
                break;
            case "Supervisor":
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
        notificarImportanceDefault("Administrador");
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

    public void superadmin(View view){
        notificarImportanceDefault("Superadministrador");
        Intent intent = new Intent(this, SuperadminActivity.class);
        startActivity(intent);
    }

    public void supervisor(View view){
        notificarImportanceDefault("Supervisor");
        Intent intent = new Intent(this, SupervisorActivity.class);
        startActivity(intent);
    }
    //QR PROBANDO
    public void qrleer(View view){
        Intent intent = new Intent(this, QRActivity.class);
        startActivity(intent);
    }


    /** Generar código aleatorio - Se debe usar en crear usuario **/
    private static String generateRandomCode(SecureRandom random, int length) {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }

    public void ingresarCuenta(View view){
        Intent intent = new Intent(this, AuthActivity2.class);
        startActivity(intent);
    }

    public void registrarCuenta(View view){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }



}