package br.com.danielwisky.pibbaeta.api.client;

import br.com.danielwisky.pibbaeta.api.resources.response.AgendaResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProgramacaoClient {

  @GET("v1/programacao")
  Call<AgendaResponse> listar();

  @GET("v1/programacao")
  Call<AgendaResponse> listar(@Query("versao") String versao);
}