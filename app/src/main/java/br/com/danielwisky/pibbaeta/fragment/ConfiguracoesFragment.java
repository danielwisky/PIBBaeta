package br.com.danielwisky.pibbaeta.fragment;

import static com.google.firebase.messaging.FirebaseMessaging.getInstance;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import br.com.danielwisky.pibbaeta.R;

public class ConfiguracoesFragment extends PreferenceFragment {

  private static final String TOPIC_NOTIFICACAO = "notificacao";

  private static final String EXIBIR_NOTIFICACAO_PROGRAMACAO = "exibir_notificacao_programacao";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.pref_configuracoes);
  }

  @Override
  public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
    switch (preference.getKey()) {
      case EXIBIR_NOTIFICACAO_PROGRAMACAO:

        final boolean notificacao = preference
            .getSharedPreferences()
            .getBoolean(EXIBIR_NOTIFICACAO_PROGRAMACAO, true);

        if (notificacao) {
          getInstance().subscribeToTopic(TOPIC_NOTIFICACAO);
        } else {
          getInstance().unsubscribeFromTopic(TOPIC_NOTIFICACAO);
        }

        break;
    }

    return super.onPreferenceTreeClick(preferenceScreen, preference);
  }
}