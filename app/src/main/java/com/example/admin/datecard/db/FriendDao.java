package com.example.admin.datecard.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.datecard.UserBean;
import com.example.admin.datecard.db.DbOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/2/8.
 */
public class FriendDao {
    private DbOpenHelper dbOpenHelper;
    public FriendDao(){
        dbOpenHelper=DbOpenHelper.getInstance();
    }
    synchronized public List<UserBean> getAllFriend(){
        List<UserBean> list=new ArrayList<>();
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        db.beginTransaction();
        try {
            Cursor cursor = db.query("friends", null, null, null, null, null, null, null);
            while (cursor.moveToNext()){
                UserBean bean=new UserBean();
                bean.U_ID=cursor.getString(cursor.getColumnIndex("U_ID"));
                bean.U_NC=cursor.getString(cursor.getColumnIndex("U_NC"));
                bean.U_TX=cursor.getString(cursor.getColumnIndex("U_TX"));
                bean.U_HX_ID=cursor.getString(cursor.getColumnIndex("U_HX_ID"));
                bean.U_IMG=cursor.getString(cursor.getColumnIndex("U_IMG"));
                bean.U_SEX=cursor.getString(cursor.getColumnIndex("U_SEX"));
                bean.U_TEL=cursor.getString(cursor.getColumnIndex("U_TEL"));
                bean.U_TEXT=cursor.getString(cursor.getColumnIndex("U_TEXT"));
                bean.F_MDR=cursor.getString(cursor.getColumnIndex("F_MDR"));
                bean.F_STAR=cursor.getString(cursor.getColumnIndex("F_STAR"));
                bean.F_TOP=cursor.getString(cursor.getColumnIndex("F_TOP"));
                list.add(bean);
            }
            cursor.close();
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
            list.clear();
        }finally {
            db.endTransaction();
        }
        return list;
    }
    synchronized public void saveFriendList(List<UserBean> list){
        if (list==null)return;
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
           for (UserBean bean:list){
               ContentValues values=new ContentValues();
               values.put("U_ID",bean.U_ID);
               values.put("U_IMG",bean.U_IMG);
               values.put("U_HX_ID",bean.U_HX_ID);
               values.put("U_NC",bean.U_NC);
               values.put("U_SEX",bean.U_SEX);
               values.put("U_TEL",bean.U_TEL);
               values.put("U_TX",bean.U_TX);
               values.put("U_TEXT",bean.U_TEXT);
               values.put("F_MDR",bean.F_MDR);
               values.put("F_STAR",bean.F_STAR);
               values.put("F_TOP",bean.F_TOP);
               db.replace("friends",null,values);
           }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }
}
