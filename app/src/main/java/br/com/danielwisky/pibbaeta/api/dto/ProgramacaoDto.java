package br.com.danielwisky.pibbaeta.api.dto;

import java.util.Date;

class ProgramacaoDto {

  private Long id;
  private String titulo;
  private String descricao;
  private Date dataInicio;
  private Date dataTermino;
  private String local;
  private String endereco;
  private String urlBanner;
  private TipoProgramacaoDto tipo;
  private String status;
}
