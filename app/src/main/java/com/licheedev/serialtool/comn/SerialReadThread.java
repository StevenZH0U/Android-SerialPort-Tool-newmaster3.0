package com.licheedev.serialtool.comn;

import android.os.SystemClock;
import android.util.Log;

import com.licheedev.myutils.LogPlus;
import com.licheedev.serialtool.comn.message.LogManager;
import com.licheedev.serialtool.comn.message.RecvMessage;
import com.licheedev.serialtool.model.Event;
import com.licheedev.serialtool.util.ByteUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 读串口线程
 */
public class SerialReadThread extends Thread {

    private static final String TAG = "SerialReadThread";

    private BufferedInputStream mInputStream;
    private String sb="";

    public SerialReadThread(InputStream is) {
        mInputStream = new BufferedInputStream(is);
    }

    @Override
    public void run() {
        byte[] received = new byte[1024];
        int size;

        LogPlus.e("开始读线程");

        while (true) {

            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            try {

                int available = mInputStream.available();

                if (available > 0) {
                    size = mInputStream.read(received);
                    if (size > 0) {
                        Log.e("111111", String.valueOf(size));
                        onDataReceive(received, size);
                    }
                } else {
                    // 暂停一点时间，免得一直循环造成CPU占用率过高
                    SystemClock.sleep(1);
                }
            } catch (IOException e) {
                LogPlus.e("读取数据失败", e);
            }
            //Thread.yield();
        }

        LogPlus.e("结束读进程");
    }

    /**
     * 处理获取到的数据
     *
     * @param received
     * @param size
     */
    private void onDataReceive(byte[] received, int size) {
        // 数据拼包
        String hexStr = String.valueOf(ByteUtil.bytes2HexStr(received, 0, size));
        LogManager.instance().post(new RecvMessage(hexStr));
        Log.e("串口数据",hexStr);
        if (hexStr.length()==64){
            String s=hexStr.substring(0, 4);
            Log.e("ssss",s);
            if (s.equals("0203")){
                sb=hexStr;
            }else {
                sb=sb+hexStr;
            }
        }
        if (hexStr.length()==42){
            sb=sb+hexStr;
            Log.e("sb",sb);
            Event event = new Event(200,sb);
            EventBus.getDefault().post(event);
            sb="";
        }


    }

    /**
     * 停止读线程
     */
    public void close() {

        try {
            mInputStream.close();
        } catch (IOException e) {
            LogPlus.e("异常", e);
        } finally {
            super.interrupt();
        }
    }

    /**
     * hex字符串转byte数组
     * @param inHex 待转换的Hex字符串
     * @return  转换后的byte数组结果
     */
    public static byte[] hexToByteArray(String inHex){
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1){
            //奇数
            hexlen++;
            result = new byte[(hexlen/2)];
            inHex="0"+inHex;
        }else {
            //偶数
            result = new byte[(hexlen/2)];
        }
        int j=0;
        for (int i = 0; i < hexlen; i+=2){
            result[j]=hexToByte(inHex.substring(i,i+2));
            j++;
        }
        return result;
    }

    /**
     * Hex字符串转byte
     * @param inHex 待转换的Hex字符串
     * @return  转换后的byte
     */
    public static byte hexToByte(String inHex){
        return (byte)Integer.parseInt(inHex,16);
    }


}
