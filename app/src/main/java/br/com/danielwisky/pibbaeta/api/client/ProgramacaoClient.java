package br.com.danielwisky.pibbaeta.api.client;

import br.com.danielwisky.pibbaeta.api.dto.AgendaDto;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProgramacaoClient {

  @GET("v1/programacao")
  Call<AgendaDto> listar();
}