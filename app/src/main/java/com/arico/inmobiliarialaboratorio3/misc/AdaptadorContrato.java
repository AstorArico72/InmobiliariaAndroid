package com.arico.inmobiliarialaboratorio3.misc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.arico.inmobiliarialaboratorio3.R;
import com.arico.inmobiliarialaboratorio3.models.Contrato;
import java.util.List;

public class AdaptadorContrato extends RecyclerView.Adapter <AdaptadorContrato.ViewHolder> {
    private List <Contrato> ListaContratos;
    private Context ContextoAplicacion;

    public AdaptadorContrato (@NonNull Context context, @NonNull List <Contrato> items) {
        this.ContextoAplicacion = context;
        this.ListaContratos = items;
    }

        /**/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from (ContextoAplicacion).inflate(R.layout.item_contrato, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contrato contrato = ListaContratos.get(position);
        holder.IdContrato.setText("#" + contrato.getIdContrato());
        holder.InquilinoContrato.setText("Locatario: " + contrato.getNombreInquilino());
        holder.InmuebleContrato.setText(contrato.getDireccionInmueble());
        holder.Tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle atado = new Bundle();
                atado.putSerializable("Contrato", contrato);
                Navigation.findNavController((Activity) ContextoAplicacion, R.id.nav_host_fragment_content_main).navigate(R.id.nav_detalles_contrato, atado);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListaContratos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView IdContrato;
        TextView InquilinoContrato;
        TextView InmuebleContrato;
        CardView Tarjeta;

        public ViewHolder(View view) {
            super(view);
            this.IdContrato = view.findViewById(R.id.IdContrato);
            this.InmuebleContrato = view.findViewById(R.id.InmuebleContrato);
            this.InquilinoContrato = view.findViewById(R.id.InquilinoContrato);
            this.Tarjeta = view.findViewById(R.id.TarjetaContrato);
        }
    }
}
