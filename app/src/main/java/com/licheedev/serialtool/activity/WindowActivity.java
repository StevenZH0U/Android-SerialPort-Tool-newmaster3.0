package com.licheedev.serialtool.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.base.BaseActivity;

import butterknife.BindView;

public class WindowActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv8)
    TextView tv8;
    @BindView(R.id.tv9)
    TextView tv9;
    @BindView(R.id.tv10)
    TextView tv10;
    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.tv12)
    TextView tv12;
    @BindView(R.id.tv13)
    TextView tv13;
    @BindView(R.id.tv14)
    TextView tv14;
    @BindView(R.id.tv15)
    TextView tv15;
    @BindView(R.id.tv16)
    TextView tv16;
    @BindView(R.id.tv17)
    TextView tv17;
    @BindView(R.id.tv18)
    TextView tv18;
    @BindView(R.id.tv19)
    TextView tv19;
    @BindView(R.id.tv20)
    TextView tv20;
    @BindView(R.id.tv21)
    TextView tv21;
    @BindView(R.id.tv22)
    TextView tv22;
    @BindView(R.id.tv23)
    TextView tv23;
    @BindView(R.id.tv25)
    TextView tv25;
    @BindView(R.id.tv26)
    TextView tv26;
    @BindView(R.id.tv27)
    TextView tv27;
    @BindView(R.id.tv28)
    TextView tv28;
    @BindView(R.id.tv29)
    TextView tv29;
    @BindView(R.id.tv30)
    TextView tv30;
    @BindView(R.id.tv31)
    TextView tv31;
    @BindView(R.id.tv32)
    TextView tv32;
    @BindView(R.id.tv33)
    TextView tv33;
    @BindView(R.id.tv34)
    TextView tv34;
    @BindView(R.id.tv35)
    TextView tv35;
    @BindView(R.id.tv36)
    TextView tv36;
    @BindView(R.id.tv37)
    TextView tv37;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        back.setOnClickListener(this);
        bar();
        initialize();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_window;
    }


    public void initialize(){
        //获取数据进行显示
        SharedPreferences sharedPreferences= getSharedPreferences("user", MODE_PRIVATE);
        String sb=sharedPreferences.getString("sb","");
        Log.e("spsb",sb);
        String s1=sb.substring(6, 10);
        Log.e("s1",s1);
        String s2=sb.substring(10,14);
        Log.e("s2",s2);
        String s3=sb.substring(14,18);
        Log.e("s3",s3);
        String s4=sb.substring(18,22);
        Log.e("s4",s4);
        String s5=sb.substring(22,26);
        Log.e("s5",s5);
        String s6=sb.substring(26,30);
        Log.e("s6",s6);
        String s7=sb.substring(30,34);
        Log.e("s7",s7);
        String s8=sb.substring(34,38);
        Log.e("s8",s8);
        String s9=sb.substring(38,42);
        Log.e("s9",s9);
        String s10=sb.substring(42,46);
        Log.e("s10",s10);
        String s11=sb.substring(46,50);
        Log.e("s11",s11);
        String s12=sb.substring(50,54);
        Log.e("s12",s12);
        String s13=sb.substring(54,58);
        Log.e("s13",s13);
        String s14=sb.substring(58,62);
        Log.e("s14",s14);
        String s15=sb.substring(62,66);
        Log.e("s15",s15);
        String s16=sb.substring(66,70);
        Log.e("s16",s16);
        String s17=sb.substring(70,74);
        Log.e("s17",s17);
        String s18=sb.substring(74,78);
        Log.e("s18",s18);
        String s19=sb.substring(78,82);
        Log.e("s19",s19);
        String s20=sb.substring(82,86);
        Log.e("s20",s20);
        String s21=sb.substring(86,90);
        Log.e("s21",s21);
        String s22=sb.substring(90,94);
        Log.e("s22",s22);
        String s23=sb.substring(94,98);
        Log.e("s23",s23);
        String s24=sb.substring(98,102);
        Log.e("s24",s24);
        String s25=sb.substring(102,106);
        Log.e("s25",s25);
        String s26=sb.substring(106,110);
        Log.e("s26",s26);
        String s27=sb.substring(110,114);
        Log.e("s27",s27);
        String s28=sb.substring(114,118);
        Log.e("s28",s28);
        String s29=sb.substring(118,122);
        Log.e("s29",s29);
        String s30=sb.substring(122,126);
        Log.e("s30",s30);
        String s31=sb.substring(126,130);
        Log.e("s31",s31);
        String s32=sb.substring(130,134);
        Log.e("s32",s32);
        String s33=sb.substring(134,138);
        Log.e("s33",s33);
        String s34=sb.substring(138,142);
        Log.e("s34",s34);
        String s35=sb.substring(142,146);
        Log.e("s35",s35);
        String s36=sb.substring(146,150);
        Log.e("s36",s36);
        String s37=sb.substring(150,154);
        Log.e("s37",s37);




        if (s1.equals("0037")){
            tv1.setText("启动中");
        }else if (s1.equals("0000")){
            tv1.setText("未启动");
        }

        int ten = Integer.parseInt(s2, 16);
        tv2.setText(String.valueOf(ten));
        int t3 = Integer.parseInt(s3, 16);
        tv3.setText(String.valueOf(t3));
        int t4 = Integer.parseInt(s4, 16);
        tv4.setText(String.valueOf(t4));

        if (s5.equals("0001")){
            tv5.setText("启动中");
        }else if (s5.equals("0000")){
            tv5.setText("未启动");
        }

        if (s6.equals("0001")){
            tv6.setText("启动中");
        }else if (s6.equals("0000")){
            tv6.setText("未启动");
        }

        if (s7.equals("0001")){
            tv7.setText("启动中");
        }else if (s7.equals("0000")){
            tv7.setText("未启动");
        }

        if (s8.equals("0001")){
            tv8.setText("运行中");
        }else if (s8.equals("0000")){
            tv8.setText("未运行");
        }

        if (s9.equals("0001")){
            tv9.setText("启动中");
        }else if (s9.equals("0000")){
            tv9.setText("未启动");
        }

        if (s10.equals("0001")){
            tv10.setText("启动中");
        }else if (s10.equals("0000")){
            tv10.setText("未启动");
        }

        if (s11.equals("0001")){
            tv6.setText("待机中");
        }else if (s11.equals("0000")){
            tv11.setText("未待机");
        }

        if (s12.equals("0001")){
            tv12.setText("故障");
        }else if (s12.equals("0000")){
            tv12.setText("正常");
        }

        if (s13.equals("0001")){
            tv13.setText("故障");
        }else if (s13.equals("0000")){
            tv13.setText("正常");
        }

        if (s14.equals("0001")){
            tv14.setText("故障");
        }else if (s14.equals("0000")){
            tv14.setText("正常");
        }

        if (s15.equals("0001")){
            tv15.setText("故障");
        }else if (s15.equals("0000")){
            tv15.setText("正常");
        }

        if (s16.equals("0001")){
            tv16.setText("故障");
        }else if (s16.equals("0000")){
            tv16.setText("正常");
        }

        if (s17.equals("0001")){
            tv17.setText("达到");
        }else if (s17.equals("0000")){
            tv17.setText("未达到");
        }

        if (s18.equals("0001")){
            tv18.setText("达到");
        }else if (s18.equals("0000")){
            tv18.setText("未达到");
        }

        if (s19.equals("0001")){
            tv19.setText("达到");
        }else if (s19.equals("0000")){
            tv19.setText("未达到");
        }

        if (s20.equals("0001")){
            tv20.setText("清洗中");
        }else if (s20.equals("0000")){
            tv20.setText("未清洗");
        }

        if (s21.equals("0001")){
            tv21.setText("填充中");
        }else if (s21.equals("0000")){
            tv21.setText("未填充");
        }

        if (s22.equals("0001")){
            tv22.setText("清洗中");
        }else if (s22.equals("0000")){
            tv22.setText("未清洗");
        }

        if (s23.equals("0001")){
            tv23.setText("填充中");
        }else if (s23.equals("0000")){
            tv23.setText("未填充");
        }

        if (s25.equals("0001")){
            tv25.setText("填充中");
        }else if (s25.equals("0000")){
            tv25.setText("未填充");
        }

        if (s26.equals("0001")){
            tv26.setText("缺料");
        }else if (s26.equals("0000")){
            tv26.setText("正常");
        }

        if (s27.equals("0001")){
            tv27.setText("缺料");
        }else if (s27.equals("0000")){
            tv27.setText("正常");
        }

        if (s28.equals("0001")){
            tv28.setText("缺料");
        }else if (s28.equals("0000")){
            tv28.setText("正常");
        }

        int t29 = Integer.parseInt(s29, 16);
        int t30 = Integer.parseInt(s30, 16);
        int t31 = Integer.parseInt(s31, 16);
        int t32 = Integer.parseInt(s32, 16);

        tv29.setText(String.valueOf(t29));
        tv30.setText(String.valueOf(t30));
        tv31.setText(String.valueOf(t31));
        tv32.setText(String.valueOf(t32));

        int t33 = Integer.parseInt(s33, 16);
        int t34 = Integer.parseInt(s34, 16);
        int t35 = Integer.parseInt(s35, 16);
        int t36 = Integer.parseInt(s36, 16);
        int t37 = Integer.parseInt(s37, 16);

        tv33.setText(String.valueOf(t33));
        tv34.setText(String.valueOf(t34));
        tv35.setText(String.valueOf(t35));
        tv36.setText(String.valueOf(t36));
        tv37.setText(String.valueOf(t37));

    }




    /**
     * 全透状态栏
     */
    public void bar() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}