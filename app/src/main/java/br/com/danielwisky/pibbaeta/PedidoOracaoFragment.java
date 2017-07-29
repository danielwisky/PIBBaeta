package br.com.danielwisky.pibbaeta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PedidoOracaoFragment extends Fragment {

  @BindView(R.id.p_nome)
  EditText nome;

  @BindView(R.id.p_email)
  EditText email;

  @BindView(R.id.p_telefone)
  EditText telefone;

  @BindView(R.id.p_pedido)
  EditText pedido;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_pedido_oracao, container, false);
    ButterKnife.bind(this, view);

    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getActivity().setTitle(R.string.pedido_oracao);
  }

  @OnClick(R.id.p_enviar_pedido)
  public void enviarPedido() {

    if(TextUtils.isEmpty(nome.getText())) {
      nome.setError(getString(R.string.campo_obrigatorio));
    }

    if(TextUtils.isEmpty(pedido.getText())) {
      pedido.setError(getString(R.string.campo_obrigatorio));
    }

    if(!isValidEmail(email.getText())) {
      email.setError(getString(R.string.email_invalido));
    }
  }

  private boolean isValidEmail(CharSequence email) {
    return TextUtils.isEmpty(email) || Patterns.EMAIL_ADDRESS.matcher(email).matches();
  }
}