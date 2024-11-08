package com.arico.inmobiliarialaboratorio3.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.arico.inmobiliarialaboratorio3.R;
import com.arico.inmobiliarialaboratorio3.misc.AdaptadorInmueble;
import com.arico.inmobiliarialaboratorio3.databinding.FragmentInmueblesBinding;
import com.arico.inmobiliarialaboratorio3.models.Inmueble;

import java.util.List;

public class Inmuebles extends Fragment {
    private FragmentInmueblesBinding binder;
    private InmueblesViewModel mViewModel;
    private LayoutInflater inflador;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflador = inflater;
        mViewModel = new ViewModelProvider(this).get(InmueblesViewModel.class);
        this.binder = FragmentInmueblesBinding.inflate(inflater);
        mViewModel.ConseguirListaInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                CargarLista(inmuebles);
            }
        });
        mViewModel.TodosLosInmuebles();
        binder.RegistrarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NuevoInmueble.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });
        return binder.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void CargarLista (List<Inmueble> ListadoInmuebles) {
        ArrayAdapter Adaptador = new AdaptadorInmueble(getContext(), R.layout.inmueble_item, ListadoInmuebles);
        ListView vista = binder.ListaInmuebles;
        vista.setAdapter(Adaptador);
    }

}