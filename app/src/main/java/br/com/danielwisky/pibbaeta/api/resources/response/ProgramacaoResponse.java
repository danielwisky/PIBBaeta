package br.com.danielwisky.pibbaeta.api.resources.response;

import br.com.danielwisky.pibbaeta.dao.Programacao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramacaoResponse {

  private String id;
  private String titulo;
  private String descricao;
  private Date dataInicio;
  private Date dataTermino;
  private String local;
  private String endereco;
  private String urlBanner;
  private TipoProgramacaoResponse tipo;
  private String observacao;
  private String status;

  public String getId() {
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

  public TipoProgramacaoResponse getTipo() {
    return tipo;
  }

  public String getObservacao() {
    return observacao;
  }

  public String getStatus() {
    return status;
  }

  public Programacao toModel() {
    Programacao programacao = new Programacao();
    programacao.setTitulo(this.getTitulo());
    programacao.setDescricao(this.getDescricao());
    programacao.setDataInicio(this.getDataInicio());
    programacao.setDataTermino(this.getDataTermino());
    programacao.setLocal(this.getLocal());
    programacao.setEndereco(this.getEndereco());
    programacao.setUrlBanner(this.getUrlBanner());
    programacao.setTipo(this.getTipo().getDescricao());
    programacao.setObservacao(this.getObservacao());
    programacao.setIdExterno(this.getId());
    return programacao;
  }
}