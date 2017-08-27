package br.com.danielwisky.pibbaeta;

import static android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import br.com.danielwisky.pibbaeta.dao.Programacao;
import br.com.danielwisky.pibbaeta.delegate.ProgramacaoDelegate;
import br.com.danielwisky.pibbaeta.fragment.DetalheProgramacaoFragment;
import br.com.danielwisky.pibbaeta.fragment.PedidoOracaoFragment;
import br.com.danielwisky.pibbaeta.fragment.ProgramacaoFragment;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity
    implements ProgramacaoDelegate, OnNavigationItemSelectedListener {

  private static final String TOPIC_AGENDA = "agenda";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_AGENDA);

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    displaySelectedScreen(R.id.nav_programacao);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    DetalheProgramacaoFragment fragment = DetalheProgramacaoFragment.newInstance(programacao);
    transaction.replace(R.id.content_frame, fragment);
    transaction.addToBackStack(null);
    transaction.commit();
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
    }

    //replacing the fragment
    if (fragment != null) {
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      ft.replace(R.id.content_frame, fragment);
      ft.commit();
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
  }
}