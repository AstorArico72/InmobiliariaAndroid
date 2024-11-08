package com.arico.inmobiliarialaboratorio3.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.arico.inmobiliarialaboratorio3.R;
import com.arico.inmobiliarialaboratorio3.databinding.ActivityNuevoPerfilBinding;

public class NuevoPerfil extends AppCompatActivity {

    private ActivityNuevoPerfilBinding binder;
    private NuevoPerfilViewModel ViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        this.binder = ActivityNuevoPerfilBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());

        ViewModel = new ViewModelProvider(this).get (NuevoPerfilViewModel.class);

        binder.GuardarNuevoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String correo = binder.EditaCorreo.getText().toString();
                    String nombre = binder.EditaNombre.getText().toString();
                    String clave = binder.EditaClave.getText().toString();
                    String contacto = binder.EditaContacto.getText().toString();
                    String dni = binder.EditaDni.getText().toString();
                    ViewModel.NuevoUsuario(correo, nombre, clave, dni, contacto);
                    if (correo == null || nombre == null || clave == null || contacto == null || dni == null)
                    Toast.makeText(getApplicationContext(), "Un campo está vacío.", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException ex) {
                    Toast.makeText(getApplicationContext(), "Un campo .", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}