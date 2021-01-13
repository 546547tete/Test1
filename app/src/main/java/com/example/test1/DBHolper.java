package com.example.test1;

import android.util.Log;

import com.example.bean.BeanDao;
import com.example.test1.db.BeanDaoDao;
import com.example.test1.db.DaoMaster;
import com.example.test1.db.DaoSession;

import java.util.List;

public class DBHolper {
    private static volatile DBHolper ourInstance = new DBHolper();
    private final BeanDaoDao beanDaoDao;
    private static final String TAG = "DBHolper";
    public static DBHolper getInstance() {
        if (ourInstance == null) {
            synchronized (DBHolper.class) {
                if (ourInstance == null) {
                    ourInstance = new DBHolper();
                }
            }
        }
        return ourInstance;
    }

    private DBHolper() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApp.getBaseApp(), "ceshi.db");

        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());

        DaoSession daoSession = daoMaster.newSession();

        beanDaoDao = daoSession.getBeanDaoDao();
    }

    public boolean insert(BeanDao beanDao) {
            beanDaoDao.insertOrReplace(beanDao);
            return true;
    }

    private boolean query(BeanDao beanDao) {
        List<BeanDao> list = beanDaoDao.queryBuilder().where(BeanDaoDao.Properties.Name.eq(beanDao.getName())).list();
        Log.e(TAG, "query: "+list.size());
        if (list.size() >= 0) {
            return true;
        }
            return false;

    }

    public List<BeanDao> queryAll() {
        List<BeanDao> list = beanDaoDao.loadAll();
        return list;
    }

    public boolean upData(BeanDao beanDao){
        if (query(beanDao)){
            Log.e(TAG, "upData: " +beanDao);
            beanDaoDao.updateInTx(beanDao);
            return true;
        }
        return false;
    }
}
