package br.com.danielwisky.pibbaeta.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoProgramacaoDto {

  private Integer id;
  private String descricao;

  public Integer getId() {
    return id;
  }

  public String getDescricao() {
    return descricao;
  }
}