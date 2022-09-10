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
import com.fxc.community.adapter.NewPostListViewAdapter;
import com.fxc.community.model.NewPostBean;
import com.fxc.util.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import zuo.biao.library.base.BaseFragment;

/**
 * Created by Administrator on 2016/10/6.
 */
public class NewPostFragment extends BaseFragment {
    private ListView lv_new_post;
    private List<NewPostBean.ResultBean> result;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //setContentView(R.layout.fragment_new_post);
        view = View.inflate(context,R.layout.fragment_new_post,null);
        initView();
        initData();
        initEvent();

        return view;
    }


    @Override
    public void initView() {
        lv_new_post = (ListView) view.findViewById(R.id.lv_new_post);
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
                .url(Constants.NEW_POST_URL)
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
                        NewPostListViewAdapter adapter = new NewPostListViewAdapter(context, result);
                        lv_new_post.setAdapter(adapter);
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
        NewPostBean newPostBean = gson.fromJson(json, NewPostBean.class);
        result = newPostBean.getResult();
    }
}
