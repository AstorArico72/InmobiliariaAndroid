package com.arico.inmobiliarialaboratorio3.ui;

import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arico.inmobiliarialaboratorio3.R;
import com.arico.inmobiliarialaboratorio3.databinding.ActivityLoginBinding;

public class Login extends androidx.appcompat.app.AppCompatActivity {

    private LoginViewModel mViewModel;
    private ActivityLoginBinding binder;

    public static Login newInstance() {
        return new Login();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        mViewModel.BorrarToken();
        SolicitarPerimisos();
        binder.SubmitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = binder.CampoUsuario.getText().toString();
                String clave = binder.CampoContraseA.getText().toString();

                mViewModel.IniciarSesion (usuario, clave);
            }
        });

        binder.CrearNuevaCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), NuevoPerfil.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });

        binder.BotonClaveOlvidada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UsuarioARecuperar = binder.CampoUsuario.getText().toString();
                if (UsuarioARecuperar.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Escribe el correo de tu cuenta.", Toast.LENGTH_SHORT).show();
                } else {
                    mViewModel.ClaveOlvidada(UsuarioARecuperar);
                }
            }
        });
    }

    private void SolicitarPerimisos(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
    }
}