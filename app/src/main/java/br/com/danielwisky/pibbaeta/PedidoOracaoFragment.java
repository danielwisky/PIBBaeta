package br.com.danielwisky.pibbaeta;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import br.com.danielwisky.pibbaeta.dao.DaoSession;
import br.com.danielwisky.pibbaeta.dao.PedidoOracao;
import br.com.danielwisky.pibbaeta.dao.PedidoOracaoDao;
import br.com.danielwisky.pibbaeta.service.WebClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidoOracaoFragment extends Fragment {

  public static final String EMPTY = "";

  @BindView(R.id.p_nome)
  EditText nome;

  @BindView(R.id.p_email)
  EditText email;

  @BindView(R.id.p_telefone)
  EditText telefone;

  @BindView(R.id.p_pedido)
  EditText pedido;

  private PedidoOracaoDao pedidoOracaoDao;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_pedido_oracao, container, false);
    ButterKnife.bind(this, view);

    PIBBaetaApplication application = (PIBBaetaApplication) this.getActivity().getApplication();
    DaoSession daoSession = application.getDaoSession();
    pedidoOracaoDao = daoSession.getPedidoOracaoDao();

    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getActivity().setTitle(R.string.pedido_oracao);
  }

  @OnClick(R.id.p_enviar_pedido)
  public void enviarPedido() {

    if (isFormularioValido()) {

      final PedidoOracao pedidoOracao = getPedidoOracao();

      Call<Void> enviar = new WebClient().getPedidoOracaoService().enviar(pedidoOracao);
      enviar.enqueue(new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
          Snackbar.make(getView(), getString(R.string.pedido_oracao_enviado_sucesso), Snackbar.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
          pedidoOracaoDao.save(pedidoOracao);
          Snackbar.make(getView(), getString(R.string.pedido_oracao_salvo_sucesso), Snackbar.LENGTH_SHORT).show();
        }
      });

      limparFormulario();
    }
  }

  @NonNull
  private PedidoOracao getPedidoOracao() {
    PedidoOracao pedidoOracao = new PedidoOracao();
    pedidoOracao.setNome(nome.getText().toString());
    pedidoOracao.setEmail(email.getText().toString());
    pedidoOracao.setTelefone(telefone.getText().toString());
    pedidoOracao.setPedido(pedido.getText().toString());
    return pedidoOracao;
  }

  private void limparFormulario() {
    nome.setText(EMPTY);
    email.setText(EMPTY);
    telefone.setText(EMPTY);
    pedido.setText(EMPTY);
  }

  private boolean isFormularioValido() {

    boolean valido = true;

    if(TextUtils.isEmpty(nome.getText())) {
      nome.setError(getString(R.string.campo_obrigatorio));
      valido = false;
    }

    if(TextUtils.isEmpty(pedido.getText())) {
      pedido.setError(getString(R.string.campo_obrigatorio));
      valido = false;
    }

    if(!isEmailValido(email.getText())) {
      email.setError(getString(R.string.email_invalido));
      valido = false;
    }

    return valido;
  }

  private boolean isEmailValido(CharSequence email) {
    return TextUtils.isEmpty(email) || Patterns.EMAIL_ADDRESS.matcher(email).matches();
  }
}