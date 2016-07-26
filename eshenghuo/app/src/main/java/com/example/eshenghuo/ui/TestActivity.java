package com.example.eshenghuo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.example.eshenghuo.CustomApplcation;
import com.example.eshenghuo.R;
import com.example.eshenghuo.util.ToastUtils;
import com.example.eshenghuo.util.VolleyUtils;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/6/25.
 */
public class TestActivity extends BaseActivity {
    private CustomApplcation application;
    private int REGISTER = 100001;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        String Url = "http://192.168.1.114:8080/mall/api/mobile/eshop!MP001.do?";//ip地址
        application = (CustomApplcation) getApplication();
        MyHandler handler=new MyHandler(TestActivity.this);//逻辑层
        VolleyUtils.NetUtils(application.getRequestQueue(), Url, new String[]{"CustType","MerchantId","SubMerchantId","OrderNo","OrderAmt","Currency","TrnxType","MemberId","FreezeEdDate","UnFreezeEdDate","Remark1","Remark2"}, new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"}, handler, REGISTER);//通讯层请求封装，包括加密

    }

    //内部静态类，使用官方推荐弱引用
    private static class  MyHandler extends Handler {
        private WeakReference<TestActivity> mActivity;
        public MyHandler(TestActivity activity) {
            mActivity = new WeakReference<TestActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            TestActivity activity=mActivity.get();
            String Json = (String) msg.obj;
            switch (msg.what){
                case 100001:
                    if(activity!=null) {
                        try {
                            JSONObject object = new JSONObject(Json);
                            String result = object.getString("resultCode");
                            if (result.equals("0")) {
                                ToastUtils.toastSuccess(activity,"成功");
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
