package com.example.eshenghuo.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eshenghuo.R;
import com.example.eshenghuo.adapter.IntegralStoreAdapter;
import com.example.eshenghuo.bean.IntegralStoreBean;
import com.example.eshenghuo.util.InitPullToRefresh;
import com.example.eshenghuo.util.JCommonUtils;
import com.example.eshenghuo.util.ToastUtils;
import com.example.eshenghuo.view.MyProgressDialog;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/6/27.
 * 积分商城
 */
public class IntegralStoreActivity extends BaseActivity implements View.OnClickListener {
    private PullToRefreshListView listView;
    private List<IntegralStoreBean> listData=new ArrayList<>();
    private IntegralStoreAdapter adapter;
    private MyProgressDialog mDialog;
    private final int pageSize=20;
    public static int pageNo=1;
    private TextView integral_all_integral,integral_checked_integral,integral_count_integral,integral_shopping_cart_number;
    private int allIntegral=10000;
    private int checkedIntegral=0;
    private int countIntegral=0;
    private  int cartNumber=0;
    private ImageView integral_admin, integral_shopping_cart;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_store);
        initView();
        initListener();
        getData();
        IntegralCount(0);
    }
    public void IntegralCount(int addIntegral){
        integral_all_integral.setText(allIntegral+"");
        checkedIntegral=checkedIntegral+addIntegral;
        integral_checked_integral.setText(checkedIntegral+"");
        countIntegral=allIntegral-checkedIntegral;
        integral_count_integral.setText(countIntegral+"");
    }
    public void addCartNumber(){
        integral_shopping_cart_number.setVisibility(View.VISIBLE);
        int newNumber=Integer.valueOf(integral_shopping_cart_number.getText().toString())+1;
        integral_shopping_cart_number.setText(newNumber+"");
    }
    public void removeCartNumber(){
        int newNumber=Integer.valueOf(integral_shopping_cart_number.getText().toString())-1;
        if(newNumber==0){
            integral_shopping_cart_number.setVisibility(View.INVISIBLE);
        }
        integral_shopping_cart_number.setText(newNumber+"");
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void startAdmin(){
      //  ImageLoader.getInstance().displayImage("http://img.blog.csdn.net/20130819182259359", integral_admin);//设置动画图片
        int[] location_admin = new int[2];//绝对坐标包括状态栏和标题栏
        integral_admin.getLocationOnScreen(location_admin);
        int admin_x=location_admin[0];
        int admin_y=location_admin[1]-JCommonUtils.getStatusBarHeight(this)-JCommonUtils.getNavigationBarHeight(this);

        int [] location_cart=new int[2];
        integral_shopping_cart.getLocationOnScreen(location_cart);
        int cart_x=location_cart[0];
        int cart_y=location_cart[1]-JCommonUtils.getStatusBarHeight(this)-JCommonUtils.getNavigationBarHeight(this);
        int moveX=admin_x-cart_x;
        int moveY=admin_y-cart_y;

        integral_admin.setVisibility(View.VISIBLE);
        AnimationSet set =new AnimationSet(true);
        TranslateAnimation tla=new TranslateAnimation(0, -moveX, 0, -moveY); //定义一个平移动画对象
        tla.setDuration(1500);//设置动画效果
        ScaleAnimation scale=new ScaleAnimation(1,0.1f,1,0.1f);
        scale.setDuration(1500);
        set.addAnimation(scale);
        set.addAnimation(tla);
        integral_admin.setAnimation(set);
        set.start();
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                integral_admin.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void initView() {
        integral_all_integral= (TextView) findViewById(R.id.integral_all_integral);
        integral_checked_integral= (TextView) findViewById(R.id.integral_checked_integral);
        integral_count_integral= (TextView) findViewById(R.id.integral_count_integral);
        integral_shopping_cart_number= (TextView) findViewById(R.id.integral_shopping_cart_number);
        integral_admin= (ImageView) findViewById(R.id.integral_admin);
        integral_shopping_cart= (ImageView) findViewById(R.id.integral_shopping_cart);
        integral_shopping_cart.setOnClickListener(this);
        listView= (PullToRefreshListView) findViewById(R.id.integral_listview);
        mDialog = new MyProgressDialog(this,"加载中...");
      //  mDialog.show();

        adapter = new IntegralStoreAdapter(this, listData);
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
    public void getData(){
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
    private void initListener() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.integral_shopping_cart:
                startNextActivity(null,ShoppingCartActivity.class,false);
                break;
        }
    }
}
