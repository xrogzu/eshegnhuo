package com.example.eshenghuo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.eshenghuo.R;
import com.example.eshenghuo.adapter.AddressEditAdapter;
import com.example.eshenghuo.bean.AddressBean;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 * 编辑收货地址
 */
public class AddressEditActivity extends BaseActivity {
    private PullToRefreshListView address_edit_list;
    private AddressEditAdapter adapter;
    private List<AddressBean> listData=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);
        initView();
        getData();
    }

    private void initView() {
        address_edit_list= (PullToRefreshListView) findViewById(R.id.address_edit_list);
        adapter=new AddressEditAdapter(this);
        address_edit_list.setAdapter(adapter);
    }

    public void getData() {
      for(int i=0;i<10;i++){
          AddressBean bean=new AddressBean();
          listData.add(bean);
      }
        adapter.listData=listData;
        adapter.notifyDataSetChanged();
    }
}
