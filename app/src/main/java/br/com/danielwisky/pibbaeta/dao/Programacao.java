package br.com.danielwisky.pibbaeta.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "PROGRAMACAO".
 */
@Entity
public class Programacao {

  @Id
  private Long id;

  @NotNull
  private String titulo;

  @NotNull
  private String tipoProgramacao;

  @NotNull
  private String descricao;

  @NotNull
  private java.util.Date dataInicio;

  @NotNull
  private java.util.Date dataTermino;

  @NotNull
  private String local;
  private String endereco;
  private String urlBanner;

  // KEEP FIELDS - put your custom fields here
  // KEEP FIELDS END

  @Generated
  public Programacao() {
  }

  public Programacao(Long id) {
    this.id = id;
  }

  @Generated
  public Programacao(Long id, String titulo, String tipoProgramacao, String descricao,
      java.util.Date dataInicio, java.util.Date dataTermino, String local, String endereco,
      String urlBanner) {
    this.id = id;
    this.titulo = titulo;
    this.tipoProgramacao = tipoProgramacao;
    this.descricao = descricao;
    this.dataInicio = dataInicio;
    this.dataTermino = dataTermino;
    this.local = local;
    this.endereco = endereco;
    this.urlBanner = urlBanner;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @NotNull
  public String getTitulo() {
    return titulo;
  }

  /**
   * Not-null value; ensure this value is available before it is saved to the database.
   */
  public void setTitulo(@NotNull String titulo) {
    this.titulo = titulo;
  }

  @NotNull
  public String getTipoProgramacao() {
    return tipoProgramacao;
  }

  /**
   * Not-null value; ensure this value is available before it is saved to the database.
   */
  public void setTipoProgramacao(@NotNull String tipoProgramacao) {
    this.tipoProgramacao = tipoProgramacao;
  }

  @NotNull
  public String getDescricao() {
    return descricao;
  }

  /**
   * Not-null value; ensure this value is available before it is saved to the database.
   */
  public void setDescricao(@NotNull String descricao) {
    this.descricao = descricao;
  }

  @NotNull
  public java.util.Date getDataInicio() {
    return dataInicio;
  }

  /**
   * Not-null value; ensure this value is available before it is saved to the database.
   */
  public void setDataInicio(@NotNull java.util.Date dataInicio) {
    this.dataInicio = dataInicio;
  }

  @NotNull
  public java.util.Date getDataTermino() {
    return dataTermino;
  }

  /**
   * Not-null value; ensure this value is available before it is saved to the database.
   */
  public void setDataTermino(@NotNull java.util.Date dataTermino) {
    this.dataTermino = dataTermino;
  }

  @NotNull
  public String getLocal() {
    return local;
  }

  /**
   * Not-null value; ensure this value is available before it is saved to the database.
   */
  public void setLocal(@NotNull String local) {
    this.local = local;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getUrlBanner() {
    return urlBanner;
  }

  public void setUrlBanner(String urlBanner) {
    this.urlBanner = urlBanner;
  }

  // KEEP METHODS - put your custom methods here
  // KEEP METHODS END

}
