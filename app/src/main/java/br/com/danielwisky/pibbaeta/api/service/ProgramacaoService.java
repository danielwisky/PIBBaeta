package br.com.danielwisky.pibbaeta.api.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import br.com.danielwisky.pibbaeta.api.client.WebClient;
import br.com.danielwisky.pibbaeta.api.resources.response.AgendaResponse;
import br.com.danielwisky.pibbaeta.api.resources.response.ProgramacaoResponse;
import br.com.danielwisky.pibbaeta.dao.DaoSession;
import br.com.danielwisky.pibbaeta.dao.Programacao;
import br.com.danielwisky.pibbaeta.dao.ProgramacaoDao;
import br.com.danielwisky.pibbaeta.dao.ProgramacaoDao.Properties;
import br.com.danielwisky.pibbaeta.event.ProgramacaoEvent;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramacaoService {

  private static final String TAG = ProgramacaoService.class.getSimpleName();
  private static final String PREFERENCES = ProgramacaoService.class.getName();
  private static final String VERSAO_AGENDA = "VERSAO_AGENDA";
  private static final String ATIVO = "ATIVO";
  private static final String EMPTY = "";

  private final Context context;
  private ProgramacaoDao programacaoDao;

  public ProgramacaoService(Context context, DaoSession daoSession) {
    this.context = context;
    this.programacaoDao = daoSession.getProgramacaoDao();
  }

  public void sincronizar(AgendaResponse agendaResponse) {
    if (validaVersao(agendaResponse.getDataAtualizacao())) {
      salvar(agendaResponse);
    }
  }

  public void sincronizar() {
    Call<AgendaResponse> call = temVersao() ?
        new WebClient().getProgramacaoClient().listar(getVersao()) :
        new WebClient().getProgramacaoClient().listar();
    call.enqueue(buscaProgramacaoCallback());
  }

  private Callback<AgendaResponse> buscaProgramacaoCallback() {
    return new Callback<AgendaResponse>() {
      @Override
      public void onResponse(Call<AgendaResponse> call, Response<AgendaResponse> response) {
        AgendaResponse agendaResponse = response.body();
        if (agendaResponse != null) {
          salvar(agendaResponse);
        }
      }

      @Override
      public void onFailure(Call<AgendaResponse> call, Throwable t) {
        Log.e(TAG, t.getMessage());
      }
    };
  }

  private void salvar(AgendaResponse agendaResponse) {
    List<ProgramacaoResponse> programacoes = agendaResponse.getProgramacoes();
    salvar(programacoes);
    setVersao(agendaResponse.getDataAtualizacao());
    EventBus.getDefault().post(new ProgramacaoEvent());
  }

  private void salvar(List<ProgramacaoResponse> programacoes) {
    for (ProgramacaoResponse response : programacoes) {

      Programacao programacao =
          programacaoDao.queryBuilder().where(Properties.IdExterno.eq(response.getId())).unique();

      if (programacao != null) {
        if (ATIVO.equals(response.getStatus())) {
          Programacao model = response.toModel();
          model.setId(programacao.getId());
          programacaoDao.update(model);
        } else {
          programacaoDao.deleteByKey(programacao.getId());
        }
      } else if (ATIVO.equals(response.getStatus())) {
        programacaoDao.insert(response.toModel());
      }
    }
  }

  private void setVersao(final String versao) {
    SharedPreferences preferences = getSharedPreferences();
    SharedPreferences.Editor editor = preferences.edit();
    editor.putString(VERSAO_AGENDA, versao);
    editor.commit();
  }

  private String getVersao() {
    SharedPreferences preferences = getSharedPreferences();
    return preferences.getString(VERSAO_AGENDA, EMPTY);
  }

  private boolean temVersao() {
    return !getVersao().isEmpty();
  }

  private boolean validaVersao(String versao) {
    return true;
  }

  private SharedPreferences getSharedPreferences() {
    return this.context.getSharedPreferences(PREFERENCES, this.context.MODE_PRIVATE);
  }
}