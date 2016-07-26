package com.example.eshenghuo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.eshenghuo.R;
import com.example.eshenghuo.adapter.ConfirmOrderAdapter;
import com.example.eshenghuo.bean.ConfirmOrderBean;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/16.
 * 积分确认订单
 */
public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener {
    private TextView confirm_ordeer_pay;
    private PullToRefreshListView listview;
    private ConfirmOrderAdapter adapter;
    private List<ConfirmOrderBean> listData=new ArrayList<>();
    private View confirm_order_address;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initView();
        initListener();
        getData();
    }

    private void initListener() {
        confirm_ordeer_pay.setOnClickListener(this);
        confirm_order_address.setOnClickListener(this);
    }

    private void initView() {
        confirm_ordeer_pay= (TextView) findViewById(R.id.confirm_ordeer_pay);
        listview= (PullToRefreshListView) findViewById(R.id.confirm_order_listview);
        confirm_order_address=findViewById(R.id.confirm_order_address);
        adapter= new ConfirmOrderAdapter(this);
        listview.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm_ordeer_pay:
              startNextActivity(null,ExchangeSuccessActivity.class,false);
                break;
            case R.id.confirm_order_address:
                startNextActivity(null,AddressManageActivity.class,false);
                break;
        }
    }

    public void getData() {
        for(int i=0;i<10;i++){
            ConfirmOrderBean bean=new ConfirmOrderBean();
            listData.add(bean);
        }
        adapter.listData=listData;
        adapter.notifyDataSetChanged();
    }
}
