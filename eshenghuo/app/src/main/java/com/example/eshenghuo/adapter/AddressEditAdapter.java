package com.example.eshenghuo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eshenghuo.R;
import com.example.eshenghuo.bean.AddressBean;
import com.example.eshenghuo.ui.BaseActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.eshenghuo.R.id.address_edit_default;

/**
 * Created by Administrator on 2016/7/19.
 * 地址编辑适配器
 */
public class AddressEditAdapter extends BaseAdapter {
    public List<AddressBean> listData=new ArrayList<>();
    private BaseActivity activity;
    public AddressEditAdapter(BaseActivity activity){
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
            convertView= LayoutInflater.from(activity).inflate(R.layout.item_address_edit,null);
            viewHoldler.address_edit_name= (TextView) convertView.findViewById(R.id.address_edit_name);
            viewHoldler.address_edit_phone= (TextView) convertView.findViewById(R.id.address_edit_phone);
            viewHoldler.address_edit_address= (TextView) convertView.findViewById(R.id.address_edit_address);
            viewHoldler.address_edit_default= (ImageView) convertView.findViewById(R.id.address_edit_default);
            viewHoldler.address_edit_delete= (ImageView) convertView.findViewById(R.id.address_edit_delete);
            convertView.setTag(viewHoldler);
            //对于listview，注意添加这一行，即可在item上使用高度
            AutoUtils.autoSize(convertView);
        }else{
            viewHoldler= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    private static class ViewHolder{
        TextView address_edit_name;
        TextView address_edit_phone;
        TextView address_edit_address;
        ImageView address_edit_default;
        ImageView address_edit_delete;
    }
}
