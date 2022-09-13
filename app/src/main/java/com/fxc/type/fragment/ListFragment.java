package com.fxc.type.fragment;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fxc.R;
import com.fxc.type.adapter.TypeLeftAdapter;
import com.fxc.type.adapter.TypeRightAdapter;
import com.fxc.type.model.TypeBean;
import com.fxc.util.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import zuo.biao.library.base.BaseFragment;

/**
 * 分类页面
 * A simple {@link } subclass.
 */
public class ListFragment extends BaseFragment {
    private FrameLayout fl_list_container;
    private ListView lv_left;
    private RecyclerView rv_right;
    private List<TypeBean.ResultBean> result;

    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL, Constants.GAME_URL};

    private TypeLeftAdapter leftAdapter;
    private boolean isFirst = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        //View view = View.inflate(context, R.layout.fragment_list, null);
        setContentView(R.layout.fragment_list);

        initView();
        initData();
        initEvent();
        return view;
    }

    @Override
    public void initView() {

        lv_left = (ListView) view.findViewById(R.id.lv_left);
        rv_right = (RecyclerView) view.findViewById(R.id.rv_right);
    }

    @Override
    public void initData() {
        //联网请求
        getDataFromNet(urls[0]);
    }

    @Override
    public void initEvent() {
        
    }

    /**
     * 具体的联网请求代码
     * @param url
     */
    public void getDataFromNet(String url) {
        OkHttpUtils
                .get()
                .url(url)
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
            //两位请求成功

            switch (id) {
                case 100:
                    Toast.makeText(context, "typeHttps", Toast.LENGTH_SHORT).show();
                    if (response != null) {
                        //解析数据
                        processData(response);
                        if (isFirst) {
                            leftAdapter = new TypeLeftAdapter(context);
                            lv_left.setAdapter(leftAdapter);
                        }

                        initListener(leftAdapter);

                        //解析右边数据
                        TypeRightAdapter rightAdapter = new TypeRightAdapter(context, result);
                        rv_right.setAdapter(rightAdapter);

                        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);

                        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int position) {
                                if (position == 0) {
                                    return 3;
                                } else {
                                    return 1;
                                }
                            }
                        });
                        rv_right.setLayoutManager(manager);
                    }

                    break;
                case 101:
                    Toast.makeText(context, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }

    private void initListener(final TypeLeftAdapter adapter) {
        //点击监听
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.changeSelected(position);//刷新
                if (position != 0) {
                    isFirst = false;
                }
                getDataFromNet(urls[position]);
                leftAdapter.notifyDataSetChanged();
            }
        });

        //选中监听
        lv_left.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.changeSelected(position);//刷新

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * 处理数据
     * @param json
     */
    private void processData(String json) {
        Gson gson = new Gson();
        TypeBean typeBean = gson.fromJson(json, TypeBean.class);
        result = typeBean.getResult();
    }
}