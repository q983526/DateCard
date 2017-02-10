package com.example.admin.datecard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.datecard.db.FriendDao;
import com.example.admin.datecard.http.FriendResponse;
import com.zhy.http.okhttp.OkHttpUtils;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private UserAdapter adapter;
    private List<UserBean> list;
    private LoadDialog mLoadDialog;
    private FriendDao friendDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadDialog=new LoadDialog(this);
        listView= (ListView) findViewById(R.id.list);
        list=new ArrayList<>();
        adapter=new UserAdapter(this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserBean item = adapter.getItem(position);
                Intent intent=new Intent(MainActivity.this,ShowUserInfoActivity.class);
                intent.putExtra("id",item.U_ID);
                startActivity(intent);
            }
        });
        friendDao=new FriendDao();
        getFriends("551");
    }

    private void getFriends(String uId) {
        List<UserBean> allFriend = friendDao.getAllFriend();
        if (allFriend.size()>0){
            for (int i = 0; i < allFriend.size(); i++) {
                UserBean bean = allFriend.get(i);
                Log.e("db",bean.toString());
            }
        }

        showPrograss();
        OkHttpUtils.get()
                .addParams("uId",uId)
                .url(Urls.GET_ALL_CONTACT_INFO_URL)
                .tag(this)
                .build().execute(new FriendResponse(){
            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
                hidePrograss();
            }

            @Override
            public void onResponse(List<UserBean> response, int id) {
                super.onResponse(response, id);
                hidePrograss();
                list.clear();
                list.addAll(response);
                adapter.notifyDataSetChanged();
                friendDao.saveFriendList(response);
            }
        });
    }


    private void showPrograss(){
        if (!mLoadDialog.isShowing()){
            mLoadDialog.show();
        }
    }
    private void hidePrograss(){
        if (mLoadDialog.isShowing()){
            mLoadDialog.dismiss();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
