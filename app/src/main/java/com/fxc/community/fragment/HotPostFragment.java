package com.fxc.community.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.fxc.R;
import com.fxc.community.adapter.HotPostListViewAdapter;
import com.fxc.community.model.HotPostBean;
import com.fxc.util.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import zuo.biao.library.base.BaseFragment;

/**
 *
 */
public class HotPostFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //`setContentView(R.layout.fragment_hot_post);
        view = View.inflate(context, R.layout.fragment_hot_post, null);
        initView();
        initData();
        initEvent();
        return view;
    }

    private ListView lv_hot_post;
    private List<HotPostBean.ResultBean> result;


    @Override
    public void initView() {
        lv_hot_post = (ListView) view.findViewById(R.id.lv_hot_post);
    }

    @Override
    public void initData() {
        getDataFromNet();
    }

    @Override
    public void initEvent() {

    }

    public void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.HOT_POST_URL)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {


        @Override
        public void onBefore(Request request, int id) {
        }

        @Override
        public void onAfter(int id) {
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("TAG", "联网失败" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {

            switch (id) {
                case 100:
//                    Toast.makeText(mContext, "http", Toast.LENGTH_SHORT).show();
                    if (response != null) {
                        processData(response);
                        HotPostListViewAdapter adapter = new HotPostListViewAdapter(context, result);
                        lv_hot_post.setAdapter(adapter);
                    }
                    break;
                case 101:
                    Toast.makeText(context, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }

    private void processData(String json) {
        Gson gson = new Gson();
        HotPostBean hotPostBean = gson.fromJson(json, HotPostBean.class);
        result = hotPostBean.getResult();
    }
}
