package com.example.peisw.hfgdgdctx;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.peisw.hfgdgdctx.utils.method;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peisw on 2019/4/11.
 */

public class Viewer_CkJl extends Activity {
    int count = 100;
    String[] t1=new String[count];String[] ename=new String[count];
    String[] t2=new String[count];String[] etime=new String[count];
    String[] t3=new String[count];String[] msg=new String[count];
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ckjl);

        final ListView lv6 = (ListView)findViewById(R.id.listview6);
        TextView tv37 = (TextView)findViewById(R.id.textView37);
        tv37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> nm = new ArrayList<String>();
                List<String> vl = new ArrayList<String>();
                nm.add("rowid");vl.add(getIntent().getStringExtra("rowid"));
                String res = method.doPost(method.myurl1+"/TXselect/ckjl",nm,vl);
                try {
                    JSONObject j = new JSONObject(res);
                    for(int i=0;i<count;i++){
                        t1[i]="ename"+i;ename[i]=j.optString(t1[i]);
                        t2[i]="etime"+i;etime[i]=j.optString(t2[i]);
                        t3[i]="msg"+i;msg[i]=j.optString(t3[i]);
                    }
                } catch (JSONException e) {e.printStackTrace();}
                lv6.setAdapter(baseAdapter6);
            }
        });tv37.performClick();
    }
    BaseAdapter baseAdapter6 = new BaseAdapter() {
        @Override
        public int getCount() {
            int index = 0;
            for(int i=0;i<count;i++){
                if(ename[i]!=""){index=index+1;}
            }return index;
        }

        @Override
        public Object getItem(int i) {
            return ename[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LinearLayout ll = (LinearLayout) LayoutInflater.from(Viewer_CkJl.this).inflate(R.layout.listview_item_view,null);
            TextView tv42 = (TextView)ll.findViewById(R.id.textView42);
            TextView tc41 = (TextView)ll.findViewById(R.id.textView41);
            TextView tv40 = (TextView)ll.findViewById(R.id.textView40);
            tv42.setText(msg[i]);
            tc41.setText(ename[i]);
            tv40.setText(etime[i]);
            return ll;
        }
    };
}
