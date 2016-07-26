package com.example.eshenghuo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eshenghuo.R;
import com.example.eshenghuo.ui.BaseActivity;

/**
 * Created by Administrator on 2016/6/23.
 */
public class HomeServiceFragment extends BaseFragment {
    private BaseActivity activity;
    private View mContent;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity= (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContent=inflater.inflate(R.layout.fragment_home_service,null);
        return mContent;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
