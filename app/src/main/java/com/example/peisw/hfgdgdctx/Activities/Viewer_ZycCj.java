package com.example.peisw.hfgdgdctx.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peisw.hfgdgdctx.MainActivity;
import com.example.peisw.hfgdgdctx.R;
import com.example.peisw.hfgdgdctx.utils.method;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by peisw on 2019/4/3.
 */

public class Viewer_ZycCj extends Activity {

    int counts = 200,count2 = 20;
    String[] t1=new String[counts];String[] empnm=new String[counts];
    String[] t2=new String[counts];String[] zycnum=new String[counts];
    String[] t3=new String[counts];String[] txnum=new String[counts];
    String[] t4=new String[counts];String[] time=new String[counts];
    String[] t5=new String[counts];String[] idgq=new String[counts];
    String[] t6=new String[counts];String[] idcj=new String[counts];
    String[] t7=new String[counts];String[] orgnm=new String[counts];
    String[] t8=new String[counts];String[] gqpmis=new String[counts];
    String[] t9=new String[counts];String[] cjpmis=new String[counts];
    String[] t10=new String[counts];String[] rowid=new String[counts];
    String[] t11=new String[counts];String[] xjjl=new String[counts];
    TextView tv14;

    String[] t15=new String[count2];String[] gqid=new String[count2];
    String[] t16=new String[count2];String[] gqnm=new String[count2];
    List<String> data9;
    String tmpGqId,tmpSdate,tmpEdate,tmpMultiGqId;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zyccj);

        data9 = new ArrayList<>();
        ///////////////////////////////////////////////////////////////////////////////////////
        final ListView lv3 = (ListView)findViewById(R.id.listview3);
        tv14 = (TextView)findViewById(R.id.textView14);
        tv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String res = method.doPostWithoutValue(method.myurl1+"/TXselect/ZycCj/AllRecode");
                try {
                    JSONObject jtmp = new JSONObject(res);
                    for(int i=0;i<counts;i++){
                        t1[i]="name"+i;empnm[i]=jtmp.optString(t1[i]);
                        t2[i]="zycnum"+i;zycnum[i]=jtmp.optString(t2[i]);
                        t3[i]="txnum"+i;txnum[i]=jtmp.optString(t3[i]);
                        t4[i]="time"+i;time[i]=jtmp.optString(t4[i]);
                        t5[i]="idgq"+i;idgq[i]=jtmp.optString(t5[i]);
                        t6[i]="idcj"+i;idcj[i]=jtmp.optString(t6[i]);
                        t7[i]="orgnm"+i;orgnm[i]=jtmp.optString(t7[i]);
                        t8[i]="gq"+i;gqpmis[i]=jtmp.optString(t8[i]);
                        t9[i]="cj"+i;cjpmis[i]=jtmp.optString(t9[i]);
                        t10[i]="rowid"+i;rowid[i]=jtmp.optString(t10[i]);
                        t11[i]="xjjl"+i;xjjl[i]=jtmp.optString(t11[i]);
                    }
                } catch (JSONException e) {e.printStackTrace();}
                lv3.setAdapter(baseAdapter3);
            }
        });tv14.performClick();
        ///////////////////////////////////////////////////////////////////////////////////////
        TextView tv31=(TextView)findViewById(R.id.textView31);
        tv31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res = method.doPostWithoutValue(method.myurl1+"/TXselect/ZycGqu");
                try {
                    JSONObject j = new JSONObject(res);
                    for(int i=0;i<count2;i++){
                        t15[i]="gqid"+i;gqid[i]=j.optString(t15[i]);
                        t16[i]="gqnm"+i;gqnm[i]=j.optString(t16[i]);
                        if(gqnm[i]!=""){data9.add(gqnm[i]);}
                    }
                } catch (JSONException e) {e.printStackTrace();}

                Spinner sp9 = (Spinner)findViewById(R.id.spinner9);
                ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(Viewer_ZycCj.this,R.layout.shape_item_spinner,R.id.sPtextView,data9);
                adapter9.setDropDownViewResource(R.layout.shape_item_dropdown);
                sp9.setAdapter(adapter9);
                sp9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i9, long l) {
                        tmpGqId = gqid[i9];
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        tmpGqId = gqid[0];
                    }
                });

            }
        });tv31.performClick();
        ///////////////////////////////////////////////////////////////////////////////////////
        final Calendar cale1 = Calendar.getInstance();
        final Button btn33 = (Button)findViewById(R.id.button33);
        btn33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Viewer_ZycCj.this,new DatePickerDialog.OnDateSetListener(){
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if(monthOfYear<10&&dayOfMonth<10){tmpSdate = year+"-0"+(monthOfYear+1)+"-0"+dayOfMonth;}
                        if(monthOfYear<10&&dayOfMonth>=10){tmpSdate = year+"-0"+(monthOfYear+1)+"-"+dayOfMonth;}
                        if(monthOfYear>=10&&dayOfMonth<10){tmpSdate = year+"-"+(monthOfYear+1)+"-0"+dayOfMonth;}
                        if(monthOfYear>=10&&dayOfMonth>=10){tmpSdate = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;}
                        btn33.setText(year+"."+(monthOfYear+1)+"."+dayOfMonth);
                    }
                } ,cale1.get(Calendar.YEAR),cale1.get(Calendar.MONTH),cale1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final Button btn34 = (Button)findViewById(R.id.button34);
        btn34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Viewer_ZycCj.this,new DatePickerDialog.OnDateSetListener(){
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if(monthOfYear<10&&dayOfMonth<10){tmpEdate = year+"-0"+(monthOfYear+1)+"-0"+dayOfMonth;}
                        if(monthOfYear<10&&dayOfMonth>=10){tmpEdate = year+"-0"+(monthOfYear+1)+"-"+dayOfMonth;}
                        if(monthOfYear>=10&&dayOfMonth<10){tmpEdate = year+"-"+(monthOfYear+1)+"-0"+dayOfMonth;}
                        if(monthOfYear>=10&&dayOfMonth>=10){tmpEdate = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;}
                        btn34.setText(year+"."+(monthOfYear+1)+"."+dayOfMonth);
                    }
                } ,cale1.get(Calendar.YEAR),cale1.get(Calendar.MONTH),cale1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        findViewById(R.id.button32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> nm = new ArrayList<String>();
                List<String> vl = new ArrayList<String>();
                nm.add("gqid");nm.add("sdate");nm.add("edate");
                vl.add(tmpGqId);vl.add(tmpSdate);vl.add(tmpEdate);
                String res = method.doPost(method.myurl1+"/TXselect/ZycCj/AllRecodeByGqId",nm,vl);
                try {
                    JSONObject jtmp = new JSONObject(res);
                    for(int i=0;i<counts;i++){
                        t1[i]="name"+i;empnm[i]=jtmp.optString(t1[i]);
                        t2[i]="zycnum"+i;zycnum[i]=jtmp.optString(t2[i]);
                        t3[i]="txnum"+i;txnum[i]=jtmp.optString(t3[i]);
                        t4[i]="time"+i;time[i]=jtmp.optString(t4[i]);
                        t5[i]="idgq"+i;idgq[i]=jtmp.optString(t5[i]);
                        t6[i]="idcj"+i;idcj[i]=jtmp.optString(t6[i]);
                        t7[i]="orgnm"+i;orgnm[i]=jtmp.optString(t7[i]);
                        t8[i]="gq"+i;gqpmis[i]=jtmp.optString(t8[i]);
                        t9[i]="cj"+i;cjpmis[i]=jtmp.optString(t9[i]);
                        t10[i]="rowid"+i;rowid[i]=jtmp.optString(t10[i]);
                        t11[i]="xjjl"+i;xjjl[i]=jtmp.optString(t11[i]);
                    }
                } catch (JSONException e) {e.printStackTrace();}
                lv3.setAdapter(baseAdapter3);

            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////
        Button btn35 = (Button)findViewById(R.id.button35);
        btn35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Viewer_ZycCj.this);
                View cv = View.inflate(Viewer_ZycCj.this,R.layout.dialog_multi_permiss,null);
                builder.setView(cv).setTitle("按作业车工区批量审核");
                final AlertDialog alertDialog = builder.create();

                Spinner sp8 = (Spinner)cv.findViewById(R.id.spinner8);
                ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(Viewer_ZycCj.this,R.layout.shape_item_spinner,R.id.sPtextView,data9);
                adapter8.setDropDownViewResource(R.layout.shape_item_dropdown);
                sp8.setAdapter(adapter8);
                sp8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i8, long l) {
                        tmpMultiGqId = gqid[i8];
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        tmpMultiGqId = gqid[0];
                    }
                });

                final ListView lv5 = (ListView)cv.findViewById(R.id.listview5);
                Button btn29 = (Button)cv.findViewById(R.id.button29);
                btn29.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<String> nm = new ArrayList<String>();
                        List<String> vl = new ArrayList<String>();
                        nm.add("gqid");vl.add(tmpMultiGqId);
                        String res = method.doPost(method.myurl1+"/TXselect/ZycCj/AllRecodeByMulti",nm,vl);
                        try {
                            JSONObject jtmp = new JSONObject(res);
                            for(int i=0;i<counts;i++){
                                t1[i]="name"+i;empnm[i]=jtmp.optString(t1[i]);
                                t2[i]="zycnum"+i;zycnum[i]=jtmp.optString(t2[i]);
                                t3[i]="txnum"+i;txnum[i]=jtmp.optString(t3[i]);
                                t4[i]="time"+i;time[i]=jtmp.optString(t4[i]);
                                t5[i]="idgq"+i;idgq[i]=jtmp.optString(t5[i]);
                                t6[i]="idcj"+i;idcj[i]=jtmp.optString(t6[i]);
                                t7[i]="orgnm"+i;orgnm[i]=jtmp.optString(t7[i]);
                                t8[i]="gq"+i;gqpmis[i]=jtmp.optString(t8[i]);
                                t9[i]="cj"+i;cjpmis[i]=jtmp.optString(t9[i]);
                                t10[i]="rowid"+i;rowid[i]=jtmp.optString(t10[i]);
                                t11[i]="xjjl"+i;xjjl[i]=jtmp.optString(t11[i]);
                            }
                        } catch (JSONException e) {e.printStackTrace();}
                        lv5.setAdapter(baseAdapter5);
                    }
                });

                final EditText ed3 = (EditText)cv.findViewById(R.id.editText3);
                cv.findViewById(R.id.button30).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<String> nm = new ArrayList<String>();
                        List<String> vl = new ArrayList<String>();
                        nm.add("gqid");nm.add("pmis");
                        vl.add(tmpMultiGqId);vl.add(ed3.getText().toString());
                        String res = method.doPost(method.myurl1+"/TXupdate/CjPermissByMulti",nm,vl);
                        try {
                            JSONObject j = new JSONObject(res);
                            Toast.makeText(getApplicationContext(),j.optString("msg"),Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {e.printStackTrace();}
                        tv14.performClick();
                        alertDialog.dismiss();
                    }
                });

                cv.findViewById(R.id.button31).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<String> nm = new ArrayList<String>();
                        List<String> vl = new ArrayList<String>();
                        nm.add("gdid");nm.add("pmis");
                        vl.add(tmpMultiGqId);vl.add(ed3.getText().toString());
                        String res = method.doPost(method.myurl1+"/TXupdate/GqMultiTuiHui",nm,vl);
                        try {
                            JSONObject j = new JSONObject(res);
                            Toast.makeText(getApplicationContext(),j.optString("msg"),Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {e.printStackTrace();}
                        tv14.performClick();
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();

            }
        });


    }

    BaseAdapter baseAdapter3 = new BaseAdapter() {
        @Override
        public int getCount() {
            int index = 0;
            for(int i=0;i<counts;i++){
                if(empnm[i]!=""){index=index+1;}
            }return index;
        }
        @Override
        public Object getItem(int i) {return empnm[i];}
        @Override
        public long getItemId(int i) {return i;}
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            /*LinearLayout ll = (LinearLayout) LayoutInflater.from(Viewer_ZycCj.this).inflate(R.layout.list_item_view02,null);
            TextView tv9 = (TextView)ll.findViewById(R.id.textView9);

            TextView tv10 = (TextView)ll.findViewById(R.id.textView10);
            TextView tv11 = (TextView)ll.findViewById(R.id.textView11);
            TextView tv12 = (TextView)ll.findViewById(R.id.textView12);

            Button btn12 = (Button)ll.findViewById(R.id.button12);
            Button btn11 = (Button)ll.findViewById(R.id.button11);
            if(idgq[i].equals("true")&&idcj[i].equals("false")){
                tv9.setText("车号:"+zycnum[i]+"/铁鞋编号:"+txnum[i]+"\n巡检人:"+empnm[i]+"//岗点:"+orgnm[i]+" /巡检日期:"+time[i]+"\n巡检记录:"+xjjl[i]+"\n工区审核:"+gqpmis[i]+"\n车间未审核");
                tv9.setBackgroundResource(R.color.text_avgpre);
                tv9.setTextColor(Color.BLACK);
                tv9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Viewer_ZycCj.this,Viewer_CkJl.class);
                        intent.putExtra("rowid",rowid[i]);
                        startActivity(intent);
                    }
                });
                tv10.setBackgroundResource(R.color.text_avgpre);
                tv11.setBackgroundResource(R.color.text_avgpre);
                tv12.setBackgroundResource(R.color.text_avgpre);
                btn12.setVisibility(View.VISIBLE);
                btn12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Viewer_ZycCj.this);
                        View childview = View.inflate(Viewer_ZycCj.this,R.layout.dialog_permiss_view,null);
                        builder.setTitle("").setView(childview);
                        AlertDialog alertDialog = builder.create();

                        final EditText ed1 = (EditText)childview.findViewById(R.id.editText);
                        TextView tv13 = (TextView)childview.findViewById(R.id.textView13);
                        tv13.setText("车号:"+zycnum[i]+"/铁鞋编号:"+txnum[i]+"\n巡检人:"+empnm[i]+"//岗点:"+orgnm[i]+" /巡检日期:"+time[i]+"巡检记录:"+xjjl[i]);

                        Button btn14 = (Button)childview.findViewById(R.id.button14);
                        btn14.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                List<String> name02 = new ArrayList<String>();
                                List<String> value02 = new ArrayList<String>();
                                name02.add("rowid");name02.add("pmis");
                                value02.add(rowid[i]);value02.add("审核通过:"+ed1.getText().toString()+","+getIntent().getStringExtra("empname")+dateStr);
                                String res02 = method.doPost(method.myurl1+"/TXupdate/CjPermiss",name02,value02);
                                try {
                                    JSONObject jtmp02 = new JSONObject(res02);
                                    Toast.makeText(getApplicationContext(),jtmp02.optString("msg"),Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {e.printStackTrace();}
                                tv14.performClick();
                            }
                        });

                        Button btn15 = (Button)childview.findViewById(R.id.button15);//退回
                        btn15.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                List<String> name03 = new ArrayList<String>();
                                List<String> value03 = new ArrayList<String>();
                                name03.add("rowid");name03.add("pmis");
                                value03.add(rowid[i]);value03.add("车间退回:"+ed1.getText().toString()+","+getIntent().getStringExtra("empname")+dateStr);
                                String res02 = method.doPost(method.myurl1+"/TXupdate/CjTuiHui",name03,value03);
                                try {
                                    JSONObject jtmp02 = new JSONObject(res02);
                                    Toast.makeText(getApplicationContext(),jtmp02.optString("msg"),Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {e.printStackTrace();}
                                tv14.performClick();
                            }
                        });
                        alertDialog.show();
                    }
                });
                btn11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Viewer_ZycCj.this,Viewer_Image.class);
                        intent.putExtra("rowid",rowid[i]);
                        intent.putExtra("whichone",zycnum[i]+"-"+txnum[i]);
                        intent.putExtra("empname",getIntent().getStringExtra("empname"));
                        startActivity(intent);
                    }
                });
            }else if(idcj[i].equals("true")){
                tv9.setText("车号:"+zycnum[i]+"/铁鞋编号:"+txnum[i]+"\n巡检人:"+empnm[i]+"//岗点:"+orgnm[i]+" /巡检日期:"+time[i]+"\n巡检记录:"+xjjl[i]+"\n工区审核:"+gqpmis[i]+"\n车间审核:"+cjpmis[i]);
                tv9.setTextColor(Color.BLACK);
                tv9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Viewer_ZycCj.this,Viewer_CkJl.class);
                        intent.putExtra("rowid",rowid[i]);
                        startActivity(intent);
                    }
                });
                tv9.setBackgroundResource(R.color.green_3dbf53);
                tv10.setBackgroundResource(R.color.green_3dbf53);
                tv11.setBackgroundResource(R.color.green_3dbf53);
                tv12.setBackgroundResource(R.color.green_3dbf53);
                // btn12.setVisibility(View.INVISIBLE);
                btn12.setBackgroundResource(R.color.green_3dbf53);
                btn12.setText("");
                btn11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Viewer_ZycCj.this,Viewer_Image.class);
                        intent.putExtra("rowid",rowid[i]);
                        intent.putExtra("whichone",zycnum[i]+"-"+txnum[i]);
                        intent.putExtra("empname",getIntent().getStringExtra("empname"));
                        startActivity(intent);
                    }
                });
            }else if(idcj[i].equals("thui")){
                tv9.setText("车号:"+zycnum[i]+"/铁鞋编号:"+txnum[i]+"\n巡检人:"+empnm[i]+"//岗点:"+orgnm[i]+" /巡检日期:"+time[i]+"\n巡检记录:"+xjjl[i]+"\n工区审核:"+gqpmis[i]+"\n车间审核:"+cjpmis[i]);
                tv9.setTextColor(Color.BLACK);
                tv9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Viewer_ZycCj.this,Viewer_CkJl.class);
                        intent.putExtra("rowid",rowid[i]);
                        startActivity(intent);
                    }
                });
                tv9.setBackgroundResource(R.color.red_f23a3a);
                tv10.setBackgroundResource(R.color.red_f23a3a);
                tv11.setBackgroundResource(R.color.red_f23a3a);
                tv12.setBackgroundResource(R.color.red_f23a3a);
                //btn12.setVisibility(View.INVISIBLE);
                btn12.setBackgroundResource(R.color.red_f23a3a);
                btn12.setText("");
                btn11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Viewer_ZycCj.this,Viewer_Image.class);
                        intent.putExtra("rowid",rowid[i]);
                        intent.putExtra("whichone",zycnum[i]+"-"+txnum[i]);
                        intent.putExtra("empname",getIntent().getStringExtra("empname"));
                        startActivity(intent);
                    }
                });
            }else{
                tv9.setText("车号:"+zycnum[i]+"/铁鞋编号:"+txnum[i]+"\n巡检人:"+empnm[i]+"//岗点:"+orgnm[i]+" /巡检日期:"+time[i]+"\n巡检记录:"+xjjl[i]+"\n工区审核:"+gqpmis[i]);
                tv9.setTextColor(Color.BLACK);
                tv9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Viewer_ZycCj.this,Viewer_CkJl.class);
                        intent.putExtra("rowid",rowid[i]);
                        startActivity(intent);
                    }
                });
                btn11.setVisibility(View.GONE);
                btn12.setVisibility(View.GONE);
            }*/
            LinearLayout ll = (LinearLayout)LayoutInflater.from(Viewer_ZycCj.this).inflate(R.layout.list_item_view_new2,null);
            TextView tv54 = (TextView)ll.findViewById(R.id.textView54);
            TextView tv55 = (TextView)ll.findViewById(R.id.textView55);
            TextView tv56 = (TextView)ll.findViewById(R.id.textView56);
            TextView tv57 = (TextView)ll.findViewById(R.id.textView57);
            TextView tv58 = (TextView)ll.findViewById(R.id.textView58);
            TextView tv59 = (TextView)ll.findViewById(R.id.textView59);
            if(idgq[i].equals("true")&&idcj[i].equals("false")){
                tv54.setText("未审核");tv54.setBackgroundResource(R.color.text_avgpre);tv54.setTextColor(Color.WHITE);
                tv55.setText(zycnum[i]+"/"+txnum[i]);
                tv56.setText(empnm[i]+"/"+time[i]);
                tv57.setText(gqpmis[i]+"/"+cjpmis[i]);
                tv58.setText("审核");tv58.setBackgroundResource(R.color.green_3dbf53);tv58.setTextColor(Color.WHITE);
                tv59.setText("照片");tv59.setBackgroundResource(R.color.blue_3a77e2);tv58.setTextColor(Color.WHITE);
                tv58.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Viewer_ZycCj.this);
                        View childview = View.inflate(Viewer_ZycCj.this,R.layout.dialog_permiss_view,null);
                        builder.setTitle("").setView(childview);
                        AlertDialog alertDialog = builder.create();

                        final EditText ed1 = (EditText)childview.findViewById(R.id.editText);
                        TextView tv13 = (TextView)childview.findViewById(R.id.textView13);
                        tv13.setText("车号:"+zycnum[i]+"/铁鞋编号:"+txnum[i]+"\n巡检人:"+empnm[i]+"//岗点:"+orgnm[i]+" /巡检日期:"+time[i]+"巡检记录:"+xjjl[i]);

                        Button btn14 = (Button)childview.findViewById(R.id.button14);
                        btn14.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                List<String> name02 = new ArrayList<String>();
                                List<String> value02 = new ArrayList<String>();
                                name02.add("rowid");name02.add("pmis");
                                value02.add(rowid[i]);value02.add("审核通过:"+ed1.getText().toString()+","+getIntent().getStringExtra("empname")+dateStr);
                                String res02 = method.doPost(method.myurl1+"/TXupdate/CjPermiss",name02,value02);
                                try {
                                    JSONObject jtmp02 = new JSONObject(res02);
                                    Toast.makeText(getApplicationContext(),jtmp02.optString("msg"),Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {e.printStackTrace();}
                                tv14.performClick();
                            }
                        });

                        Button btn15 = (Button)childview.findViewById(R.id.button15);//退回
                        btn15.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                List<String> name03 = new ArrayList<String>();
                                List<String> value03 = new ArrayList<String>();
                                name03.add("rowid");name03.add("pmis");
                                value03.add(rowid[i]);value03.add("车间退回:"+ed1.getText().toString()+","+getIntent().getStringExtra("empname")+dateStr);
                                String res02 = method.doPost(method.myurl1+"/TXupdate/CjTuiHui",name03,value03);
                                try {
                                    JSONObject jtmp02 = new JSONObject(res02);
                                    Toast.makeText(getApplicationContext(),jtmp02.optString("msg"),Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {e.printStackTrace();}
                                tv14.performClick();
                            }
                        });
                        alertDialog.show();
                    }
                });
                tv59.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Viewer_ZycCj.this,Viewer_Image.class);
                        intent.putExtra("rowid",rowid[i]);
                        intent.putExtra("whichone",zycnum[i]+"-"+txnum[i]);
                        intent.putExtra("empname",getIntent().getStringExtra("empname"));
                        startActivity(intent);
                    }
                });
            }else if(idcj[i].equals("true")){
                tv54.setText("流程结束");tv54.setBackgroundResource(R.color.green_3dbf53);tv54.setTextColor(Color.WHITE);
                tv55.setText(zycnum[i]+"/"+txnum[i]);
                tv56.setText(empnm[i]+"/"+time[i]);
                tv57.setText(gqpmis[i]+"/"+cjpmis[i]);
                tv58.setVisibility(View.INVISIBLE);
                tv59.setText("照片");tv59.setBackgroundResource(R.color.blue_3a77e2);tv58.setTextColor(Color.WHITE);
                tv59.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Viewer_ZycCj.this,Viewer_Image.class);
                        intent.putExtra("rowid",rowid[i]);
                        intent.putExtra("whichone",zycnum[i]+"-"+txnum[i]);
                        intent.putExtra("empname",getIntent().getStringExtra("empname"));
                        startActivity(intent);
                    }
                });
            }else if(idcj[i].equals("thui")){
                tv54.setText("被退回");tv54.setBackgroundResource(R.color.red_f23a3a);tv54.setTextColor(Color.WHITE);
                tv55.setText(zycnum[i]+"/"+txnum[i]);
                tv56.setText(empnm[i]+"/"+time[i]);
                tv57.setText(gqpmis[i]+"/"+cjpmis[i]);
                tv58.setVisibility(View.INVISIBLE);
                tv59.setText("照片");tv59.setBackgroundResource(R.color.blue_3a77e2);tv58.setTextColor(Color.WHITE);
                tv59.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Viewer_ZycCj.this,Viewer_Image.class);
                        intent.putExtra("rowid",rowid[i]);
                        intent.putExtra("whichone",zycnum[i]+"-"+txnum[i]);
                        intent.putExtra("empname",getIntent().getStringExtra("empname"));
                        startActivity(intent);
                    }
                });
            }else{
                tv54.setText("工区未审核");
                tv55.setText(zycnum[i]+"/"+txnum[i]);
                tv56.setText(empnm[i]+"/"+time[i]);
                tv57.setText(gqpmis[i]+"/"+cjpmis[i]);
                tv58.setVisibility(View.INVISIBLE);
                tv59.setText("照片");tv59.setBackgroundResource(R.color.blue_3a77e2);tv58.setTextColor(Color.WHITE);
                tv59.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Viewer_ZycCj.this,Viewer_Image.class);
                        intent.putExtra("rowid",rowid[i]);
                        intent.putExtra("whichone",zycnum[i]+"-"+txnum[i]);
                        intent.putExtra("empname",getIntent().getStringExtra("empname"));
                        startActivity(intent);
                    }
                });
            }

            tv55.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Viewer_ZycCj.this,Viewer_CkJl.class);
                    intent.putExtra("rowid",rowid[i]);
                    startActivity(intent);
                }
            });
            tv56.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Viewer_ZycCj.this,Viewer_CkJl.class);
                    intent.putExtra("rowid",rowid[i]);
                    startActivity(intent);
                }
            });
            tv57.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Viewer_ZycCj.this,Viewer_CkJl.class);
                    intent.putExtra("rowid",rowid[i]);
                    startActivity(intent);
                }
            });

            return ll;
        }
    };

    BaseAdapter baseAdapter5 = new BaseAdapter() {
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
            LinearLayout ll = (LinearLayout)LayoutInflater.from(Viewer_ZycCj.this).inflate(R.layout.list_item_view1,null);
            TextView tv6 = (TextView)ll.findViewById(R.id.textView6);
            Button btn7 = (Button)ll.findViewById(R.id.button7);
            tv6.setText("车号:"+zycnum[i]+"/铁鞋编号:"+txnum[i]+" //巡检人:"+empnm[i]+"//岗点:"+orgnm[i]+"//巡检日期:"+time[i]+" //巡检记录:"+xjjl[i]+"\n工区审核:"+gqpmis[i]);
            btn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Viewer_ZycCj.this,Viewer_Image.class);
                    intent.putExtra("rowid",rowid[i]);
                    intent.putExtra("whichone",zycnum[i]+"-"+txnum[i]);
                    intent.putExtra("empname",getIntent().getStringExtra("empname"));
                    startActivity(intent);
                }
            });
            return ll;
        }
    };

    public void onBackPressed(){
        super.onBackPressed();
        Viewer_ZycCj.this.finish();
        MainActivity.instance.finish();
    }

}
