package com.arico.inmobiliarialaboratorio3.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import android.widget.Toast;

import com.arico.inmobiliarialaboratorio3.api.ClienteApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Intent;

public class LoginViewModel extends AndroidViewModel {
    private android.content.Context ContextoAplicacion;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.ContextoAplicacion = application.getApplicationContext();
    }

    public void IniciarSesion (String usuario, String clave) {
        ClienteApi.InterfazApi api = ClienteApi.ConseguirApi();
        Call<String> llamada = api.IniciarSesion(usuario, clave);
        if (llamada != null) {
            llamada.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == 200) {
                        String token = "Bearer " + response.body();
                        ClienteApi.GuardarToken (token, ContextoAplicacion);
                        Intent intent = new Intent(ContextoAplicacion, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ContextoAplicacion.startActivity(intent);
                    } else if (response.code() == 400) {
                        Toast.makeText(ContextoAplicacion, "[HTTP 400 - Mal pedido] Credenciales incorrectas.", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 500) {
                        Toast.makeText(ContextoAplicacion, "[HTTP 500 - Error del servidor] " + response.body(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ContextoAplicacion, "[HTTP " + response.code() + "] " + response.body(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable throwable) {
                    throw new RuntimeException(throwable);
                }
            });
        }
    }

    public void BorrarToken () {
        String token = ClienteApi.LeerToken(ContextoAplicacion);
        if (token != null) {
            ClienteApi.BorrarToken(ContextoAplicacion);
        }
    }

    public void ClaveOlvidada (String correo) {
        ClienteApi.InterfazApi api = ClienteApi.ConseguirApi();
        Call<String> llamada = api.ClaveOlvidada(correo);
        if (llamada != null) {
            llamada.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == 400) {
                        Toast.makeText(ContextoAplicacion, "[HTTP 400 - Mal pedido] Ésa cuenta no existe.", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 200) {
                        Toast.makeText(ContextoAplicacion, "Se ha enviado una clave temporal a tu correo, usa ésa clave para iniciar sesión.", Toast.LENGTH_LONG).show();
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

    //TODO: Implementar "cerrar sesión" en el "menú hamburguesa".
}