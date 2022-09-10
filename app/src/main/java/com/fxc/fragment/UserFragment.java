package com.fxc.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fxc.R;

import zuo.biao.library.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends BaseFragment {
    /**创建一个Fragment实例
     * @return
     */
    public static UserFragment createInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //类相关初始化，必须使用<<<<<<<<<<<<<<<<
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_user);

        initView();
        initData();
        initEvent();
        //功能归类分区方法，必须调用>>>>>>>>>>

        return view;
    }


    @Override
    public void initView() {//必须调用

    }


    @Override
    public void initData() {//必须调用

    }

    @Override
    public void initEvent() {//必须调用

    }

}