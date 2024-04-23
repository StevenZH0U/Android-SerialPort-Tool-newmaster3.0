package com.licheedev.serialtool.util;

import static android.content.Context.MODE_PRIVATE;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.comn.LoginServer;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.vi.vioserial.COMSerial;

import java.util.Timer;
import java.util.TimerTask;

public class PriceDialog implements View.OnClickListener {
    Dialog mRadioDialog;
    private ImageView erweima;
    private View.OnClickListener okOnClickListener;
    private View.OnClickListener cancelOnClickListener;
    private Context c1;
    private TextView leixing;
    private TextView jiage1;
    private String wendu="";
    private ImageButton back;
    private int guozhi=0;
    private boolean isStopThread = false;


    public void setOkOnClickListener(View.OnClickListener okOnClickListener) {
        this.okOnClickListener = okOnClickListener;
    }

    public void setCancelOnClickListener(View.OnClickListener cancelOnClickListener) {
        this.cancelOnClickListener = cancelOnClickListener;
    }

    public PriceDialog(Activity context,String url,String price,String jiage,String recordid,String wendu,int guozhi) {
        // 首先得到整个View
        View view = LayoutInflater.from(context).inflate(
                R.layout.pricedialog, null);
        c1 = context;
        erweima=view.findViewById(R.id.erweima);
        leixing=view.findViewById(R.id.leixing);
        jiage1=view.findViewById(R.id.jiage);
        back=view.findViewById(R.id.back);
        back.setOnClickListener(this);
        Log.e("erweimaurl",url);
        if (url.equals("")|url==null){
            erweima.setImageResource(R.mipmap.nowifi);
            leixing.setText("没有网络！请联系工作人员");
            jiage1.setText("");
        }else {
            Bitmap qr = CodeUtils.createImage(url, 350, 350, BitmapFactory.decodeResource(c1.getResources(), R.mipmap.ic_launcher));
            erweima.setImageBitmap(qr);
            if (price.equals("1")){
                leixing.setText("请在60秒内使用微信支付");
            }else if (price.equals("2")){
                leixing.setText("请在60秒内使用支付宝支付");
            }else if (price.equals("3")){
                leixing.setText("请在60秒内使用银联支付");
            }
            double c = Integer.parseInt(jiage);
            jiage1.setText("￥:0.0"+jiage);
        }

        // 创建自定义样式的Dialog
        mRadioDialog = new Dialog(context);
//        int w = context.getWindowManager().getDefaultDisplay().getWidth();
//        view.setMinimumWidth(w);//设置dialog的宽度
        // 设置返回键无效
        mRadioDialog.setCancelable(false);
        mRadioDialog.setContentView(view, new ActionBar.LayoutParams(//设置dialog
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
        //确定果汁配方
        COMSerial.instance().sendHex("/dev/ttyS2", "020611CF00017D3A");

        // 开启轮询
        new Thread() {
            public void run() {
//                isRunning = true;
                for (int i=0;i<=30;i++) {
//                while (true){
                    if (isStopThread){
                        break;
                    }

                    try {
                        LoginServer lg = new LoginServer();
                        String qingqiujieguo = lg.findzhifuByGet(recordid);
                        Log.e("支付结果",String.valueOf(qingqiujieguo));
                        Log.e("支付结果",String.valueOf(i));
                        if (i==30){
                            break;
                        }
                        try {
                            if (qingqiujieguo.equals("交易支付成功".trim())|qingqiujieguo.equals("支付成功".trim())){
                                Log.e("交易结果",String.valueOf(guozhi)+wendu);
                                //进行打饮
                                //获取SharedPreferences对象
                                SharedPreferences sharedPreferences = context.getSharedPreferences("user", MODE_PRIVATE);
                                //获取Editor对象的引用
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                if (guozhi==1){
                                    if (wendu.equals("cold")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "1cold");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行

                                        break;
                                    }else if (wendu.equals("hot")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "1hot");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                        break;
                                    }else if (wendu.equals("qipao")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "1qipao");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                    }
                                }else if (guozhi==2){
                                    if (wendu.equals("cold")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "2cold");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                        break;
                                    }else if (wendu.equals("hot")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "2hot");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                        break;
                                    }else if (wendu.equals("qipao")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "2qipao");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                    }

                                }else if (guozhi==3){
                                    if (wendu.equals("cold")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "3cold");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                        break;
                                    }else if (wendu.equals("hot")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "3hot");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行0206121C00018C87");
                                        break;
                                    }else if (wendu.equals("qipao")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "3qipao");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                    }
                                }else if (guozhi==4){
                                    if (wendu.equals("cold")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "4cold");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                        break;
                                    }else if (wendu.equals("hot")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "4hot");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                        break;
                                    }else if (wendu.equals("qipao")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "4qipao");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                    }
                                }else if (guozhi==5){
                                    if (wendu.equals("cold")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "5cold");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                        break;
                                    }else if (wendu.equals("hot")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "5hot");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                        break;
                                    }else if (wendu.equals("qipao")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "5qipao");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                    }
                                }else if (guozhi==6){
                                    if (wendu.equals("cold")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "6cold");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                        break;
                                    }else if (wendu.equals("hot")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "6hot");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                        break;
                                    }else if (wendu.equals("qipao")){
                                        //将获取过来的值放入文件
                                        editor.putString("wendu", "6qipao");
                                        // 提交数据
                                        editor.commit();

                                        //落杯移动位置
                                        COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00C98CC0");
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                //do something
                                                COMSerial.instance().sendHex("/dev/ttyS3", "0303138600016085");
                                                mRadioDialog.dismiss();
                                            }
                                        },8000);//延时1s执行
                                    }
                                }


                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                mRadioDialog.dismiss();
            }
        }.start();

    }

    public void show() {
        if (!mRadioDialog.isShowing()) {
            mRadioDialog.show();
        }
    }

    public void close() {
        if (mRadioDialog != null) {
            mRadioDialog.dismiss();
            isStopThread=true;
            mRadioDialog = null;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.close();
                break;
            default:
                if (okOnClickListener != null) {
                    okOnClickListener.onClick(view);
                }
                break;
        }
    }



}

