package br.com.danielwisky.pibbaeta.api.resources.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoProgramacaoResponse {

  private String descricao;

  public String getDescricao() {
    return descricao;
  }
}