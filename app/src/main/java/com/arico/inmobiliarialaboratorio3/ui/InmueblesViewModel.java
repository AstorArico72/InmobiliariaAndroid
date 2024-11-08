package com.arico.inmobiliarialaboratorio3.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import android.app.Application;
import android.util.Log;

import com.arico.inmobiliarialaboratorio3.api.ClienteApi;

import com.arico.inmobiliarialaboratorio3.models.Inmueble;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {
    private MutableLiveData <List<Inmueble>> MutableListaInmuebles;

    public MutableLiveData<List<Inmueble>> ConseguirListaInmuebles () {
        if (MutableListaInmuebles == null) {
            MutableListaInmuebles = new MutableLiveData<>();
        }
        return MutableListaInmuebles;
    }

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
    }

    public void TodosLosInmuebles () {
        ClienteApi.InterfazApi api = ClienteApi.ConseguirApi();
        String token = ClienteApi.LeerToken(getApplication().getApplicationContext());
        Call <List <Inmueble>> llamada = api.TodosLosInmuebles(token);
        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                MutableListaInmuebles.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
    }

}