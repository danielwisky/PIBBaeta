package br.com.danielwisky.pibbaeta.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.danielwisky.pibbaeta.PIBBaetaApplication;
import br.com.danielwisky.pibbaeta.R;
import br.com.danielwisky.pibbaeta.adapter.ProgramacaoAdapter;
import br.com.danielwisky.pibbaeta.api.service.ProgramacaoService;
import br.com.danielwisky.pibbaeta.dao.DaoSession;
import br.com.danielwisky.pibbaeta.dao.Programacao;
import br.com.danielwisky.pibbaeta.dao.ProgramacaoDao;
import br.com.danielwisky.pibbaeta.dao.ProgramacaoDao.Properties;
import br.com.danielwisky.pibbaeta.event.ProgramacaoEvent;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.query.Query;

public class ProgramacaoFragment extends Fragment {

  @BindView(R.id.prog_programacoes)
  RecyclerView recyclerView;

  private ProgramacaoService programacaoService;
  private ProgramacaoDao programacaoDao;
  private Query<Programacao> programacaoQuery;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_programacao, container, false);
    ButterKnife.bind(this, view);

    setUpViews();

    PIBBaetaApplication application = (PIBBaetaApplication) this.getActivity().getApplication();
    DaoSession daoSession = application.getDaoSession();
    programacaoDao = daoSession.getProgramacaoDao();

    programacaoQuery = programacaoDao.queryBuilder().orderDesc(Properties.DataInicio, Properties.DataTermino).build();

    programacaoService = new ProgramacaoService(this.getContext(), daoSession);
    programacaoService.sincronizar();

    updateProgramacoes();

    return view;
  }

  @Override
  public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override
  public void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getActivity().setTitle(R.string.programacao);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void programacaoEvent(ProgramacaoEvent event) {
    updateProgramacoes();
  }

  private void setUpViews() {
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setHasFixedSize(true);
  }

  private void updateProgramacoes() {
    List<Programacao> programacoes = programacaoQuery.list();
    recyclerView.setAdapter(new ProgramacaoAdapter(programacoes));
  }
}