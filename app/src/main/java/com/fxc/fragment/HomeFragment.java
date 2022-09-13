package com.fxc.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.fxc.R;
import com.fxc.adapter.HomeFragmentAdapter;
import com.fxc.model.ResultBeanData;
import com.fxc.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.base.BaseFragment;
import zuo.biao.library.ui.AlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, AlertDialog.OnDialogButtonClickListener {

    private static final String TAG = HomeFragment.class.getSimpleName();

    private RecyclerView rvHome;
    private HomeFragmentAdapter adapter;


    /**
     * 返回的数据
     */
    private ResultBeanData.ResultBean resultBean;

    /**
     * 创建一个Fragment实例
     *
     * @return
     */
    public static HomeFragment createInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //类相关初始化，必须使用<<<<<<<<<<<<<<<<
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_home);

        initView();
        initData();
        initEvent();
        //功能归类分区方法，必须调用>>>>>>>>>>
        return view;
    }

    @Override
    public void initView() {//必须调用
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
    }


    @Override
    public void initData() {//必须调用
        //联网请求主页的数据
        getDataFromNet();
    }

    /**
     * 发送请求
     */
    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /**
                     * 当请求失败的时候回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Log.e(TAG, "首页请求失败==" + e.getMessage());
                    }

                    /**
                     * 当联网成功的时候回调
                     * @param response 请求成功的数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "首页请求成功==" + response);
                        //解析数据
                        processData(response);
                    }


                });
    }

    /**
     * 处理数据
     *
     * @param json
     */
    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        if (resultBean != null) {
            //有数据
            //设置适配器
            adapter = new HomeFragmentAdapter(context, resultBean);
            rvHome.setAdapter(adapter);
            GridLayoutManager manager = new GridLayoutManager(context, 1);
            //设置跨度大小监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 1;
                }
            });
            //设置布局管理者
            rvHome.setLayoutManager(manager);

        } else {
            //没有数据
        }

    }

    @Override
    public void initEvent() {//必须调用

    }

    @Override
    public void onDialogButtonClick(int requestCode, boolean isPositive) {
        if (!isPositive) {
            return;
        }
        switch (requestCode) {
            case 0:
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {//直接调用不会显示v被点击效果

    }
}