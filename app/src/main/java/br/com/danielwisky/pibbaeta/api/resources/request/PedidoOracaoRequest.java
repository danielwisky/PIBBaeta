package br.com.danielwisky.pibbaeta.api.resources.request;

import br.com.danielwisky.pibbaeta.dao.PedidoOracao;

public class PedidoOracaoRequest {

  private String nome;
  private String email;
  private String telefone;
  private String pedido;

  public PedidoOracaoRequest(PedidoOracao pedidoOracao) {
    this.nome = pedidoOracao.getNome();
    this.email = pedidoOracao.getEmail();
    this.telefone = pedidoOracao.getTelefone();
    this.pedido = pedidoOracao.getPedido();
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public String getTelefone() {
    return telefone;
  }

  public String getPedido() {
    return pedido;
  }
}