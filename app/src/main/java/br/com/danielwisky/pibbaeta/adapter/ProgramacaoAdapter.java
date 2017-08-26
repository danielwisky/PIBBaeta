package br.com.danielwisky.pibbaeta.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.danielwisky.pibbaeta.R;
import br.com.danielwisky.pibbaeta.dao.Programacao;
import br.com.danielwisky.pibbaeta.delegate.ProgramacaoDelegate;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.text.SimpleDateFormat;
import java.util.List;

public class ProgramacaoAdapter extends RecyclerView.Adapter {

  private List<Programacao> programacoes;
  private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM");

  public ProgramacaoAdapter(List<Programacao> programacoes) {
    this.programacoes = programacoes;
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

  @Override
  public int getItemViewType(int position) {
    return R.layout.item_programacao;
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
      tipo.setText(programacao.getTipo());
      data.setText(simpleDateFormat.format(programacao.getDataInicio()));
    }

    @OnClick(R.id.prog_item)
    void clickItem(){
      Programacao programacao = programacoes.get(getAdapterPosition());
      ProgramacaoDelegate delegate = (ProgramacaoDelegate) itemView.getContext();
      delegate.lidaComProgramacaoSelecionada(programacao);
    }
  }
}