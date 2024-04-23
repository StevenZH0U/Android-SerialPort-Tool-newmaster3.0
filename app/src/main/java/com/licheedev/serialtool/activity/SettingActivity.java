package com.licheedev.serialtool.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.CallSuper;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.base.BaseActivity;
import com.licheedev.serialtool.util.KeyboardsUtils;
import com.vi.vioserial.COMSerial;

import butterknife.BindView;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.qidong)
    Button qidong;
    @BindView(R.id.tingzhi)
    Button tingzhi;
    @BindView(R.id.hotwater)
    EditText hotwater;
    @BindView(R.id.hotqidong)
    Button hotqidong;
    @BindView(R.id.hottingzhi)
    Button hottingzhi;
    @BindView(R.id.coldwater)
    EditText coldwater;
    @BindView(R.id.coldqidong)
    Button coldqidong;
    @BindView(R.id.coldtingzhi)
    Button coldtingzhi;
    @BindView(R.id.qipaowater)
    EditText qipaowater;
    @BindView(R.id.qipaoqidong)
    Button qipaoqidong;
    @BindView(R.id.qipaotingzhi)
    Button qipaotingzhi;
    @BindView(R.id.big)
    EditText big;
    @BindView(R.id.bigsure)
    Button bigsure;
    @BindView(R.id.xiaodu)
    Button xiaodu;
    @BindView(R.id.tingzhixiaodu)
    Button tingzhixiaodu;
    @BindView(R.id.zidong)
    Button zidong;
    @BindView(R.id.shoudong)
    Button shoudong;
    @BindView(R.id.qingxi)
    Button qingxi;
    @BindView(R.id.biaoding)
    Button biaoding;
    @BindView(R.id.fuwei)
    Button fuwei;
    @BindView(R.id.lengshui1)
    Button lengshui1;
    @BindView(R.id.lengshui2)
    Button lengshui2;
    @BindView(R.id.lengshui3)
    Button lengshui3;
    @BindView(R.id.lengshui4)
    Button lengshui4;
    @BindView(R.id.lengshui5)
    Button lengshui5;
    @BindView(R.id.lengshui6)
    Button lengshui6;
    @BindView(R.id.reshui1)
    Button reshui1;
    @BindView(R.id.reshui2)
    Button reshui2;
    @BindView(R.id.reshui3)
    Button reshui3;
    @BindView(R.id.reshui4)
    Button reshui4;
    @BindView(R.id.reshui5)
    Button reshui5;
    @BindView(R.id.reshui6)
    Button reshui6;
    @BindView(R.id.qipao1)
    Button qipao1;
    @BindView(R.id.qipao2)
    Button qipao2;
    @BindView(R.id.qipao3)
    Button qipao3;
    @BindView(R.id.qipao4)
    Button qipao4;
    @BindView(R.id.qipao5)
    Button qipao5;
    @BindView(R.id.qipao6)
    Button qipao6;
    @BindView(R.id.guozhi1)
    Button guozhi1;
    @BindView(R.id.guozhi2)
    Button guozhi2;
    @BindView(R.id.guozhi3)
    Button guozhi3;
    @BindView(R.id.guozhi4)
    Button guozhi4;
    @BindView(R.id.guozhi5)
    Button guozhi5;
    @BindView(R.id.guozhi6)
    Button guozhi6;
    @BindView(R.id.luobeizidong)
    Button luobeizidong;
    @BindView(R.id.luobeishoudong)
    Button luobeishoudong;
    @BindView(R.id.luobei)
    Button luobei;
    @BindView(R.id.luobeixuanzhuan)
    Button luobeixuanzhuan;
    @BindView(R.id.luobeigai)
    Button luobeigai;
    @BindView(R.id.yagaiup)
    Button yagaiup;
    @BindView(R.id.yagaidown)
    Button yagaidown;
    @BindView(R.id.yidongluobei)
    Button yidongluobei;
    @BindView(R.id.yidongguozhi)
    Button yidongguozhi;
    @BindView(R.id.yidongbeigai)
    Button yidongbeigai;
    @BindView(R.id.yidongyagai)
    Button yidongyagai;
    @BindView(R.id.kaimen)
    Button kaimen;






    private String guozhiduankou="/dev/ttyS2";
    private String luobeiduankou="/dev/ttyS3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bar();
        back.setOnClickListener(this);
        qidong.setOnClickListener(this);
        tingzhi.setOnClickListener(this);
        bigsure.setOnClickListener(this);
        hotqidong.setOnClickListener(this);
        hottingzhi.setOnClickListener(this);
        coldqidong.setOnClickListener(this);
        coldtingzhi.setOnClickListener(this);
        qipaoqidong.setOnClickListener(this);
        qipaotingzhi.setOnClickListener(this);
        xiaodu.setOnClickListener(this);
        tingzhixiaodu.setOnClickListener(this);
        zidong.setOnClickListener(this);
        shoudong.setOnClickListener(this);
        qingxi.setOnClickListener(this);
        biaoding.setOnClickListener(this);
        fuwei.setOnClickListener(this);
        lengshui1.setOnClickListener(this);
        lengshui2.setOnClickListener(this);
        lengshui3.setOnClickListener(this);
        lengshui4.setOnClickListener(this);
        lengshui5.setOnClickListener(this);
        lengshui6.setOnClickListener(this);
        reshui1.setOnClickListener(this);
        reshui2.setOnClickListener(this);
        reshui3.setOnClickListener(this);
        reshui4.setOnClickListener(this);
        reshui5.setOnClickListener(this);
        reshui6.setOnClickListener(this);
        qipao1.setOnClickListener(this);
        qipao2.setOnClickListener(this);
        qipao3.setOnClickListener(this);
        qipao4.setOnClickListener(this);
        qipao5.setOnClickListener(this);
        qipao6.setOnClickListener(this);
        guozhi1.setOnClickListener(this);
        guozhi2.setOnClickListener(this);
        guozhi3.setOnClickListener(this);
        guozhi4.setOnClickListener(this);
        guozhi5.setOnClickListener(this);
        guozhi6.setOnClickListener(this);
        luobeizidong.setOnClickListener(this);
        luobeishoudong.setOnClickListener(this);
        luobei.setOnClickListener(this);
        luobeixuanzhuan.setOnClickListener(this);
        luobeigai.setOnClickListener(this);
        yagaiup.setOnClickListener(this);
        yagaidown.setOnClickListener(this);
        yidongluobei.setOnClickListener(this);
        yidongguozhi.setOnClickListener(this);
        yidongbeigai.setOnClickListener(this);
        yidongyagai.setOnClickListener(this);
        kaimen.setOnClickListener(this);

        COMSerial.instance().sendHex(guozhiduankou, "020611CE0000ED3A");
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
//                            MainActivity.sendData("030611FC00370CF2");
                    COMSerial.instance().sendHex(luobeiduankou, "0306120C00004D53");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 3s后会执行的操作
            }
        }.start();


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    /**
     * 点击非编辑区域收起键盘
     * 获取点击事件
     * CSDN-深海呐
     */
    @CallSuper
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (KeyboardsUtils.isShouldHideKeyBord(view, ev)) {
                KeyboardsUtils.hintKeyBoards(view);
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.qidong:
                COMSerial.instance().sendHex(guozhiduankou, "020611CD00375CEC");
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(300);
//                            MainActivity.sendData("030611FC00370CF2");
                            COMSerial.instance().sendHex(luobeiduankou, "030611FC00370CF2");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // 3s后会执行的操作
                    }
                }.start();
                break;

            case R.id.tingzhi:
                COMSerial.instance().sendHex(guozhiduankou, "020611CD00001D3A");
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(300);
//                            MainActivity.sendData("030611FC00004D24");
                            COMSerial.instance().sendHex(luobeiduankou, "030611FC00004D24");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // 3s后会执行的操作
                    }
                }.start();
                break;

            case R.id.hotqidong:
                int hot = Integer.parseInt(hotwater.getText().toString());
                if (hot < 30 | hot > 95) {
                    Toast.makeText(SettingActivity.this, "请输入正确的温度（30°-95°）", Toast.LENGTH_SHORT).show();
                } else {
                    String sixteen = Integer.toHexString(hot);
                    COMSerial.instance().sendHex(guozhiduankou, "020611A900" + sixteen + crc2("A900" + sixteen));
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500);
                                COMSerial.instance().sendHex(guozhiduankou, "020611D30001BCFC");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // 3s后会执行的操作
                        }
                    }.start();
                }

                break;

            case R.id.hottingzhi:
                COMSerial.instance().sendHex(guozhiduankou, "020611D300007D3C");
                break;

            case R.id.coldqidong:
                int cold = Integer.parseInt(coldwater.getText().toString());
                if (cold > 30) {
                    Toast.makeText(SettingActivity.this, "请输入正确的温度（<30°）", Toast.LENGTH_SHORT).show();
                } else {
                    String sixteen = Integer.toHexString(cold);
                    if (sixteen.length() == 1) {
                        sixteen = "0" + sixteen;
                    }
                    COMSerial.instance().sendHex(guozhiduankou, "020611A800" + sixteen + crc2("A800" + sixteen));
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500);
                                COMSerial.instance().sendHex(guozhiduankou, "020611D100011D3C");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // 3s后会执行的操作
                        }
                    }.start();
                }

                break;

            case R.id.coldtingzhi:
                COMSerial.instance().sendHex(guozhiduankou, "020611D10000DCFC");
                break;

            case R.id.qipaoqidong:
                int qipao = Integer.parseInt(qipaowater.getText().toString());
                if (qipao > 30) {
                    Toast.makeText(SettingActivity.this, "请输入正确的温度（<30°）", Toast.LENGTH_SHORT).show();
                } else {
                    String sixteen = Integer.toHexString(qipao);
                    if (sixteen.length() == 1) {
                        sixteen = "0" + sixteen;
                    }
                    COMSerial.instance().sendHex(guozhiduankou, "020611AB00" + sixteen + crc2("AB00" + sixteen));
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500);
                                COMSerial.instance().sendHex(guozhiduankou, "020611D20001ED3C");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // 3s后会执行的操作
                        }
                    }.start();
                }
                break;

            case R.id.qipaotingzhi:
                COMSerial.instance().sendHex(guozhiduankou, "020611D200002CFC");
                break;

            case R.id.bigsure:
                if (big.getText() != null) {
                    int b = Integer.parseInt(big.getText().toString());
                    int guozhi = 0;
                    int shui = 0;
                    if (b > 400 || b < 300) {
                        Toast.makeText(SettingActivity.this, "请输入300-400毫升正确的值", Toast.LENGTH_SHORT).show();
                    } else {
                        guozhi = (int) (b / 8 * 10);
                        shui = (int) (guozhi * 7);
                        bigcap(guozhi, shui);
                    }
                }
                break;

            case R.id.xiaodu:
//                MainActivity.sendData("020611D400010D3D");
                COMSerial.instance().sendHex(guozhiduankou, "020611D400010D3D");
                break;

            case R.id.tingzhixiaodu:
                COMSerial.instance().sendHex(guozhiduankou, "020611D40000CCFD");
                break;

            case R.id.zidong:
                COMSerial.instance().sendHex(guozhiduankou, "020611CE0000ED3A");
                zidong.setText("开启");
                shoudong.setText("关闭");
                qingxi.setText("关闭");
                biaoding.setText("关闭");
                break;

            case R.id.shoudong:
                COMSerial.instance().sendHex(guozhiduankou, "020611CE00012CFA");
                zidong.setText("关闭");
                shoudong.setText("开启");
                qingxi.setText("关闭");
                biaoding.setText("关闭");
                break;

            case R.id.qingxi:
                COMSerial.instance().sendHex(guozhiduankou, "020611CE00026CFB");
                zidong.setText("关闭");
                shoudong.setText("关闭");
                qingxi.setText("开启");
                biaoding.setText("关闭");
                break;

            case R.id.biaoding:
                COMSerial.instance().sendHex(guozhiduankou, "020611CE0003AD3B");
                zidong.setText("关闭");
                shoudong.setText("关闭");
                qingxi.setText("关闭");
                biaoding.setText("开启");
                break;

            case R.id.fuwei:
                COMSerial.instance().sendHex(guozhiduankou, "020611D500015CFD");
                break;

            case R.id.lengshui1:
                COMSerial.instance().sendHex(guozhiduankou, "020611D0000BCCFB");
                break;

            case R.id.lengshui2:
                COMSerial.instance().sendHex(guozhiduankou, "020611D0000C8D39");
                break;

            case R.id.lengshui3:
                COMSerial.instance().sendHex(guozhiduankou, "020611D0000D4CF9");
                break;

            case R.id.lengshui4:
                COMSerial.instance().sendHex(guozhiduankou, "020611D0000E0CF8");
                break;

            case R.id.lengshui5:
                COMSerial.instance().sendHex(guozhiduankou, "020611D0000FCD38");
                break;

            case R.id.lengshui6:
                COMSerial.instance().sendHex(guozhiduankou, "020611D000108CF0");
                break;

            case R.id.reshui1:
                COMSerial.instance().sendHex(guozhiduankou, "020611D000154CF3");
                break;

            case R.id.reshui2:
                COMSerial.instance().sendHex(guozhiduankou, "020611D000160CF2");
                break;

            case R.id.reshui3:
                COMSerial.instance().sendHex(guozhiduankou, "020611D00017CD32");
                break;

            case R.id.reshui4:
                COMSerial.instance().sendHex(guozhiduankou, "020611D000188D36");
                break;

            case R.id.reshui5:
                COMSerial.instance().sendHex(guozhiduankou, "020611D000194CF6");
                break;

            case R.id.reshui6:
                COMSerial.instance().sendHex(guozhiduankou, "020611D0001A0CF7");
                break;

            case R.id.qipao1:
                COMSerial.instance().sendHex(guozhiduankou, "020611D0001FCCF4");
                break;

            case R.id.qipao2:
                COMSerial.instance().sendHex(guozhiduankou, "020611D000208CE4");
                break;

            case R.id.qipao3:
                COMSerial.instance().sendHex(guozhiduankou, "020611D000214D24");
                break;

            case R.id.qipao4:
                COMSerial.instance().sendHex(guozhiduankou, "020611D000220D25");
                break;

            case R.id.qipao5:
                COMSerial.instance().sendHex(guozhiduankou, "020611D00023CCE5");
                break;

            case R.id.qipao6:
                COMSerial.instance().sendHex(guozhiduankou, "020611D000248D27");
                break;

            case R.id.guozhi1:
                COMSerial.instance().sendHex(guozhiduankou, "020611D000294CE2");
                break;

            case R.id.guozhi2:
                COMSerial.instance().sendHex(guozhiduankou, "020611D0002A0CE3");
                break;

            case R.id.guozhi3:
                COMSerial.instance().sendHex(guozhiduankou, "020611D0002BCD23");
                break;

            case R.id.guozhi4:
                COMSerial.instance().sendHex(guozhiduankou, "020611D0002C8CE1");
                break;

            case R.id.guozhi5:
                COMSerial.instance().sendHex(guozhiduankou, "020611D0002D4D21");
                break;

            case R.id.guozhi6:
                COMSerial.instance().sendHex(guozhiduankou, "020611D0002E0D20");
                break;


            case R.id.luobeizidong:
                COMSerial.instance().sendHex(luobeiduankou, "03 06 12 0C 00 00 4D 53");
                luobeizidong.setText("开启");
                luobeishoudong.setText("关闭");
                break;

            case R.id.luobeishoudong:
                COMSerial.instance().sendHex(luobeiduankou, "03 06 12 0C 00 01 8C 93");
                luobeizidong.setText("关闭");
                luobeishoudong.setText("开启");
                break;

            case R.id.luobei:
                COMSerial.instance().sendHex(luobeiduankou, "03 06 12 30 00 01 4C 9F");
                break;

            case R.id.luobeixuanzhuan:
                COMSerial.instance().sendHex(luobeiduankou, "03 06 12 30 00 02 0C 9E");
                break;

            case R.id.luobeigai:
                COMSerial.instance().sendHex(luobeiduankou, "03 06 12 30 00 03 CD 5E");
                break;

            case R.id.yagaiup:
                COMSerial.instance().sendHex(luobeiduankou, "03 06 12 30 00 05 4D 5C");
                break;

            case R.id.yagaidown:
                COMSerial.instance().sendHex(luobeiduankou, "03 06 12 30 00 06 0D 5D");
                break;

            case R.id.yidongluobei:
                COMSerial.instance().sendHex(luobeiduankou, "03 06 12 30 00 07 CC 9D");
                break;

            case R.id.yidongguozhi:
                COMSerial.instance().sendHex(luobeiduankou, "03 06 12 30 00 08 8C 99");
                break;

            case R.id.yidongbeigai:
                COMSerial.instance().sendHex(luobeiduankou, "03 06 12 30 00 09 4D 59");
                break;

            case R.id.yidongyagai:
                COMSerial.instance().sendHex(luobeiduankou, "03 06 12 30 00 0A 0D 58");
                break;

            case R.id.kaimen:
                COMSerial.instance().sendHex(luobeiduankou, "03 06 12 30 00 0B CC 98");
                break;





            default:
                break;
        }
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


    public void bigcap(int guozhi, int shui) {
        Log.e("guozhi" + guozhi, "shui" + shui);
        String guozhi1 = Integer.toHexString(guozhi);
        String shui1 = Integer.toHexString(shui);
        if (guozhi1.length() == 2) {
            guozhi1 = "00" + guozhi1;
        } else if (guozhi1.length() == 3) {
            guozhi1 = "0" + guozhi1;
        }
        if (shui1.length() == 2) {
            shui1 = "00" + shui1;
        } else if (shui1.length() == 3) {
            shui1 = "0" + shui1;
        }

        //水份数1
        //02 06 11 91 0B B8 DA 6A
//        MainActivity.sendData("020611" + "91" + shui1 + crc2("91" + shui1));
        COMSerial.instance().sendHex(guozhiduankou, "020611" + "91" + shui1 + crc2("91" + shui1));
        String finalGuozhi = guozhi1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //浆料份数1
                MainActivity.sendData("020611" + "9B" + finalGuozhi + crc2("9B" + finalGuozhi));
            }
        }, 500);
        Log.e("11", "020611" + "91" + shui1 + crc2("91" + shui1));
        Log.e("11", "020611" + "9B" + finalGuozhi + crc2("9B" + finalGuozhi));
    }


    //出水速度
    public void chushui(String i2, int i) {
        String guozhi1 = Integer.toHexString(i);
        if (guozhi1.length() == 2) {
            guozhi1 = "00" + guozhi1;
        } else if (guozhi1.length() == 3) {
            guozhi1 = "0" + guozhi1;
        }

        //水份数1
        MainActivity.sendData("020613" + i2 + guozhi1 + crc3(i2, guozhi1));
    }


    //出水设置
    public String crc(String s1, String s2) {
        String s = "020611" + s1 + s2;

        byte[] bytes = hexStringToBytes(s);
//        Log.e("sssss", String.valueOf(bytes));
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        CRC = ((CRC & 0x0000FF00) >> 8) | ((CRC & 0x000000FF) << 8);
        if (Integer.toHexString(CRC).length()==3){
            return "0"+Integer.toHexString(CRC);
        }else {
            return Integer.toHexString(CRC);
        }
    }

    //温度设置
    public String crc2(String s1) {
        String s = "020611" + s1;

        byte[] bytes = hexStringToBytes(s);
//        Log.e("sssss", String.valueOf(bytes));
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        CRC = ((CRC & 0x0000FF00) >> 8) | ((CRC & 0x000000FF) << 8);
        if (Integer.toHexString(CRC).length()==3){
            return "0"+Integer.toHexString(CRC);
        }else {
            return Integer.toHexString(CRC);
        }
    }

    //水流速度设置
    public String crc3(String s1, String s2) {
        String s = "020613" + s1 + s2;

        byte[] bytes = hexStringToBytes(s);
//        Log.e("sssss", String.valueOf(bytes));
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        CRC = ((CRC & 0x0000FF00) >> 8) | ((CRC & 0x000000FF) << 8);
        if (Integer.toHexString(CRC).length()==3){
            return "0"+Integer.toHexString(CRC);
        }else {
            return Integer.toHexString(CRC);
        }
    }

    /**
     * hex字符串转byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    //转换char to byte
    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

}