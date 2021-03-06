package com.changtai.sqlModelDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.changtai.sqlModel.LoginModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LOGIN_MODEL".
*/
public class LoginModelDao extends AbstractDao<LoginModel, Long> {

    public static final String TABLENAME = "LOGIN_MODEL";

    /**
     * Properties of entity LoginModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property LoginName = new Property(1, String.class, "loginName", false, "LOGIN_NAME");
        public final static Property UserName = new Property(2, String.class, "userName", false, "USER_NAME");
        public final static Property Password = new Property(3, String.class, "password", false, "PASSWORD");
        public final static Property QxString = new Property(4, String.class, "qxString", false, "QX_STRING");
    }


    public LoginModelDao(DaoConfig config) {
        super(config);
    }
    
    public LoginModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LOGIN_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"LOGIN_NAME\" TEXT," + // 1: loginName
                "\"USER_NAME\" TEXT," + // 2: userName
                "\"PASSWORD\" TEXT," + // 3: password
                "\"QX_STRING\" TEXT);"); // 4: qxString
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LOGIN_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LoginModel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String loginName = entity.getLoginName();
        if (loginName != null) {
            stmt.bindString(2, loginName);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(3, userName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(4, password);
        }
 
        String qxString = entity.getQxString();
        if (qxString != null) {
            stmt.bindString(5, qxString);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LoginModel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String loginName = entity.getLoginName();
        if (loginName != null) {
            stmt.bindString(2, loginName);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(3, userName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(4, password);
        }
 
        String qxString = entity.getQxString();
        if (qxString != null) {
            stmt.bindString(5, qxString);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LoginModel readEntity(Cursor cursor, int offset) {
        LoginModel entity = new LoginModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // loginName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // userName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // password
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // qxString
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LoginModel entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLoginName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUserName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPassword(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setQxString(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LoginModel entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LoginModel entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LoginModel entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
