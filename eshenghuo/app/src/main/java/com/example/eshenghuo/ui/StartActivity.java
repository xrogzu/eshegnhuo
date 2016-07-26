package com.example.eshenghuo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.example.eshenghuo.CustomApplcation;
import com.example.eshenghuo.R;
import com.example.eshenghuo.bean.TestBean;
import com.example.eshenghuo.bean.User;
import com.example.eshenghuo.util.ToastUtils;
import com.example.eshenghuo.util.VolleyUtils;
import com.example.eshenghuo.view.MyProgressDialog;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/6/22.
 * 启动界面
 */
public class StartActivity extends BaseActivity {
    private MyProgressDialog mDialog;
    //内部静态类，使用官方推荐弱引用
    private static class  MyHandler extends Handler {
        private WeakReference<StartActivity> mActivity;
        public MyHandler(StartActivity activity) {
            mActivity = new WeakReference<StartActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            StartActivity activity=mActivity.get();
            String Json = (String) msg.obj;
            switch (msg.what){
                case 100001:
                    if(activity!=null) {
                        //json解析
                        Gson gson = new Gson();
                        TestBean bean = gson.fromJson(Json, TestBean.class);
                        if (bean.getResult() == 1) {
                            //逻辑处理
                           String content=bean.getData().getContent();
                            ToastUtils.toastSuccess(activity,content);//打印内容
                        }
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satrt_app);




        CustomApplcation  application = (CustomApplcation) getApplication();
        String Url = "http://www.shequchina.cn/javamall/api/mobile/fin!getProductById.do?";//ip地址
        int GET_DATA = 100001;
        MyHandler handler=new MyHandler(this);//逻辑层
        VolleyUtils.NetUtils(application.getRequestQueue(), Url, new String[]{"product_id"}, new String[]{"113"}, handler, GET_DATA);//通讯层请求封装，包括加密






//        User user= CustomApplcation.getUserInfo(this);
//        startNextActivity(null,HomeActivity.class,true);
//        if(user==null){
//            //登录
//        }else{
//            //跳转首页
//        }

        //图片下载
//        ImageView image_loader= (ImageView) findViewById(R.id.image_loader);
//        ImageLoader.getInstance().displayImage("http://img.blog.csdn.net/20130819182259359", image_loader);
        //自定义loading
        //mDialog = new MyProgressDialog(this,"加载中...");
        //mDialog.show();
        //mDialog.dismiss();
    }
}
