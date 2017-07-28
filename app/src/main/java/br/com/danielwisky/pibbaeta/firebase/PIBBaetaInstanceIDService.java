package br.com.danielwisky.pibbaeta.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class PIBBaetaInstanceIDService extends FirebaseInstanceIdService {

  @Override
  public void onTokenRefresh() {
    // Get updated InstanceID token.
    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
    Log.d("token firebase", "Refreshed token: " + refreshedToken);

    enviaTokenServidor(refreshedToken);
  }

  private void enviaTokenServidor(String refreshedToken) {

  }
}
