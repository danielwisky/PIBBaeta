package br.com.danielwisky.pibbaeta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.danielwisky.pibbaeta.dao.Programacao;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;

public class DetalheProgramacaoActivity extends AppCompatActivity {

  @BindView(R.id.prog_detail_banner)
  ImageView banner;

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

  private Programacao programacao;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detalhe_programacao);

    ButterKnife.bind(this);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    programacao = getIntent().getParcelableExtra(MainActivity.ARGS_PROGRAMACAO);

    setTitle(programacao.getTipo());

    if (programacao.getUrlBanner() != null
        && !programacao.getUrlBanner().isEmpty()) {
      Picasso.with(this)
          .load(programacao.getUrlBanner())
          .placeholder(R.drawable.ic_enviar)
          .into(banner);
    }

    local.setText(programacao.getLocal());
    endereco.setText(programacao.getEndereco());
    descricao.setText(programacao.getDescricao());
    observacao.setText(programacao.getObservacao());
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

}
