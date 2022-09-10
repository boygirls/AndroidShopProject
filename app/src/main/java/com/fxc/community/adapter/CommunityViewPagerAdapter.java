package com.fxc.community.adapter;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fxc.community.fragment.HotPostFragment;
import com.fxc.community.fragment.NewPostFragment;

import java.util.ArrayList;
import java.util.List;


public class CommunityViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titles = new String[]{"新帖", "热帖"};

    public CommunityViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        NewPostFragment newPostFragment = new NewPostFragment();
        HotPostFragment hotPostFragment = new HotPostFragment();
        fragmentList.add(newPostFragment);
        fragmentList.add(hotPostFragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
