package com.arico.inmobiliarialaboratorio3.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.arico.inmobiliarialaboratorio3.R;
import com.arico.inmobiliarialaboratorio3.databinding.FragmentDetallesContratoBinding;
import com.arico.inmobiliarialaboratorio3.misc.AdaptadorInmueble;
import com.arico.inmobiliarialaboratorio3.misc.AdaptadorPago;
import com.arico.inmobiliarialaboratorio3.models.Contrato;
import com.arico.inmobiliarialaboratorio3.models.Inmueble;
import com.arico.inmobiliarialaboratorio3.models.Pago;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DetallesContrato extends Fragment {
    private FragmentDetallesContratoBinding binder;
    private DetallesContratoViewModel ViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.binder = FragmentDetallesContratoBinding.inflate(inflater);
        this.ViewModel = new ViewModelProvider(this).get (DetallesContratoViewModel.class);
        Bundle AtadoContrato = getArguments();
        Contrato contrato = (Contrato)AtadoContrato.getSerializable("Contrato");
        binder.DetallesIdContrato.setText("Contrato #" + contrato.getIdContrato());
        binder.DetallesInmueble.setText("Inmueble: " + contrato.getDireccionInmueble() + " (#" + contrato.getIdInmueble() + ")");
        if (contrato.getVigente() == 0) {
            binder.DetallesVigente.setText("Contrato expirado.");
            binder.DetallesVigente.setTextColor(Color.rgb(255, 0, 0));
        } else {
            binder.DetallesVigente.setTextColor(Color.rgb(0, 255, 0));
            binder.DetallesVigente.setText("Contrato vigente.");
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        LocalDateTime fechaContrato = LocalDateTime.parse (contrato.getFechaContrato());
        String fechaContratoFormateada = fechaContrato.format(dateFormatter);
        LocalDateTime fechaLimite = LocalDateTime.parse (contrato.getFechaLímite());
        String fechaLimiteFormateada = fechaLimite.format(dateFormatter);
        StringBuilder ConstructorFechaContrato = new StringBuilder("Válido desde: ");
        StringBuilder ConstructorFechaLimite = new StringBuilder("Válido hasta: ");
        ConstructorFechaContrato.append(fechaContratoFormateada);
        ConstructorFechaLimite.append(fechaLimiteFormateada);
        binder.DetallesFechaContrato.setText(ConstructorFechaContrato);
        binder.DetallesFechaLimite.setText(ConstructorFechaLimite);

        ViewModel.LeerPagos().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                CargarLista(pagos);
            }
        });
        binder.DetallesInquilino.setText(contrato.getNombreInquilino());

        ViewModel.LeerPagosPorContrato(contrato.getIdContrato());

        return binder.getRoot();
    }

    private void CargarLista (List<Pago> ListadoPagos) {
        ArrayAdapter Adaptador = new AdaptadorPago(getContext(), R.layout.item_pago, ListadoPagos);
        ListView vista = binder.ListaPagos;
        vista.setAdapter(Adaptador);
    }
}