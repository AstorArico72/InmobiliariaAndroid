package com.arico.inmobiliarialaboratorio3.ui;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.arico.inmobiliarialaboratorio3.misc.RealPathUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.arico.inmobiliarialaboratorio3.api.ClienteApi;
import com.arico.inmobiliarialaboratorio3.models.Inmueble;

import java.io.File;

public class NuevoInmuebleViewModel extends androidx.lifecycle.AndroidViewModel {
    private Context ContextoAplicacion;
    public NuevoInmuebleViewModel(@NonNull Application application) {
        super(application);
        this.ContextoAplicacion = application.getApplicationContext();
    }

    private MutableLiveData<Uri> MutableUri;

    public MutableLiveData<Uri> ConseguirUri () {
        if (this.MutableUri == null) {
            this.MutableUri = new MutableLiveData<Uri>();
        }
        return this.MutableUri;
    }

    public void RecibirFoto (ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent DatosFoto = result.getData();
            Uri UriFoto = DatosFoto.getData();
            MutableUri.postValue(UriFoto);
        }
    }

    public void RegistrarInmueble (Inmueble InmuebleARegistrar) {
        ClienteApi.InterfazApi api = ClienteApi.ConseguirApi();
        String token = ClienteApi.LeerToken(ContextoAplicacion);
        RequestBody direccion = RequestBody.create(MultipartBody.FORM, InmuebleARegistrar.getDirección());
        RequestBody superficie = RequestBody.create(MultipartBody.FORM, String.valueOf(InmuebleARegistrar.getSuperficie()));
        RequestBody precio = RequestBody.create(MultipartBody.FORM, String.valueOf(InmuebleARegistrar.getPrecio()));
        RequestBody tipo = RequestBody.create(MultipartBody.FORM, InmuebleARegistrar.getTipo());
        RequestBody uso = RequestBody.create(MultipartBody.FORM, InmuebleARegistrar.getUso());
        RequestBody ambientes = RequestBody.create(MultipartBody.FORM, String.valueOf(InmuebleARegistrar.getAmbientes()));
        RequestBody coordenadasX = RequestBody.create(MultipartBody.FORM, String.valueOf(InmuebleARegistrar.getCoordenadasX()));
        RequestBody coordenadasY = RequestBody.create(MultipartBody.FORM, String.valueOf(InmuebleARegistrar.getCoordenadasY()));
        String Camino = RealPathUtil.getRealPath(ContextoAplicacion, MutableUri.getValue());
        File foto = new File (Camino);
        RequestBody FotoInmueble = RequestBody.create(MediaType.parse("multipart/form-data"), foto);
        MultipartBody.Part CuerpoInmueble = MultipartBody.Part.createFormData("Foto", foto.getName(), FotoInmueble);

        Call <Inmueble> llamada = api.NuevoInmueble(token, direccion, superficie, precio, tipo, uso, ambientes, coordenadasX, coordenadasY, CuerpoInmueble);
        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.code() == 200) {
                    Toast.makeText(ContextoAplicacion, "[HTTP 200 - OK] Inmueble guardado.", Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<Inmueble> call, Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
    }
}
