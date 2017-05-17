package br.com.danielwisky.pibbaeta.service;

import br.com.danielwisky.pibbaeta.modelo.PedidoOracao;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PedidoOracaoService {

    @POST("v1/pedido-oracao")
    Call<Void> insere(@Body PedidoOracao pedidoOracao);
}