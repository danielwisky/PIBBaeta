package br.com.danielwisky.pibbaeta.api.resources.request;

public class DispositivoRequest {

  private String token;

  public DispositivoRequest(final String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }
}
