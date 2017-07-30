package br.com.danielwisky.pibbaeta.service;

import br.com.danielwisky.pibbaeta.service.PedidoOracaoService;
import br.com.danielwisky.pibbaeta.service.ProgramacaoService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class WebClient {

  private static final String ENDPOINT_API = "http://IP_SERVER:8080/pibbaeta/rest/";

  private final Retrofit retrofit;

  public WebClient() {

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder client = new OkHttpClient.Builder();
    client.addInterceptor(interceptor);

    retrofit = new Retrofit.Builder()
        .baseUrl(ENDPOINT_API)
        .addConverterFactory(JacksonConverterFactory.create())
        .client(client.build())
        .build();
  }

  public PedidoOracaoService getPedidoOracaoService() {
    return retrofit.create(PedidoOracaoService.class);
  }

  public ProgramacaoService getProgramacaoService() {
    return retrofit.create(ProgramacaoService.class);
  }
}