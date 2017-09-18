package br.com.danielwisky.pibbaeta.fragment;

import static android.text.TextUtils.isEmpty;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import br.com.danielwisky.pibbaeta.PIBBaetaApplication;
import br.com.danielwisky.pibbaeta.R;
import br.com.danielwisky.pibbaeta.api.client.WebClient;
import br.com.danielwisky.pibbaeta.api.resources.request.FeedbackRequest;
import br.com.danielwisky.pibbaeta.dao.DaoSession;
import br.com.danielwisky.pibbaeta.dao.Feedback;
import br.com.danielwisky.pibbaeta.dao.FeedbackDao;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackFragment extends Fragment {

  public static final String EMPTY = "";

  @BindView(R.id.feedback_descricao)
  EditText descricao;

  private FeedbackDao feedbackDao;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_feedback, container, false);

    ButterKnife.bind(this, view);

    setHasOptionsMenu(true);

    final PIBBaetaApplication application = (PIBBaetaApplication) this.getActivity().getApplication();
    final DaoSession daoSession = application.getDaoSession();
    feedbackDao = daoSession.getFeedbackDao();

    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getActivity().setTitle(R.string.feedback);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_feedback, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.feedback_enviar_feedback:
        enviarFeedback();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  private void enviarFeedback() {
    if (isFormularioValido()) {
      final Feedback feedback = getFeedback();

      Call<Void> enviar = new WebClient().getFeedbackClient().enviar(new FeedbackRequest(feedback));
      enviar.enqueue(new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
          Snackbar
              .make(getView(), getString(R.string.feedback_enviado_sucesso), Snackbar.LENGTH_SHORT)
              .show();
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
          feedbackDao.save(feedback);
          Snackbar
              .make(getView(), getString(R.string.feedback_salvo_sucesso), Snackbar.LENGTH_SHORT)
              .show();
        }
      });

      limparFormulario();
    }
  }

  @NonNull
  private Feedback getFeedback() {
    final Feedback feedback = new Feedback();
    feedback.setDescricao(descricao.getText().toString());
    return feedback;
  }

  private boolean isFormularioValido() {

    boolean valido = true;

    if (isEmpty(descricao.getText())) {
      descricao.setError(getString(R.string.campo_obrigatorio));
      valido = false;
    }

    return valido;
  }

  private void limparFormulario() {
    descricao.setText(EMPTY);
  }
}