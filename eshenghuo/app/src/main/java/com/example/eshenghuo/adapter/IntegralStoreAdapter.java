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
import com.example.eshenghuo.ui.ProductDetailsActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 * 积分商城适配器
 */
public class IntegralStoreAdapter extends BaseAdapter {
    private BaseActivity activity;
    public  List<IntegralStoreBean> listData=new ArrayList<>();

    public IntegralStoreAdapter(BaseActivity activity, List<IntegralStoreBean> listData){
        this.activity=activity;
        this.listData=listData;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            viewHoldler=new ViewHolder();
            convertView= LayoutInflater.from(activity).inflate(R.layout.item_integral_store,null);
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
                         IntegralStoreAdapter.this.notifyDataSetChanged();
                        IntegralStoreActivity  integralStoreActivity= (IntegralStoreActivity) activity;
                        integralStoreActivity.IntegralCount(Integer.valueOf(bean.getIntegralNumber()));
                        integralStoreActivity.addCartNumber();
                        integralStoreActivity.startAdmin();//添加购物车动画
            }
        });
        viewHoldler.item_integral_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bean.getPurchaseNumber()!=0) {
                    bean.setPurchaseNumber(bean.getPurchaseNumber() - 1);
                    viewHoldler.item_integral_number.setText(bean.getPurchaseNumber() + "");
                    IntegralStoreAdapter.this.notifyDataSetChanged();
                    IntegralStoreActivity  integralStoreActivity= (IntegralStoreActivity) activity;
                    integralStoreActivity.IntegralCount(-Integer.valueOf(bean.getIntegralNumber()));
                    integralStoreActivity.removeCartNumber();
                }
            }
        });
        viewHoldler.item_integral_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startNextActivity(null, ProductDetailsActivity.class,false);
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
