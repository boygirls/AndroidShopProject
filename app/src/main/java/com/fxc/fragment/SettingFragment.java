package com.fxc.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.fxc.R;

import zuo.biao.library.base.BaseFragment;
import zuo.biao.library.ui.AlertDialog;
import zuo.biao.library.ui.AlertDialog.OnDialogButtonClickListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends BaseFragment implements OnClickListener, OnDialogButtonClickListener {
//	private static final String TAG = "SettingFragment";

    //与Activity通信<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**创建一个Fragment实例
     * @return
     */
    public static SettingFragment createInstance() {
        return new SettingFragment();
    }

    //与Activity通信>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //类相关初始化，必须使用<<<<<<<<<<<<<<<<
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_setting);
        //类相关初始化，必须使用>>>>>>>>>>>>>>>>

        //功能归类分区方法，必须调用<<<<<<<<<<
        initView();
        initData();
        initEvent();
        //功能归类分区方法，必须调用>>>>>>>>>>

        return view;
    }


    private ImageView ivSettingHead;
    @Override
    public void initView() {//必须调用

        ivSettingHead = findView(R.id.ivSettingHead);
    }


    @Override
    public void initData() {//必须调用

    }


    private void logout() {
        context.finish();
    }

    @Override
    public void initEvent() {//必须调用

        ivSettingHead.setOnClickListener(this);

        findView(R.id.llSettingSetting).setOnClickListener(this);
        findView(R.id.llSettingAbout).setOnClickListener(this);
        findView(R.id.llSettingLogout).setOnClickListener(this);
    }




    @Override
    public void onDialogButtonClick(int requestCode, boolean isPositive) {
        if (! isPositive) {
            return;
        }

        switch (requestCode) {
            case 0:
                logout();
                break;
            default:
                break;
        }
    }



    @Override
    public void onClick(View v) {//直接调用不会显示v被点击效果
        switch (v.getId()) {
            case R.id.ivSettingHead:
                showShortToast("onClick  ivSettingHead");
                break;
            case R.id.llSettingSetting:
                //toActivity(SettingActivity.createIntent(context));
                break;
            case R.id.llSettingAbout:
                //toActivity(AboutActivity.createIntent(context));
                break;
            case R.id.llSettingLogout:
                new AlertDialog(context, "退出登录", "确定退出登录？", true, 0, this).show();
                break;
            default:
                break;
        }
    }
}