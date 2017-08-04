package br.com.danielwisky.pibbaeta.api.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import br.com.danielwisky.pibbaeta.api.client.WebClient;
import br.com.danielwisky.pibbaeta.api.dto.AgendaDto;
import br.com.danielwisky.pibbaeta.api.dto.ProgramacaoDto;
import br.com.danielwisky.pibbaeta.dao.DaoSession;
import br.com.danielwisky.pibbaeta.dao.Programacao;
import br.com.danielwisky.pibbaeta.dao.ProgramacaoDao;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramacaoService {

  private static final String TAG = ProgramacaoService.class.getSimpleName();
  private static final String PREFERENCES = ProgramacaoService.class.getName();
  private static final String VERSAO_AGENDA = "VERSAO_AGENDA";

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
        List<ProgramacaoDto> programacoes = agendaDto.getProgramacoes();
        salvar(programacoes);
      }

      @Override
      public void onFailure(Call<AgendaDto> call, Throwable t) {
        Log.e(TAG, t.getMessage());
      }
    };
  }

  private void salvar(List<ProgramacaoDto> programacoes) {
    for (ProgramacaoDto dto : programacoes) {
      Programacao exists = programacaoDao.load(dto.getId());
      if(exists != null) {
        if(dto.isAtivo()) {
          programacaoDao.update(dto.toModel());
        } else {
          programacaoDao.deleteByKey(dto.getId());
        }
      } else if(dto.isAtivo()){
        programacaoDao.insert(dto.toModel());
      }
    }
  }

  private void setVersao(Long versao) {
    SharedPreferences preferences = getSharedPreferences();
    SharedPreferences.Editor editor = preferences.edit();
    editor.putLong(VERSAO_AGENDA, versao);
    editor.commit();
  }

  private long getVersao() {
    SharedPreferences preferences = getSharedPreferences();
    return preferences.getLong(VERSAO_AGENDA, 0L);
  }

  private SharedPreferences getSharedPreferences() {
    return this.context.getSharedPreferences(PREFERENCES, this.context.MODE_PRIVATE);
  }
}