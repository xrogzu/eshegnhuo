package com.example.eshenghuo.util;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.example.eshenghuo.CustomApplcation;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Volley封装类
 * Created by acer on 2016/1/28.
 */
public class VolleyUtils {
    private static final String TAG = "JSON";

    /**
     * 请求网络
     * @param requestQueue 需要利用BaseApplication中的getRequestQueue方法进行获取
     * @param httpurl 请求网络的地址
     * @param parameter 传参key，必须与value对应
     * @param params 传参value，必须与key对应
     * @param handler 自定的handler，处理网络请求返回
     * @param which 与handler对应的handler
     */
    public static void NetUtils(RequestQueue requestQueue, final String httpurl, final String[] parameter, final String[] params, final Handler handler, final int which) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, httpurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        response=decodeData(response);// 解密json密文串
                        try {
                            JSONObject object = new JSONObject(response);
                            String result = object.getString("resultCode");
                            if (result.equals("0")) {
                                Message message = Message.obtain();
                                message.obj = response;
                                message.what = which;
                                handler.sendMessage(message);
                            } else {
                                String messageIslogin = object.getString("message");
                                if (messageIslogin.equals("用户尚未登录") || messageIslogin.equals("用户未登录，请先登录。") || messageIslogin.equals("服务器连接超时，请刷新页面！")) {
                                    Toast.makeText(CustomApplcation.getAppContext(),"请先登录", Toast.LENGTH_SHORT);

                                } else {
                                    Message message = Message.obtain();
                                    message.obj = response;
                                    message.what = which;
                                    handler.sendMessage(message);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("Request", "response -> " + response);
                            Message message = Message.obtain();
                            message.obj = response;
                            message.what = which;
                            handler.sendMessage(message);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Request", "错误-->" + error.getMessage(), error);
                NetworkResponse response = error.networkResponse;
                Toast.makeText(CustomApplcation.getAppContext(),"请检查网络", Toast.LENGTH_SHORT);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                if (parameter != null) {
                    for (int i = 0; i < parameter.length; i++) {
//                        map.put(parameter[i],fieldEncryption(params[i]));//加密参数
                          map.put(parameter[i],params[i]);//未加密字段
                    }
                }
                return map;
            }

            //获取返回的cookies
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    CookieManager cookieManager = CookieManager.getInstance();
                    Map<String, String> responseHeaders = response.headers;
                    String rawCookies = responseHeaders.get("Set-Cookie");//Cookie值
                    cookieManager.setCookie("http://www.shequchina.cn/javamall", rawCookies);//设置baseURL**************************************
                    CookieSyncManager.createInstance( CustomApplcation.getAppContext() );
                    CookieSyncManager.getInstance().sync();
                    String dataString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));//返回值
                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }

            }

            //设置post的cookies
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                CookieManager cookieManager = CookieManager.getInstance();
                HashMap localHashMap = new HashMap();
                if (cookieManager.hasCookies()) {
                    //Log.e(TAG,"里面还是有cookie的");
                    String cookie = cookieManager.getCookie(httpurl);

                    if (!TextUtils.isEmpty(cookie)) {
                      // Log.e(TAG,"从本地取出的cookier--->"+cookie);

                        localHashMap.put("Cookie", cookie);
                    }
                }
                //Log.e(TAG,"这是空的");
                return localHashMap;
            }
        };
        stringRequest.setTag(TAG);
        requestQueue.add(stringRequest);
    }


    //加密字段
    public static String fieldEncryption(String field){
        String afterencrypt = null;
        try {
            // 从文件中得到公钥
            InputStream inPublic = CustomApplcation.getAppContext().getResources().getAssets().open("rsa_public_key.pem");
            PublicKey publicKey = RSAUtils.loadPublicKey(inPublic);
            // 加密
            byte[] encryptByte = RSAUtils.encryptData(field.getBytes(), publicKey);
            // 为了方便观察吧加密后的数据用base64加密转一下，要不然看起来是乱码,所以解密是也是要用Base64先转换
            afterencrypt= Base64Utils.encode(encryptByte);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return afterencrypt;
    }
    //解密字段
    public static String decodeData(String json){
        String decryptStr=null;
        try
        {
            // 从文件中得到私钥
            InputStream inPrivate = CustomApplcation.getAppContext(). getResources().getAssets().open("pkcs8_rsa_private_key.pem");
            PrivateKey privateKey = RSAUtils.loadPrivateKey(inPrivate);
            // 因为RSA加密后的内容经Base64再加密转换了一下，所以先Base64解密回来再给RSA解密
            byte[] decryptByte = RSAUtils.decryptData(Base64Utils.decode(json), privateKey);
            decryptStr = new String(decryptByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return decryptStr;
    }
}


