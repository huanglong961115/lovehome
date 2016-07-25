package com.example.lovehometown.service;

import android.os.Environment;

import com.example.lovehometown.constant.Constants;
import com.example.lovehometown.model.BusinessList;
import com.example.lovehometown.vo.Love;
import com.example.lovehometown.vo.Publish;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

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
                .setDbDir(Environment.getExternalStorageDirectory())
                .setDbVersion(4)
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
    //查询是否收藏
    public List<Love> isLove(String mobile,String businessname)throws DbException{
        DbManager db  = x.getDb(daoConfig);
        List<Love> list= db.selector(Love.class).where("usermobile","=",mobile).and("businessname","=",businessname).findAll();
        return list;
    }
    //收藏
    public void collect(Love tlove) throws DbException {
        DbManager db  = x.getDb(daoConfig);
        List<Love> l= isLove(tlove.getUserMobile(),tlove.getBusinessName());
        if (l==null ||l.size()==0){
            db.save(tlove);
        }

    }
    //取消收藏
    public void delete(int loveId) throws DbException {
        DbManager db  = x.getDb(daoConfig);
        db.deleteById(Love.class,loveId);
    }
    //查询所有收藏的数据
    public List<Love> selectLove(String mobile)throws DbException{
        DbManager db  = x.getDb(daoConfig);
        List<Love> list= db.selector(Love.class).where("usermobile","=",mobile).findAll();
        return list;
    }
    //发布
    public void collectPublish(Publish publish) throws DbException {
        DbManager db  = x.getDb(daoConfig);
        db.saveOrUpdate(publish);

    }
    //草稿
    public void drafPublish(Publish publish) throws DbException {
        DbManager db  = x.getDb(daoConfig);
        db.saveOrUpdate(publish);

    }
    //取消发布
    public void deletePublish(int publishId) throws DbException {
        DbManager db  = x.getDb(daoConfig);
        db.deleteById(Publish.class,publishId);
    }
    //查询发布信息
    public List<Publish> selectPublish(String mobile,int publishorlove)throws DbException{
        DbManager db  = x.getDb(daoConfig);
        return  db.selector(Publish.class).where("usermobile","=",mobile).and("publishorlove","=",publishorlove).findAll();
    }
    //显示单条数据
    public  List<Publish> showPublish(int publishId)throws DbException{
        DbManager db=x.getDb(daoConfig);
        return db.selector(Publish.class).where("loveid","=",publishId).findAll();
    }
    //修改编辑
    public void updatePublish(int publishId,Publish publish)throws DbException{
        DbManager db=x.getDb(daoConfig);
        Publish publish1= (Publish) showPublish(publishId);
    }

}
