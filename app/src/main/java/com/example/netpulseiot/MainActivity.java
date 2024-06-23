package com.example.netpulseiot;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.netpulseiot.databinding.ActivityMainBinding;
import com.example.netpulseiot.fragmentos.superadmin.SuperadminActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 20;

    private ActivityMainBinding binding;
    private String canal1 = "importanteDefault";
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Crear canales de notificación al inicio de la actividad
        crearCanalesNotificacion();

        // Ejemplo: Botón para iniciar sesión (puedes modificar según tu diseño)
        binding.ingresarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AuthActivity2.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            // Hay un usuario autenticado, obtener rol y redirigir según corresponda
            db = FirebaseFirestore.getInstance();
            db.collection("usuarios")
                    .document(firebaseUser.getUid())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document != null && document.exists()) {
                                    String rol = document.getString("rol");
                                    if (rol != null) {
                                        switch (rol) {
                                            case "Administrador":
                                                notificarImportanceDefault("Administrador");
                                                startActivityWithClearTask(AdminActivity.class);
                                                break;
                                            case "Supervisor":
                                                notificarImportanceDefault("Supervisor");
                                                startActivityWithClearTask(SupervisorActivity.class);
                                                break;
                                            case "Superadministrador":
                                                notificarImportanceDefault("Superadministrador");
                                                startActivityWithClearTask(SuperadminActivity.class);
                                                break;
                                            default:
                                                Toast.makeText(MainActivity.this, "Rol no reconocido.", Toast.LENGTH_SHORT).show();
                                                break;
                                        }
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "Documento de usuario no encontrado.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Error al obtener el documento del usuario.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void crearCanalesNotificacion() {
        // Crear canal de notificación
        NotificationChannel channel = new NotificationChannel(canal1,
                "Canal notificaciones default",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Canal para notificaciones con prioridad default");
        channel.enableVibration(true);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        // Pedir permisos si no están concedidos
        pedirPermisos();
    }

    private void pedirPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 101);
        }
    }

    private void notificarImportanceDefault(String valor) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent activityPendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        String mensajeBienvenida = "";
        switch (valor) {
            case "Administrador":
                mensajeBienvenida = "Bienvenido Administrador";
                break;
            case "Superadministrador":
                mensajeBienvenida = "Bienvenido SuperAdministrador";
                break;
            case "Supervisor":
                mensajeBienvenida = "Bienvenido Supervisor";
                break;
            default:
                mensajeBienvenida = "Bienvenido";
                break;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, canal1)
                .setSmallIcon(R.drawable.baseline_rocket_launch_24)
                .setContentTitle("Informe de Ingreso")
                .setContentText(mensajeBienvenida)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(activityPendingIntent)
                .setAutoCancel(true);

        Notification notification = builder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(1, notification);
        }
    }

    private void startActivityWithClearTask(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Finaliza la actividad actual para evitar que el usuario vuelva atrás
    }

    public void admin(View view) {
        notificarImportanceDefault("Administrador");
        startActivity(new Intent(this, AdminActivity.class));
    }

    public void superadmin(View view) {
        notificarImportanceDefault("Superadministrador");
        startActivity(new Intent(this, SuperadminActivity.class));
    }

    public void supervisor(View view) {
        notificarImportanceDefault("Supervisor");
        startActivity(new Intent(this, SupervisorActivity.class));
    }

    public void qrleer(View view) {
        startActivity(new Intent(this, QRActivity.class));
    }

    public void mensajeria(View view) {
        startActivity(new Intent(this, IniciandoActivity.class));
    }

    private static String generateRandomCode(SecureRandom random, int length) {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }
}
