package com.example.nice.everywhere.util;

import com.example.nice.everywhere.base.BaseApp;
import com.example.nice.everywhere.bean.BanmiDaoBean;
import com.example.nice.everywhere.dao.BanmiDaoBeanDao;
import com.example.nice.everywhere.dao.DaoMaster;
import com.example.nice.everywhere.dao.DaoSession;

import java.util.List;

public class DbUtils {

    private static DbUtils dbUtils;
    private final BanmiDaoBeanDao banmiDaoBeanDao;

    public DbUtils() {

        //1.
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApp.getsBaseApp(), "list.db");
        //2.
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        //3.
        DaoSession daoSession = daoMaster.newSession();
        //4.
        banmiDaoBeanDao = daoSession.getBanmiDaoBeanDao();
    }


    public static DbUtils getDbUtils() {
        if (dbUtils == null) {
            synchronized (DbUtils.class) {
                if (dbUtils == null) {
                    dbUtils = new DbUtils();
                }
            }
        }
        return dbUtils;
    }


    //插入所有
    public void insertAll(BanmiDaoBean banmiDaoBean) {
        if (has(banmiDaoBean)) {
            return;
        }
        banmiDaoBeanDao.insertOrReplace(banmiDaoBean);
    }

    //删除
    public void delete(BanmiDaoBean banmiDaoBean) {
        if (has(banmiDaoBean)) {
            banmiDaoBeanDao.delete(banmiDaoBean);
        }
    }

    //查询
    public List<BanmiDaoBean> query() {
        return banmiDaoBeanDao.queryBuilder().list();
    }

    //判断是否存在
    public boolean has(BanmiDaoBean banmiDaoBean) {
        List<BanmiDaoBean> list = banmiDaoBeanDao.queryBuilder().where(BanmiDaoBeanDao.Properties.Name.eq(banmiDaoBean.getName())).list();

        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

}
