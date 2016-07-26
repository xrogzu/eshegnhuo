package com.example.eshenghuo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.eshenghuo.R;
import com.example.eshenghuo.adapter.AddressManageAdapter;
import com.example.eshenghuo.bean.AddressBean;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 * 选择地址列表
 */
public class AddressManageActivity extends BaseActivity implements View.OnClickListener{
    private PullToRefreshListView address_manage_list;
    private AddressManageAdapter adapter;
    private List<AddressBean> listData=new ArrayList<>();
    private TextView address_manage_edit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manage);
        initView();
        initListener();
        getData();
    }

    private void initListener() {
        address_manage_edit.setOnClickListener(this);
    }

    private void initView() {
        address_manage_list= (PullToRefreshListView) findViewById(R.id.address_manage_list);
        address_manage_edit= (TextView) findViewById(R.id.address_manage_edit);
        adapter=new AddressManageAdapter(this);
        address_manage_list.setAdapter(adapter);

    }

    public void getData() {
       for(int i=0;i<10;i++){
           AddressBean bean=new AddressBean();
           listData.add(bean);
       }
        adapter.listData=listData;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.address_manage_edit:
                startNextActivity(null,AddressEditActivity.class,false);
                break;
        }
    }
}
