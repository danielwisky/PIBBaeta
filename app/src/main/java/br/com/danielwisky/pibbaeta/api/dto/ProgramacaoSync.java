package br.com.danielwisky.pibbaeta.api.dto;

import br.com.danielwisky.pibbaeta.dao.Programacao;
import java.util.List;

public class ProgramacaoSync {

  private List<Programacao> programacoes;
  private String momentoDaUltimaModificacao;

  public String getMomentoDaUltimaModificacao() {
    return momentoDaUltimaModificacao;
  }

  public List<Programacao> getProgramacoes() {
    return programacoes;
  }

}
