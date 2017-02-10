package com.example.admin.datecard;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by admin on 2017/1/23.
 */
public class App extends Application {

    private static final int DB_VERSION = 3;
    private static DbManager.DaoConfig daoConfig;
    public static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        initXUtil3();
        initOkHttp();
    }

    private void initOkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }

    private void initXUtil3() {
        x.Ext.init(this);
       daoConfig=new DbManager.DaoConfig()
                .setDbName("lzm.db")
                .setDbVersion(DB_VERSION)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager dbManager) {
                        dbManager.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setTableCreateListener(new DbManager.TableCreateListener() {
                    @Override
                    public void onTableCreated(DbManager dbManager, TableEntity<?> tableEntity) {

                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager dbManager, int i, int i1) {
                        if (i<i1){
                            try {
                                dbManager.dropTable(UserBean.class);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .setAllowTransaction(true);
    }

    public static DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }
}
