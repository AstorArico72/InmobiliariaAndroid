package com.arico.inmobiliarialaboratorio3.ui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.navigation.Navigation;

import com.arico.inmobiliarialaboratorio3.R;
import com.arico.inmobiliarialaboratorio3.api.ClienteApi;
import com.arico.inmobiliarialaboratorio3.models.Inmueble;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallesInmuebleViewModel extends AndroidViewModel {
    private Context ContextoAplicacion;

    public DetallesInmuebleViewModel(@NonNull Application application) {
        super(application);
        this.ContextoAplicacion = application.getApplicationContext();
    }

    public void CambiarDisponibilidad (Inmueble inmueble) {
        ClienteApi.InterfazApi api = ClienteApi.ConseguirApi();
        String token = ClienteApi.LeerToken(ContextoAplicacion);
        Call<String> llamada = api.CambiarDisponibilidad(token, inmueble.getId());
        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    Toast.makeText(ContextoAplicacion, "[HTTP 200 - OK] " + response.body(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ContextoAplicacion, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ContextoAplicacion.startActivity(intent);
                } else {
                    Toast.makeText(ContextoAplicacion, "[HTTP " + response.code() + "] " + response.body(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Toast.makeText(ContextoAplicacion, throwable.getMessage(), Toast.LENGTH_LONG).show();
                throw new RuntimeException(throwable);
            }
        });
    }
}