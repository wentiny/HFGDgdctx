package com.example.peisw.hfgdgdctx.Activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;

import com.example.peisw.hfgdgdctx.R;
import com.example.peisw.hfgdgdctx.utils.method;
import com.example.peisw.hfgdgdctx.utils.zoomImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peisw on 2019/4/3.
 */

public class Viewer_Image extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        final zoomImageView zimgv = (zoomImageView)findViewById(R.id.zoomImageview1);
        TextView tv18 = (TextView)findViewById(R.id.textView18);
        tv18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> name = new ArrayList<String>();
                List<String> value = new ArrayList<String>();
                name.add("rowid");name.add("msg");name.add("empname");
                value.add(getIntent().getStringExtra("rowid"));
                value.add(getIntent().getStringExtra("whichone"));
                value.add(getIntent().getStringExtra("empname"));
                String res = method.doPost("http://218.22.41.234:3011/newlxjiaserver1/TXselect/image",name,value);
                try {
                    JSONObject jtmp = new JSONObject(res);
                    final String imgStr = jtmp.optString("photo");
                    byte[] imageByte1 = Base64.decode(imgStr,Base64.URL_SAFE);
                    Bitmap bitmap1 = BitmapFactory.decodeByteArray(imageByte1,0,imageByte1.length);
                    zimgv.setImage(bitmap1);
                    //Toast.makeText(getApplicationContext(),imgStr,Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });tv18.performClick();



    }

}
