package br.com.danielwisky.pibbaeta.dao;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS
// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "PROGRAMACAO".
 */
@Entity
public class Programacao implements Parcelable {

  @Id
  private Long id;

  @NotNull
  private String titulo;

  @NotNull
  private String descricao;

  @NotNull
  private String tipo;

  @NotNull
  private java.util.Date dataInicio;

  @NotNull
  private java.util.Date dataTermino;

  @NotNull
  private String local;
  private String endereco;
  private String urlBanner;
  private String observacao;

  @Index(unique = true)
  private String idExterno;

  // KEEP FIELDS - put your custom fields here
  protected Programacao(Parcel in) {
    id = in.readLong();
    titulo = in.readString();
    descricao = in.readString();
    tipo = in.readString();
    dataInicio = (Date) in.readSerializable();
    dataTermino = (Date) in.readSerializable();
    local = in.readString();
    endereco = in.readString();
    urlBanner = in.readString();
    observacao = in.readString();
    idExterno = in.readString();
  }

  public static final Creator<Programacao> CREATOR = new Creator<Programacao>() {
    @Override
    public Programacao createFromParcel(Parcel in) {
      return new Programacao(in);
    }

    @Override
    public Programacao[] newArray(int size) {
      return new Programacao[size];
    }
  };
  // KEEP FIELDS END

  @Generated(hash = 2057270027)
  public Programacao() {
  }

  public Programacao(Long id) {
    this.id = id;
  }

  @Generated(hash = 381865608)
  public Programacao(Long id, @NotNull String titulo, @NotNull String descricao,
      @NotNull String tipo, @NotNull java.util.Date dataInicio,
      @NotNull java.util.Date dataTermino, @NotNull String local, String endereco,
      String urlBanner, String observacao, String idExterno) {
    this.id = id;
    this.titulo = titulo;
    this.descricao = descricao;
    this.tipo = tipo;
    this.dataInicio = dataInicio;
    this.dataTermino = dataTermino;
    this.local = local;
    this.endereco = endereco;
    this.urlBanner = urlBanner;
    this.observacao = observacao;
    this.idExterno = idExterno;
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
  public String getTipo() {
    return tipo;
  }

  /**
   * Not-null value; ensure this value is available before it is saved to the database.
   */
  public void setTipo(@NotNull String tipo) {
    this.tipo = tipo;
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

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  public String getIdExterno() {
    return idExterno;
  }

  public void setIdExterno(String idExterno) {
    this.idExterno = idExterno;
  }

  // KEEP METHODS - put your custom methods here
  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(id);
    dest.writeString(titulo);
    dest.writeString(descricao);
    dest.writeString(tipo);
    dest.writeSerializable(dataInicio);
    dest.writeSerializable(dataTermino);
    dest.writeString(local);
    dest.writeString(endereco);
    dest.writeString(urlBanner);
    dest.writeString(observacao);
    dest.writeString(idExterno);
  }

  @Override
  public int describeContents() {
    return 0;
  }
  // KEEP METHODS END
}