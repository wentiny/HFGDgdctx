package com.example.peisw.hfgdgdctx.OvanCjian;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peisw.hfgdgdctx.MainActivity;
import com.example.peisw.hfgdgdctx.OvanManage.Viewer_Managment;
import com.example.peisw.hfgdgdctx.R;
import com.example.peisw.hfgdgdctx.Viewer_CkJl;
import com.example.peisw.hfgdgdctx.Viewer_Image;
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

    String[] t20=new String[count2];String[] aid=new String[count2];
    String[] t21=new String[count2];String[] anm=new String[count2];
    String[] t25=new String[counts];String[] userid=new String[counts];
    String[] t26=new String[counts];String[] usernm=new String[counts];
    String[] t27=new String[counts];String[] unitnm=new String[counts];

    String[] t30=new String[counts];String[] ac_orgid=new String[counts];
    String[] t31=new String[counts];String[] ac_orgnm=new String[counts];
    String[] t32=new String[counts];String[] ac_empid=new String[counts];
    String[] t33=new String[counts];String[] ac_empnm=new String[counts];
    String tmpActionId,tmpDLorg,tmpEmpId,tmpGroupId,tmpSelectXgOrgid;
    Button btn19;
    List<String> data3,data4,data5,data6,data6_1;

    TextView tv93,tv94,tv95,tv96,tv23_1,tv14_1;;
    String[] t42=new String[counts];String[] xg_zycnum=new String[counts];
    String[] t43=new String[counts];String[] xg_gdname=new String[counts];
    String[] t44=new String[counts];String[] xg_zycid=new String[counts];
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zyccj);

        final LinearLayout cjlineartiexie = (LinearLayout)findViewById(R.id.cjlineartiexie);
        final LinearLayout cjlinearaddoil = (LinearLayout)findViewById(R.id.cjlinearaddoil);
        final LinearLayout cjlinearusermng = (LinearLayout)findViewById(R.id.cjlinearusermng);
        final LinearLayout cjlinearzycmng = (LinearLayout)findViewById(R.id.cjlinearzycmng);

        tv93 = (TextView)findViewById(R.id.textView93);
        tv94 = (TextView)findViewById(R.id.textView94);
        tv95 = (TextView)findViewById(R.id.textView95);
        tv96 = (TextView)findViewById(R.id.textView96);

        tv93.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cjlineartiexie.setVisibility(View.VISIBLE);cjlinearaddoil.setVisibility(View.GONE);cjlinearusermng.setVisibility(View.GONE);cjlinearzycmng.setVisibility(View.GONE);
                setSelectedFalse();
                tv93.setSelected(true);
                data9 = new ArrayList<>();
                ///////////////////////////////////////////////////////////////////////////////////////
                final ListView lv3 = (ListView)findViewById(R.id.listview3);
                tv14 = (TextView)findViewById(R.id.textView14);
                tv14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            JSONObject jtmp = new JSONObject(method.doPostWithoutValue(method.myurl1+"/TXselect/ZycCj/AllRecode"));
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
                        try {
                            JSONObject j = new JSONObject(method.doPostWithoutValue(method.myurl1+"/TXselect/ZycGqu"));
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
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i9, long l) {tmpGqId = gqid[i9];}
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {tmpGqId = gqid[0];}
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
                        List<String> nm = new ArrayList<String>();List<String> vl = new ArrayList<String>();
                        nm.add("gqid");nm.add("sdate");nm.add("edate");
                        vl.add(tmpGqId);vl.add(tmpSdate);vl.add(tmpEdate);
                        try {
                            JSONObject jtmp = new JSONObject(method.doPost(method.myurl1+"/TXselect/ZycCj/AllRecodeByGqId",nm,vl));
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
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i8, long l) {tmpMultiGqId = gqid[i8];}
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {tmpMultiGqId = gqid[0];}
                        });

                        final ListView lv5 = (ListView)cv.findViewById(R.id.listview5);
                        Button btn29 = (Button)cv.findViewById(R.id.button29);
                        btn29.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                List<String> nm = new ArrayList<String>();
                                List<String> vl = new ArrayList<String>();
                                nm.add("gqid");vl.add(tmpMultiGqId);
                                try {
                                    JSONObject jtmp = new JSONObject(method.doPost(method.myurl1+"/TXselect/ZycCj/AllRecodeByMulti",nm,vl));
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
                                try {
                                    JSONObject j = new JSONObject(method.doPost(method.myurl1+"/TXupdate/CjPermissByMulti",nm,vl));
                                    Toast.makeText(getApplicationContext(),j.optString("msg"),Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {e.printStackTrace();}
                                tv14.performClick();
                                alertDialog.dismiss();
                            }
                        });
                        cv.findViewById(R.id.button31).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                List<String> nm = new ArrayList<String>();List<String> vl = new ArrayList<String>();
                                nm.add("gdid");nm.add("pmis");vl.add(tmpMultiGqId);vl.add(ed3.getText().toString());
                                try {
                                    JSONObject j = new JSONObject(method.doPost(method.myurl1+"/TXupdate/GqMultiTuiHui",nm,vl));
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

        });

        tv95.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cjlineartiexie.setVisibility(View.GONE);cjlinearaddoil.setVisibility(View.GONE);cjlinearusermng.setVisibility(View.VISIBLE);cjlinearzycmng.setVisibility(View.GONE);
                setSelectedFalse();
                tv95.setSelected(true);

                data3 = new ArrayList<>();data4=new ArrayList<>();
                data5=new ArrayList<>();data6=new ArrayList<>();data6_1=new ArrayList<>();
                TextView tv23 = (TextView)findViewById(R.id.container_usermng).findViewById(R.id.textView23);
                final Spinner sp3 = (Spinner)findViewById(R.id.container_usermng).findViewById(R.id.spinner3);
                final ListView lv4 = (ListView)findViewById(R.id.container_usermng).findViewById(R.id.listview4);
                btn19 = (Button)findViewById(R.id.container_usermng).findViewById(R.id.button19);
                Button btn20 = (Button)findViewById(R.id.container_usermng).findViewById(R.id.button20);

                tv23.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        data3.clear();
                        try {
                            JSONObject jtmp2 = new JSONObject(method.doPostWithoutValue(method.myurl1+"/TXselect/TxAction"));
                            for(int i=0;i<count2;i++){
                                t20[i]="aid"+i;aid[i]=jtmp2.optString(t20[i]);
                                t21[i]="anm"+i;anm[i]=jtmp2.optString(t21[i]);
                                if(anm[i]!=""){data3.add(anm[i]);}
                            }
                        } catch (JSONException e) {e.printStackTrace();}

                        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(Viewer_ZycCj.this,R.layout.shape_item_spinner,R.id.sPtextView,data3);
                        adapter3.setDropDownViewResource(R.layout.shape_item_dropdown);
                        sp3.setAdapter(adapter3);
                        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i3, long l) {tmpActionId = aid[i3];}
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {tmpActionId = aid[0];}
                        });
                    }
                });tv23.performClick();

                btn19.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<String> name1 = new ArrayList<String>();
                        List<String> value1 = new ArrayList<String>();
                        name1.add("groupid");value1.add(tmpActionId);
                        try {
                            JSONObject jtmp1 = new JSONObject(method.doPost(method.myurl1+"/APPselesctEmpByGroup",name1,value1));
                            for(int i=0;i<counts;i++){
                                t25[i]="empid"+i;userid[i]=jtmp1.optString(t25[i]);
                                t26[i]="empname"+i;usernm[i]=jtmp1.optString(t26[i]);
                                t27[i]="orgname"+i;unitnm[i]=jtmp1.optString(t27[i]);
                            }
                        } catch (JSONException e) {e.printStackTrace();}
                        lv4.setAdapter(baseAdapter4);setLvHeight(lv4);//listview自适应高度
                    }
                });

                btn20.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        data4.clear();data5.clear();data6.clear();
                        try {
                            JSONObject jtmp = new JSONObject(method.doPostWithoutValue(method.myurl1+"/TXselect/dlcj"));
                            for(int i=0;i<counts;i++){
                                t30[i]="orgid"+i;ac_orgid[i]=jtmp.optString(t30[i]);
                                t31[i]="orgnm"+i;ac_orgnm[i]=jtmp.optString(t31[i]);
                                if(ac_orgnm[i]!=""){data4.add(ac_orgnm[i]);}
                            }
                        } catch (JSONException e) {e.printStackTrace();}

                        try {
                            JSONObject jtmp2 = new JSONObject(method.doPostWithoutValue(method.myurl1+"/TXselect/TxAction"));
                            for(int i=0;i<count2;i++){
                                t20[i]="aid"+i;aid[i]=jtmp2.optString(t20[i]);
                                t21[i]="anm"+i;anm[i]=jtmp2.optString(t21[i]);
                                if(anm[i]!=""){data6.add(anm[i]);}
                            }
                        } catch (JSONException e) {e.printStackTrace();}

                        AlertDialog.Builder builder=new AlertDialog.Builder(Viewer_ZycCj.this);
                        final View childview = View.inflate(Viewer_ZycCj.this,R.layout.dialog_addinto_group,null);
                        builder.setTitle("授权").setView(childview);
                        AlertDialog alertDialog = builder.create();

                        Spinner sp4 = (Spinner)childview.findViewById(R.id.spinner4);
                        final ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(Viewer_ZycCj.this,R.layout.shape_item_spinner,R.id.sPtextView,data4);
                        adapter4.setDropDownViewResource(R.layout.shape_item_dropdown);
                        sp4.setAdapter(adapter4);
                        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i4, long l) {
                                data5.clear();
                                tmpDLorg = ac_orgid[i4];
                                List<String> name01=new ArrayList<String>();List<String> value01=new ArrayList<String>();
                                name01.add("orgid");value01.add(tmpDLorg);
                                try {
                                    JSONObject jtmp01 = new JSONObject(method.doPost(method.myurl1+"/APPSelectUsersByOrgid",name01,value01));
                                    for (int i=0;i<counts;i++){
                                        t32[i]="empid"+i;ac_empid[i]=jtmp01.optString(t32[i]);
                                        t33[i]="empname"+i;ac_empnm[i]=jtmp01.optString(t33[i]);
                                        if(ac_empnm[i]!=""){data5.add(ac_empnm[i]);}
                                    }
                                } catch (JSONException e) {e.printStackTrace();}

                                Spinner sp5 = (Spinner)childview.findViewById(R.id.spinner5);
                                ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(Viewer_ZycCj.this,R.layout.shape_item_spinner,R.id.sPtextView,data5);
                                adapter5.setDropDownViewResource(R.layout.shape_item_dropdown);
                                sp5.setAdapter(adapter5);
                                sp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i5, long l) {tmpEmpId = ac_empid[i5];}
                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {tmpEmpId = ac_empid[0];}
                                });
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                tmpDLorg = ac_orgid[0];
                            }
                        });

                        Spinner sp6 = (Spinner)childview.findViewById(R.id.spinner6);
                        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(Viewer_ZycCj.this,R.layout.shape_item_spinner,R.id.sPtextView,data6);
                        adapter6.setDropDownViewResource(R.layout.shape_item_dropdown);
                        sp6.setAdapter(adapter6);
                        sp6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i6, long l) {tmpGroupId = aid[i6];}
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {tmpGroupId = aid[0];}
                        });

                        Button btn22 = (Button)childview.findViewById(R.id.button22);
                        btn22.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                List<String> nm = new ArrayList<String>();
                                List<String> va = new ArrayList<String>();
                                nm.add("empid");nm.add("groupid");
                                va.add(tmpEmpId);va.add(tmpGroupId);
                                try {
                                    JSONObject jtmp = new JSONObject(method.doPost(method.myurl1+"/APPaddEmpFromGroup",nm,va));
                                    Toast.makeText(getApplicationContext(),jtmp.optString("msg"),Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }btn19.performClick();
                            }
                        });

                        alertDialog.show();
                    }
                });
            }
        });

        tv96.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cjlineartiexie.setVisibility(View.GONE);cjlinearaddoil.setVisibility(View.GONE);cjlinearusermng.setVisibility(View.GONE);cjlinearzycmng.setVisibility(View.VISIBLE);
                setSelectedFalse();
                tv96.setSelected(true);
                data6_1=new ArrayList<>();

                LinearLayout llsp3btn19btn20 = (LinearLayout)findViewById(R.id.container_zycmng).findViewById(R.id.llsp3btn19btn20);llsp3btn19btn20.setVisibility(View.GONE);
                final ListView listView4 = (ListView)findViewById(R.id.container_zycmng).findViewById(R.id.listview4);
                tv23_1 = (TextView)findViewById(R.id.container_zycmng).findViewById(R.id.textView23);tv23_1.setText("作业车管理");
                tv23_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            JSONObject json23 = new JSONObject(method.doPostWithoutValue(method.myurl1+"/Ovan/selectAllZycNum"));
                            for(int i=0;i<counts;i++){
                                t42[i]="zycnum"+i;xg_zycnum[i]=json23.optString(t42[i]);
                                t43[i]="gdname"+i;xg_gdname[i]=json23.optString(t43[i]);
                                t44[i]="zycid"+i;xg_zycid[i]=json23.optString(t44[i]);
                            }
                        } catch (JSONException e) {e.printStackTrace();}
                        listView4.setAdapter(listAdapter4);setLvHeight(listView4);
                    }
                });tv23_1.performClick();
            }
        });

        tv93.performClick();

    }

    public void setSelectedFalse(){
        tv93.setSelected(false);
        tv94.setSelected(false);
        tv95.setSelected(false);
        tv96.setSelected(false);
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

    String[] t40=new String[counts];String[] xg_orgid=new String[counts];
    String[] t41=new String[counts];String[] xg_orgnm=new String[counts];
    BaseAdapter baseAdapter4 = new BaseAdapter() {
        @Override
        public int getCount() {
            int index = 0;
            for(int i=0;i<counts;i++){
                if(usernm[i]!=""){index=index+1;}
            }return index;
        }
        @Override
        public Object getItem(int i) {
            return usernm[i];
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            LinearLayout ll = (LinearLayout)LayoutInflater.from(Viewer_ZycCj.this).inflate(R.layout.list_item_usermanage_view,null);
            TextView tv71 = (TextView)ll.findViewById(R.id.textView71);
            ImageView imgv10 = (ImageView)ll.findViewById(R.id.imageView10);
            Button btn36 = (Button)ll.findViewById(R.id.button36);
            Button btn37 = (Button)ll.findViewById(R.id.button37);
            tv71.setText(usernm[i]+"("+unitnm[i]+")");
            imgv10.setImageResource(R.drawable.tx);
            btn37.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Viewer_ZycCj.this).create();
                    alertDialog.setIcon(R.drawable.tx);alertDialog.setMessage("是否将"+usernm[i]+"移出群组!");alertDialog.setTitle("移出群组");
                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "移出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            List<String> name = new ArrayList<String>();
                            List<String> value = new ArrayList<String>();
                            name.add("empid");name.add("groupid");
                            value.add(userid[i]);value.add(tmpActionId);
                            try {
                                JSONObject j = new JSONObject(method.doPost(method.myurl1+"/APPdeleteEmpFromGroup",name,value));
                                Toast.makeText(getApplicationContext(),j.optString("msg"),Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }btn19.performClick();alertDialog.dismiss();
                        }
                    });
                    alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            });
            btn36.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    data6_1.clear();
                    try {
                        JSONObject json36_1 = new JSONObject(method.doPostWithoutValue(method.myurl1+"/Ovan/selectOrgs"));
                        for(int k=0;k<counts;k++){
                            t40[k]="orgid"+k;xg_orgid[k]=json36_1.optString(t40[k]);
                            t41[k]="orgnm"+k;xg_orgnm[k]=json36_1.optString(t41[k]);
                            if(xg_orgnm[k]!=""){data6_1.add(xg_orgnm[k]);}
                        }
                    } catch (JSONException e) {e.printStackTrace();}

                    AlertDialog.Builder builder = new AlertDialog.Builder(Viewer_ZycCj.this);
                    View cv = View.inflate(Viewer_ZycCj.this,R.layout.dialog_addinto_group,null);
                    builder.setTitle("调整组织机构").setView(cv);
                    final AlertDialog alertDialog = builder.create();
                    LinearLayout llsp4sp5 = (LinearLayout)cv.findViewById(R.id.sp4sp5);llsp4sp5.setVisibility(View.GONE);
                    TextView tv24 = (TextView)cv.findViewById(R.id.textView24);tv24.setBackgroundResource(R.color.white);tv24.setTextColor(Color.BLACK);tv24.setText("\n姓名:"+usernm[i]+"\n现工区(岗点):"+unitnm[i]);
                    Spinner sp6 = (Spinner)cv.findViewById(R.id.spinner6);
                    ArrayAdapter<String> adapter6_1 = new ArrayAdapter<String>(Viewer_ZycCj.this,R.layout.shape_item_spinner,R.id.sPtextView,data6_1);
                    adapter6_1.setDropDownViewResource(R.layout.shape_item_dropdown);
                    sp6.setAdapter(adapter6_1);
                    sp6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position6_1, long l) {tmpSelectXgOrgid = xg_orgid[position6_1];}
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {tmpSelectXgOrgid = xg_orgid[0];}
                    });

                    Button btn22 = (Button)cv.findViewById(R.id.button22);btn22.setText("修改");
                    btn22.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            List<String> list1 = new ArrayList<String>();
                            List<String> list2 = new ArrayList<String>();
                            list1.add("empid");list1.add("orgid");
                            list2.add(userid[i]);list2.add(tmpSelectXgOrgid);
                            try {
                                JSONObject json22 = new JSONObject(method.doPost(method.myurl1+"/Ovan/updateOrgId",list1,list2));
                                Toast.makeText(getApplicationContext(),json22.optString("msg"),Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {e.printStackTrace();}
                            btn19.performClick();alertDialog.dismiss();
                        }
                    });

                    alertDialog.show();
                }
            });
            return ll;
        }
    };

    BaseAdapter listAdapter4 = new BaseAdapter() {
        @Override
        public int getCount() {
            int index = 0;
            for(int i=0;i<counts;i++){
                if(xg_zycnum[i]!=""){index=index+1;}
            }return index;
        }

        @Override
        public Object getItem(int i) {
            return xg_zycnum[i];
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            LinearLayout ll = (LinearLayout)LayoutInflater.from(Viewer_ZycCj.this).inflate(R.layout.list_item_usermanage_view,null);
            TextView tv71 = (TextView)ll.findViewById(R.id.textView71);tv71.setText("作业车号:"+xg_zycnum[i]+"\n所属岗点:"+xg_gdname[i]);
            ImageView imgv10 = (ImageView)ll.findViewById(R.id.imageView10);imgv10.setImageResource(R.drawable.zyc);
            Button btn36 = (Button)ll.findViewById(R.id.button36);btn36.setBackgroundResource(R.color.green_3dbf53);btn36.setTextColor(Color.WHITE);btn36.setText("修改岗点");
            Button btn37 = (Button)ll.findViewById(R.id.button37);btn37.setBackgroundResource(R.color.blue_3a77e2);btn37.setTextColor(Color.WHITE);btn37.setText("详情");
            btn36.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    data6_1.clear();
                    try {
                        JSONObject json36_1 = new JSONObject(method.doPostWithoutValue(method.myurl1+"/Ovan/selectOrgs"));
                        for(int k=0;k<counts;k++){
                            t40[k]="orgid"+k;xg_orgid[k]=json36_1.optString(t40[k]);
                            t41[k]="orgnm"+k;xg_orgnm[k]=json36_1.optString(t41[k]);
                            if(xg_orgnm[k]!=""){data6_1.add(xg_orgnm[k]);}
                        }
                    } catch (JSONException e) {e.printStackTrace();}

                    AlertDialog.Builder builder = new AlertDialog.Builder(Viewer_ZycCj.this);
                    View cv = View.inflate(Viewer_ZycCj.this,R.layout.dialog_addinto_group,null);
                    builder.setTitle("调整作业车岗点").setView(cv);
                    final AlertDialog alertDialog = builder.create();
                    LinearLayout llsp4sp5 = (LinearLayout)cv.findViewById(R.id.sp4sp5);llsp4sp5.setVisibility(View.GONE);
                    TextView tv24 = (TextView)cv.findViewById(R.id.textView24);tv24.setBackgroundResource(R.color.white);tv24.setTextColor(Color.BLACK);tv24.setText("\n车号:"+xg_zycnum[i]+"\n现岗点:"+xg_gdname[i]);
                    Spinner sp6 = (Spinner)cv.findViewById(R.id.spinner6);
                    ArrayAdapter<String> adapter6_1 = new ArrayAdapter<String>(Viewer_ZycCj.this,R.layout.shape_item_spinner,R.id.sPtextView,data6_1);
                    adapter6_1.setDropDownViewResource(R.layout.shape_item_dropdown);
                    sp6.setAdapter(adapter6_1);
                    sp6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position6_1, long l) {tmpSelectXgOrgid = xg_orgid[position6_1];}
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {tmpSelectXgOrgid = xg_orgid[0];}
                    });

                    Button btn22 = (Button)cv.findViewById(R.id.button22);btn22.setText("修改");
                    btn22.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            List<String> list1 = new ArrayList<String>();
                            List<String> list2 = new ArrayList<String>();
                            list1.add("zycid");list1.add("orgid");
                            list2.add(xg_zycid[i]);list2.add(tmpSelectXgOrgid);
                            try {
                                JSONObject json22 = new JSONObject(method.doPost(method.myurl1+"/Ovan/updateZycOrgId",list1,list2));
                                Toast.makeText(getApplicationContext(),json22.optString("msg"),Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {e.printStackTrace();}
                            tv23_1.performClick();alertDialog.dismiss();
                        }
                    });

                    alertDialog.show();
                }
            });
            return ll;
        }
    };

    public void setLvHeight(ListView listView){
        ListAdapter adapter = listView.getAdapter();
        if(adapter==null){
            return;
        }
        int totalHeight = 0;
        for(int i=0,len=adapter.getCount();i<len;i++){
            View listItem = adapter.getView(i,null,listView);
            listItem.measure(0,0);
            totalHeight = totalHeight+listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+(listView.getDividerHeight()*(adapter.getCount()-1));
        listView.setLayoutParams(params);
    }

    public void onBackPressed(){
        super.onBackPressed();
        Viewer_ZycCj.this.finish();
        MainActivity.instance.finish();
    }

}
