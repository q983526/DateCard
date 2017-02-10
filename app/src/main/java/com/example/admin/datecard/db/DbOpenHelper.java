package com.example.admin.datecard.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.datecard.App;

/**
 * Created by admin on 2017/2/8.
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION=2;
    private DbOpenHelper(Context context) {
        super(context, "qz.db", null, DB_VERSION);
    }
    private static DbOpenHelper dbOpenHelper;
    public static DbOpenHelper getInstance(){

        if (dbOpenHelper==null){
            synchronized (DbOpenHelper.class){
             if (dbOpenHelper==null){
                 dbOpenHelper=new DbOpenHelper(App.instance.getApplicationContext());
             }
            }
        }
        return dbOpenHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tables.CREATE_FRIEND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion==1){
            db.execSQL("alter table friends add column F_MDR TEXT");
            db.execSQL("alter table friends add column F_TOP TEXT");
            db.execSQL("alter table friends add column F_STAR TEXT");
        }
    }

    private static class Tables{
        public static final String CREATE_FRIEND_TABLE=" create table friends (U_TEL TEXT,U_IMG TEXT,U_HX_ID TEXT,U_TX TEXT,U_NC TEXT,U_SEX TEXT,U_TEXT TEXT,F_MDR TEXT,F_TOP TEXT,F_STAR TEXT,U_ID TEXT PRIMARY KEY); ";
    }

}
