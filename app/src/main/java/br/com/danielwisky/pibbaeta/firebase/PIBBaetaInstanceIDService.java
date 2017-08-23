package br.com.danielwisky.pibbaeta.firebase;

import android.util.Log;
import br.com.danielwisky.pibbaeta.api.client.WebClient;
import br.com.danielwisky.pibbaeta.api.resources.request.DispositivoRequest;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PIBBaetaInstanceIDService extends FirebaseInstanceIdService {

  private static final String TAG = PIBBaetaInstanceIDService.class.getSimpleName();

  @Override
  public void onTokenRefresh() {
    // Get updated InstanceID token.
    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
    Log.d(TAG, "Refreshed token: " + refreshedToken);

    enviaTokenServidor(refreshedToken);
  }

  private void enviaTokenServidor(final String token) {
    Call<Void> call = new WebClient().getDispositivoService().enviar(new DispositivoRequest(token));
    call.enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        Log.i(TAG, "Token enviado com sucesso.");
      }

      @Override
      public void onFailure(Call<Void> call, Throwable t) {
        Log.e(TAG, t.getMessage());
      }
    });
  }
}