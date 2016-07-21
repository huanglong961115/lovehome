package com.example.lovehometown.service;

import android.os.Environment;

import com.example.lovehometown.constant.Constants;

import org.xutils.DbManager;

/**
 * Created by Administrator on 2016/7/16.
 */
public class DBService {
    private static DBService dbService;
    DbManager.DaoConfig daoConfig;

    public static DBService getInstance() {
        if (dbService == null) {
            dbService = new DBService();
        }
        return dbService;
    }

    private DBService() {
        daoConfig = new DbManager.DaoConfig()
                .setDbName(Constants.DB_NAME)
               // .setDbDir(Environment.getExternalStorageDirectory())
                .setDbVersion(1)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                    }
                }).setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });
    }
}
