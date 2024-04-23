package com.licheedev.serialtool.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.base.BaseActivity;
import com.licheedev.serialtool.comn.Device;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.model.Event;
import com.licheedev.serialtool.util.AutoUpdater;
import com.licheedev.serialtool.util.LoopViewAdapter;
import com.licheedev.serialtool.util.RadioDialog;
import com.licheedev.serialtool.util.RadioDialog2;
import com.licheedev.serialtool.util.RadioDialog3;
import com.licheedev.serialtool.util.RadioDialog4;
import com.licheedev.serialtool.util.RadioDialog5;
import com.licheedev.serialtool.util.RadioDialog6;
import com.licheedev.serialtool.util.pagerOnClickListener;
import com.vi.vioserial.COMSerial;
import com.vi.vioserial.listener.OnComDataListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.setting)
    ImageButton setting;
    @BindView(R.id.coution)
    ImageButton coution;
    @BindView(R.id.quit)
    TextView quit;
    @BindView(R.id.wifi)
    ImageButton wifi;


    private ViewPager viewPager;  //轮播图模块
    private int[] mImg;
    private ArrayList<Integer> datas = new ArrayList<>();
    private int[] mImg_id;
    private ArrayList<ImageView> mImgList;
    private LinearLayout ll_dots_container;
    private TextView loop_dec;
    private int previousSelectedPosition = 0;
    boolean isRunning = false;


    private Device mDevice;
    private Device mDevice2;

    private int mDeviceIndex;
    private int mBaudrateIndex;

    private String[] mDevices;
    private String[] mBaudrates;

    private boolean mOpened = false;

    private RecyclerView recyclerview_horizontal3;
    private GalleryAdapter mAdapter3;
    private List<Integer> mDatas3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 全透状态栏
         */
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

//        initDevice();
        initLoopView();
        switchSerialPort();//打开串口
//        updateViewState(mOpened);
//        sendData("020611FC00370D23");
//        sendData("020611FC00370D23");
        COMSerial.instance().sendHex("/dev/ttyS2", "020611CD00375CEC");
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    COMSerial.instance().sendHex("/dev/ttyS3", "03 06 11 FC 00 37 0C F2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //TODO: 定时做某件事情
                Log.e("网络状态",String.valueOf(isNetworkConnected(MainActivity.this)));
                if (isNetworkConnected(MainActivity.this)==true){
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            wifi.setBackgroundResource(R.mipmap.wifitrue);
                        }
                    });
                }else {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            wifi.setBackgroundResource(R.mipmap.wififalse);
                        }
                    });

                }
            }
        },  5 * 1000,60 * 1000);

        //接收数据
        COMSerial.instance().addDataListener(new OnComDataListener() {
            @Override
            public void comDataBack(String com, String hexData) {
                //com 为串口号
                //hexData 为接收到的数据
                Log.e("设备:" + com, "数据:" + hexData);

                if (hexData.equals("0303023200D4E4")) {
                    SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                    String wendu = sharedPreferences.getString("wendu", "");
                    if (wendu.equals("1cold")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "020611D0000BCCFB");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }
                    if (wendu.equals("2cold")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 0C 8D 39");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("3cold")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 0D 4C F9");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("4cold")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 0E 0C F8");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("5cold")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 0F CD 38");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("6cold")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 10 8C F0");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("1hot")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 15 4C F3");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("2hot")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 16 0C F2");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("3hot")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 17 CD 32");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("4hot")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 18 8D 36");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("5hot")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 19 4C F6");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("6hot")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 1A 0C F7");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("1qipao")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 1F CC F4");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("2qipao")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 20 8C E4");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("3qipao")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 21 4D 24");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("4qipao")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 22 0D 25");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("5qipao")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 23 CC E5");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }if (wendu.equals("6qipao")) {
                        COMSerial.instance().sendHex("/dev/ttyS2", "02 06 11 D0 00 24 8D 27");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //do something
                                COMSerial.instance().sendHex("/dev/ttyS3", "0306121C00CB0D01");
                            }
                        },4000*3);//延时1s执行
                    }


                }


            }
        });

        initDatas();


        //检查更新
        try {
            //6.0才用动态权限
            if (Build.VERSION.SDK_INT >= 23) {
                String[] permissions = {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.INTERNET};
                List<String> permissionList = new ArrayList<>();
                for (int i = 0; i < permissions.length; i++) {
                    if (ActivityCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                        permissionList.add(permissions[i]);
                    }
                }
                if (permissionList.size() <= 0) {
                    //说明权限都已经通过，可以做你想做的事情去
                    //自动更新
                    AutoUpdater manager = new AutoUpdater(MainActivity.this);
                    manager.CheckUpdate();
                } else {
                    //存在未允许的权限
                    ActivityCompat.requestPermissions(this, permissions, 100);
                }
            }
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "自动更新异常：" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }


    private void initDatas() {
        mDatas3 = new ArrayList<>(Arrays.asList(R.mipmap.pager1, R.mipmap.pager2, R.mipmap.pager3, R.mipmap.pager4, R.mipmap.pager5, R.mipmap.pager6, R.mipmap.pager7));

        //得到控件
        recyclerview_horizontal3 = (RecyclerView) findViewById(R.id.recyclerview_horizontal3);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this);
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview_horizontal3.setLayoutManager(linearLayoutManager3);
        //设置适配器
        mAdapter3 = new GalleryAdapter(this, mDatas3);
        recyclerview_horizontal3.setAdapter(mAdapter3);
        mAdapter3.setOnItemClickListener(new onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
//                sendData("0206121000014C84");
                //这里强制设置自动模式
                COMSerial.instance().sendHex("/dev/ttyS2", "020611CE0000ED3A");
                COMSerial.instance().sendHex("/dev/ttyS3", "0306120C00004D53");
                if (position == 0) {
                    RadioDialog radioDialog = new RadioDialog(MainActivity.this);
                    radioDialog.show();
                    radioDialog.setCancelOnClickListener(v -> radioDialog.close());
                    radioDialog.setOkOnClickListener(v -> {
//                    String item=radioDialog.getItem();
//                    Toast.makeText(context,item,Toast.LENGTH_SHORT).show();
                        radioDialog.close();
                    });
                }
                if (position == 1) {
                    RadioDialog2 radioDialog2 = new RadioDialog2(MainActivity.this);
                    radioDialog2.show();
                    radioDialog2.setCancelOnClickListener(v -> radioDialog2.close());
                    radioDialog2.setOkOnClickListener(v -> {
//                    String item=radioDialog2.getItem();
//                    Toast.makeText(context,item,Toast.LENGTH_SHORT).show();
                        radioDialog2.close();
                    });
                }
                if (position == 2) {
                    RadioDialog3 radioDialog3 = new RadioDialog3(MainActivity.this);
                    radioDialog3.show();
                    radioDialog3.setCancelOnClickListener(v -> radioDialog3.close());
                    radioDialog3.setOkOnClickListener(v -> {
//                    String item=radioDialog2.getItem();
//                    Toast.makeText(context,item,Toast.LENGTH_SHORT).show();
                        radioDialog3.close();
                    });
                }
                if (position == 3) {
                    RadioDialog4 radioDialog4 = new RadioDialog4(MainActivity.this);
                    radioDialog4.show();
                    radioDialog4.setCancelOnClickListener(v -> radioDialog4.close());
                    radioDialog4.setOkOnClickListener(v -> {
//                    String item=radioDialog2.getItem();
//                    Toast.makeText(context,item,Toast.LENGTH_SHORT).show();
                        radioDialog4.close();
                    });
                }
                if (position == 4) {
                    RadioDialog5 radioDialog5 = new RadioDialog5(MainActivity.this);
                    radioDialog5.show();
                    radioDialog5.setCancelOnClickListener(v -> radioDialog5.close());
                    radioDialog5.setOkOnClickListener(v -> {
//                    String item=radioDialog2.getItem();
//                    Toast.makeText(context,item,Toast.LENGTH_SHORT).show();
                        radioDialog5.close();
                    });
                }
                if (position == 5) {
                    RadioDialog6 radioDialog6 = new RadioDialog6(MainActivity.this);
                    radioDialog6.show();
                    radioDialog6.setCancelOnClickListener(v -> radioDialog6.close());
                    radioDialog6.setOkOnClickListener(v -> {
//                    String item=radioDialog2.getItem();
//                    Toast.makeText(context,item,Toast.LENGTH_SHORT).show();
                        radioDialog6.close();
                    });
                }
//                else {
//                    Toast.makeText(MainActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onItemLongClick(RecyclerView parent, View view, int position) {
                //自定义长按后的功能事件 ...
            }
        });
    }


    /**
     * 判断是否有网络连接
     */
    public  boolean isNetworkConnected(Context context) {
        if (context != null) {
            // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取NetworkInfo对象
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            //判断NetworkInfo对象是否为空
            if (networkInfo != null)
                return networkInfo.isAvailable();
        }
        return false;
    }



    //获取子线程电路板数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void msg(Event event) {
        System.out.println(event.getMsg());
        //获取SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        //获取Editor对象的引用
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //将获取过来的值放入文件
        editor.putString("sb", event.getMsg());
        // 提交数据
        editor.commit();
    }


    //recyclerview接口
    public interface onRecyclerViewItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position);

        void onItemLongClick(RecyclerView parent, View view, int position);
    }

    public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> implements View.OnClickListener {
        private LayoutInflater mInflater;
        private List<Integer> mDatas;
        //初使化自定义的接口，接口定义在哪个包下就会把哪个包名也引入进来，只是名字长点，不影响使用
        private onRecyclerViewItemClickListener onRecyclerViewItemClickListener;
        //为了方便接口函数传值，这里把RecyclerView控件拉出来，如果接口方法参数列表里没有，可以不用写
        private RecyclerView rvParent;

        public GalleryAdapter(Context context, List<Integer> datats) {
            mInflater = LayoutInflater.from(context);
            mDatas = datats;
        }

        @Override
        public void onClick(View v) {
            //此时就用到上面拉出来的那个 rvParent 了
            int position = rvParent.getChildAdapterPosition(v);
            if (onRecyclerViewItemClickListener != null)
                onRecyclerViewItemClickListener.onItemClick(rvParent, v, position);
        }

        //定义一个公用方法，可以在Activity中来绑定接口事件
        public void setOnItemClickListener(onRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
            this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View arg0) {
                super(arg0);
            }

            ImageView mImg;
            TextView mTxt;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }


        /**
         * 创建ViewHolder
         */
        @SuppressLint("MissingInflatedId")
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            rvParent = (RecyclerView) viewGroup;
            View view = mInflater.inflate(R.layout.item_listview,
                    viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.mImg = (ImageView) view
                    .findViewById(R.id.id_index_gallery_item_image);
            //绑定监听点击事件，因为类本身 implement 了 View.OnClickListener 此时只要监听绑定到 this 就可以了
            view.setOnClickListener(this);
            return viewHolder;
        }


        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
            viewHolder.mImg.setImageResource(mDatas.get(i));
        }
    }


    @Override
    protected void onDestroy() {
        SerialPortManager.instance().close();
        super.onDestroy();
    }

    @Override
    protected boolean hasActionBar() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    private void initLoopView() {
        viewPager = (ViewPager) findViewById(R.id.loopviewpager);
        ll_dots_container = (LinearLayout) findViewById(R.id.ll_dots_loop);
        loop_dec = (TextView) findViewById(R.id.loop_dec);

        // 图片资源id数组
        mImg = new int[]{
                R.drawable.test1,
                R.drawable.test2,
                R.drawable.test3,
//                R.drawable.test4,
//                R.drawable.test5
        };

        mImg_id = new int[]{
                R.id.pager_img1,
                R.id.pager_img2,
                R.id.pager_img3,
                R.id.pager_img4,
                R.id.pager_img5
        };

        // 初始化要展示的5个ImageView
        mImgList = new ArrayList<ImageView>();
        ImageView imageView;
        View dotView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < mImg.length; i++) {
            //初始化要显示的图片对象
            imageView = new ImageView(this);
            imageView.setBackgroundResource(mImg[i]);
            imageView.setId(mImg_id[i]);
            imageView.setOnClickListener(new pagerOnClickListener(getApplicationContext()));//图片点击操作
            mImgList.add(imageView);
            //加引导点
            dotView = new View(this);
            dotView.setBackgroundResource(R.drawable.dot);
            layoutParams = new LinearLayout.LayoutParams(10, 10);
            if (i != 0) {
                layoutParams.leftMargin = 10;
            }
            //设置默认所有都不可用
            dotView.setEnabled(false);
            ll_dots_container.addView(dotView, layoutParams);
        }

        ll_dots_container.getChildAt(0).setEnabled(true);
        previousSelectedPosition = 0;
        //设置适配器
        viewPager.setAdapter(new LoopViewAdapter(mImgList));
        // 把ViewPager设置为默认选中Integer.MAX_VALUE / t2，从十几亿次开始轮播图片，达到无限循环目的;
        int m = (Integer.MAX_VALUE / 2) % mImgList.size();
        int currentPosition = Integer.MAX_VALUE / 2 - m;
        viewPager.setCurrentItem(currentPosition);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int newPosition = i % mImgList.size();
                ll_dots_container.getChildAt(previousSelectedPosition).setEnabled(false);
                ll_dots_container.getChildAt(newPosition).setEnabled(true);
                previousSelectedPosition = newPosition;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // 开启轮询
        new Thread() {
            public void run() {
                isRunning = true;
                while (isRunning) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //下一条
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }.start();

    }


    @OnClick({R.id.setting, R.id.coution, R.id.quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;

            case R.id.coution:
                startActivity(new Intent(this, WindowActivity.class));
                break;

            case R.id.quit:
                finish();
                break;

        }
    }

    public static void sendData(String s) {

        String text = s;
        if (TextUtils.isEmpty(text) || text.length() % 2 != 0) {
//            ToastUtil.showOne(this, "无效数据");
            return;
        }

        SerialPortManager.instance().sendCommand(text);
    }

    /**
     * 打开或关闭串口
     */
    private void switchSerialPort() {

        COMSerial.instance().addCOM("/dev/ttyS2", 9600);
        COMSerial.instance().addCOM("/dev/ttyS3", 9600);


//        if (mOpened) {
//            SerialPortManager.instance().close();
//            mOpened = false;
//        } else {
//
//            // 保存配置
//            PrefHelper.getDefault().saveInt(PreferenceKeys.SERIAL_PORT_DEVICES, mDeviceIndex);
//            PrefHelper.getDefault().saveInt(PreferenceKeys.BAUD_RATE, mBaudrateIndex);
//
//            mOpened = SerialPortManager.instance().open(mDevice) != null;
//            SerialPortManager.instance().open(mDevice2);
//            if (mOpened) {
//                ToastUtil.showOne(this, "成功打开串口");
//            } else {
//                ToastUtil.showOne(this, "打开串口失败");
//            }
//        }
//        updateViewState(mOpened);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean haspermission = false;
        if (100 == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    haspermission = true;
                }
            }
            if (haspermission) {
                //跳转到系统设置权限页面，或者直接关闭页面，不让他继续访问
                permissionDialog();
            } else {
                //全部权限通过，可以进行下一步操作
                AutoUpdater manager = new AutoUpdater(MainActivity.this);
                manager.CheckUpdate();
            }
        }
    }

    AlertDialog alertDialog;

    //打开手动设置应用权限
    private void permissionDialog() {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle("提示信息")
                    .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();
                            Uri packageURI = Uri.parse("package:" + getPackageName());
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();
                        }
                    })
                    .create();
        }
        alertDialog.show();
    }

    private void cancelPermissionDialog() {
        alertDialog.cancel();
    }


}
