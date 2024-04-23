package com.licheedev.serialtool.comn;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginServer {
    private static String http = "https://manage-mis.threeapple.cn/api";

    /**
     * get的方式请求设备是否可用
     *
     * @param id 用户名
     * @return 返回null 登录异常
     */
    public String loginByGet(String id) {
        //get的方式提交就是url拼接的方式
        String path = http + "/equipment/selectEquipmentIsUseAble" + "?equipmentId=" + id;
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Authorization",token);
            //获得结果码
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                //请求成功 获得返回的流
                InputStream is = connection.getInputStream();
                return is.toString();
            } else {
                //请求失败
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * post的方式请求
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回null 登录异常
     */
    public static String loginByPost(String username, String password) {
        String path = "http://172.16.168.111:1010/login.php";
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("POST");

            //数据准备
            String data = "username=" + username + "&password=" + password;
            //至少要设置的两个请求头
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", data.length() + "");

            //post的方式提交实际上是留的方式提交给服务器
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(data.getBytes());

            //获得结果码
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                //请求成功
                InputStream is = connection.getInputStream();
                return is.toString();
            } else {
                //请求失败
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送验证码
     */
    public String fasongyanzhengma(String id) {
        //get的方式提交就是url拼接的方式
        String path = http + "/login/sendVerifyCodeMsg" + "?phone=" + id;
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            BufferedReader reader = null;
            //获得结果码
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                //请求成功 获得返回的流
                InputStream is = connection.getInputStream();
                //转换成一个加强型的buffered流
                reader = new BufferedReader(new InputStreamReader(is));
                //把读到的内容赋值给result
                String result = reader.readLine();
                JSONObject json_test = new JSONObject(result);
                String js = json_test.get("data").toString();
//                String json = js.substring(js.indexOf("{"), js.lastIndexOf("}") + 1);
                //打印json 数据
                Log.e("json", js);
                return js;
            } else {
                //请求失败
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 验证码注册并登录
     */
    public String yanzhengmadenglu(String code, String outid, String phone) {
        //get的方式提交就是url拼接的方式
        String path = http + "/login/registerAndLogin" + "?code=" + code + "&outId=" + outid + "&phone=" + phone;
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Authorization",token);
            //获得结果码
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                //请求成功 获得返回的流
                InputStream is = connection.getInputStream();
                //转换成一个加强型的buffered流
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                //把读到的内容赋值给result
                String result = reader.readLine();
                JSONObject json_test = new JSONObject(result);
                String js = json_test.get("data").toString();
//                String json = js.substring(js.indexOf("{"), js.lastIndexOf("}") + 1);
                //打印json 数据
                Log.e("token", js);
                return js;
            } else {
                //请求失败
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 判断用户是否登录
     */
    public String panduandenglu(String token,String phone) {
        //get的方式提交就是url拼接的方式
        String path = http + "/login/verifyLoginStatus";
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", token);
            connection.setRequestProperty("phone", phone);
            //获得结果码
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                //请求成功 获得返回的流
                InputStream is = connection.getInputStream();
                String response = getStringFromInputStream(is);
                return response;
//
//                InputStream is = connection.getInputStream();
//                return is.toString();
            } else {
                //请求失败
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * post 添加果汁记录
     * <p>
     * cardId	string 支付卡号
     * equipmentId	string 设备id
     * payType	integer($int32) 支付方式 1 IC卡 2 线上支付
     * price	integer($int32) 果汁金额
     * size	integer($int32) 果汁规格 1小杯 2中杯 3大杯
     * type	integer($int32) 果汁类型1果汁 2梨汁
     */
    public String addjuiceByPost(String equipmentId, int size, int price, int type, int productTaste,int payType) {
        String path = http + "/app/saleRecord/recordJuice";
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("POST");

            Map<String, Object> params = new HashMap<>();
            params.put("equipmentId", equipmentId);
            params.put("size", size);
            params.put("price", price);
            params.put("type", type);
            params.put("productTaste", productTaste);
            params.put("payType", payType);

            JSONObject mapObject = new JSONObject(params);


            //数据准备
//            String data = "cardId=" + cardId + "&equipmentId=" + equipmentId + "&payType=" + payType + "&price=" + price + "&size=" + size + "&type=" + type;
            //至少要设置的两个请求头
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", mapObject.toString().length() + "");

            //post的方式提交实际上是留的方式提交给服务器
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(mapObject.toString().getBytes());

            //获得结果码
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                //请求成功 获得返回的流
                InputStream is = connection.getInputStream();
                //转换成一个加强型的buffered流
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                //把读到的内容赋值给result
                String result = reader.readLine();
                JSONObject json_test = new JSONObject(result);
//                if (result)
                String js = json_test.get("data").toString();
                JSONObject idjs = new JSONObject(js);
                String recordId = idjs.optString("recordId", null);
                return recordId;
            } else {
                //请求失败
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("22222222222222",e.toString());
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * get的方式请求支付二维码
     *
     * @param id 用户名
     * @return 返回null 登录异常
     */
    public String erweimaByGet(String id,int pay) {
        //get的方式提交就是url拼接的方式
        String path = http + "/app/pay/initiatePay" + "?recordId=" + id+"&payType=" + pay;
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Authorization",token);
            //获得结果码
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                //请求成功 获得返回的流
                InputStream is = connection.getInputStream();
                //转换成一个加强型的buffered流
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                //把读到的内容赋值给result
                String result = reader.readLine();
                JSONObject json_test = new JSONObject(result);
                String js = json_test.get("data").toString();
                return js;
            } else {
                //请求失败
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * get的方式查询支付结果
     *
     */
    public String findzhifuByGet(String recordid) {
        //get的方式提交就是url拼接的方式
        String path = http + "/app/pay/queryOrder" + "?recordId=" + recordid;
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Authorization",token);
            //获得结果码
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                //请求成功 获得返回的流
                InputStream is = connection.getInputStream();
                //转换成一个加强型的buffered流
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                //把读到的内容赋值给result
                String result = reader.readLine();
                JSONObject json_test = new JSONObject(result);
                String js = json_test.get("data").toString();
                return js;
            } else {
                //请求失败
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }



    private static String getStringFromInputStream(InputStream is) {
        String state = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //代码板块 必须熟练
        byte[] buf = new byte[1024];
        int len;
        try {
            while ((len = is.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            is.close();
            //把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
            state = os.toString();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }


}
