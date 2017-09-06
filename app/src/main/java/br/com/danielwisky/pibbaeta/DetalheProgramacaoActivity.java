package br.com.danielwisky.pibbaeta;

import android.os.Bundle;
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

  private final Locale locale = new Locale("pt", "BR");
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", locale);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detalhe_programacao);

    ButterKnife.bind(this);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    programacao = getIntent().getParcelableExtra(MainActivity.ARGS_PROGRAMACAO);

    setTitle(programacao.getTipo());

    populaCampos();
  }

  private void populaCampos() {
    if (programacao.getUrlBanner() != null
        && !programacao.getUrlBanner().isEmpty()) {
      Picasso.with(this)
          .load(programacao.getUrlBanner())
          .placeholder(R.drawable.ic_enviar)
          .into(banner);
    }

    String periodo =
        String.format("%1$s - %2$s",
            dateFormat.format(programacao.getDataInicio()),
            dateFormat.format(programacao.getDataTermino()));

    data.setText(periodo);
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