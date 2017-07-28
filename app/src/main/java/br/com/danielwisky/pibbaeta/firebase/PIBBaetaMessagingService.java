package br.com.danielwisky.pibbaeta.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class PIBBaetaMessagingService extends FirebaseMessagingService {

  @Override
  public void onMessageReceived(RemoteMessage remoteMessage) {
    super.onMessageReceived(remoteMessage);

    Map<String, String> mensagem = remoteMessage.getData();
    Log.i("mensagem FCM", String.valueOf(mensagem));
  }
}