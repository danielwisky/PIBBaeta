package br.com.danielwisky.pibbaeta.api.client;

import br.com.danielwisky.pibbaeta.api.resources.request.FeedbackRequest;
import br.com.danielwisky.pibbaeta.api.resources.request.PedidoOracaoRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FeedbackClient {

  @POST("v1/feedback")
  Call<Void> enviar(@Body FeedbackRequest feedback);
}