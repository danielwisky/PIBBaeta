package br.com.danielwisky.pibbaeta.api.dto;

import br.com.danielwisky.pibbaeta.dao.Programacao;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramacaoDto {

  private Long id;
  private String titulo;
  private String descricao;
  private Date dataInicio;
  private Date dataTermino;
  private String local;
  private String endereco;
  private String urlBanner;
  private TipoProgramacaoDto tipo;
  private StatusDto status;

  public Long getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public Date getDataInicio() {
    return dataInicio;
  }

  public Date getDataTermino() {
    return dataTermino;
  }

  public String getLocal() {
    return local;
  }

  public String getEndereco() {
    return endereco;
  }

  public String getUrlBanner() {
    return urlBanner;
  }

  public TipoProgramacaoDto getTipo() {
    return tipo;
  }

  public StatusDto getStatus() {
    return status;
  }

  public boolean isAtivo() {
    return StatusDto.ATIVO.equals(getStatus());
  }

  public Programacao toModel() {
    Programacao programacao = new Programacao();
    programacao.setId(this.getId());
    programacao.setTitulo(this.getTitulo());
    programacao.setDescricao(this.getDescricao());
    programacao.setDataInicio(this.getDataInicio());
    programacao.setDataTermino(this.getDataTermino());
    programacao.setLocal(this.getLocal());
    programacao.setEndereco(this.getEndereco());
    programacao.setUrlBanner(this.getUrlBanner());
    programacao.setTipo(this.getTipo().getDescricao());
    return programacao;
  }
}