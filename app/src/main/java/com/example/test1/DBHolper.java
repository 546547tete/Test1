package com.example.test1;

import com.example.bean.BeanDao;
import com.example.test1.db.BeanDaoDao;
import com.example.test1.db.DaoMaster;
import com.example.test1.db.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class DBHolper {
    private static volatile DBHolper ourInstance = new DBHolper();
    private final BeanDaoDao beanDaoDao;

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
        if (query(beanDao)) {
            long insert = beanDaoDao.insert(beanDao);
            return true;
        } else {
            return false;
        }
    }

    private boolean query(BeanDao beanDao) {
        List<BeanDao> list = beanDaoDao.queryBuilder().where(BeanDaoDao.Properties.Name.eq(beanDao.getName())).list();
        if (list != null && list.size() >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private List<BeanDao> queryAll() {
        List<BeanDao> list = beanDaoDao.loadAll();
        return list;
    }

    private boolean upData(BeanDao beanDao){
        if (query(beanDao)){
            beanDaoDao.updateInTx(beanDao);
            return true;
        }
        return false;
    }
}
