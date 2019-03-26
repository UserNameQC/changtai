package com.changtai.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.changtai.sqlModelDao.CardReplacementModelDao;
import com.changtai.sqlModelDao.ConfigModelDao;
import com.changtai.sqlModelDao.DBEntityModelDao;
import com.changtai.sqlModelDao.DaoMaster;
import com.changtai.sqlModelDao.DeviceModelDao;
import com.changtai.sqlModelDao.LoginInformationModelDao;
import com.changtai.sqlModelDao.LoginModelDao;
import com.changtai.sqlModelDao.PriceModelDao;
import com.changtai.sqlModelDao.PurchaseRecordModelDao;
import com.changtai.sqlModelDao.UserModelDao;
import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;

public class SqLiteOpenHelper extends DaoMaster.OpenHelper {

    public SqLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);

        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, CardReplacementModelDao.class, ConfigModelDao.class, DBEntityModelDao.class, DeviceModelDao.class
        , LoginInformationModelDao.class, LoginModelDao.class, PriceModelDao.class, PurchaseRecordModelDao.class, UserModelDao.class);
    }
}
