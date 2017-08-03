package br.com.danielwisky.pibbaeta.api.sinc;

import android.content.Context;
import br.com.danielwisky.pibbaeta.api.WebClient;
import br.com.danielwisky.pibbaeta.api.dto.ProgramacaoSync;
import br.com.danielwisky.pibbaeta.dao.DaoSession;
import br.com.danielwisky.pibbaeta.dao.Programacao;
import br.com.danielwisky.pibbaeta.dao.ProgramacaoDao;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramacaoSincronizador {

  private final Context context;
  private ProgramacaoDao programacaoDao;

  public ProgramacaoSincronizador(Context context, DaoSession daoSession) {
    this.context = context;
    this.programacaoDao = daoSession.getProgramacaoDao();
  }

  public void sincronizar() {
    Call<ProgramacaoSync> call = new WebClient().getProgramacaoService().listar();
    call.enqueue(buscaProgramacaoCallback());
  }

  private Callback<ProgramacaoSync> buscaProgramacaoCallback() {
    return new Callback<ProgramacaoSync>() {
      @Override
      public void onResponse(Call<ProgramacaoSync> call, Response<ProgramacaoSync> response) {
        ProgramacaoSync programacaoSync = response.body();
        List<Programacao> programacoes = programacaoSync.getProgramacoes();
        salvar(programacoes);
      }

      @Override
      public void onFailure(Call<ProgramacaoSync> call, Throwable t) {

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