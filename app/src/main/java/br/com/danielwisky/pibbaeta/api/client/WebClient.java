package br.com.danielwisky.pibbaeta.api.client;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class WebClient {

  private static final String BASE_URL = "http://192.168.0.12:8080/pibbaeta/rest/";

  private final Retrofit retrofit;

  public WebClient() {

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder client = new OkHttpClient.Builder();
    client.addInterceptor(interceptor);

    retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(JacksonConverterFactory.create())
        .client(client.build())
        .build();
  }

  public PedidoOracaoClient getPedidoOracaoService() {
    return retrofit.create(PedidoOracaoClient.class);
  }

  public ProgramacaoClient getProgramacaoService() {
    return retrofit.create(ProgramacaoClient.class);
  }
}