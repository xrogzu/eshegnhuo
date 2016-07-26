package com.example.eshenghuo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.eshenghuo.R;
import com.example.eshenghuo.adapter.ExpenseCalendarAdapter;
import com.example.eshenghuo.bean.ExpenseCalendarBean;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14.
 * 查看消费记录
 */
public class ExpenseCalendarActivity extends BaseActivity{
    private ExpenseCalendarAdapter adapter;
    private List<ExpenseCalendarBean> listData=new ArrayList<>();
    private PullToRefreshListView listview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_calendar);
        initView();
        getData();
}

    private void initView() {
        listview= (PullToRefreshListView) findViewById(R.id.expense_calendar_listview);
        adapter=new ExpenseCalendarAdapter(this);
        listview.setAdapter(adapter);
        adapter.listData=listData;
        adapter.notifyDataSetChanged();
    }

    public void getData() {
        for(int i=0;i<10;i++){
            ExpenseCalendarBean ben=new ExpenseCalendarBean();
            listData.add(ben);
        }

    }
}
