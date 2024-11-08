package com.arico.inmobiliarialaboratorio3.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.arico.inmobiliarialaboratorio3.R;
import com.arico.inmobiliarialaboratorio3.databinding.ActivityNuevoInmuebleBinding;
import com.arico.inmobiliarialaboratorio3.models.Inmueble;

public class NuevoInmueble extends AppCompatActivity {
    private ActivityNuevoInmuebleBinding binder;
    private NuevoInmuebleViewModel ViewModel;
    private ActivityResultLauncher<Intent> ResultLauncher;
    private Intent IntentGaleria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binder = ActivityNuevoInmuebleBinding.inflate(getLayoutInflater());
        this.ViewModel = new ViewModelProvider(this).get(NuevoInmuebleViewModel.class);
        setContentView(binder.getRoot());
        ArrayAdapter <CharSequence> AdaptadorTipos = ArrayAdapter.createFromResource(getApplicationContext(), R.array.tipos_inmueble, android.R.layout.simple_spinner_item);
        binder.MenuTipo.setAdapter(AdaptadorTipos);
        ArrayAdapter <CharSequence> AdaptadorUsos = ArrayAdapter.createFromResource(getApplicationContext(), R.array.usos_inmueble, android.R.layout.simple_spinner_item);
        binder.MenuUso.setAdapter(AdaptadorUsos);
        AbrirGalería();
        ViewModel.ConseguirUri().observe(this, new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                binder.CampoFoto.setImageURI(uri);
                binder.CampoFoto.setTag(uri);
            }
        });

        binder.GuardarNuevoInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Inmueble NuevoInmueble = new Inmueble();
                    NuevoInmueble.setDirección(binder.CampoDireccion.getText().toString());
                    NuevoInmueble.setDisponible((byte) 0);
                    NuevoInmueble.setPrecio(Integer.parseInt(binder.CampoPrecio.getText().toString()));
                    NuevoInmueble.setAmbientes(Byte.parseByte(binder.CampoAmbientes.getText().toString()));
                    NuevoInmueble.setSuperficie(Short.parseShort(binder.CampoSuperficie.getText().toString()));
                    NuevoInmueble.setCoordenadasX(Float.parseFloat(binder.CampoLongitud.getText().toString()));
                    NuevoInmueble.setCoordenadasY(Float.parseFloat(binder.CampoLatitud.getText().toString()));
                    NuevoInmueble.setTipo(binder.MenuTipo.getSelectedItem().toString());
                    NuevoInmueble.setUso(binder.MenuUso.getSelectedItem().toString());
                    ViewModel.RegistrarInmueble(NuevoInmueble);
                } catch (NumberFormatException ex) {
                    Toast.makeText(getApplicationContext(), "Uno o más campos están vacíos.", Toast.LENGTH_SHORT).show();
                } catch (NullPointerException ex) {
                    Toast.makeText(getApplicationContext(), "Uno o más campos están vacíos.", Toast.LENGTH_SHORT).show();
                }
                //File foto = new File(binder.CampoFoto.getTag().toString());
            }
        });

        binder.CampoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultLauncher.launch(IntentGaleria);
            }
        });
    }

    private void AbrirGalería () {
        IntentGaleria = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                ViewModel.RecibirFoto(result);
            }
        });
    }
}