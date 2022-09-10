package com.fxc.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.fxc.R;
import com.fxc.community.adapter.CommunityViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import zuo.biao.library.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommunityFragment#} factory method to
 * create an instance of this fragment.
 */
public class CommunityFragment extends BaseFragment {
    private ImageButton ibCommunityIcon;
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ImageButton ibCommunityMessage;

    /**
     * 创建一个Fragment实例
     *
     * @return
     */
    public static CommunityFragment createInstance() {
        return new CommunityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //类相关初始化，必须使用<<<<<<<<<<<<<<<<
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_community);

        initView();
        initData();
        initEvent();
        //功能归类分区方法，必须调用>>>>>>>>>>

        return view;
    }


    @Override
    public void initView() {//必须调用
        View.inflate(context, R.layout.fragment_community, null);
        //ibCommunityIcon = (ImageButton) view.findViewById(R.id.ib_community_icon);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        //ibCommunityMessage = (ImageButton) view.findViewById(R.id.ib_community_message);

        CommunityViewPagerAdapter adapter = new CommunityViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        tablayout.setVisibility(View.VISIBLE);
        tablayout.setupWithViewPager(viewPager);
    }


    @Override
    public void initData() {//必须调用

    }

    @Override
    public void initEvent() {//必须调用

    }

}