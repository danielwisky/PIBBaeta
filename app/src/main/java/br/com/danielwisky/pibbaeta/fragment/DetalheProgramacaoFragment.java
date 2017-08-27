package br.com.danielwisky.pibbaeta.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.danielwisky.pibbaeta.R;
import br.com.danielwisky.pibbaeta.dao.Programacao;

public class DetalheProgramacaoFragment extends Fragment {

  private static final String ARG_PROGRAMACAO = "programacao";

  private Programacao programacao;

  public static DetalheProgramacaoFragment newInstance(Programacao programacao) {
    DetalheProgramacaoFragment fragment = new DetalheProgramacaoFragment();
    Bundle args = new Bundle();
    args.putParcelable(ARG_PROGRAMACAO, programacao);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_detalhe_programacao, container, false);

    if (getArguments() != null) {
      programacao = getArguments().getParcelable(ARG_PROGRAMACAO);
      getActivity().setTitle(programacao.getTipo());
    }

    return view;
  }
}
