package br.com.danielwisky.pibbaeta.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TIPO_PROGRAMACAO".
*/
public class TipoProgramacaoDao extends AbstractDao<TipoProgramacao, Long> {

    public static final String TABLENAME = "TIPO_PROGRAMACAO";

    /**
     * Properties of entity TipoProgramacao.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Descricao = new Property(1, String.class, "descricao", false, "DESCRICAO");
    }


    public TipoProgramacaoDao(DaoConfig config) {
        super(config);
    }
    
    public TipoProgramacaoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TIPO_PROGRAMACAO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"DESCRICAO\" TEXT NOT NULL );"); // 1: descricao
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TIPO_PROGRAMACAO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TipoProgramacao entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getDescricao());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TipoProgramacao entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getDescricao());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TipoProgramacao readEntity(Cursor cursor, int offset) {
        TipoProgramacao entity = new TipoProgramacao( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1) // descricao
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TipoProgramacao entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDescricao(cursor.getString(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TipoProgramacao entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TipoProgramacao entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TipoProgramacao entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}