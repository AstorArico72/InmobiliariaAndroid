package com.arico.inmobiliarialaboratorio3.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arico.inmobiliarialaboratorio3.R;
import com.arico.inmobiliarialaboratorio3.databinding.FragmentEditarPerfilBinding;
import com.arico.inmobiliarialaboratorio3.models.Propietario;

public class EditarPerfil extends Fragment {

    private FragmentEditarPerfilBinding binder;
    private EditarPerfilViewModel mViewModel;

    public static EditarPerfil newInstance() {
        return new EditarPerfil();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binder = FragmentEditarPerfilBinding.inflate(inflater);
        Bundle arguments = getArguments();
        Propietario perfil = (Propietario) arguments.getSerializable("Perfil");

        binder.EditaNombre.setText(perfil.getNombre());
        binder.EditaDni.setText(perfil.getDni());
        binder.EditaContacto.setText(perfil.getContacto());

        binder.GuardarCambiosPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfil.setNombre(binder.EditaNombre.getText().toString());
                perfil.setContacto(binder.EditaContacto.getText().toString());
                perfil.setDni(binder.EditaDni.getText().toString());
                mViewModel.ActualizarPerfil(perfil);
            }
        });

        return binder.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EditarPerfilViewModel.class);
        // TODO: Use the ViewModel
    }

}