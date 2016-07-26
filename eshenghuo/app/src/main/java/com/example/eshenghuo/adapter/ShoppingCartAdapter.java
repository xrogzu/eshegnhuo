package com.example.eshenghuo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eshenghuo.R;
import com.example.eshenghuo.bean.IntegralStoreBean;
import com.example.eshenghuo.ui.BaseActivity;
import com.example.eshenghuo.ui.IntegralStoreActivity;
import com.example.eshenghuo.ui.ShoppingCartActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 * 购物车适配器
 */
public class ShoppingCartAdapter extends BaseAdapter {
    public List<IntegralStoreBean> listData=new ArrayList<>();
    private BaseActivity activity;
    public ShoppingCartAdapter(List<IntegralStoreBean> list,BaseActivity activity){
        this.listData=list;
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
            convertView= LayoutInflater.from(activity).inflate(R.layout.item_shopping_cart,null);
            viewHoldler.item_integral_img= (ImageView) convertView.findViewById(R.id.item_integral_img);
            viewHoldler.item_integral_title= (TextView) convertView.findViewById(R.id.item_integral_title);
            viewHoldler.item_integral_integral= (TextView) convertView.findViewById(R.id.item_integral_integral);
            viewHoldler.item_integral_sold= (TextView) convertView.findViewById(R.id.item_integral_sold);
            viewHoldler.item_integral_number= (TextView) convertView.findViewById(R.id.item_integral_number);
            viewHoldler.item_integral_minus= (ImageView) convertView.findViewById(R.id.item_integral_minus);
            viewHoldler.item_integral_plus= (ImageView) convertView.findViewById(R.id.item_integral_plus);
            convertView.setTag(viewHoldler);
            //对于listview，注意添加这一行，即可在item上使用高度
            AutoUtils.autoSize(convertView);
        }else{
            viewHoldler= (ViewHolder) convertView.getTag();
        }
        final IntegralStoreBean bean=listData.get(position);
        viewHoldler.item_integral_title.setText(bean.getTitle());
        viewHoldler.item_integral_integral.setText(bean.getIntegralNumber());
        viewHoldler.item_integral_sold.setText(bean.getSoldNumber());
        viewHoldler.item_integral_number.setText(""+bean.getPurchaseNumber());
        viewHoldler.item_integral_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setPurchaseNumber(bean.getPurchaseNumber()+1);
                viewHoldler.item_integral_number.setText(bean.getPurchaseNumber() + "");
                ShoppingCartAdapter.this.notifyDataSetChanged();
                ShoppingCartActivity shoppingCartActivity= (ShoppingCartActivity) activity;
                shoppingCartActivity.integralCount(Integer.valueOf(bean.getIntegralNumber()));
            }
        });
        viewHoldler.item_integral_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bean.getPurchaseNumber()!=0) {
                    bean.setPurchaseNumber(bean.getPurchaseNumber() - 1);
                    viewHoldler.item_integral_number.setText(bean.getPurchaseNumber() + "");
                    ShoppingCartAdapter.this.notifyDataSetChanged();
                    ShoppingCartActivity  shoppingCartActivity= (ShoppingCartActivity) activity;
                    shoppingCartActivity.integralCount(-Integer.valueOf(bean.getIntegralNumber()));
                }
            }
        });
        return convertView;
    }

    private static class ViewHolder{
        ImageView item_integral_img;
        TextView item_integral_title;
        TextView item_integral_integral;
        TextView item_integral_sold;
        TextView item_integral_number;
        ImageView item_integral_minus;
        ImageView item_integral_plus;
    }
}
