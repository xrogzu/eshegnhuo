package com.example.eshenghuo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eshenghuo.CustomApplcation;
import com.example.eshenghuo.R;
import com.example.eshenghuo.bean.TestBean;
import com.example.eshenghuo.util.ToastUtils;
import com.example.eshenghuo.util.VolleyUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/7/16.
 * 注册
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText register_phone,register_password,confirm_password;
    private TextView register_confirm;
    private CustomApplcation application;
    private int REGISTER = 100001;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        application = (CustomApplcation) getApplication();
        initView();
        initListener();
    }

    private void initListener() {
        register_confirm.setOnClickListener(this);
    }

    private void initView() {
        register_phone= (EditText) findViewById(R.id.register_phone);
        register_password= (EditText) findViewById(R.id.register_password);
        confirm_password= (EditText) findViewById(R.id.confirm_password);
        register_confirm= (TextView) findViewById(R.id.register_confirm);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_confirm:

                String phone=register_phone.getText().toString();
                String password=register_password.getText().toString();
                String password2=confirm_password.getText().toString();
                if(!isMobile(phone)){
                    ToastUtils.toastFail(this,"请输入正确的手机号码");
                }else if(!isPassword(password)){
                    ToastUtils.toastFail(this,"密码格式不正确");
                }else if(!password.equals(password2)) {
                    ToastUtils.toastFail(this,"两次密码不一致");
                }else{
                    register(phone,password);
                }
                break;
        }

    }
    /**
     * 手机号验证
     * @param  str
     * @return 验证通过返回true
     */
    public  boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 验证密码
     * @param password
     * @return
     */
     public boolean isPassword(String password){
         if(password.length()>6&&password.length()<12){
             return true;
         }
         return false;
     }


    private void register(String phone,String password){
        String Url = "http://192.168.1.114:8080/springTest/member/registMember.do?";//ip地址

        MyHandler handler=new MyHandler(RegisterActivity.this);//逻辑层
        VolleyUtils.NetUtils(application.getRequestQueue(), Url, new String[]{"r_memberAccount","r_passward"}, new String[]{phone,password}, handler, REGISTER);//通讯层请求封装，包括加密

    }
    //内部静态类，使用官方推荐弱引用
    private static class  MyHandler extends Handler {
        private WeakReference<RegisterActivity> mActivity;
        public MyHandler(RegisterActivity activity) {
            mActivity = new WeakReference<RegisterActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            RegisterActivity activity=mActivity.get();
            String Json = (String) msg.obj;
            switch (msg.what){
                case 100001:
                    if(activity!=null) {
                        try {
                            JSONObject object = new JSONObject(Json);
                            String result = object.getString("resultCode");
                        if (result.equals("0")) {
                           ToastUtils.toastSuccess(activity,"注册成功");
                        }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
