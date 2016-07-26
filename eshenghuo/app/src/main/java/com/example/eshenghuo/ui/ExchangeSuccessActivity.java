package com.example.eshenghuo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eshenghuo.R;

/**
 * Created by Administrator on 2016/7/14.
 */
public class ExchangeSuccessActivity extends BaseActivity implements View.OnClickListener {
    private ImageView exchange_success_back;
    private TextView exchange_success_confirm,exchange_success_record;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_success);
        initView();
        intListener();
    }

    private void intListener() {
        exchange_success_back.setOnClickListener(this);
        exchange_success_confirm.setOnClickListener(this);
        exchange_success_record.setOnClickListener(this);

    }

    private void initView() {
        exchange_success_back= (ImageView) findViewById(R.id.exchange_success_back);
        exchange_success_confirm= (TextView) findViewById(R.id.exchange_success_confirm);
        exchange_success_record= (TextView) findViewById(R.id.exchange_success_record);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exchange_success_back:
                this.finish();
                break;
            case R.id.exchange_success_confirm:
               startNextActivity(null,IntegralStoreActivity.class,true);
                break;
            case R.id.exchange_success_record:
              startNextActivity(null,ExpenseCalendarActivity.class,false);
                break;
        }

    }
}
