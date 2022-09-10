package com.fxc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.fxc.fragment.CommunityFragment;
import com.fxc.fragment.HomeFragment;
import com.fxc.fragment.SettingFragment;
import com.fxc.fragment.ShoppingCartFragment;
import com.fxc.fragment.TypeFragment;
import com.fxc.fragment.UserFragment;

import zuo.biao.library.base.BaseBottomTabActivity;
import zuo.biao.library.interfaces.OnBottomDragListener;

public class main_tab_activity extends BaseBottomTabActivity implements OnBottomDragListener {
    //	private static final String TAG = "MainTabActivity";

    /**启动这个Activity的Intent
     * @param context
     * @return
     */
    public static Intent createIntent(Context context) {
        return new Intent(context, main_tab_activity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab_activity, this);

        //功能归类分区方法，必须调用<<<<<<<<<<
        initView();
        initData();
        initEvent();
        //功能归类分区方法，必须调用>>>>>>>>>>
    }



    //private DemoTabFragment demoTabFragment;

//    private CommunityFragment communityFragment;
//    private ShoppingCartFragment shoppingCartFragment;
//    private TypeFragment typeFragment;
//    private UserFragment userFragment;
    @Override
    public void initView() {// 必须调用
        super.initView();
        // zhuije
        //exitAnim = R.anim.bottom_push_out;

        //demoTabFragment = DemoTabFragment.createInstance("杭州");


    }


    @Override
    protected int[] getTabClickIds() {
        return new int[]{R.id.llBottomTabTab0, R.id.llBottomTabTab1, R.id.llBottomTabTab2, R.id.shopBottom, R.id.llBottomTabTab3};
    }

    @Override
    protected int[][] getTabSelectIds() {
        return new int[][]{
                new int[]{R.id.ivBottomTabTab0, R.id.ivBottomTabTab1, R.id.ivBottomTabTab2,R.id.imgShop, R.id.ivBottomTabTab3},//顶部图标
                new int[]{R.id.tvBottomTabTab0, R.id.tvBottomTabTab1, R.id.tvBottomTabTab2,R.id.textShop, R.id.tvBottomTabTab3}//底部文字
        };
    }
    @Override
    public int getFragmentContainerResId() {
        return R.id.flMainTabFragmentContainer;
    }

    @Override
    protected Fragment getFragment(int position) {

        showShortToast("点击的页面\n" + position);
        switch (position) {
            case 1:
                return TypeFragment.createInstance();
            case 2:
                return CommunityFragment.createInstance();
            case 3:
                return ShoppingCartFragment.createInstance();
            case 4:
                return UserFragment.createInstance();
            default:
                return HomeFragment.createInstance();
        }
    };

    private static final String[] TAB_NAMES = {"首页", "分类", "发现", "购物车","用户中心"};
    @Override
    protected void selectTab(int position) {
        //导致切换时闪屏，建议去掉BottomTabActivity中的topbar，在fragment中显示topbar
        //		rlBottomTabTopbar.setVisibility(position == 2 ? View.GONE : View.VISIBLE);

        tvBaseTitle.setText(TAB_NAMES[position]);

        //点击底部tab切换顶部tab，非必要/
//        if (position == 2 && position == currentPosition && demoTabFragment != null) {
        //    demoTabFragment.selectNext();
//        }

    }
    @Override
    public void initData() {// 必须调用
        super.initData();

    }

    @Override
    public void initEvent() {// 必须调用
        super.initEvent();

    }



    @Override
    public void onDragBottom(boolean rightToLeft) {
        //将Activity的onDragBottom事件传递到Fragment，非必要<<<<<<<<<<<<<<<<<<<<<<<<<<<
//        switch (currentPosition) {
//            case 2:
//                if (demoTabFragment != null) {
//                    if (rightToLeft) {
//                        demoTabFragment.selectMan();
//                    } else {
//                        demoTabFragment.selectPlace();
//                    }
//                }
//                break;
//            default:
//                break;
//        }
        //将Activity的onDragBottom事件传递到Fragment，非必要>>>>>>>>>>>>>>>>>>>>>>>>>>>
    }



    //双击手机返回键退出
    private long firstTime = 0;//第一次返回按钮计时
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if(secondTime - firstTime > 2000){
                    showShortToast("再按一次退出");
                    firstTime = secondTime;
                } else {//完全退出
                    moveTaskToBack(false);//应用退到后台
                    System.exit(0);
                }
                return true;
        }

        return super.onKeyUp(keyCode, event);
    }
}