package br.com.danielwisky.pibbaeta;

import android.app.Application;
import br.com.danielwisky.pibbaeta.dao.DaoMaster;
import br.com.danielwisky.pibbaeta.dao.DaoMaster.DevOpenHelper;
import br.com.danielwisky.pibbaeta.dao.DaoSession;
import org.greenrobot.greendao.database.Database;

public class PIBBaetaApplication extends Application {

  private DaoSession daoSession;

  @Override
  public void onCreate() {
    super.onCreate();

    daoSession = new DaoMaster(db).newSession();
  }

  public DaoSession getDaoSession() {
    return daoSession;
  }
}