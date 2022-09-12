package com.fxc.type.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.fxc.R;
import com.fxc.type.adapter.TagGridViewAdapter;
import com.fxc.type.model.TagBean;
import com.fxc.util.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import zuo.biao.library.base.BaseFragment;

/**
 * A simple {@link } subclass.
 */
public class TagFragment extends BaseFragment {

    private GridView gv_tag;
    private TagGridViewAdapter adapter;
    private List<TagBean.ResultBean> result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_tag);
        return view;
    }

    @Override
    public void initView() {

        gv_tag = (GridView) view.findViewById(R.id.gv_tag);
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
                .url(Constants.TAG_URL)
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
//                    Toast.makeText(context, "http", Toast.LENGTH_SHORT).show();
                    if (response != null) {
                        processData(response);
                        Log.e("TAG", response);
                        adapter = new TagGridViewAdapter(context, result);
                        gv_tag.setAdapter(adapter);
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
        TagBean tagBean = gson.fromJson(json, TagBean.class);
        result = tagBean.getResult();
    }

}
