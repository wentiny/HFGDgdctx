package com.example.peisw.hfgdgdctx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.peisw.hfgdgdctx.Activities.Viewer_Driver;
import com.example.peisw.hfgdgdctx.Activities.Viewer_Managment;
import com.example.peisw.hfgdgdctx.Activities.Viewer_ZycCj;
import com.example.peisw.hfgdgdctx.Activities.Viewer_ZycGq;
import com.example.peisw.hfgdgdctx.utils.method;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static MainActivity instance;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        TextView tv1 = (TextView)findViewById(R.id.textView1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> name = new ArrayList<String>();
                List<String> value = new ArrayList<String>();
                name.add("empid");value.add(getIntent().getStringExtra("empid"));
                String re1 = method.doPost(method.myurl1+"/TXLoginv2",name,value);
                try {
                    JSONObject jtmp1 = new JSONObject(re1);
                    String groupid = jtmp1.optString("groupid");
                    if(groupid.equals("5")){
                        Intent i = new Intent(MainActivity.this,Viewer_Driver.class);
                        i.putExtra("empid",getIntent().getStringExtra("empid"));
                        i.putExtra("empname",getIntent().getStringExtra("empname"));
                        i.putExtra("orgid",getIntent().getStringExtra("orgid"));
                        startActivity(i);
                    }else if(groupid.equals("6")){
                        Intent i = new Intent(MainActivity.this,Viewer_ZycGq.class);
                        i.putExtra("empid",getIntent().getStringExtra("empid"));
                        i.putExtra("empname",getIntent().getStringExtra("empname"));
                        i.putExtra("orgid",getIntent().getStringExtra("orgid"));
                        startActivity(i);
                    }
                    else if(groupid.equals("7")){
                        Intent i = new Intent(MainActivity.this,Viewer_ZycCj.class);
                        i.putExtra("empid",getIntent().getStringExtra("empid"));
                        i.putExtra("empname",getIntent().getStringExtra("empname"));
                        i.putExtra("orgid",getIntent().getStringExtra("orgid"));
                        startActivity(i);
                    }else if(groupid.equals("8")){
                        Intent i = new Intent(MainActivity.this,Viewer_Managment.class);
                        i.putExtra("empid",getIntent().getStringExtra("empid"));
                        i.putExtra("empname",getIntent().getStringExtra("empname"));
                        i.putExtra("orgid",getIntent().getStringExtra("orgid"));
                        startActivity(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });tv1.performClick();
    }



}
