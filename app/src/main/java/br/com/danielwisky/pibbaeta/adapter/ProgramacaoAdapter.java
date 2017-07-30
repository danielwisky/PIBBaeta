package br.com.danielwisky.pibbaeta.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.danielwisky.pibbaeta.R;
import br.com.danielwisky.pibbaeta.dao.Programacao;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

public class ProgramacaoAdapter extends RecyclerView.Adapter {

  private List<Programacao> programacoes;

  public ProgramacaoAdapter() {
    this.programacoes = new ArrayList<>();
  }

  @Override
  public ProgramacaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
    return new ProgramacaoViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ProgramacaoViewHolder viewHolder = (ProgramacaoViewHolder) holder;
    Programacao programacao = programacoes.get(position);
    viewHolder.bind(programacao);
  }

  @Override
  public int getItemCount() {
    return programacoes.size();
  }

  public void setProgramacoes(List<Programacao> programacoes) {
    this.programacoes = programacoes;
    notifyDataSetChanged();
  }

  class ProgramacaoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.prog_titulo)
    TextView titulo;

    @BindView(R.id.prog_tipo)
    TextView tipo;

    @BindView(R.id.prog_data)
    TextView data;

    ProgramacaoViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }

    void bind(Programacao programacao){
      titulo.setText(programacao.getTitulo());
      tipo.setText(programacao.getTipoProgramacao());
    }
  }
}