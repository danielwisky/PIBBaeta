package br.com.danielwisky.pibbaeta.api.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgendaDto {

  private List<ProgramacaoDto> programacoes;
  private Date dataAtualizacao;

  public List<ProgramacaoDto> getProgramacoes() {
    return programacoes;
  }

  public Date getDataAtualizacao() {
    return dataAtualizacao;
  }
}
