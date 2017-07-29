package br.com.danielwisky.pibbaeta;

import android.app.Application;
import br.com.danielwisky.pibbaeta.dao.DaoMaster;
import br.com.danielwisky.pibbaeta.dao.DaoMaster.DevOpenHelper;
import br.com.danielwisky.pibbaeta.dao.DaoSession;
import org.greenrobot.greendao.database.Database;

public class PIBBaetaApplication extends Application {

  /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
  public static final boolean ENCRYPTED = false;

  private DaoSession daoSession;

  @Override
  public void onCreate() {
    super.onCreate();

    DevOpenHelper helper = new DevOpenHelper(this, ENCRYPTED ? "pibbaeta-db-encrypted" : "pibbaeta-db");
    Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
    daoSession = new DaoMaster(db).newSession();
  }

  public DaoSession getDaoSession() {
    return daoSession;
  }
}