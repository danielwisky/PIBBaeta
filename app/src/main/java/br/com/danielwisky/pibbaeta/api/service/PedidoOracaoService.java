package br.com.danielwisky.pibbaeta.api.service;

import br.com.danielwisky.pibbaeta.dao.PedidoOracao;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PedidoOracaoService {

  @POST("v1/pedido-oracao")
  Call<Void> enviar(@Body PedidoOracao pedidoOracao);
}