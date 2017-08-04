package br.com.danielwisky.pibbaeta.api.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgendaDto {

  private List<ProgramacaoDto> programacoes;
  private String momentoDaUltimaModificacao;

  public List<ProgramacaoDto> getProgramacoes() {
    return programacoes;
  }

  public String getMomentoDaUltimaModificacao() {
    return momentoDaUltimaModificacao;
  }
}
