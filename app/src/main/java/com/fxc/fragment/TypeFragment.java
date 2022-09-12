package com.fxc.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.fxc.R;
import com.fxc.type.fragment.ListFragment;
import com.fxc.type.fragment.TagFragment;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TypeFragment#} factory method to
 * create an instance of this fragment.
 */
public class TypeFragment extends BaseFragment {

    private SegmentTabLayout segmentTabLayout;
    private ImageView iv_type_search;
    private FrameLayout fl_type;
    private List<BaseFragment> fragmentList;
    private Fragment tempFragment;
    public ListFragment listFragment;
    public TagFragment tagFragment;

    /**创建一个Fragment实例
     * @return
     */
    public static TypeFragment createInstance() {
        return new TypeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //类相关初始化，必须使用
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_type);

        initView();
        initData();
        initEvent();
        //功能归类分区方法，必须调用

        return view;
    }


    @Override
    public void initView() {//必须调用
        segmentTabLayout = (SegmentTabLayout) view.findViewById(R.id.tl_1);
        iv_type_search = (ImageView) view.findViewById(R.id.iv_type_search);
        fl_type = (FrameLayout) view.findViewById(R.id.fl_type);
    }


    @Override
    public void initData() {//必须调用
        initFragment();

        String[] titles = {"分类", "标签"};

        segmentTabLayout.setTabData(titles);

        segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchFragment(tempFragment, fragmentList.get(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public void initEvent() {//必须调用

    }

    @Override
    public void onResume() {
        super.onResume();
        switchFragment(tempFragment, fragmentList.get(0));
    }

    public void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.fl_type, nextFragment, "tagFragment").commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        listFragment = new ListFragment();
        tagFragment = new TagFragment();

        fragmentList.add(listFragment);
        fragmentList.add(tagFragment);

        switchFragment(tempFragment, fragmentList.get(0));
    }
}