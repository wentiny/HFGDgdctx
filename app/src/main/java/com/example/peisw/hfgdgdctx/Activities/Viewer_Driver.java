package com.example.peisw.hfgdgdctx.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peisw.hfgdgdctx.MainActivity;
import com.example.peisw.hfgdgdctx.R;
import com.example.peisw.hfgdgdctx.utils.DrawTextImageView;
import com.example.peisw.hfgdgdctx.utils.method;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by peisw on 2019/3/25.
 */

public class Viewer_Driver extends Activity {

    int counts = 100,counts02 = 20;
    String[] t1=new String[counts];String[] numid=new String[counts];
    String[] t2=new String[counts];String[] numnm=new String[counts];
    List<String> data1,data2;

    String tmpZycNum,tmpTxnum,sDate,eDate,tmpZmu="nil",strTx="铁鞋编号：";
    LinearLayout ll01,ll02;
    String[] t3=new String[counts02];String[] txid=new String[counts02];
    String[] t4=new String[counts02];String[] txnm=new String[counts02];
    TextView bartv;
    DrawTextImageView imgv4,imgv1,imgv2,imgv3,imgv5,imgv6,imgv7,imgv8,imgv9;
    String[] tempImg={"nil","nil","nil","nil","nil","nil","nil","nil"};

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd");
        final String strDate = smdf.format(new Date());

        data1=new ArrayList<>();data2=new ArrayList<>();

        bartv = (TextView)findViewById(R.id.bartextView);
        final Button barbtn = (Button)findViewById(R.id.barbutton);
        bartv.setText(getIntent().getStringExtra("empname")+"(作业车司机)");
        barbtn.setText("提报");
        ImageView barimgv = (ImageView)findViewById(R.id.barimageView);
        barimgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"默认显示当日提报的和被退回需要再次提交的巡检记录",Toast.LENGTH_LONG).show();
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////////
        barbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> name01 = new ArrayList<String>();
                final List<String> value01 = new ArrayList<String>();
                name01.add("orgid");value01.add(getIntent().getStringExtra("orgid"));
                final String re1 = method.doPost(method.myurl1+"/TXselectNumber",name01,value01);
                try {
                    JSONObject jtmp1 = new JSONObject(re1);
                    for(int i=0;i<counts;i++){
                        t1[i]="numid"+i;numid[i]=jtmp1.optString(t1[i]);
                        t2[i]="numnm"+i;numnm[i]=jtmp1.optString(t2[i]);
                        if(numnm[i]!=""){data1.add(numnm[i]);}
                    }
                } catch (JSONException e) {e.printStackTrace();}

                AlertDialog.Builder builder = new AlertDialog.Builder(Viewer_Driver.this);
                final View childView = View.inflate(Viewer_Driver.this,R.layout.dialog_add_new_recode,null);
                builder.setView(childView).setTitle("新增巡检记录");
                final AlertDialog alertDialog = builder.create();

                imgv4 = (DrawTextImageView)childView.findViewById(R.id.imageView4);
                imgv1 = (DrawTextImageView)childView.findViewById(R.id.imageView);
                imgv2 = (DrawTextImageView)childView.findViewById(R.id.imageView2);
                imgv3 = (DrawTextImageView)childView.findViewById(R.id.imageView3);
                imgv5 = (DrawTextImageView)childView.findViewById(R.id.imageView5);
                imgv6 = (DrawTextImageView)childView.findViewById(R.id.imageView6);
                imgv7 = (DrawTextImageView)childView.findViewById(R.id.imageView7);
                imgv8 = (DrawTextImageView)childView.findViewById(R.id.imageView8);
                imgv9 = (DrawTextImageView)childView.findViewById(R.id.imageView9);
                ll01 = (LinearLayout)childView.findViewById(R.id.linear1);
                ll02 = (LinearLayout)childView.findViewById(R.id.linear2);

                Spinner sp1 = (Spinner)childView.findViewById(R.id.spinner);
                final TextView tv36 = (TextView)childView.findViewById(R.id.textView36);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Viewer_Driver.this,R.layout.shape_item_view,R.id.textViewsp,data1);
                adapter1.setDropDownViewResource(R.layout.shape_dropdown_view);
                sp1.setAdapter(adapter1);
                sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i1, long l) {
                        data2.clear();
                        strTx="铁鞋编号：";
                        tmpZycNum = numid[i1];
                        List<String> name02 = new ArrayList<String>();
                        List<String> value02 = new ArrayList<String>();
                        name02.add("numid");value02.add(tmpZycNum);
                        String re2 = method.doPost(method.myurl1+"/TXselectTx",name02,value02);
                        try {
                            JSONObject jtmp2 = new JSONObject(re2);
                            for(int i=0;i<counts02;i++){
                                t3[i]="txid"+i;txid[i]=jtmp2.optString(t3[i]);
                                t4[i]="txnm"+i;txnm[i]=jtmp2.optString(t4[i]);
                                if(txnm[i]!=""){
                                    data2.add(txnm[i]);
                                    strTx = strTx+txnm[i]+",";
                                }
                            }
                            tv36.setText(strTx);

                            if(data2.size()==5){
                                ll01.setVisibility(View.VISIBLE);
                                ll02.setVisibility(View.VISIBLE);
                                imgv4.setVisibility(View.VISIBLE);
                                imgv1.setVisibility(View.VISIBLE);
                                imgv2.setVisibility(View.VISIBLE);
                                imgv3.setVisibility(View.VISIBLE);
                                imgv9.setVisibility(View.VISIBLE);
                                imgv4.setDrawText("铁鞋1");
                                imgv1.setDrawText("铁鞋2");
                                imgv2.setDrawText("铁鞋3");
                                imgv3.setDrawText("铁鞋4");
                                imgv9.setDrawText("枕木");
                                imgv4.setImageResource(R.drawable.photo);
                                imgv1.setImageResource(R.drawable.photo);
                                imgv2.setImageResource(R.drawable.photo);
                                imgv3.setImageResource(R.drawable.photo);
                                imgv9.setImageResource(R.drawable.photo);
                                imgv4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        takePhoto(imgv4.getId());
                                        galleryAddPic(mImageUri);
                                    }
                                });
                                imgv1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        takePhoto(imgv1.getId());
                                        galleryAddPic(mImageUri);
                                    }
                                });
                                imgv2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        takePhoto(imgv2.getId());
                                        galleryAddPic(mImageUri);
                                    }
                                });
                                imgv3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        takePhoto(imgv3.getId());
                                        galleryAddPic(mImageUri);
                                    }
                                });
                                imgv9.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        takePhoto(imgv9.getId());
                                        galleryAddPic(mImageUri);
                                    }
                                });

                            }

                            if(data2.size()==9){
                                ll01.setVisibility(View.VISIBLE);
                                ll02.setVisibility(View.VISIBLE);
                                imgv4.setVisibility(View.VISIBLE);
                                imgv1.setVisibility(View.VISIBLE);
                                imgv2.setVisibility(View.VISIBLE);
                                imgv3.setVisibility(View.VISIBLE);
                                imgv5.setVisibility(View.VISIBLE);
                                imgv6.setVisibility(View.VISIBLE);
                                imgv7.setVisibility(View.VISIBLE);
                                imgv8.setVisibility(View.VISIBLE);
                                imgv9.setVisibility(View.VISIBLE);
                                imgv4.setDrawText("铁鞋1");
                                imgv1.setDrawText("铁鞋2");
                                imgv2.setDrawText("铁鞋3");
                                imgv3.setDrawText("铁鞋4");
                                imgv5.setDrawText("铁鞋5");
                                imgv6.setDrawText("铁鞋6");
                                imgv7.setDrawText("铁鞋7");
                                imgv8.setDrawText("铁鞋8");
                                imgv9.setDrawText("枕木");
                                imgv4.setImageResource(R.drawable.photo);
                                imgv1.setImageResource(R.drawable.photo);
                                imgv2.setImageResource(R.drawable.photo);
                                imgv3.setImageResource(R.drawable.photo);
                                imgv5.setImageResource(R.drawable.photo);
                                imgv6.setImageResource(R.drawable.photo);
                                imgv7.setImageResource(R.drawable.photo);
                                imgv8.setImageResource(R.drawable.photo);
                                imgv9.setImageResource(R.drawable.photo);
                                imgv4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        takePhoto(imgv4.getId());
                                        galleryAddPic(mImageUri);
                                    }
                                });
                                imgv1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        takePhoto(imgv1.getId());
                                        galleryAddPic(mImageUri);
                                    }
                                });
                                imgv2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        takePhoto(imgv2.getId());
                                        galleryAddPic(mImageUri);
                                    }
                                });
                                imgv3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        takePhoto(imgv3.getId());
                                        galleryAddPic(mImageUri);
                                    }
                                });
                                imgv5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        takePhoto(imgv5.getId());
                                        galleryAddPic(mImageUri);
                                    }
                                });
                                imgv6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        takePhoto(imgv6.getId());
                                        galleryAddPic(mImageUri);
                                    }
                                });
                                imgv7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        takePhoto(imgv7.getId());
                                        galleryAddPic(mImageUri);
                                    }
                                });
                                imgv8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        takePhoto(imgv8.getId());
                                        galleryAddPic(mImageUri);
                                    }
                                });
                                imgv9.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        takePhoto(imgv9.getId());
                                        galleryAddPic(mImageUri);
                                    }
                                });
                            }
                        } catch (JSONException e) {e.printStackTrace();}

                        /*Spinner sp2 = (Spinner)childView.findViewById(R.id.spinner2);
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Viewer_Driver.this,R.layout.shape_item_view,R.id.textViewsp,data2);
                        adapter2.setDropDownViewResource(R.layout.shape_dropdown_view);
                        sp2.setAdapter(adapter2);
                        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long l) {
                                tmpTxnum = txid[i2];
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                tmpTxnum = txid[0];
                            }
                        });*/
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        tmpZycNum = numid[0];
                    }
                });

                TextView tv = (TextView)childView.findViewById(R.id.textView);
                tv.setText("\n巡检人："+getIntent().getStringExtra("empname")+"\n巡检日期："+strDate);

                final EditText ed2 = (EditText)childView.findViewById(R.id.editText2);
                childView.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /*if(tmpImg4.equals("nil")){
                            Toast.makeText(getApplicationContext(),"照片不能为空，请拍照后再上传",Toast.LENGTH_SHORT).show();
                        }else{
                            List<String> name03 = new ArrayList<String>();
                            List<String> value03 = new ArrayList<String>();
                            name03.add("numid");name03.add("txid");name03.add("empname");
                            name03.add("imgstr");name03.add("orgid");name03.add("xjjl");
                            value03.add(tmpZycNum);value03.add(tmpTxnum);value03.add(getIntent().getStringExtra("empname"));
                            value03.add(tmpImg4);value03.add(getIntent().getStringExtra("orgid"));value03.add(ed2.getText().toString());
                            String re3 = method.doPost(method.myurl1+"/TXinsertNewRecode",name03,value03);
                            try {
                                JSONObject jtmp3 = new JSONObject(re3);
                                Toast.makeText(getApplicationContext(),jtmp3.optString("msg"),Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {e.printStackTrace();}
                            alertDialog.dismiss();
                            bartv.performClick();
                        }*/
                        for(int i=0;i<tempImg.length;i++){
                            if(tempImg[i].equals("nil")){
                                Toast.makeText(getApplicationContext(),"照片不能为空，请拍照后再上传",Toast.LENGTH_SHORT).show();
                            }else if(txid[i]!=""){
                                List<String> name03 = new ArrayList<String>();
                                List<String> value03 = new ArrayList<String>();
                                name03.add("numid");name03.add("txid");name03.add("empname");
                                name03.add("imgstr");name03.add("orgid");name03.add("xjjl");
                                value03.add(tmpZycNum);value03.add(txid[i]);value03.add(getIntent().getStringExtra("empname"));
                                value03.add(tempImg[i]);value03.add(getIntent().getStringExtra("orgid"));value03.add(ed2.getText().toString());
                                String re3 = method.doPost(method.myurl1+"/TXinsertNewRecode",name03,value03);
                                try {
                                    JSONObject jtmp3 = new JSONObject(re3);
                                    Toast.makeText(getApplicationContext(),jtmp3.optString("msg"),Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {e.printStackTrace();}

                            }
                        }
                        List<String> name033 = new ArrayList<String>();
                        List<String> value033 = new ArrayList<String>();
                        name033.add("numid");name033.add("txid");name033.add("empname");
                        name033.add("imgstr");name033.add("orgid");name033.add("xjjl");
                        value033.add(tmpZycNum);value033.add(txid[data2.size()-1]);value033.add(getIntent().getStringExtra("empname"));
                        value033.add(tmpZmu);value033.add(getIntent().getStringExtra("orgid"));value033.add(ed2.getText().toString());
                        String re3 = method.doPost(method.myurl1+"/TXinsertNewRecode",name033,value033);
                        try {
                            JSONObject jtmp3 = new JSONObject(re3);
                            Toast.makeText(getApplicationContext(),jtmp3.optString("msg"),Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {e.printStackTrace();}
                        alertDialog.dismiss();
                        bartv.performClick();
                    }
                });

                childView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////
        final ListView lv1 = (ListView)findViewById(R.id.listview1);
        bartv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> name = new ArrayList<String>();
                List<String> value = new ArrayList<String>();
                name.add("orgid");value.add(getIntent().getStringExtra("orgid"));
                String re = method.doPost(method.myurl1+"/TXselect/AllRecode",name,value);
                try {
                    JSONObject jtmp1 = new JSONObject(re);
                    for(int i=0;i<counts;i++){
                        t5[i]="name"+i;empnm[i]=jtmp1.optString(t5[i]);
                        t6[i]="zycnum"+i;zycnum[i]=jtmp1.optString(t6[i]);
                        t7[i]="txnum"+i;txnum[i]=jtmp1.optString(t7[i]);
                        t8[i]="time"+i;time[i]=jtmp1.optString(t8[i]);
                        t9[i]="idgq"+i;idgq[i]=jtmp1.optString(t9[i]);
                        t10[i]="idcj"+i;idcj[i]=jtmp1.optString(t10[i]);
                        //t11[i]="photo"+i;photo[i]=jtmp1.optString(t11[i]);
                        t12[i]="gq"+i;gqpmis[i]=jtmp1.optString(t12[i]);
                        t13[i]="cj"+i;cjpmis[i]=jtmp1.optString(t13[i]);
                        t14[i]="rowid"+i;rowid[i]=jtmp1.optString(t14[i]);
                        t15[i]="xjjl"+i;xjjl[i]=jtmp1.optString(t15[i]);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }lv1.setAdapter(baseAdapter1);
            }
        });
        bartv.performClick();
        //////////////////////////////////////////////////////////////////////////////////////////

        final Button btn23 = (Button)findViewById(R.id.button23);
        final Button btn21 = (Button)findViewById(R.id.button21);

        final Calendar cale1 = Calendar.getInstance();
        btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Viewer_Driver.this,new DatePickerDialog.OnDateSetListener(){
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if(monthOfYear<10&&dayOfMonth<10){sDate = year+"-0"+(monthOfYear+1)+"-0"+dayOfMonth;}
                        if(monthOfYear<10&&dayOfMonth>=10){sDate = year+"-0"+(monthOfYear+1)+"-"+dayOfMonth;}
                        if(monthOfYear>=10&&dayOfMonth<10){sDate = year+"-"+(monthOfYear+1)+"-0"+dayOfMonth;}
                        if(monthOfYear>=10&&dayOfMonth>=10){sDate = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;}
                        btn23.setText(year+"."+(monthOfYear+1)+"."+dayOfMonth);
                    }
                } ,cale1.get(Calendar.YEAR),cale1.get(Calendar.MONTH),cale1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Viewer_Driver.this,new DatePickerDialog.OnDateSetListener(){
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if(monthOfYear<10&&dayOfMonth<10){eDate = year+"-0"+(monthOfYear+1)+"-0"+dayOfMonth;}
                        if(monthOfYear<10&&dayOfMonth>=10){eDate = year+"-0"+(monthOfYear+1)+"-"+dayOfMonth;}
                        if(monthOfYear>=10&&dayOfMonth<10){eDate = year+"-"+(monthOfYear+1)+"-0"+dayOfMonth;}
                        if(monthOfYear>=10&&dayOfMonth>=10){eDate = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;}
                        btn21.setText(year+"."+(monthOfYear+1)+"."+dayOfMonth);
                    }
                } ,cale1.get(Calendar.YEAR),cale1.get(Calendar.MONTH),cale1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Button btn24 = (Button)findViewById(R.id.button24);
        btn24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> name = new ArrayList<String>();
                List<String> value = new ArrayList<String>();
                name.add("orgid");name.add("sdate");name.add("edate");
                value.add(getIntent().getStringExtra("orgid"));
                value.add(sDate);value.add(eDate);
                String re = method.doPost(method.myurl1+"/TXselect/AllRecodeByDate",name,value);
                try {
                    JSONObject jtmp1 = new JSONObject(re);
                    for(int i=0;i<counts;i++){
                        t5[i]="name"+i;empnm[i]=jtmp1.optString(t5[i]);
                        t6[i]="zycnum"+i;zycnum[i]=jtmp1.optString(t6[i]);
                        t7[i]="txnum"+i;txnum[i]=jtmp1.optString(t7[i]);
                        t8[i]="time"+i;time[i]=jtmp1.optString(t8[i]);
                        t9[i]="idgq"+i;idgq[i]=jtmp1.optString(t9[i]);
                        t10[i]="idcj"+i;idcj[i]=jtmp1.optString(t10[i]);
                        //t11[i]="photo"+i;photo[i]=jtmp1.optString(t11[i]);
                        t12[i]="gq"+i;gqpmis[i]=jtmp1.optString(t12[i]);
                        t13[i]="cj"+i;cjpmis[i]=jtmp1.optString(t13[i]);
                        t14[i]="rowid"+i;rowid[i]=jtmp1.optString(t14[i]);
                        t15[i]="xjjl"+i;xjjl[i]=jtmp1.optString(t15[i]);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }lv1.setAdapter(baseAdapter1);
            }
        });

    }

    String[] t5=new String[counts];String[] empnm=new String[counts];
    String[] t6=new String[counts];String[] zycnum=new String[counts];
    String[] t7=new String[counts];String[] txnum=new String[counts];
    String[] t8=new String[counts];String[] time=new String[counts];
    String[] t9=new String[counts];String[] idgq=new String[counts];
    String[] t10=new String[counts];String[] idcj=new String[counts];
    String[] t11=new String[counts];String[] photo=new String[counts];
    String[] t12=new String[counts];String[] gqpmis=new String[counts];
    String[] t13=new String[counts];String[] cjpmis=new String[counts];
    String[] t14=new String[counts];String[] rowid=new String[counts];
    String[] t15=new String[counts];String[] xjjl=new String[counts];

    BaseAdapter baseAdapter1 = new BaseAdapter() {
        @Override
        public int getCount() {
            int index = 0;
            for(int i=0;i<counts;i++){
                if(empnm[i]!=""){index=index+1;}
            }return index;
        }

        @Override
        public Object getItem(int i) {
            return empnm[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            /*LinearLayout ll = (LinearLayout) LayoutInflater.from(Viewer_Driver.this).inflate(R.layout.list_item_view1,null);
            TextView tv6 = (TextView)ll.findViewById(R.id.textView6);
            final Button btn7 = (Button)ll.findViewById(R.id.button7);
            TextView tv16 = (TextView)ll.findViewById(R.id.textView16);
            TextView tv17 = (TextView)ll.findViewById(R.id.textView17);

            if(idgq[i].equals("thui")||idcj[i].equals("thui")){
                tv6.setText("车号:"+zycnum[i]+"/编号:"+txnum[i]+"\n巡检人:"+empnm[i]+"/巡检日期:"+time[i]+"\n巡检记录:"+xjjl[i]+"\n工区审核:"+gqpmis[i]+"\n车间审核:"+cjpmis[i]);
                tv6.setBackgroundResource(R.color.red_f23a3a);
                tv6.setTextColor(Color.WHITE);
                tv16.setBackgroundResource(R.color.red_f23a3a);
                tv17.setBackgroundResource(R.color.red_f23a3a);
                btn7.setText("修改后重新发送");
                btn7.setTextColor(Color.BLACK);
                btn7.setBackgroundResource(R.color.text_avgpre);
                btn7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Viewer_Driver.this);
                        View childview = View.inflate(Viewer_Driver.this,R.layout.dialog_add_new_recode,null);
                        builder.setTitle("修改重新发送").setView(childview);
                        final AlertDialog alertDialog = builder.create();
                        TextView tv3=(TextView)childview.findViewById(R.id.textView3);
                        TextView tv2 = (TextView)childview.findViewById(R.id.textView2);
                        Spinner sp=(Spinner)childview.findViewById(R.id.spinner);
                        Spinner sp2 =(Spinner)childview.findViewById(R.id.spinner2);
                        tv2.setVisibility(View.GONE);tv3.setVisibility(View.GONE);
                        sp.setVisibility(View.GONE);sp2.setVisibility(View.GONE);
                        TextView tv = (TextView)childview.findViewById(R.id.textView);
                        tv.setText("车号:"+zycnum[i]+"/编号:"+txnum[i]+"\n巡检人:"+empnm[i]+"/巡检日期:"+time[i]+"\n巡检记录:"+xjjl[i]+"\n工区审核:"+gqpmis[i]+"\n车间审核:"+cjpmis[i]);

                        imgv4 = (com.example.peisw.hfgdgdctx.utils.DrawTextImageView)childview.findViewById(R.id.imageView4);
                        ll01 = (LinearLayout)childview.findViewById(R.id.linear1);
                        ll01.setVisibility(View.VISIBLE);
                        imgv4.setVisibility(View.VISIBLE);
                        imgv4.setImageResource(R.drawable.photo);
                        imgv4.setDrawText("重新拍照");
                        imgv4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                takePhoto(imgv4.getId());
                                galleryAddPic(mImageUri);
                            }
                        });

                        final EditText ed2 = (EditText)childview.findViewById(R.id.editText2);
                        childview.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                List<String> name03 = new ArrayList<String>();
                                List<String> value03 = new ArrayList<String>();
                                name03.add("rowid");name03.add("imgv");name03.add("xjjl");
                                value03.add(rowid[i]);value03.add(tempImg[0]);value03.add(ed2.getText().toString());
                                String re3 = method.doPost(method.myurl1+"/TXupdate/AgainRecode",name03,value03);
                                try {
                                    JSONObject jtmp3 = new JSONObject(re3);
                                    Toast.makeText(getApplicationContext(),jtmp3.optString("msg"),Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {e.printStackTrace();}
                                alertDialog.dismiss();
                                bartv.performClick();
                            }
                        });

                        childview.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                });
            }else if(idgq[i].equals("false")&&idcj[i].equals("false")){
                tv6.setText("车号:"+zycnum[i]+"/编号:"+txnum[i]+"\n巡检人:"+empnm[i]+"/巡检日期:"+time[i]+"\n巡检记录:"+xjjl[i]+"\n工区未审核");
                tv6.setBackgroundResource(R.color.text_avgpre);
                tv6.setTextColor(Color.BLACK);
                tv16.setBackgroundResource(R.color.text_avgpre);
                tv17.setBackgroundResource(R.color.text_avgpre);
                btn7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Viewer_Driver.this,Viewer_Image.class);
                        intent.putExtra("rowid",rowid[i]);
                        startActivity(intent);
                    }
                });
            }else if(idcj[i].equals("false")&&idgq[i].equals("true")){
                tv6.setText("车号:"+zycnum[i]+"/编号:"+txnum[i]+"\n巡检人:"+empnm[i]+"/巡检日期:"+time[i]+"\n巡检记录:"+xjjl[i]+"\n工区审核:"+gqpmis[i]+"\n车间未审核");
                tv6.setBackgroundResource(R.color.orange_deep);
                tv6.setTextColor(Color.WHITE);
                tv16.setBackgroundResource(R.color.orange_deep);
                tv17.setBackgroundResource(R.color.orange_deep);
                btn7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Viewer_Driver.this,Viewer_Image.class);
                        intent.putExtra("rowid",rowid[i]);
                        startActivity(intent);
                    }
                });
            }else if(idgq[i].equals("true")&&idcj[i].equals("true")){
                tv6.setText("车号:"+zycnum[i]+"/编号:"+txnum[i]+"\n巡检人:"+empnm[i]+"/巡检日期:"+time[i]+"\n巡检记录:"+xjjl[i]+"\n工区审核:"+gqpmis[i]+"\n车间审核:"+cjpmis[i]);
                tv6.setBackgroundResource(R.color.green_3dbf53);
                tv6.setTextColor(Color.WHITE);
                tv16.setBackgroundResource(R.color.green_3dbf53);
                tv17.setBackgroundResource(R.color.green_3dbf53);
                btn7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Viewer_Driver.this,Viewer_Image.class);
                        intent.putExtra("rowid",rowid[i]);
                        startActivity(intent);
                    }
                });
            }*/
            LinearLayout ll = (LinearLayout)LayoutInflater.from(Viewer_Driver.this).inflate(R.layout.list_item_view_new1,null);
            TextView tv44 = (TextView)ll.findViewById(R.id.textView44);
            TextView tv45 = (TextView)ll.findViewById(R.id.textView45);
            TextView tv46 = (TextView)ll.findViewById(R.id.textView46);
            TextView tv47 = (TextView)ll.findViewById(R.id.textView47);
            TextView tv48 = (TextView)ll.findViewById(R.id.textView48);
            if(idgq[i].equals("thui")||idcj[i].equals("thui")){
                tv44.setText("被退回");tv44.setBackgroundResource(R.color.red_f23a3a);tv44.setTextColor(Color.WHITE);
                tv45.setText(zycnum[i]+"/"+txnum[i]);
                tv46.setText(empnm[i]+"/"+time[i]);
                tv47.setText(gqpmis[i]+"/"+cjpmis[i]);
                tv48.setText("修改后重新发送");tv48.setBackgroundResource(R.color.text_avgpre);
                tv48.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Viewer_Driver.this);
                        View childview = View.inflate(Viewer_Driver.this,R.layout.dialog_add_new_recode,null);
                        builder.setTitle("修改重新发送").setView(childview);
                        final AlertDialog alertDialog = builder.create();
                        TextView tv3=(TextView)childview.findViewById(R.id.textView3);
                        TextView tv2 = (TextView)childview.findViewById(R.id.textView2);
                        Spinner sp=(Spinner)childview.findViewById(R.id.spinner);
                        Spinner sp2 =(Spinner)childview.findViewById(R.id.spinner2);
                        tv2.setVisibility(View.GONE);tv3.setVisibility(View.GONE);
                        sp.setVisibility(View.GONE);sp2.setVisibility(View.GONE);
                        TextView tv = (TextView)childview.findViewById(R.id.textView);
                        tv.setText("车号:"+zycnum[i]+"/编号:"+txnum[i]+"\n巡检人:"+empnm[i]+"/巡检日期:"+time[i]+"\n巡检记录:"+xjjl[i]+"\n工区审核:"+gqpmis[i]+"\n车间审核:"+cjpmis[i]);

                        imgv4 = (DrawTextImageView)childview.findViewById(R.id.imageView4);
                        ll01 = (LinearLayout)childview.findViewById(R.id.linear1);
                        ll01.setVisibility(View.VISIBLE);
                        imgv4.setVisibility(View.VISIBLE);
                        imgv4.setImageResource(R.drawable.photo);
                        imgv4.setDrawText("重新拍照");
                        imgv4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                takePhoto(imgv4.getId());
                                galleryAddPic(mImageUri);
                            }
                        });

                        final EditText ed2 = (EditText)childview.findViewById(R.id.editText2);
                        childview.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                List<String> name03 = new ArrayList<String>();
                                List<String> value03 = new ArrayList<String>();
                                name03.add("rowid");name03.add("imgv");name03.add("xjjl");
                                value03.add(rowid[i]);value03.add(tempImg[0]);value03.add(ed2.getText().toString());
                                String re3 = method.doPost(method.myurl1+"/TXupdate/AgainRecode",name03,value03);
                                try {
                                    JSONObject jtmp3 = new JSONObject(re3);
                                    Toast.makeText(getApplicationContext(),jtmp3.optString("msg"),Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {e.printStackTrace();}
                                alertDialog.dismiss();
                                bartv.performClick();
                            }
                        });

                        childview.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                });
            }else if(idgq[i].equals("false")&&idcj[i].equals("false")){
                tv44.setText("工区未审核");tv44.setBackgroundResource(R.color.text_avgpre);tv44.setTextColor(Color.WHITE);
                tv45.setText(zycnum[i]+"/"+txnum[i]);
                tv46.setText(empnm[i]+"/"+time[i]);
                tv47.setText(gqpmis[i]+"/"+cjpmis[i]);
                tv48.setText("照片");tv48.setBackgroundResource(R.color.blue_3a77e2);tv48.setTextColor(Color.WHITE);
                tv48.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Viewer_Driver.this,Viewer_Image.class);
                        intent.putExtra("rowid",rowid[i]);
                        startActivity(intent);
                    }
                });
            }else if(idcj[i].equals("false")&&idgq[i].equals("true")){
                tv44.setText("车间未审核");tv44.setBackgroundResource(R.color.orange);tv44.setTextColor(Color.WHITE);
                tv45.setText(zycnum[i]+"/"+txnum[i]);
                tv46.setText(empnm[i]+"/"+time[i]);
                tv47.setText(gqpmis[i]+"/"+cjpmis[i]);
                tv48.setText("照片");tv48.setBackgroundResource(R.color.blue_3a77e2);tv48.setTextColor(Color.WHITE);
                tv48.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Viewer_Driver.this,Viewer_Image.class);
                        intent.putExtra("rowid",rowid[i]);
                        startActivity(intent);
                    }
                });
            }else if(idgq[i].equals("true")&&idcj[i].equals("true")){
                tv44.setText("流程结束");tv44.setBackgroundResource(R.color.green_3dbf53);tv44.setTextColor(Color.WHITE);
                tv45.setText(zycnum[i]+"/"+txnum[i]);
                tv46.setText(empnm[i]+"/"+time[i]);
                tv47.setText(gqpmis[i]+"/"+cjpmis[i]);
                tv48.setText("照片");tv48.setBackgroundResource(R.color.blue_3a77e2);tv48.setTextColor(Color.WHITE);
                tv48.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Viewer_Driver.this,Viewer_Image.class);
                        intent.putExtra("rowid",rowid[i]);
                        startActivity(intent);
                    }
                });
            }

            return ll;
        }
    };

    Uri mImageUri;
    private void takePhoto(int id){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            File imageFile = createImageFile();
            if(imageFile!=null){
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                    mImageUri = FileProvider.getUriForFile(this,"com.example.peisw.hfgdgdctx.fileprovider",imageFile);
                }else {
                    mImageUri = Uri.fromFile(imageFile);
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT,mImageUri);
                if(id==imgv4.getId()){
                    startActivityForResult(intent,4);
                }else if(id==imgv1.getId()){
                    startActivityForResult(intent,1);
                }else if(id==imgv2.getId()){
                    startActivityForResult(intent,2);
                }else if(id==imgv3.getId()){
                    startActivityForResult(intent,3);
                }else if(id==imgv5.getId()){
                    startActivityForResult(intent,5);
                }else if(id==imgv6.getId()){
                    startActivityForResult(intent,6);
                }else if(id==imgv7.getId()){
                    startActivityForResult(intent,7);
                }else if(id==imgv8.getId()){
                    startActivityForResult(intent,8);
                }else if(id==imgv9.getId()){
                    startActivityForResult(intent,9);
                }

            }
        }
    }

    private File createImageFile(){
        String tempStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_"+tempStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = null;
        try {
            imageFile = File.createTempFile(imageFileName,".jpg",storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }return imageFile;
    }

    private void galleryAddPic(Uri uri){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(uri);
        sendBroadcast(mediaScanIntent);
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode==Activity.RESULT_OK){
            switch (requestCode){
                case 4:
                    try {
                        Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG,20,outputStream );
                        byte[] imagebyte = outputStream.toByteArray();
                        /*for(int i=0;i<data2.size();i++){
                            imgArray[i] = Base64.encodeToString(imagebyte,Base64.URL_SAFE);
                            imgvArray[i].setImageBitmap(bm);
                        }*/
                        tempImg[0] = Base64.encodeToString(imagebyte,Base64.URL_SAFE);
                        imgv4.setImageBitmap(bm);
                    } catch (FileNotFoundException e) {e.printStackTrace();}
                break;
                case 1:
                    try {
                        Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG,20,outputStream );
                        byte[] imagebyte = outputStream.toByteArray();
                        tempImg[1]  = Base64.encodeToString(imagebyte,Base64.URL_SAFE);
                        imgv1.setImageBitmap(bm);
                    } catch (FileNotFoundException e) {e.printStackTrace();}
                    break;
                case 2:
                    try {
                        Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG,20,outputStream );
                        byte[] imagebyte = outputStream.toByteArray();
                        tempImg[2]  = Base64.encodeToString(imagebyte,Base64.URL_SAFE);
                        imgv2.setImageBitmap(bm);
                    } catch (FileNotFoundException e) {e.printStackTrace();}
                    break;
                case 3:
                    try {
                        Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG,20,outputStream );
                        byte[] imagebyte = outputStream.toByteArray();
                        tempImg[3]  = Base64.encodeToString(imagebyte,Base64.URL_SAFE);
                        imgv3.setImageBitmap(bm);
                    } catch (FileNotFoundException e) {e.printStackTrace();}
                    break;
                case 5:
                    try {
                        Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG,20,outputStream );
                        byte[] imagebyte = outputStream.toByteArray();
                        tempImg[4] = Base64.encodeToString(imagebyte,Base64.URL_SAFE);
                        imgv5.setImageBitmap(bm);
                    } catch (FileNotFoundException e) {e.printStackTrace();}
                    break;
                case 6:
                    try {
                        Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG,20,outputStream );
                        byte[] imagebyte = outputStream.toByteArray();
                        tempImg[5]  = Base64.encodeToString(imagebyte,Base64.URL_SAFE);
                        imgv6.setImageBitmap(bm);
                    } catch (FileNotFoundException e) {e.printStackTrace();}
                    break;
                case 7:
                    try {
                        Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG,20,outputStream );
                        byte[] imagebyte = outputStream.toByteArray();
                        tempImg[6] = Base64.encodeToString(imagebyte,Base64.URL_SAFE);
                        imgv7.setImageBitmap(bm);
                    } catch (FileNotFoundException e) {e.printStackTrace();}
                    break;
                case 8:
                    try {
                        Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG,20,outputStream );
                        byte[] imagebyte = outputStream.toByteArray();
                        tempImg[7] = Base64.encodeToString(imagebyte,Base64.URL_SAFE);
                        imgv8.setImageBitmap(bm);
                    } catch (FileNotFoundException e) {e.printStackTrace();}
                    break;
                case 9:
                    try {
                        Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG,20,outputStream );
                        byte[] imagebyte = outputStream.toByteArray();
                        tmpZmu = Base64.encodeToString(imagebyte,Base64.URL_SAFE);
                        imgv9.setImageBitmap(bm);
                    } catch (FileNotFoundException e) {e.printStackTrace();}
                    break;
                default:
                    break;
            }
        }
    }

    public void onBackPressed(){
        super.onBackPressed();
        Viewer_Driver.this.finish();
        MainActivity.instance.finish();
    }

}
