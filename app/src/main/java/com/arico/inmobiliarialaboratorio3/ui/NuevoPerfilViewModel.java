package com.arico.inmobiliarialaboratorio3.ui;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;
import android.content.Context;
import androidx.annotation.NonNull;
import com.arico.inmobiliarialaboratorio3.api.ClienteApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevoPerfilViewModel extends androidx.lifecycle.AndroidViewModel {
    private Context ContextoAplicacion;
    public NuevoPerfilViewModel(@NonNull Application application) {
        super(application);
        this.ContextoAplicacion = application.getApplicationContext();
    }

    public void NuevoUsuario (String correo, String nombre, String clave, String dni, String contacto) {
        ClienteApi.InterfazApi api = ClienteApi.ConseguirApi();
        Call<String> llamada = api.NuevoUsuario(correo, nombre, clave, dni, contacto);
        if (llamada != null) {
            llamada.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == 200) {
                        Toast.makeText(ContextoAplicacion, "[HTTP 200 - OK] Cuenta creada, úsala para iniciar sesión.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ContextoAplicacion, Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ContextoAplicacion.startActivity(intent);
                    } else if (response.code() == 400) {
                        Toast.makeText(ContextoAplicacion, "[HTTP 400 - Mal pedido] " + response.body(), Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 500) {
                        Toast.makeText(ContextoAplicacion, "[HTTP 500 - Error del servidor] " + response.body(), Toast.LENGTH_LONG).show();
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
}
