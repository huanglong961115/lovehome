package com.example.lovehometown.service;

import android.os.Environment;

import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.model.BusinessList;
import com.example.lovehometown.vo.Love;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

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
    //收藏
    public void collect(Love tlove) throws DbException {
        DbManager db  = x.getDb(daoConfig);
        db.save(tlove);

    }
    //取消收藏
    public void delete(Love tlove) throws DbException {
        DbManager db  = x.getDb(daoConfig);
        db.delete(tlove);
    }
    //查询所有收藏的数据
    public void select(Love tlove)throws DbException{
        DbManager db  = x.getDb(daoConfig);
        db.delete(tlove);
    }

}
