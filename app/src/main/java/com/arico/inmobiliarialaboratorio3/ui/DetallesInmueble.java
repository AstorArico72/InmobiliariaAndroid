package com.arico.inmobiliarialaboratorio3.ui;

import static com.arico.inmobiliarialaboratorio3.api.ClienteApi.UrlBase;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arico.inmobiliarialaboratorio3.databinding.FragmentDetallesInmuebleBinding;
import com.arico.inmobiliarialaboratorio3.models.Inmueble;
import com.bumptech.glide.Glide;

public class DetallesInmueble extends Fragment {

    private DetallesInmuebleViewModel mViewModel;
    private FragmentDetallesInmuebleBinding binder;

    public static DetallesInmueble newInstance() {
        return new DetallesInmueble();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binder = FragmentDetallesInmuebleBinding.inflate(inflater);
        Bundle AtadoInmueble = getArguments();
        Inmueble inmueble = (Inmueble) AtadoInmueble.getSerializable("ItemInmueble");
        StringBuilder ConstructorCoordenadas = new StringBuilder("Coordenadas: ");
        StringBuilder ConstructorTipoUso = new StringBuilder();

        binder.DetallesDireccion.setText(inmueble.getDirección());
        binder.DetallesId.setText("ID #" + inmueble.getId());
        binder.DetallesAmbientes.setText(inmueble.getAmbientes() + " Ambientes");
        binder.DetallesPrecio.setText("Precio de alquiler: $" + inmueble.getPrecio());

        if (inmueble.getCoordenadasY() < 0) {
            ConstructorCoordenadas.append(Math.abs (inmueble.getCoordenadasY()) + " S, ");
        } else {
            ConstructorCoordenadas.append(inmueble.getCoordenadasY() + " N, ");
        }
        if (inmueble.getCoordenadasX() < 0) {
            ConstructorCoordenadas.append(Math.abs (inmueble.getCoordenadasX()) + " O");
        } else {
            ConstructorCoordenadas.append(inmueble.getCoordenadasX() + " E");
        }

        binder.DetallesCoordenadas.setText(ConstructorCoordenadas);
        ConstructorTipoUso.append(inmueble.getTipo());
        ConstructorTipoUso.append(" para uso ");
        ConstructorTipoUso.append(inmueble.getUso());
        binder.DetallesTipoUso.setText(ConstructorTipoUso);
        binder.DetallesSuperficie.setText(inmueble.getSuperficie() + " m²");
        Glide.with(getContext()).load(UrlBase + inmueble.getUriFoto()).into(binder.DetallesImagen);
        switch (inmueble.getDisponible()) {
            case 1:
                binder.BotonDisponible.setText("Deshabilitar inmueble");
                break;
            case 0:
                binder.BotonDisponible.setText("Habilitar inmueble");
                break;
            default:
                break;
        }

        binder.BotonDisponible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.CambiarDisponibilidad(inmueble);
            }
        });

        return binder.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetallesInmuebleViewModel.class);
        // TODO: Use the ViewModel
    }

}