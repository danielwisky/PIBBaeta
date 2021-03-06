package br.com.danielwisky.pibbaeta;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

  @BindView(R.id.detalhe_titulo)
  TextView titulo;

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

  @BindView(R.id.detalhe_label_observacao)
  TextView labelObservacao;

  @BindView(R.id.detalhe_collapsing)
  CollapsingToolbarLayout collapsing;

  @BindView(R.id.detalhe_toolbar)
  Toolbar toolbar;

  private Programacao programacao;

  private final Locale locale = new Locale("pt", "BR");
  private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detalhe_programacao);

    ButterKnife.bind(this);

    programacao = getIntent().getParcelableExtra(MainActivity.ARGS_PROGRAMACAO);
    collapsing.setTitle(programacao.getTipo());

    setSupportActionBar(toolbar);

    final ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);

    populaCampos();
  }

  @Override
  public boolean onCreateOptionsMenu(final Menu menu) {
    final MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_detalhe, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem item) {
    switch (item.getItemId()) {
      case R.id.detalhe_mapa:
        abrirMapa();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

  private void populaCampos() {

    if (isNotEmpty(programacao.getUrlBanner())) {
      Picasso.with(this)
          .load(programacao.getUrlBanner())
          .into(banner);
    }

    final String periodo =
        String.format("%1$s - %2$s",
            format.format(programacao.getDataInicio()),
            format.format(programacao.getDataTermino()));

    data.setText(periodo);
    titulo.setText(programacao.getTitulo());
    local.setText(programacao.getLocal());
    endereco.setText(programacao.getEndereco());
    descricao.setText(programacao.getDescricao());

    if (isNotEmpty(programacao.getObservacao())) {
      observacao.setText(programacao.getObservacao());
    } else {
      labelObservacao.setVisibility(View.INVISIBLE);
    }
  }

  private void abrirMapa() {
    final Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse("geo:0,0?q=" + programacao.getEndereco()));
    startActivity(intent);
  }

  private boolean isNotEmpty(final String value) {
    return value != null && !value.isEmpty();
  }
}