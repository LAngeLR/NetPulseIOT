package com.example.netpulseiot.fragmentos.admin;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpulseiot.Adapter.AdminUsuarioAdapter;
import com.example.netpulseiot.R;
import com.example.netpulseiot.databinding.FragmentAdminUsuariosBinding;
import com.example.netpulseiot.entity.AdminUserItem;

import java.util.ArrayList;
import java.util.List;

public class AdminUsuariosFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public AdminUsuariosFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment AdminUsuariosFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static AdminUsuariosFragment newInstance(String param1, String param2) {
//        AdminUsuariosFragment fragment = new AdminUsuariosFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
    FragmentAdminUsuariosBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminUsuariosBinding.inflate(inflater,container,false);

        //hardoceo de la lista (se cambiará cuando tengamos BD o API para extraer los dto
        List<AdminUserItem> list = new ArrayList<AdminUserItem>();
        for (int i=0; i<=12;i++){
            list.add(new AdminUserItem("José Rivera","supervisor",R.drawable.fotoperfil_u));
        }

        binding.adminUsariosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.adminUsariosRecyclerView.setAdapter(new AdminUsuarioAdapter(getContext(),list));

        return binding.getRoot();
    }
}