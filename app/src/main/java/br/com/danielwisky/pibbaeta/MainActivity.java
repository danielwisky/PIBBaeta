package br.com.danielwisky.pibbaeta;

import static android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import br.com.danielwisky.pibbaeta.dao.Programacao;
import br.com.danielwisky.pibbaeta.delegate.ProgramacaoDelegate;
import br.com.danielwisky.pibbaeta.fragment.FeedbackFragment;
import br.com.danielwisky.pibbaeta.fragment.PedidoOracaoFragment;
import br.com.danielwisky.pibbaeta.fragment.ProgramacaoFragment;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity
    implements ProgramacaoDelegate, OnNavigationItemSelectedListener {

  private static final String TOPIC_AGENDA = "agenda";
  public static final String ARGS_PROGRAMACAO = "programacao";

  private DrawerLayout drawer;
  private ActionBarDrawerToggle toggle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_AGENDA);

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    displaySelectedScreen(R.id.nav_programacao);
  }

  @Override
  public void onBackPressed() {
    drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    //calling the method displayselectedscreen and passing the id of selected menu
    displaySelectedScreen(item.getItemId());
    //make this method blank
    return true;
  }

  @Override
  public void lidaComProgramacaoSelecionada(Programacao programacao) {

    final Bundle args = new Bundle();
    final Intent intent = new Intent(this, DetalheProgramacaoActivity.class);

    args.putParcelable(ARGS_PROGRAMACAO, programacao);
    intent.putExtras(args);

    startActivity(intent);
  }

  private void displaySelectedScreen(int itemId) {

    // Creating fragment object
    Fragment fragment = null;
    switch (itemId) {
      case R.id.nav_programacao:
        fragment = new ProgramacaoFragment();
        break;
      case R.id.nav_pedido_oracao:
        fragment = new PedidoOracaoFragment();
        break;
      case R.id.nav_configuracao:
        startActivity(new Intent(this, ConfiguracoesActivity.class));
        break;
      case R.id.nav_feedback:
        fragment = new FeedbackFragment();
        break;
    }

    //replacing the fragment
    if (fragment != null) {
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.content_frame, fragment)
          .commit();
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
  }
}