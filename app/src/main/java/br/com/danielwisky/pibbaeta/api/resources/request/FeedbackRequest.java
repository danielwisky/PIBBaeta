package br.com.danielwisky.pibbaeta.api.resources.request;

import br.com.danielwisky.pibbaeta.dao.Feedback;

public class FeedbackRequest {

  private String descricao;

  public FeedbackRequest(final Feedback feedback) {
    this.descricao = feedback.getDescricao();
  }
}