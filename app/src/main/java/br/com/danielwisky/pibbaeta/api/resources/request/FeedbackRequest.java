package br.com.danielwisky.pibbaeta.api.resources.request;

import br.com.danielwisky.pibbaeta.dao.PedidoOracao;

public class FeedbackRequest {

  private String descricao;

  public FeedbackRequest(final Feedback feedback) {
    this.descricao = feedback.getDescricao();
  }
}