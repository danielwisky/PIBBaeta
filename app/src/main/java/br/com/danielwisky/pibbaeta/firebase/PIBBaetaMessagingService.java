package br.com.danielwisky.pibbaeta.firebase;

import android.util.Log;
import br.com.danielwisky.pibbaeta.PIBBaetaApplication;
import br.com.danielwisky.pibbaeta.api.resources.response.AgendaResponse;
import br.com.danielwisky.pibbaeta.api.service.ProgramacaoService;
import br.com.danielwisky.pibbaeta.dao.DaoSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.io.IOException;
import java.util.Map;

public class PIBBaetaMessagingService extends FirebaseMessagingService {

  private static final String TAG = PIBBaetaMessagingService.class.getSimpleName();

  public static final String TOPICS_AGENDA = "/topics/agenda";
  public static final String AGENDA_RESPONSE = "agendaResponse";

  @Override
  public void onMessageReceived(RemoteMessage remoteMessage) {
    super.onMessageReceived(remoteMessage);

    final Map<String, String> mensagem = remoteMessage.getData();
    if (TOPICS_AGENDA.equals(remoteMessage.getFrom())) {
      if (mensagem.containsKey(AGENDA_RESPONSE)) {
        sincronizarAgendaResponse(mensagem);
      }
    }
  }

  private void sincronizarAgendaResponse(Map<String, String> mensagem) {
    try {

      final String json = mensagem.get(AGENDA_RESPONSE);
      final ObjectMapper mapper = new ObjectMapper();
      final AgendaResponse agendaResponse = mapper.readValue(json, AgendaResponse.class);

      final PIBBaetaApplication application = (PIBBaetaApplication) this.getApplication();
      final DaoSession daoSession = application.getDaoSession();
      new ProgramacaoService(this, daoSession).sincronizar(agendaResponse);

    } catch (IOException e) {
      Log.e(TAG, e.getMessage());
    }
  }
}