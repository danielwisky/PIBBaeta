package br.com.danielwisky.pibbaeta;

import android.app.Application;
import br.com.danielwisky.pibbaeta.dao.DaoMaster;
import br.com.danielwisky.pibbaeta.dao.DaoMaster.DevOpenHelper;
import br.com.danielwisky.pibbaeta.dao.DaoSession;
import org.greenrobot.greendao.database.Database;

public class PIBBaetaApplication extends Application {

  private static final String PIBBAETA_DB = "pibbaeta-db";

  private DaoSession daoSession;

  @Override
  public void onCreate() {
    super.onCreate();
    final DevOpenHelper helper = new DevOpenHelper(this, PIBBAETA_DB);
    final Database db = helper.getWritableDb();
    daoSession = new DaoMaster(db).newSession();
  }

  public DaoSession getDaoSession() {
    return daoSession;
  }
}