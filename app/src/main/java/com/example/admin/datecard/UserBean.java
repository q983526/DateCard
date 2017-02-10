package com.example.admin.datecard;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.http.annotation.HttpResponse;
import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by admin on 2017/1/23.
 */
@Table(name = "users")
@HttpResponse(parser = UserBean.class)
public class UserBean implements ResponseParser,Serializable{

    /**
     * F_BZ : 徐总
     * U_ID : 541
     * U_TEL : 15945671111
     * U_IMG : /img_user/541/ltTl8Q.png
     * U_HX_ID : lzm541
     * U_TX : /img_user/541/7A9JUm.png
     * U_NC : 徐国栋
     * F_MDR : 0
     * F_TOP : 1
     * F_STAR : 0
     * U_SEX : 男
     * U_TEXT : 改变世界
     * U_PROVINCE : 黑龙江省
     * U_CITY : 哈尔滨市
     * F_BLACK : 0
     */
    @Column(name = "F_BZ")
    public String F_BZ;
    @Column(name = "U_ID",isId = true)
    public String U_ID;
    @Column(name = "U_TEL")
    public String U_TEL;
    public String U_IMG;
    @Column(name = "U_HX_ID")
    public String U_HX_ID;
    @Column(name = "U_TX")
    public String U_TX;
    @Column(name = "U_NC")
    public String U_NC;
    public String F_MDR;
    public String F_TOP;
    public String F_STAR;
    @Column(name = "U_SEX")
    public String U_SEX;
    @Column(name = "U_TEXT")
    public String U_TEXT;
    public String U_PROVINCE;
    public String U_CITY;
    public String F_BLACK;

    @Override
    public void checkResponse(UriRequest uriRequest) throws Throwable {

    }

    @Override
    public Object parse(Type type, Class<?> aClass, String s) throws Throwable {
        Type t = new TypeToken<List<UserBean>>() {
        }.getType();
        return new Gson().fromJson(s,t);
    }

    @Override
    public String toString() {
        return U_ID+"   "+U_NC+"  "+U_TEXT;
    }
}
