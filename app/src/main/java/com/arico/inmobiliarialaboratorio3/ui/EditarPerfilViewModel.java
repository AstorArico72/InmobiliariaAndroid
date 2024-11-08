package com.arico.inmobiliarialaboratorio3.ui;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.arico.inmobiliarialaboratorio3.api.ClienteApi;
import com.arico.inmobiliarialaboratorio3.models.Propietario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarPerfilViewModel extends AndroidViewModel {
    private Context ContextoAplicacion;
    public EditarPerfilViewModel(@NonNull Application application) {
        super(application);
        this.ContextoAplicacion = application.getApplicationContext();
    }

    public void ActualizarPerfil (Propietario perfil) {
        ClienteApi.InterfazApi api = ClienteApi.ConseguirApi();
        String token = ClienteApi.LeerToken(ContextoAplicacion);
        Call<Propietario> llamada = api.EditarPerfil(token, perfil.getNombre(), perfil.getContacto(), perfil.getDni());
        llamada.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.code() == 200) {
                    Toast.makeText(ContextoAplicacion, "[HTTP 200 - OK] Perfil editado.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ContextoAplicacion, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ContextoAplicacion.startActivity(intent);
                } else if (response.code() == 400) {
                    Toast.makeText(ContextoAplicacion, "[HTTP 400 - Mal pedido] " + response.body(), Toast.LENGTH_LONG).show();
                } else if (response.code() == 500) {
                    Toast.makeText(ContextoAplicacion, "[HTTP 500 - Error del servidor] " + response.body(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ContextoAplicacion, "[HTTP " + response.code() + "] " + response.body(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable throwable) {
                Toast.makeText(ContextoAplicacion, throwable.getMessage(), Toast.LENGTH_LONG).show();
                throw new RuntimeException(throwable);
            }
        });
    }
}