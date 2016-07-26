package com.example.eshenghuo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.eshenghuo.R;
import com.example.eshenghuo.ui.fragment.BaseFragment;
import com.example.eshenghuo.ui.fragment.HomeFamilyFragment;
import com.example.eshenghuo.ui.fragment.HomeMeFragment;
import com.example.eshenghuo.ui.fragment.HomeServiceFragment;
import com.example.eshenghuo.ui.fragment.HomeThemeFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/23.
 */
public class HomeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initFragment();
    }

    private   int currentFragment=Family_fragment;
    private static final int Family_fragment=0;
    private static final int Me_Fragment=1;
    private static final int Service_fragment=2;
    private static final int Theme_fragment=3;
    private ArrayList<BaseFragment> allFragment;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private void initFragment(){
        allFragment=new ArrayList<BaseFragment>();
        allFragment.add(new HomeFamilyFragment());
        allFragment.add(new HomeMeFragment());
        allFragment.add(new HomeServiceFragment());
        allFragment.add(new HomeThemeFragment());

        fragmentManager=getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.home_fragment,allFragment.get(currentFragment));
        transaction.commit();
    }
    private void switchFragment(int from,int to){
        transaction=fragmentManager.beginTransaction();

        BaseFragment fromFragment=allFragment.get(from);
        BaseFragment toFragment=allFragment.get(to);
        if (currentFragment != to) {
            currentFragment = to;
            if (!toFragment.isAdded()) {  // 先判断是否被add过
                transaction.hide(fromFragment).add(R.id.home_fragment, toFragment).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(fromFragment).show(toFragment).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

}
