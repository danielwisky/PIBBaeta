package br.com.danielwisky.pibbaeta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.danielwisky.pibbaeta.adapter.ProgramacaoAdapter;
import br.com.danielwisky.pibbaeta.dao.DaoSession;
import br.com.danielwisky.pibbaeta.dao.Programacao;
import br.com.danielwisky.pibbaeta.dao.ProgramacaoDao;
import br.com.danielwisky.pibbaeta.dao.ProgramacaoDao.Properties;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;
import org.greenrobot.greendao.query.Query;

public class ProgramacaoFragment extends Fragment {

  @BindView(R.id.prog_programacoes)
  RecyclerView recyclerView;

  private ProgramacaoDao programacaoDao;
  private Query<Programacao> programacaoQuery;
  private ProgramacaoAdapter programacaoAdapter;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_programacao, container, false);
    ButterKnife.bind(this, view);

    setUpViews();

    // get the programacao DAO
    PIBBaetaApplication application = (PIBBaetaApplication) this.getActivity().getApplication();
    DaoSession daoSession = application.getDaoSession();
    programacaoDao = daoSession.getProgramacaoDao();

    programacaoQuery = programacaoDao.queryBuilder().orderDesc(Properties.DataInicio, Properties.DataTermino).build();

    updateProgramacoes();

    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getActivity().setTitle(R.string.programacao);
  }

  private void setUpViews() {
    programacaoAdapter = new ProgramacaoAdapter();
    recyclerView.setAdapter(programacaoAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setHasFixedSize(true);
  }

  private void updateProgramacoes() {
    List<Programacao> programacoes = programacaoQuery.list();
    programacaoAdapter.setProgramacoes(programacoes);
  }
}