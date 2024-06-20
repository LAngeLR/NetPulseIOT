package com.example.netpulseiot.fragmentos.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.netpulseiot.databinding.FragmentEditarUsuarioAdminBinding;
import com.google.firebase.firestore.FirebaseFirestore;
public class EditarUsuarioAdminFragment extends Fragment {
    FragmentEditarUsuarioAdminBinding binding;
    String nombre = null, apellido = null, rol = null, correo = null, telefono = null, direccion = null, idUsuario = null;

    //    Integer celularInt = Integer.parseInt(telefono);
    String idUsuarioEditar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentEditarUsuarioAdminBinding.inflate(inflater,container,false);
        View rootView = binding.getRoot();

        Bundle args = getArguments();
        if (args != null) {
            nombre = args.getString("nombre");
            apellido = args.getString("apellido");
            rol = args.getString("rol");
            correo = args.getString("correo");
            telefono = args.getString("telefono");
            direccion = args.getString("direccion");
            idUsuario = args.getString("idUsuario");
            Log.d("msg-test","El ID obtenido fue: " + idUsuario);

            idUsuarioEditar = idUsuario;


            // Actualiza las vistas con los datos del usuario
            binding.textView101.setText(nombre != null ? nombre : "No definido");
            binding.textView103.setText(apellido != null ? apellido : "No definido");
            binding.textView105.setText(rol != null ? rol : "No definido");
            binding.textView107.setText(correo != null ? correo : "No definido");
            binding.telefonoUserAdmin.setText(telefono != null ? telefono : "No definido");
            binding.textView111.setText(direccion != null ? direccion : "No definido");

        } else {
            idUsuarioEditar = null;
        }

        binding.guardarEditarAdmin.setOnClickListener(view -> {

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            int MandarCelarAdmin = Integer.parseInt(binding.telefonoUserAdmin.getText().toString());

            db.collection("usuarios")
                    .document(idUsuarioEditar)
                    .update("celular", MandarCelarAdmin)
                    .addOnSuccessListener( unused -> {
                        Log.d("msg-test","LA DATA SE ACTULIZO CORRECTAMENTE WIIII");
                        Toast.makeText(requireContext(), "Usuario '" + nombre + " " + apellido + "' se actualizo", Toast.LENGTH_SHORT).show();

                        // Retroceder al fragmento anterior
                        if (getFragmentManager() != null) {
                            getFragmentManager().popBackStack();
                        }

                    })
                    .addOnFailureListener(e -> e.printStackTrace());


        });

        binding.cancelarEditarAdmin.setOnClickListener(view -> {
            // Retroceder al fragmento anterior
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
                Toast.makeText(requireContext(), "Acción de editar cancelada", Toast.LENGTH_SHORT).show();

            }
        });
        return rootView;

    }

}
