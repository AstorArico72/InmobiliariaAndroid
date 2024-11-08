package com.arico.inmobiliarialaboratorio3.misc;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arico.inmobiliarialaboratorio3.R;
import com.arico.inmobiliarialaboratorio3.models.Pago;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdaptadorPago extends android.widget.ArrayAdapter<Pago>{
    private List<Pago> ListaPagos;
    private Context ContextoAplicacion;
    public AdaptadorPago(@NonNull Context context, int resource, @NonNull List<Pago> objects) {
        super(context, resource, objects);
        this.ListaPagos = objects;
        this.ContextoAplicacion = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View VistaItem = convertView;
        if (VistaItem == null) {
            VistaItem = LayoutInflater.from (ContextoAplicacion).inflate(R.layout.item_pago, parent, false);
        }
        Pago pago = ListaPagos.get (position);
        TextView IdPago = VistaItem.findViewById(R.id.IdPago);
        TextView MesPago = VistaItem.findViewById(R.id.NumeroPago);
        TextView Pagado = VistaItem.findViewById(R.id.Pagado);
        TextView Vencimiento = VistaItem.findViewById(R.id.FechaVencimiento);
        TextView ImportePago = VistaItem.findViewById(R.id.ImportePago);

        IdPago.setText("#" + pago.getId());
        MesPago.setText("Pago mensual #" + pago.getNumeroPago());
        switch (pago.getPagado()) {
            case 0:
                Pagado.setText("(Próximo)");
                Pagado.setTextColor(Color.rgb(255, 255, 0));
                break;
            case 1:
                Pagado.setText("(Pagado)");
                Pagado.setTextColor(Color.rgb(0, 255, 0));
                break;
            case 2:
                Pagado.setText("(Pendiente)");
                Pagado.setTextColor(Color.rgb(255, 0, 0));
        }
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("d MMMM yyyy");
        StringBuilder ConstructorFecha = new StringBuilder("Vence: ");
        LocalDateTime fechaVencimiento = LocalDateTime.parse(pago.getFechaPago());
        ConstructorFecha.append(fechaVencimiento.format(formateador));
        Vencimiento.setText(ConstructorFecha);
        ImportePago.setText("Importe: $" + pago.getMonto());

        return VistaItem;
    }
}
