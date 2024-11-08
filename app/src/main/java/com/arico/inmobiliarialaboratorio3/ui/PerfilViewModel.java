package com.arico.inmobiliarialaboratorio3.ui;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.arico.inmobiliarialaboratorio3.api.*;
import com.arico.inmobiliarialaboratorio3.models.Propietario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private Context ContextoAplicacion;
    private MutableLiveData<Propietario> MutablePropietario;
    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.ContextoAplicacion = application.getApplicationContext();
    }

    public MutableLiveData<Propietario> ConseguirMutablePropietario() {
        if (MutablePropietario == null) {
            MutablePropietario = new MutableLiveData<>();
        }
        return MutablePropietario;
    }

    public void ConseguirPerfil () {
        String token = ClienteApi.LeerToken(ContextoAplicacion);
        ClienteApi.InterfazApi api = ClienteApi.ConseguirApi();
        Call<Propietario> llamada = api.VerPerfil(token);
        llamada.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                MutablePropietario.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
    }
}