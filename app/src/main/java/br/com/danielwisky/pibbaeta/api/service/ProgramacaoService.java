package br.com.danielwisky.pibbaeta.api.service;

import br.com.danielwisky.pibbaeta.api.dto.ProgramacaoSync;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProgramacaoService {

  @GET("v1/programacao")
  Call<ProgramacaoSync> listar();
}