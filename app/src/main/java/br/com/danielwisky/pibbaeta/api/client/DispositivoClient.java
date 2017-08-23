package br.com.danielwisky.pibbaeta.api.client;

import br.com.danielwisky.pibbaeta.api.resources.request.DispositivoRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DispositivoClient {

  @POST("v1/dispositivo")
  Call<Void> enviar(@Body DispositivoRequest dispositivo);
}