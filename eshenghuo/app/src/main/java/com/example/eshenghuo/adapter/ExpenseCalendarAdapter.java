package com.example.eshenghuo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eshenghuo.R;
import com.example.eshenghuo.bean.ExpenseCalendarBean;
import com.example.eshenghuo.ui.BaseActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/15.
 */
public class ExpenseCalendarAdapter extends BaseAdapter {
    public List<ExpenseCalendarBean> listData=new ArrayList<>();
    private BaseActivity activity;
    public ExpenseCalendarAdapter(BaseActivity activity){
        this.activity=activity;
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private  ViewHolder viewHolder=null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(activity).inflate(R.layout.item_expense_calendar,null);
            viewHolder.item_expense_img= (ImageView) convertView.findViewById(R.id.item_expense_img);
            viewHolder.item_expense_title= (TextView) convertView.findViewById(R.id.item_expense_title);
            viewHolder.item_expense_number= (TextView) convertView.findViewById(R.id.item_expense_number);
            viewHolder.item_expense_integral= (TextView) convertView.findViewById(R.id.item_expense_integral);
            viewHolder.item_expense_logistics= (TextView) convertView.findViewById(R.id.item_expense_logistics);
            viewHolder.item_expense_money= (TextView) convertView.findViewById(R.id.item_expense_money);
            viewHolder.item_expense_confirm= (TextView) convertView.findViewById(R.id.item_expense_confirm);
            convertView.setTag(viewHolder);
            //对于listview，注意添加这一行，即可在item上使用高度
            AutoUtils.autoSize(convertView);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ExpenseCalendarBean bean=listData.get(position);

        return convertView;
    }

    private static class ViewHolder{
        ImageView item_expense_img;
        TextView item_expense_title;
        TextView item_expense_number;
        TextView item_expense_integral;
        TextView item_expense_logistics;
        TextView item_expense_money;
        TextView item_expense_confirm;
    }
}
