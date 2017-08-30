package br.com.danielwisky.pibbaeta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import br.com.danielwisky.pibbaeta.dao.Programacao;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalheProgramacaoActivity extends AppCompatActivity {

  private Programacao programacao;

  @BindView(R.id.prog_detail_data)
  TextView data;

  @BindView(R.id.prog_detail_local)
  TextView local;

  @BindView(R.id.prog_detail_endereco)
  TextView endereco;

  @BindView(R.id.prog_detail_descricao)
  TextView descricao;

  @BindView(R.id.prog_detail_observacao)
  TextView observacao;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detalhe_programacao);

    ButterKnife.bind(this);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    programacao = getIntent().getParcelableExtra(MainActivity.ARGS_PROGRAMACAO);
    setTitle(programacao.getTitulo());

    descricao.setText(programacao.getDescricao());
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

}
