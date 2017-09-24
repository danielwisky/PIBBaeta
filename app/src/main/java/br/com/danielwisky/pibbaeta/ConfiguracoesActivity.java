package br.com.danielwisky.pibbaeta;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import br.com.danielwisky.pibbaeta.fragment.ConfiguracoesFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.firebase.messaging.FirebaseMessaging;

public class ConfiguracoesActivity extends AppCompatPreferenceActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupActionBar();

    getFragmentManager()
        .beginTransaction()
        .replace(android.R.id.content, new ConfiguracoesFragment())
        .commit();
  }

  private void setupActionBar() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  public boolean onOptionsItemSelected(final MenuItem Item) {
    switch (Item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
    }
    return super.onOptionsItemSelected(Item);
  }
}