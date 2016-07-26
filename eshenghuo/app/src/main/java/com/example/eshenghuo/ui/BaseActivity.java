package com.example.eshenghuo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.example.eshenghuo.util.ExitUtil;
import com.example.eshenghuo.util.ToastUtils;

/**
 * Created by Administrator on 2016/6/22.
 */
public class BaseActivity extends FragmentActivity {
    /**
     *  返回键 退出时间
     */
    private long firstTime, spaceTime, secondTime;
    /**
     * 双击退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String currentActivityName = this.getClass().getSimpleName();
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 && currentActivityName.equals("HomeActivity")) {
            firstTime = System.currentTimeMillis();
            spaceTime = firstTime - secondTime;
            secondTime = firstTime;
            if (spaceTime > 2000) {
                ToastUtils.toastLong(this, "点击第二次退出");
                return false;
            } else {
                ExitUtil.getInstance().exit();
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    public void startNextActivity(Bundle bundle, Class<?> pClass, boolean finishFlag) {
        Intent intent = new Intent(this, pClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (finishFlag) {
            super.finish();
        }
    }

    @Override
    public void onBackPressed() {
         super.onBackPressed();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //管理器清除当前Activity
        ExitUtil.getInstance().removeActivity(this);

    }
}
