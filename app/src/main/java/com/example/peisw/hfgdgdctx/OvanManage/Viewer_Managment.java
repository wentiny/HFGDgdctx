package com.example.peisw.hfgdgdctx.OvanManage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.peisw.hfgdgdctx.MainActivity;
import com.example.peisw.hfgdgdctx.R;

/**
 * Created by peisw on 2019/4/3.
 */

public class Viewer_Managment extends Activity {
    private TextView tv20,tv21,tv69,tv68;
    private FrameLayout frameLayout;
    FirstFragment f1,f2,f3,f4;
    public static Viewer_Managment instance;
    public static String getEmpname;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        instance = this;
        getEmpname = getIntent().getStringExtra("empname");

        TextView tv19 = (TextView)findViewById(R.id.textView19);
        tv19.setText("作业车铁鞋巡检管理("+getIntent().getStringExtra("empname")+")");
        LinearLayout llswitch = (LinearLayout)findViewById(R.id.cjlinearswitch);llswitch.setVisibility(View.GONE);

        tv20 = (TextView)findViewById(R.id.textView20);
        tv21 = (TextView)findViewById(R.id.textView21);
        tv69 = (TextView)findViewById(R.id.textView69);
        tv68 = (TextView)findViewById(R.id.textView68);
        frameLayout = (FrameLayout)findViewById(R.id.framelayout01);
        tv20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//放遛铁鞋巡检记录
                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                hide(transaction);
                setSelectedFalse();
                tv20.setSelected(true);
                if(f1==null){
                    f1 = FirstFragment.newInstance("tab01");
                    transaction.add(R.id.framelayout01,f1);
                }else{
                    transaction.show(f1);
                }transaction.commit();
            }
        });
        tv69.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//燃油申请记录
                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                hide(transaction);
                setSelectedFalse();
                tv69.setSelected(true);
                if(f3==null){
                    f3 = FirstFragment.newInstance("tab03");
                    transaction.add(R.id.framelayout01,f3);
                }else{
                    transaction.show(f3);
                }transaction.commit();
            }
        });
        tv21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//应用管理
                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                hide(transaction);
                setSelectedFalse();
                tv21.setSelected(true);
                if(f2==null){
                    f2 = FirstFragment.newInstance("tab02");
                    transaction.add(R.id.framelayout01,f2);
                }else{
                    transaction.show(f2);
                }transaction.commit();
            }
        });
        tv68.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//燃油申请记录
                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                hide(transaction);
                setSelectedFalse();
                tv68.setSelected(true);
                if(f4==null){
                    f4 = FirstFragment.newInstance("tab04");
                    transaction.add(R.id.framelayout01,f4);
                }else{
                    transaction.show(f4);
                }transaction.commit();
            }
        });
        tv20.performClick();
    }
    public void setSelectedFalse(){
        tv20.setSelected(false);
        tv21.setSelected(false);
        tv69.setSelected(false);
        tv68.setSelected(false);
    }
    private void hide(android.app.FragmentTransaction transaction){
        if(f1!=null){transaction.hide(f1);}
        if(f2!=null){transaction.hide(f2);}
        if(f3!=null){transaction.hide(f3);}
        if(f4!=null){transaction.hide(f4);}
    }

    public void onBackPressed(){
        super.onBackPressed();
        Viewer_Managment.this.finish();
        MainActivity.instance.finish();
    }
}
