package com.example.admin.datecard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

public class ShowUserInfoActivity extends AppCompatActivity {

    private UserBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_info);
        String id = getIntent().getStringExtra("id");
        DbManager db = x.getDb(App.getDaoConfig());
        try {
          bean = db.selector(UserBean.class).where(WhereBuilder.b("U_ID", "=", id)).findFirst();
            if (bean==null){
                Toast.makeText(this,"查无此人",Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        ImageView iv= (ImageView) findViewById(R.id.iv);
        TextView name= (TextView) findViewById(R.id.name);
        TextView tel= (TextView) findViewById(R.id.tel);
        TextView sex= (TextView) findViewById(R.id.sex);
        TextView text= (TextView) findViewById(R.id.text);
        LoadImgUtil.loadImg(Urls.BASE_IMG_URL+bean.U_TX,this,iv);
        name.setText(bean.U_NC);
        tel.setText(bean.U_TEL);
        sex.setText(bean.U_SEX);
        text.setText(bean.U_TEXT);

    }
}
