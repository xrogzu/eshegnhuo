
package com.example.eshenghuo.util;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eshenghuo.R;


/**
 * 吐司工具类
 */
public class ToastUtils {

    public static void toastShort(Context context, String msg) {
        try {
            View toastView = LayoutInflater.from(context).inflate(R.layout.layout_view_toast, null);
            TextView mTvToast = (TextView) toastView.findViewById(R.id.tv_toast);
            mTvToast.setText(msg);
            Toast toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(toastView);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toastLong(Context context, String msg) {
        try {
            View toastView = LayoutInflater.from(context).inflate(R.layout.layout_view_toast, null);
            TextView mTvToast = (TextView) toastView.findViewById(R.id.tv_toast);
            mTvToast.setText(msg);
            Toast toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastView);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 成功
    public static void toastSuccess(Context context, String title) {
        View toastView = LayoutInflater.from(context).inflate(R.layout.layout_main_toast, null);
        ImageView iv = (ImageView) toastView.findViewById(R.id.iv_toast);
        TextView tv = (TextView) toastView.findViewById(R.id.tv_show);
        tv.setVisibility(View.VISIBLE);
        tv.setText(title);
        iv.setImageResource(R.mipmap.ic_contacts_yes);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.show();
    }

    // 失败
    public static void toastFail(Context context, String title) {
        View toastView = LayoutInflater.from(context).inflate(R.layout.layout_main_toast, null);
        ImageView iv = (ImageView) toastView.findViewById(R.id.iv_toast);
        TextView tv = (TextView) toastView.findViewById(R.id.tv_show);
        tv.setVisibility(View.VISIBLE);
        tv.setText(title);
        iv.setImageResource(R.mipmap.ic_contacts_no);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.show();
    }

}
