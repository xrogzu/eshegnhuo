package com.example.eshenghuo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eshenghuo.R;
import com.example.eshenghuo.bean.ConfirmOrderBean;
import com.example.eshenghuo.ui.BaseActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 * 确认订单适配器
 */
public class ConfirmOrderAdapter extends BaseAdapter {
    public List<ConfirmOrderBean> listData=new ArrayList<>();
    public BaseActivity activity;
    public ConfirmOrderAdapter(BaseActivity activity){
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
    private  ViewHolder viewHoldler=null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            viewHoldler=new ViewHolder();
            convertView= LayoutInflater.from(activity).inflate(R.layout.item_cofirm_order,null);
            viewHoldler.confirm_order_img= (ImageView) convertView.findViewById(R.id.confirm_order_img);
            viewHoldler.confirm_order_title= (TextView) convertView.findViewById(R.id.confirm_order_title);
            viewHoldler.cofirm_order_integral= (TextView) convertView.findViewById(R.id.cofirm_order_integral);
            viewHoldler.confirm_order_sold= (TextView) convertView.findViewById(R.id.confirm_order_sold);
            viewHoldler.cofirm_order_number= (TextView) convertView.findViewById(R.id.cofirm_order_number);
            convertView.setTag(viewHoldler);
            //对于listview，注意添加这一行，即可在item上使用高度
            AutoUtils.autoSize(convertView);
        }else{
            viewHoldler= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private static class ViewHolder{
        ImageView confirm_order_img;
        TextView confirm_order_title;
        TextView cofirm_order_integral;
        TextView confirm_order_sold;
        TextView cofirm_order_number;
    }
}
