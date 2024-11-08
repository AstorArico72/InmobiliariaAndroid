package com.arico.inmobiliarialaboratorio3.misc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import com.bumptech.glide.*;

import com.arico.inmobiliarialaboratorio3.R;
import com.arico.inmobiliarialaboratorio3.models.Inmueble;
import static com.arico.inmobiliarialaboratorio3.api.ClienteApi.UrlBase;

import java.util.List;

public class AdaptadorInmueble extends android.widget.ArrayAdapter <Inmueble> {
    private List <Inmueble> ListaInmuebles;
    private Context ContextoAplicacion;
    public AdaptadorInmueble(@NonNull Context context, int resource, @NonNull List<Inmueble> objects) {
        super(context, resource, objects);
        this.ListaInmuebles = objects;
        this.ContextoAplicacion = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View VistaItem = convertView;
        if (VistaItem == null) {
            VistaItem = LayoutInflater.from (ContextoAplicacion).inflate(R.layout.inmueble_item, parent, false);
        }
        Inmueble inmueble = ListaInmuebles.get (position);
        TextView direccion = VistaItem.findViewById(R.id.DireccionInmuebles);
        TextView id = VistaItem.findViewById(R.id.IdInmuebles);
        direccion.setText(inmueble.getDirección());
        id.setText("ID #" + inmueble.getId());
        CardView tarjeta = VistaItem.findViewById(R.id.TarjetaItem);
        if (inmueble.getUriFoto() != null) {
            Glide.with(ContextoAplicacion).load(UrlBase + inmueble.getUriFoto()).into((ImageView) VistaItem.findViewById(R.id.ImagenInmueble));
        }
        tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle atado = new Bundle();
                atado.putSerializable("ItemInmueble", inmueble);
                Navigation.findNavController((Activity) ContextoAplicacion, R.id.nav_host_fragment_content_main).navigate(R.id.nav_detalles_inmueble, atado);
            }
        });

        return VistaItem;
    }
}
