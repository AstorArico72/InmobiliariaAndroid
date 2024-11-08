package com.arico.inmobiliarialaboratorio3.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.arico.inmobiliarialaboratorio3.R;
import com.arico.inmobiliarialaboratorio3.databinding.FragmentContratosBinding;
import com.arico.inmobiliarialaboratorio3.misc.AdaptadorContrato;
import com.arico.inmobiliarialaboratorio3.models.Contrato;

import java.util.List;

public class Contratos extends Fragment {

    private ContratosViewModel mViewModel;
    private FragmentContratosBinding binder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binder = FragmentContratosBinding.inflate(inflater);
        mViewModel = new ViewModelProvider(this).get(ContratosViewModel.class);
        mViewModel.LeerContratos().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contratos) {
                CargarLista(contratos);
            }
        });
        mViewModel.TodosLosContratos();

        return binder.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void CargarLista (List<Contrato> ListadoContratos) {
        AdaptadorContrato Adaptador = new AdaptadorContrato(getContext(), ListadoContratos);
        RecyclerView vista = binder.ListaContratos;
        vista.setAdapter(Adaptador);
        vista.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}