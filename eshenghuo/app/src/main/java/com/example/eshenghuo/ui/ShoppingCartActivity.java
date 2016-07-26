package com.example.eshenghuo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eshenghuo.R;
import com.example.eshenghuo.adapter.ShoppingCartAdapter;
import com.example.eshenghuo.bean.IntegralStoreBean;
import com.example.eshenghuo.util.InitPullToRefresh;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 * 购物车
 */
public class ShoppingCartActivity extends BaseActivity implements View.OnClickListener {
    private PullToRefreshListView listView;
    private List<IntegralStoreBean> listData=new ArrayList<>();
    private ShoppingCartAdapter adapter;
    private final int pageSize=20;
    public static int pageNo=1;
    private int allIntegral=10000;
    private int checkedIntegral=0;
    private int countIntegral=0;
    private TextView integral_all_integral,integral_checked_integral,integral_count_integral;
    private ImageView cart_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        initView();
        initListener();
        getData();
    }
    public void integralCount(int addIntegral){
        integral_all_integral.setText(allIntegral+"");
        checkedIntegral=checkedIntegral+addIntegral;
        integral_checked_integral.setText(checkedIntegral+"");
        countIntegral=allIntegral-checkedIntegral;
        integral_count_integral.setText(countIntegral+"");
    }
    private void initView() {
        integral_all_integral= (TextView) findViewById(R.id.integral_all_integral);
        integral_checked_integral= (TextView) findViewById(R.id.integral_checked_integral);
        integral_count_integral= (TextView) findViewById(R.id.integral_count_integral);
        cart_back= (ImageView) findViewById(R.id.cart_back);
        cart_back.setOnClickListener(this);
        listView= (PullToRefreshListView) findViewById(R.id.cart_listview);
        adapter=new ShoppingCartAdapter(listData,this);
        listView.setAdapter(adapter);
        InitPullToRefresh.initPullToRefresh(listView);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<android.widget.ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo ++;
                getData();
            }
        });

    }
    private void initListener() {
    }
    public void getData() {
        for (int i=0;i<10;i++){
            IntegralStoreBean bean=new IntegralStoreBean();
            bean.setIntegralNumber("1000");
            bean.setSoldNumber("已售出"+i+"件");
            bean.setPurchaseNumber(0);
            bean.setTitle("乐町冬装新款长袖轻薄羽绒");
            listData.add(bean);
        }
        adapter.listData=listData;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cart_back:
                this.finish();
                break;
        }
    }
}
