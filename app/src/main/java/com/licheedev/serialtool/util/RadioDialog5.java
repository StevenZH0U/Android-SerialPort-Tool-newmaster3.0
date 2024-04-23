package com.licheedev.serialtool.util;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.comn.LoginServer;

public class RadioDialog5 implements View.OnClickListener {
    Dialog mRadioDialog;
    private ImageButton big;
    private ImageButton center;
    private ImageButton small;
    private Button hot;
    private Button cold;
    private ImageButton sure;
    private ImageButton back;
    private Button qipao;
    private View.OnClickListener okOnClickListener;
    private View.OnClickListener cancelOnClickListener;
    private RadioGroup pay;
    private int zhifu = 0;
    private int guige = 0;
    private int price = 0;
    private String wendu = "";
    String item;//选择的选项值
    private Activity c;
    private String erweima = "";
    private String recordid = "";

    public void setOkOnClickListener(View.OnClickListener okOnClickListener) {
        this.okOnClickListener = okOnClickListener;
    }

    public void setCancelOnClickListener(View.OnClickListener cancelOnClickListener) {
        this.cancelOnClickListener = cancelOnClickListener;
    }

    public RadioDialog5(Activity context) {
        // 首先得到整个View
        View view = LayoutInflater.from(context).inflate(
                R.layout.mydialog, null);
        c = context;
        big = view.findViewById(R.id.big);
        center = view.findViewById(R.id.center);
        small = view.findViewById(R.id.small);
        hot = view.findViewById(R.id.hot);
        cold = view.findViewById(R.id.cold);
        sure = view.findViewById(R.id.sure);
        back = view.findViewById(R.id.back);
        pay = view.findViewById(R.id.pay);
        qipao=view.findViewById(R.id.qipao);
        sure.setOnClickListener(this);
        back.setOnClickListener(this);
        big.setOnClickListener(this);
        center.setOnClickListener(this);
        small.setOnClickListener(this);
        hot.setOnClickListener(this);
        cold.setOnClickListener(this);
        qipao.setOnClickListener(this);
//        small.setOnClickListener(this);
//        mBtnCancel.setOnClickListener(this);
        // 创建自定义样式的Dialog
        mRadioDialog = new Dialog(context);
//        int w = context.getWindowManager().getDefaultDisplay().getWidth();
//        view.setMinimumWidth(w);//设置dialog的宽度
        // 设置返回键无效
//        mRadioDialog.setCancelable(false);
        mRadioDialog.setContentView(view, new ActionBar.LayoutParams(//设置dialog
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
        pay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.vxpay:
                        zhifu = 1;
                        break;
                    case R.id.alipay:
                        zhifu = 2;
                        break;
                    case R.id.yinlianpay:
                        zhifu = 3;
                        break;

                }
            }
        });

    }

    public void show() {
        if (!mRadioDialog.isShowing()) {
            mRadioDialog.show();
        }
    }

    public void close() {
        if (mRadioDialog != null) {
            mRadioDialog.dismiss();
            mRadioDialog = null;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.big:
//                center.setBackgroundResource(R.mipmap.zhongbei2);
//                small.setBackgroundResource(R.mipmap.xiaobei2);
//                big.setBackgroundResource(R.mipmap.dabei);
//                MainActivity.sendData("0206121000014C84");//写1
//                guige = 3;
//                price = 3;
//                break;
//            case R.id.center:
//                center.setBackgroundResource(R.mipmap.zhongbei);
//                small.setBackgroundResource(R.mipmap.xiaobei2);
//                big.setBackgroundResource(R.mipmap.dabei2);
//                MainActivity.sendData("0206121000020C85");//写2
//                guige = 2;
//                price = 2;
//                break;
//            case R.id.small:
//                center.setBackgroundResource(R.mipmap.zhongbei2);
//                small.setBackgroundResource(R.mipmap.xiaobei);
//                big.setBackgroundResource(R.mipmap.dabei2);
//                MainActivity.sendData("020612100003CD45");//写3
//                guige = 1;
//                price = 1;
//                break;

            case R.id.cold:
                wendu = "cold";
                cold.setBackgroundResource(R.mipmap.code1);
                hot.setBackgroundResource(R.mipmap.hot2);
                qipao.setBackgroundResource(R.mipmap.qipao2);
                break;
            case R.id.hot:
                wendu = "hot";
                cold.setBackgroundResource(R.mipmap.code2);
                hot.setBackgroundResource(R.mipmap.hot1);
                qipao.setBackgroundResource(R.mipmap.qipao2);
                break;
            case R.id.qipao:
                wendu = "qipao";
                cold.setBackgroundResource(R.mipmap.code2);
                hot.setBackgroundResource(R.mipmap.hot2);
                qipao.setBackgroundResource(R.mipmap.qipao1);

                break;
            case R.id.sure:
//                if (guige == 0) {
//                    Toast.makeText(c, "请先选择果汁规格", Toast.LENGTH_SHORT).show();
//                } else
                if (wendu.equals("")) {
                    Toast.makeText(c, "请先选择果汁类型", Toast.LENGTH_SHORT).show();
                } else if (zhifu == 0) {
                    Toast.makeText(c, "请先选择支付方式", Toast.LENGTH_SHORT).show();
                } else {
                    if (Utils.isFastClick()) {
                        // 进行点击事件后的逻辑操作
                    new Thread() {
                        @Override
                        public void run() {
                            //需要在子线程中处理的逻辑
                            LoginServer lg = new LoginServer();
                            try {
                                recordid = lg.addjuiceByPost("001", 1, 1, 1, 5, zhifu);
                                Log.e("返回数据", recordid);
                                if (recordid != null) {
                                    erweima = lg.erweimaByGet(recordid, zhifu);
                                    Log.e("price", String.valueOf(price));
                                    Log.e("erweima", erweima);

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            c.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (erweima != null | erweima != "") {

                                        PriceDialog pd = new PriceDialog(c, erweima, String.valueOf(zhifu), String.valueOf(price), recordid, wendu, 5);
                                        pd.show();
                                        mRadioDialog.dismiss();

                                    }
                                }
                            });
                        }
                    }.start();
                }else {
                Toast.makeText(c, "请不要短时间连续提交订单", Toast.LENGTH_SHORT).show();
            }

                }
                break;
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
