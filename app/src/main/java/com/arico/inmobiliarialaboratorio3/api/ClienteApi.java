package com.arico.inmobiliarialaboratorio3.api;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.*;

import com.arico.inmobiliarialaboratorio3.models.Contrato;
import com.arico.inmobiliarialaboratorio3.models.Inmueble;
import com.arico.inmobiliarialaboratorio3.models.Pago;
import com.arico.inmobiliarialaboratorio3.models.Propietario;
import com.google.gson.*;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class ClienteApi {
    public static final String UrlApi = "http://192.168.1.150:8011/Api/";
    public static final String UrlBase = "http://192.168.1.150:8011";

    public static InterfazApi ConseguirApi () {
        Gson NotAJson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(UrlApi)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(NotAJson))
                .build();
        return retrofit.create(InterfazApi.class);
    }

    public static void GuardarToken (String token, Context applicationContext) {
        SharedPreferences preferences = applicationContext.getSharedPreferences("PreferenciasCompartidas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("TokenAcceso", token);
        editor.apply();
    }

    public static void BorrarToken (Context applicationContext) {
        SharedPreferences preferences = applicationContext.getSharedPreferences("PreferenciasCompartidas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("TokenAcceso");
        editor.apply();
    }

    public static String LeerToken (Context applicationContext) {
        SharedPreferences preferences = applicationContext.getSharedPreferences("PreferenciasCompartidas", Context.MODE_PRIVATE);
        String token = preferences.getString("TokenAcceso", null);
        return token;
    }

    public interface InterfazApi {
        @FormUrlEncoded
        @POST("Propietarios/Ingresar")
        public Call<String> IniciarSesion (@Field("Correo") String correo, @Field("Clave") String clave);
        @FormUrlEncoded
        @POST("Propietarios/Nuevo")
        public Call<String> NuevoUsuario (@Field("Correo") String correo, @Field("Nombre") String nombre, @Field("Clave") String clave, @Field("DNI") String dni, @Field("Contacto") String contacto);
        @GET("Inmuebles/Todos")
        public Call<List<Inmueble>> TodosLosInmuebles (@Header("Authorization") String token);
        @Multipart
        @POST("Inmuebles/Nuevo")
        public Call<Inmueble> NuevoInmueble (@Header("Authorization") String token, @Part("Direccion") RequestBody dirección, @Part("Superficie") RequestBody superficie, @Part("Precio") RequestBody precio, @Part("Tipo") RequestBody tipo, @Part("Uso") RequestBody uso, @Part("Ambientes") RequestBody ambientes, @Part("CoordenadasX") RequestBody longitud, @Part("CoordenadasY") RequestBody latitud, @Part MultipartBody.Part foto);
        @GET("Propietarios/Perfil")
        public Call<Propietario> VerPerfil (@Header("Authorization") String token);
        @FormUrlEncoded
        @PUT("Propietarios/EditarPerfil")
        public Call<Propietario> EditarPerfil (@Header("Authorization") String token, @Field("Nombre") String nombre, @Field("Contacto") String contacto, @Field("Dni") String dni);
        @FormUrlEncoded
        @POST("Propietarios/ClaveOlvidada")
        public Call<String> ClaveOlvidada (@Field("Correo") String correo);
        @FormUrlEncoded
        @PATCH("Inmuebles/CambiarDisponibilidad")
        public Call<String> CambiarDisponibilidad (@Header("Authorization") String token, @Field("IdInmueble") int id);
        @GET("Contratos/Todos")
        public Call<List<Contrato>> TodosLosContratos (@Header("Authorization") String token);
        @GET("Pagos/PorContrato/{id}")
        public Call<List<Pago>> VerPagos (@Header("Authorization") String token, @Path(value="id") int IdContrato);
    }
}
