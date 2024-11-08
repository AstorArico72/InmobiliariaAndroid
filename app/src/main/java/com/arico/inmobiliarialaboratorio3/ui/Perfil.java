package com.arico.inmobiliarialaboratorio3.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arico.inmobiliarialaboratorio3.R;
import com.arico.inmobiliarialaboratorio3.databinding.FragmentPerfilBinding;
import com.arico.inmobiliarialaboratorio3.models.Propietario;

public class Perfil extends Fragment {
    private FragmentPerfilBinding binder;
    private PerfilViewModel mViewModel;

    public static Perfil newInstance() {
        return new Perfil();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binder = FragmentPerfilBinding.inflate(inflater);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        Bundle atado = new Bundle();

        mViewModel.ConseguirPerfil();
        mViewModel.ConseguirMutablePropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                binder.NombrePropietario.setText (propietario.getNombre());
                binder.NumeroPropietario.setText ("#" + propietario.getId());
                binder.ContactoPropietario.setText (propietario.getContacto());
                binder.RolPropietario.setText (propietario.getRol());
                binder.DniPropietario.setText ("DNI #" + propietario.getDni());

                atado.putSerializable("Perfil", propietario);
            }
        });

        binder.BotonEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController((Activity) getContext(), R.id.nav_host_fragment_content_main).navigate (R.id.nav_editar_perfil, atado);
            }
        });

        return binder.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}