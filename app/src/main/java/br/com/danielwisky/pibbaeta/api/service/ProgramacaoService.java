package br.com.danielwisky.pibbaeta.api.service;

import android.content.Context;
import br.com.danielwisky.pibbaeta.api.client.WebClient;
import br.com.danielwisky.pibbaeta.api.dto.AgendaDto;
import br.com.danielwisky.pibbaeta.dao.DaoSession;
import br.com.danielwisky.pibbaeta.dao.Programacao;
import br.com.danielwisky.pibbaeta.dao.ProgramacaoDao;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramacaoService {

  private final Context context;
  private ProgramacaoDao programacaoDao;

  public ProgramacaoService(Context context, DaoSession daoSession) {
    this.context = context;
    this.programacaoDao = daoSession.getProgramacaoDao();
  }

  public void sincronizar() {
    Call<AgendaDto> call = new WebClient().getProgramacaoService().listar();
    call.enqueue(buscaProgramacaoCallback());
  }

  private Callback<AgendaDto> buscaProgramacaoCallback() {
    return new Callback<AgendaDto>() {
      @Override
      public void onResponse(Call<AgendaDto> call, Response<AgendaDto> response) {
        AgendaDto agendaDto = response.body();
        //List<Programacao> programacoes = programacaoSync.getProgramacoes();
        //salvar(programacoes);
      }

      @Override
      public void onFailure(Call<AgendaDto> call, Throwable t) {

      }
    };
  }

  private void salvar(List<Programacao> programacoes) {

    for (Programacao programacao : programacoes) {
      Programacao exists = programacaoDao.load(programacao.getId());
      if(exists != null) {

      } else {
        programacaoDao.insert(programacao);
      }
    }
  }
}