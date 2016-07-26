package com.example.eshenghuo.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.eshenghuo.R;
import com.example.eshenghuo.ui.BaseActivity;

/**
 * Created by Administrator on 2016/7/2.
 */
public class PopwindowShow {


    /**
     *
     * @param content 上下文环境
     * @param view popup 弹出层布局
     * @param popupWindow popupwindow
     * @param locationView 弹框父控件
     * @param  height 相对于窗口的百分比高度
     */
     public static void showPopwindow(final BaseActivity content, View view, PopupWindow popupWindow, View locationView,int height){
         // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
         popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
         DisplayMetrics dm = new DisplayMetrics(); //获取窗口高度
         content.getWindowManager().getDefaultDisplay().getMetrics(dm);  //取得窗口属性
         popupWindow.setHeight(dm.heightPixels*height/100); //窗口高度
        //  popupWindow.setHeight(1050); //窗口高度
         // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
         popupWindow.setFocusable(true);
         // 必须要给调用这个方法，否则点击popWindow以外部分，popWindow不会消失
         ColorDrawable dw = new ColorDrawable(0x000000);
         popupWindow.setBackgroundDrawable(dw);
         backgroundAlpha(0.5f,content);//背景半透明
         // 设置popWindow的显示和消失动画
         popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
         popupWindow.showAtLocation(locationView, Gravity.BOTTOM, 0, 0);  // 在显示位置
         popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {     // popWindow消失监听方法
             @Override
             public void onDismiss() {
                 backgroundAlpha(1f,content); //窗体背景透明度取消
             }
         });
     }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public static void backgroundAlpha(float bgAlpha,BaseActivity context) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().setAttributes(lp);
    }
}
