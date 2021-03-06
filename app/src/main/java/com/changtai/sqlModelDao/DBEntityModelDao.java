package com.changtai.sqlModelDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.changtai.sqlModel.DBEntityModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DBENTITY_MODEL".
*/
public class DBEntityModelDao extends AbstractDao<DBEntityModel, Long> {

    public static final String TABLENAME = "DBENTITY_MODEL";

    /**
     * Properties of entity DBEntityModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "Id", true, "_id");
        public final static Property USER_NAME = new Property(1, String.class, "USER_NAME", false, "USER__NAME");
        public final static Property PASS_WORD = new Property(2, String.class, "PASS_WORD", false, "PASS__WORD");
        public final static Property ADDRESS_ID = new Property(3, int.class, "ADDRESS_ID", false, "ADDRESS__ID");
        public final static Property DEFAULT_NUM = new Property(4, int.class, "DEFAULT_NUM", false, "DEFAULT__NUM");
        public final static Property Token = new Property(5, String.class, "token", false, "TOKEN");
    }


    public DBEntityModelDao(DaoConfig config) {
        super(config);
    }
    
    public DBEntityModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DBENTITY_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: Id
                "\"USER__NAME\" TEXT," + // 1: USER_NAME
                "\"PASS__WORD\" TEXT," + // 2: PASS_WORD
                "\"ADDRESS__ID\" INTEGER NOT NULL ," + // 3: ADDRESS_ID
                "\"DEFAULT__NUM\" INTEGER NOT NULL ," + // 4: DEFAULT_NUM
                "\"TOKEN\" TEXT);"); // 5: token
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DBENTITY_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DBEntityModel entity) {
        stmt.clearBindings();
 
        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }
 
        String USER_NAME = entity.getUSER_NAME();
        if (USER_NAME != null) {
            stmt.bindString(2, USER_NAME);
        }
 
        String PASS_WORD = entity.getPASS_WORD();
        if (PASS_WORD != null) {
            stmt.bindString(3, PASS_WORD);
        }
        stmt.bindLong(4, entity.getADDRESS_ID());
        stmt.bindLong(5, entity.getDEFAULT_NUM());
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(6, token);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DBEntityModel entity) {
        stmt.clearBindings();
 
        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }
 
        String USER_NAME = entity.getUSER_NAME();
        if (USER_NAME != null) {
            stmt.bindString(2, USER_NAME);
        }
 
        String PASS_WORD = entity.getPASS_WORD();
        if (PASS_WORD != null) {
            stmt.bindString(3, PASS_WORD);
        }
        stmt.bindLong(4, entity.getADDRESS_ID());
        stmt.bindLong(5, entity.getDEFAULT_NUM());
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(6, token);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DBEntityModel readEntity(Cursor cursor, int offset) {
        DBEntityModel entity = new DBEntityModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // Id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // USER_NAME
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // PASS_WORD
            cursor.getInt(offset + 3), // ADDRESS_ID
            cursor.getInt(offset + 4), // DEFAULT_NUM
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // token
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DBEntityModel entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUSER_NAME(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPASS_WORD(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setADDRESS_ID(cursor.getInt(offset + 3));
        entity.setDEFAULT_NUM(cursor.getInt(offset + 4));
        entity.setToken(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DBEntityModel entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DBEntityModel entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DBEntityModel entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
