package com.example.netpulseiot.fragmentos.superadmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netpulseiot.Adapter.Superadmin.SuperadminUsuarioAdapter;
import com.example.netpulseiot.entity.UserItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentVerUsuarioSuperadminBinding;
//import com.example.netpulseiot.databinding.FragmentSuperadminVerUsuarioBinding;
import com.example.netpulseiot.dto.UsuarioDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class VerUsuarioSuperadminFragment extends Fragment {

    FragmentVerUsuarioSuperadminBinding binding;
//    FragmentSuperadminVerUsuario binding;
    String nombre = null, apellido = null, rol = null, correo = null, telefono = null, direccion = null, idUsuario = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentVerUsuarioSuperadminBinding.inflate(inflater,container,false);
        View rootView = binding.getRoot();

        String idUsuarioEditar;

        Bundle args = getArguments();
        if (args != null) {
             nombre = args.getString("nombre");
             apellido = args.getString("apellido");
             rol = args.getString("rol");
             correo = args.getString("correo");
             telefono = args.getString("telefono");
             direccion = args.getString("direccion");
             idUsuario = args.getString("idUsuario");

            idUsuarioEditar = idUsuario;

            // Actualiza las vistas con los datos del usuario
            binding.nombreUser.setText(nombre != null ? nombre : "No definido");
            binding.apellidoUser.setText(apellido != null ? apellido : "No definido");
            binding.rolUser.setText(rol != null ? rol : "No definido");
            binding.correoUser.setText(correo != null ? correo : "No definido");
            binding.telefonoUser.setText(telefono != null ? telefono : "No definido");
            binding.direccionUser.setText(direccion != null ? direccion : "No definido");
        }


        FloatingActionButton fab = rootView.findViewById(R.id.floating_action_button);
        /** Boton de editado **/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("msg-test", "FUNCIONA EL BOTON FLOTANTE");

                // Crear el nuevo fragmento y configurar los argumentos
                EditarUsuarioSuperadminFragment editarFragment = new EditarUsuarioSuperadminFragment();
                Fragment editarUsuarioSuperadminFragment = new EditarUsuarioSuperadminFragment();


                // Pasar los datos al fragmento destino
                Bundle args = new Bundle();
                args.putString("nombre", nombre);
                args.putString("apellido", apellido);
                args.putString("rol", rol);
                args.putString("correo", correo);
                args.putString("telefono", telefono);
                args.putString("direccion", direccion);
                args.putString("idUsuario", idUsuario);

                editarUsuarioSuperadminFragment.setArguments(args);

                // Reemplazar el fragmento actual con el fragmento de edición
                if (getActivity() instanceof SuperadminActivity) {
                    ((SuperadminActivity) getActivity()).replaceFragment(editarUsuarioSuperadminFragment);
                }
            }
        });

        return rootView;
    }
}