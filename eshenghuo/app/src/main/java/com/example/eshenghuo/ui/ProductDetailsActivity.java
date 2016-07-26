package com.example.eshenghuo.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.eshenghuo.R;
import com.example.eshenghuo.util.PopwindowShow;
import com.example.eshenghuo.util.ToastUtils;

/**
 * Created by Administrator on 2016/6/30.
 * 产品详情
 */
public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener {
    private TextView product_detail_to_cart,product_detail_exchange;
    private View layout;
    private ImageView product_detail_back,integral_shopping_cart,product_detail_phone;
    private PopupWindow popupWindow=null;
    private LayoutInflater inflater = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initView();
        initListener();
    }
    private void initView(){
        layout=findViewById(R.id.layout);
        product_detail_to_cart= (TextView) findViewById(R.id.product_detail_to_cart);
        product_detail_back= (ImageView) findViewById(R.id.product_detail_back);
        integral_shopping_cart= (ImageView) findViewById(R.id.integral_shopping_cart);
        product_detail_exchange= (TextView) findViewById(R.id.product_detail_exchange);
        product_detail_phone= (ImageView) findViewById(R.id.product_detail_phone);
        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    private void initListener(){
        product_detail_to_cart.setOnClickListener(this);
        product_detail_back.setOnClickListener(this);
        integral_shopping_cart.setOnClickListener(this);
        product_detail_exchange.setOnClickListener(this);
        product_detail_phone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.product_detail_to_cart:
                ToastUtils.toastSuccess(this,"已加入购物车");
                break;
            case R.id.product_detail_back:
                this.finish();
                break;
            case R.id.integral_shopping_cart:
                startNextActivity(null,ShoppingCartActivity.class,false);
                break;
            case R.id.product_detail_exchange:
                View view = inflater.inflate(R.layout.popup_prodact_detail_exchange, null);
                PopwindowShow.showPopwindow(ProductDetailsActivity.this,view,popupWindow,layout,58);
                initPopupView(view);
                break;
            case R.id.product_detail_phone:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + "053266888577");
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.exchange_confirm:
              startNextActivity(null,ConfirmOrderActivity.class,false);
                break;
            case R.id.exchange_integral_minus: {
                int number = Integer.valueOf(exchange_integral_number.getText().toString()) - 1;
                if (number < 1) {
                    exchange_integral_number.setText("1");
                } else {
                    exchange_integral_number.setText(number+"");
                }
            }
                break;
            case R.id.exchange_integral_plus:
                int number=Integer.valueOf(exchange_integral_number.getText().toString())+1;
                exchange_integral_number.setText(number+"");
                break;
        }
    }

    private TextView exchange_integral_number;
    private void initPopupView(View view) {
        ImageView exchange_integral_plus= (ImageView) view.findViewById(R.id.exchange_integral_plus);
         exchange_integral_number= (TextView) view.findViewById(R.id.exchange_integral_number);
        ImageView exchange_integral_minus= (ImageView) view.findViewById(R.id.exchange_integral_minus);
        TextView exchange_confirm= (TextView) view.findViewById(R.id.exchange_confirm);
        exchange_confirm.setOnClickListener(this);
        exchange_integral_minus.setOnClickListener(this);
        exchange_integral_plus.setOnClickListener(this);
    }
}
