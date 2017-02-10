package com.example.admin.datecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 2017/1/23.
 */
public class UserAdapter extends BaseAdapter {
    private Context context;
    private List<UserBean> list;

    public UserAdapter(Context context, List<UserBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public UserBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserHolder userHolder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.user_itrem,parent,false);
            userHolder=new UserHolder();
            userHolder.iv= (ImageView) convertView.findViewById(R.id.iv);
            userHolder.name= (TextView) convertView.findViewById(R.id.name);
            userHolder.tel= (TextView) convertView.findViewById(R.id.tel);
            convertView.setTag(userHolder);
        }else {
            userHolder= (UserHolder) convertView.getTag();
        }
        UserBean item = getItem(position);
        userHolder.name.setText(item.U_NC);
        userHolder.tel.setText(item.U_TEL);
        LoadImgUtil.loadImg(Urls.BASE_IMG_URL+item.U_TX,context,userHolder.iv);
        return convertView;
    }

    static class UserHolder{
        TextView name,tel;
        ImageView iv;

    }
}
