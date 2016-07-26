package com.example.eshenghuo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.eshenghuo.bean.User;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by Administrator on 2016/6/22.
 */
public class CustomApplcation extends Application {
    public static User user;
    //Volley的请求队列
    private RequestQueue mRequestQueue;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        CustomApplcation.context = getApplicationContext();
        initImageLoader(getApplicationContext());
    }

    private void initImageLoader(Context context) {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true)
                .showStubImage(R.mipmap.plugin_camera_no_pictures) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.plugin_camera_no_pictures) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.plugin_camera_no_pictures) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);


    };
    //获取全局context
    public static Context getAppContext() {
        return CustomApplcation.context;
    }
    //获取volley请求队列
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }
    public static void signIn(Context context, User user) {
        CustomApplcation.user=user;
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_info", new Gson().toJson(user));
        editor.commit();
    }
    public static boolean Islogin(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Activity.MODE_PRIVATE);
        String jsonUserInfo = sharedPreferences.getString("user_info", "");
        if("".equals(jsonUserInfo)){
            return false;
        }
        return true;
    }
    public static void signOut(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_info", "");
        editor.commit();
        user=null;
    }
    //启动时调用给内存中的user赋值
    public static User getUserInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Activity.MODE_PRIVATE);
        String jsonUserInfo = sharedPreferences.getString("user_info", "");
        if("".equals(jsonUserInfo)){
            return null;
        }else {
            user = new Gson().fromJson(jsonUserInfo, User.class);
            return user;
        }
    }
}
