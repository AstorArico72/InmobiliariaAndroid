package com.arico.inmobiliarialaboratorio3.ui;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.arico.inmobiliarialaboratorio3.api.ClienteApi;
import com.arico.inmobiliarialaboratorio3.models.Pago;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallesContratoViewModel extends androidx.lifecycle.AndroidViewModel {
    private Context ContextoAplicacion;
    private MutableLiveData <List<Pago>> MutablePagos;

    public DetallesContratoViewModel(@NonNull Application application) {
        super(application);
        this.ContextoAplicacion = application.getApplicationContext();
    }

    public MutableLiveData<List<Pago>> LeerPagos () {
        if (this.MutablePagos == null) {
            this.MutablePagos = new MutableLiveData<>();
        }
        return this.MutablePagos;
    }

    public void LeerPagosPorContrato (int idContrato) {
        ClienteApi.InterfazApi api = ClienteApi.ConseguirApi();
        String token = ClienteApi.LeerToken(ContextoAplicacion);
        Call<List<Pago>> llamada = api.VerPagos(token, idContrato);

        llamada.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if (response.code() == 200) {
                    MutablePagos.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable throwable) {
                Toast.makeText(ContextoAplicacion, throwable.getMessage(), Toast.LENGTH_LONG).show();
                throw new RuntimeException(throwable);
            }
        });
    }
}
