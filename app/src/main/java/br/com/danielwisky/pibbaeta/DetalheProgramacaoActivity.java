package br.com.danielwisky.pibbaeta;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.danielwisky.pibbaeta.dao.Programacao;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetalheProgramacaoActivity extends AppCompatActivity {

  @BindView(R.id.detalhe_banner)
  ImageView banner;

  @BindView(R.id.detalhe_data)
  TextView data;

  @BindView(R.id.detalhe_local)
  TextView local;

  @BindView(R.id.detalhe_endereco)
  TextView endereco;

  @BindView(R.id.detalhe_descricao)
  TextView descricao;

  @BindView(R.id.detalhe_observacao)
  TextView observacao;

  @BindView(R.id.detalhe_collapsing)
  CollapsingToolbarLayout collapsing;

  private Programacao programacao;

  private final Locale locale = new Locale("pt", "BR");
  private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", locale);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detalhe_programacao);

    ButterKnife.bind(this);

    programacao = getIntent().getParcelableExtra(MainActivity.ARGS_PROGRAMACAO);

    collapsing.setTitle(programacao.getTitulo());

    populaCampos();
  }

  private void populaCampos() {
    if (existeBanner()) {
      Picasso.with(this)
          .load(programacao.getUrlBanner())
          .placeholder(R.drawable.ic_enviar)
          .into(banner);
    }

    String periodo =
        String.format("%1$s - %2$s",
            format.format(programacao.getDataInicio()),
            format.format(programacao.getDataTermino()));

    data.setText(periodo);
    local.setText(programacao.getLocal());
    endereco.setText(programacao.getEndereco());
    descricao.setText(programacao.getDescricao());
    observacao.setText(programacao.getObservacao());
  }

  private boolean existeBanner() {
    return programacao.getUrlBanner() != null && !programacao.getUrlBanner().isEmpty();
  }
}