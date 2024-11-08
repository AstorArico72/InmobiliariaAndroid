package com.arico.inmobiliarialaboratorio3.ui;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.arico.inmobiliarialaboratorio3.api.ClienteApi;
import com.arico.inmobiliarialaboratorio3.models.Contrato;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosViewModel extends AndroidViewModel {
    private Context ContextoAplicacion;
    public ContratosViewModel(@NonNull Application application) {
        super(application);
        this.ContextoAplicacion = application.getApplicationContext();
    }
    private MutableLiveData<List<Contrato>> MutableContratos;

    public MutableLiveData<List<Contrato>> LeerContratos() {
        if (MutableContratos == null) {
            MutableContratos = new MutableLiveData<>();
        }
        return MutableContratos;
    }

    public void TodosLosContratos () {
        ClienteApi.InterfazApi api = ClienteApi.ConseguirApi();
        String token = ClienteApi.LeerToken(ContextoAplicacion);
        Call<List<Contrato>> llamada = api.TodosLosContratos(token);
        llamada.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if (response.code() == 200) {
                    MutableContratos.postValue(response.body());
                } else if (response.code() == 500) {
                    Toast.makeText(ContextoAplicacion, "[HTTP 500 - Error del servidor] " + response.body(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable throwable) {
                Toast.makeText(ContextoAplicacion, throwable.getMessage(), Toast.LENGTH_LONG).show();
                throw new RuntimeException(throwable);
            }
        });
    }
}