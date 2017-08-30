package br.com.danielwisky.pibbaeta;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import br.com.danielwisky.pibbaeta.dao.Programacao;

public class DetalheProgramacaoActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detalhe_programacao);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    final Programacao programacao = getIntent().getParcelableExtra(MainActivity.ARGS_PROGRAMACAO);
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

}
