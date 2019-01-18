package com.changtai.sqlModelDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.changtai.sqlModel.CardReeplacementModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CARD_REEPLACEMENT_MODEL".
*/
public class CardReeplacementModelDao extends AbstractDao<CardReeplacementModel, Long> {

    public static final String TABLENAME = "CARD_REEPLACEMENT_MODEL";

    /**
     * Properties of entity CardReeplacementModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CardReplacementId = new Property(1, String.class, "CardReplacementId", false, "CARD_REPLACEMENT_ID");
        public final static Property StationNo = new Property(2, String.class, "StationNo", false, "STATION_NO");
        public final static Property DeviceNo = new Property(3, String.class, "DeviceNo", false, "DEVICE_NO");
        public final static Property Linkman = new Property(4, String.class, "Linkman", false, "LINKMAN");
        public final static Property Phone = new Property(5, String.class, "Phone", false, "PHONE");
        public final static Property Location = new Property(6, String.class, "Location", false, "LOCATION");
        public final static Property AdministratorName = new Property(7, String.class, "AdministratorName", false, "ADMINISTRATOR_NAME");
        public final static Property ServerVersion = new Property(8, Long.class, "ServerVersion", false, "SERVER_VERSION");
        public final static Property ClientVersion = new Property(9, long.class, "ClientVersion", false, "CLIENT_VERSION");
        public final static Property UserName = new Property(10, String.class, "UserName", false, "USER_NAME");
        public final static Property UserNo = new Property(11, String.class, "UserNo", false, "USER_NO");
        public final static Property UsedTotal = new Property(12, String.class, "UsedTotal", false, "USED_TOTAL");
        public final static Property PurchaseTotal = new Property(13, String.class, "PurchaseTotal", false, "PURCHASE_TOTAL");
        public final static Property CreateDateTime = new Property(14, java.util.Date.class, "CreateDateTime", false, "CREATE_DATE_TIME");
        public final static Property LastTotal = new Property(15, String.class, "LastTotal", false, "LAST_TOTAL");
        public final static Property LastDateTime = new Property(16, java.util.Date.class, "LastDateTime", false, "LAST_DATE_TIME");
    }


    public CardReeplacementModelDao(DaoConfig config) {
        super(config);
    }
    
    public CardReeplacementModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CARD_REEPLACEMENT_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"CARD_REPLACEMENT_ID\" TEXT," + // 1: CardReplacementId
                "\"STATION_NO\" TEXT," + // 2: StationNo
                "\"DEVICE_NO\" TEXT," + // 3: DeviceNo
                "\"LINKMAN\" TEXT," + // 4: Linkman
                "\"PHONE\" TEXT," + // 5: Phone
                "\"LOCATION\" TEXT," + // 6: Location
                "\"ADMINISTRATOR_NAME\" TEXT," + // 7: AdministratorName
                "\"SERVER_VERSION\" INTEGER," + // 8: ServerVersion
                "\"CLIENT_VERSION\" INTEGER NOT NULL ," + // 9: ClientVersion
                "\"USER_NAME\" TEXT," + // 10: UserName
                "\"USER_NO\" TEXT," + // 11: UserNo
                "\"USED_TOTAL\" TEXT," + // 12: UsedTotal
                "\"PURCHASE_TOTAL\" TEXT," + // 13: PurchaseTotal
                "\"CREATE_DATE_TIME\" INTEGER," + // 14: CreateDateTime
                "\"LAST_TOTAL\" TEXT," + // 15: LastTotal
                "\"LAST_DATE_TIME\" INTEGER);"); // 16: LastDateTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CARD_REEPLACEMENT_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CardReeplacementModel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String CardReplacementId = entity.getCardReplacementId();
        if (CardReplacementId != null) {
            stmt.bindString(2, CardReplacementId);
        }
 
        String StationNo = entity.getStationNo();
        if (StationNo != null) {
            stmt.bindString(3, StationNo);
        }
 
        String DeviceNo = entity.getDeviceNo();
        if (DeviceNo != null) {
            stmt.bindString(4, DeviceNo);
        }
 
        String Linkman = entity.getLinkman();
        if (Linkman != null) {
            stmt.bindString(5, Linkman);
        }
 
        String Phone = entity.getPhone();
        if (Phone != null) {
            stmt.bindString(6, Phone);
        }
 
        String Location = entity.getLocation();
        if (Location != null) {
            stmt.bindString(7, Location);
        }
 
        String AdministratorName = entity.getAdministratorName();
        if (AdministratorName != null) {
            stmt.bindString(8, AdministratorName);
        }
 
        Long ServerVersion = entity.getServerVersion();
        if (ServerVersion != null) {
            stmt.bindLong(9, ServerVersion);
        }
        stmt.bindLong(10, entity.getClientVersion());
 
        String UserName = entity.getUserName();
        if (UserName != null) {
            stmt.bindString(11, UserName);
        }
 
        String UserNo = entity.getUserNo();
        if (UserNo != null) {
            stmt.bindString(12, UserNo);
        }
 
        String UsedTotal = entity.getUsedTotal();
        if (UsedTotal != null) {
            stmt.bindString(13, UsedTotal);
        }
 
        String PurchaseTotal = entity.getPurchaseTotal();
        if (PurchaseTotal != null) {
            stmt.bindString(14, PurchaseTotal);
        }
 
        java.util.Date CreateDateTime = entity.getCreateDateTime();
        if (CreateDateTime != null) {
            stmt.bindLong(15, CreateDateTime.getTime());
        }
 
        String LastTotal = entity.getLastTotal();
        if (LastTotal != null) {
            stmt.bindString(16, LastTotal);
        }
 
        java.util.Date LastDateTime = entity.getLastDateTime();
        if (LastDateTime != null) {
            stmt.bindLong(17, LastDateTime.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CardReeplacementModel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String CardReplacementId = entity.getCardReplacementId();
        if (CardReplacementId != null) {
            stmt.bindString(2, CardReplacementId);
        }
 
        String StationNo = entity.getStationNo();
        if (StationNo != null) {
            stmt.bindString(3, StationNo);
        }
 
        String DeviceNo = entity.getDeviceNo();
        if (DeviceNo != null) {
            stmt.bindString(4, DeviceNo);
        }
 
        String Linkman = entity.getLinkman();
        if (Linkman != null) {
            stmt.bindString(5, Linkman);
        }
 
        String Phone = entity.getPhone();
        if (Phone != null) {
            stmt.bindString(6, Phone);
        }
 
        String Location = entity.getLocation();
        if (Location != null) {
            stmt.bindString(7, Location);
        }
 
        String AdministratorName = entity.getAdministratorName();
        if (AdministratorName != null) {
            stmt.bindString(8, AdministratorName);
        }
 
        Long ServerVersion = entity.getServerVersion();
        if (ServerVersion != null) {
            stmt.bindLong(9, ServerVersion);
        }
        stmt.bindLong(10, entity.getClientVersion());
 
        String UserName = entity.getUserName();
        if (UserName != null) {
            stmt.bindString(11, UserName);
        }
 
        String UserNo = entity.getUserNo();
        if (UserNo != null) {
            stmt.bindString(12, UserNo);
        }
 
        String UsedTotal = entity.getUsedTotal();
        if (UsedTotal != null) {
            stmt.bindString(13, UsedTotal);
        }
 
        String PurchaseTotal = entity.getPurchaseTotal();
        if (PurchaseTotal != null) {
            stmt.bindString(14, PurchaseTotal);
        }
 
        java.util.Date CreateDateTime = entity.getCreateDateTime();
        if (CreateDateTime != null) {
            stmt.bindLong(15, CreateDateTime.getTime());
        }
 
        String LastTotal = entity.getLastTotal();
        if (LastTotal != null) {
            stmt.bindString(16, LastTotal);
        }
 
        java.util.Date LastDateTime = entity.getLastDateTime();
        if (LastDateTime != null) {
            stmt.bindLong(17, LastDateTime.getTime());
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CardReeplacementModel readEntity(Cursor cursor, int offset) {
        CardReeplacementModel entity = new CardReeplacementModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // CardReplacementId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // StationNo
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // DeviceNo
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Linkman
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // Phone
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // Location
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // AdministratorName
            cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8), // ServerVersion
            cursor.getLong(offset + 9), // ClientVersion
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // UserName
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // UserNo
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // UsedTotal
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // PurchaseTotal
            cursor.isNull(offset + 14) ? null : new java.util.Date(cursor.getLong(offset + 14)), // CreateDateTime
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // LastTotal
            cursor.isNull(offset + 16) ? null : new java.util.Date(cursor.getLong(offset + 16)) // LastDateTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CardReeplacementModel entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCardReplacementId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStationNo(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDeviceNo(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setLinkman(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPhone(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setLocation(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setAdministratorName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setServerVersion(cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8));
        entity.setClientVersion(cursor.getLong(offset + 9));
        entity.setUserName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setUserNo(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setUsedTotal(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setPurchaseTotal(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setCreateDateTime(cursor.isNull(offset + 14) ? null : new java.util.Date(cursor.getLong(offset + 14)));
        entity.setLastTotal(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setLastDateTime(cursor.isNull(offset + 16) ? null : new java.util.Date(cursor.getLong(offset + 16)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CardReeplacementModel entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CardReeplacementModel entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CardReeplacementModel entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
