package com.example.netpulseiot.fragmentos.superadmin;

import static java.util.Locale.filter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.netpulseiot.Adapter.Superadmin.SuperadminMensajeAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentMensajesSuperadminBinding;
import com.example.netpulseiot.entity.SuperadminMensajeItem;

import java.util.ArrayList;
import java.util.List;


public class MensajesSuperadminFragment extends Fragment {

    FragmentMensajesSuperadminBinding binding;

    /** SE AÑADIO CON CHAT GPT **/
    private List<SuperadminMensajeItem> originalList; // Original data list
    private SuperadminMensajeAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMensajesSuperadminBinding.inflate(inflater,container,false);

        // Initialize the original list
        originalList = new ArrayList<>();
        for (int i = 0; i <= 12; i++) {
            originalList.add(new SuperadminMensajeItem("Alex Valera", "La reunión será a las 11:00 am", R.drawable.fotoperfil_u2, "1"));
        }

        // Setup RecyclerView
        binding.superadminMensajesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SuperadminMensajeAdapter(getContext(), originalList);
        binding.superadminMensajesRecyclerView.setAdapter(adapter);


        // Find the EditText inside the SearchBar
        EditText searchEditText = binding.searchBar.findViewById(R.id.search_edit_text);


        // Set up search functionality
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });






        // Inflate the layout for this fragment
        return binding.getRoot();
    }


    private void filter(String text) {
        List<SuperadminMensajeItem> filteredList = new ArrayList<>();
        for (SuperadminMensajeItem item : originalList) {
            if (item.getMensaje().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.updateList(filteredList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}