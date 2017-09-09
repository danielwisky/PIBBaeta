package br.com.danielwisky.pibbaeta.api.resources.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgendaResponse {

  private List<ProgramacaoResponse> programacoes;
  private String dataAtualizacao;

  public List<ProgramacaoResponse> getProgramacoes() {
    return programacoes;
  }

  public String getDataAtualizacao() {
    return dataAtualizacao;
  }
}
