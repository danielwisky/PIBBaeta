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
 * DAO for table "PEDIDO_ORACAO".
*/
public class PedidoOracaoDao extends AbstractDao<PedidoOracao, Long> {

    public static final String TABLENAME = "PEDIDO_ORACAO";

    /**
     * Properties of entity PedidoOracao.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Nome = new Property(1, String.class, "nome", false, "NOME");
        public final static Property Email = new Property(2, String.class, "email", false, "EMAIL");
        public final static Property Telefone = new Property(3, String.class, "telefone", false, "TELEFONE");
        public final static Property Pedido = new Property(4, String.class, "pedido", false, "PEDIDO");
        public final static Property MeuPedido = new Property(5, Boolean.class, "meuPedido", false, "MEU_PEDIDO");
        public final static Property IdExterno = new Property(6, String.class, "idExterno", false, "ID_EXTERNO");
    }


    public PedidoOracaoDao(DaoConfig config) {
        super(config);
    }
    
    public PedidoOracaoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PEDIDO_ORACAO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NOME\" TEXT NOT NULL ," + // 1: nome
                "\"EMAIL\" TEXT," + // 2: email
                "\"TELEFONE\" TEXT," + // 3: telefone
                "\"PEDIDO\" TEXT NOT NULL ," + // 4: pedido
                "\"MEU_PEDIDO\" INTEGER," + // 5: meuPedido
                "\"ID_EXTERNO\" TEXT);"); // 6: idExterno
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_PEDIDO_ORACAO_ID_EXTERNO ON \"PEDIDO_ORACAO\"" +
                " (\"ID_EXTERNO\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PEDIDO_ORACAO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PedidoOracao entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNome());
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(3, email);
        }
 
        String telefone = entity.getTelefone();
        if (telefone != null) {
            stmt.bindString(4, telefone);
        }
        stmt.bindString(5, entity.getPedido());
 
        Boolean meuPedido = entity.getMeuPedido();
        if (meuPedido != null) {
            stmt.bindLong(6, meuPedido ? 1L: 0L);
        }
 
        String idExterno = entity.getIdExterno();
        if (idExterno != null) {
            stmt.bindString(7, idExterno);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PedidoOracao entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNome());
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(3, email);
        }
 
        String telefone = entity.getTelefone();
        if (telefone != null) {
            stmt.bindString(4, telefone);
        }
        stmt.bindString(5, entity.getPedido());
 
        Boolean meuPedido = entity.getMeuPedido();
        if (meuPedido != null) {
            stmt.bindLong(6, meuPedido ? 1L: 0L);
        }
 
        String idExterno = entity.getIdExterno();
        if (idExterno != null) {
            stmt.bindString(7, idExterno);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PedidoOracao readEntity(Cursor cursor, int offset) {
        PedidoOracao entity = new PedidoOracao( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // nome
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // email
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // telefone
            cursor.getString(offset + 4), // pedido
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0, // meuPedido
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // idExterno
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PedidoOracao entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNome(cursor.getString(offset + 1));
        entity.setEmail(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTelefone(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPedido(cursor.getString(offset + 4));
        entity.setMeuPedido(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0);
        entity.setIdExterno(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PedidoOracao entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PedidoOracao entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PedidoOracao entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
