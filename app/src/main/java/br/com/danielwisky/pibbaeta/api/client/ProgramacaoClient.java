package br.com.danielwisky.pibbaeta.api.client;

import br.com.danielwisky.pibbaeta.api.dto.AgendaDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProgramacaoClient {

  @GET("v1/programacao")
  Call<AgendaDto> listar();

  @GET("v1/programacao")
  Call<AgendaDto> novos(@Query("versao") Long versao);
}