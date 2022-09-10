package com.fxc.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fxc.R;
import com.fxc.main_tab_activity;
import com.fxc.model.GoodsBean;
import com.fxc.shoppingcart.adapter.ShopCartAdapter;
import com.fxc.util.CartProvider;

import java.util.List;

import zuo.biao.library.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoppingCartFragment#} factory method to
 * create an instance of this fragment.
 */
public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener {

    private ImageButton ibShopcartBack;
    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private LinearLayout ll_check_all;
    private LinearLayout ll_delete;
    private CheckBox cb_all;
    private Button btn_delete;
    private Button btn_collection;
    private Button btnCheckOut;
    private ShopCartAdapter adapter;
    private LinearLayout ll_empty_shopcart;
    private TextView tv_empty_cart_tobuy;
    /**
     * 编辑状态
     */
    private static final int ACTION_EDIT = 0;
    /**
     * 完成状态
     */
    private static final int ACTION_COMPLETE = 1;

    /**
     * 创建一个Fragment实例
     *
     * @return
     */
    public static ShoppingCartFragment createInstance() {
        return new ShoppingCartFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ibShopcartBack.setOnClickListener(this);
        btnCheckOut.setOnClickListener(this);
        tvShopcartEdit.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        tv_empty_cart_tobuy.setClickable(true);
        tv_empty_cart_tobuy.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //类相关初始化，必须使用<<<<<<<<<<<<<<<<
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        initView();
        initData();
        initEvent();
        //功能归类分区方法，必须调用>>>>>>>>>>

        return view;
    }

    @Override
    public void initView() {//必须调用
        ibShopcartBack = (ImageButton) findViewById(R.id.ib_shopcart_back);
        tvShopcartEdit = (TextView) findViewById(R.id.tv_shopcart_edit);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        checkboxAll = (CheckBox) findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);
        ll_check_all = (LinearLayout) findViewById(R.id.ll_check_all);
        ll_delete = (LinearLayout) findViewById(R.id.ll_delete);
        cb_all = (CheckBox) findViewById(R.id.cb_all);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_collection = (Button) findViewById(R.id.btn_collection);
        ll_empty_shopcart = (LinearLayout) findViewById(R.id.ll_empty_shopcart);
        tv_empty_cart_tobuy = (TextView) findViewById(R.id.tv_empty_cart_tobuy);


    }

    @Override
    public void initData() {//必须调用
        showData();
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");
    }

    @Override
    public void initEvent() {//必须调用

    }

    @Override
    public void onClick(View v) {
        if (v == ibShopcartBack) {
            //finish();
        } else if (v == btnCheckOut) {
            //Toast.makeText(ShoppingCartActivity.this, "结算", Toast.LENGTH_SHORT).show();
            showShortToast("结算");
        } else if (v == tvShopcartEdit) {
            //设置编辑的点击事件
            int tag = (int) tvShopcartEdit.getTag();
            if (tag == ACTION_EDIT) {
                //变成完成状态
                showDelete();
            } else {
                //变成编辑状态
                hideDelete();
            }
        } else if (v == btn_delete) {
            adapter.deleteData();
            adapter.showTotalPrice();
            //显示空空如也
            checkData();
            adapter.checkAll();
        } else if (v == tv_empty_cart_tobuy) {

            //页面跳转
           Intent intent = new Intent(context, main_tab_activity.class);
            startActivity(intent);
        }
    }

    private void hideDelete() {
        tvShopcartEdit.setText("编辑");
        tvShopcartEdit.setTag(ACTION_EDIT);

        adapter.checkAll_none(true);
        ll_delete.setVisibility(View.GONE);
        ll_check_all.setVisibility(View.VISIBLE);

        adapter.showTotalPrice();
    }

    private void showDelete() {
        tvShopcartEdit.setText("完成");
        tvShopcartEdit.setTag(ACTION_COMPLETE);

        adapter.checkAll_none(false);
        cb_all.setChecked(false);
        checkboxAll.setChecked(false);

        ll_delete.setVisibility(View.VISIBLE);
        ll_check_all.setVisibility(View.GONE);

        adapter.showTotalPrice();
    }

    //-----------------------------------------
    private void checkData() {
        if (adapter != null && adapter.getItemCount() > 0) {
            tvShopcartEdit.setVisibility(View.VISIBLE);
            ll_empty_shopcart.setVisibility(View.GONE);
            ll_check_all.setVisibility(View.GONE);
        } else {
            ll_empty_shopcart.setVisibility(View.VISIBLE);
            tvShopcartEdit.setVisibility(View.GONE);
            ll_check_all.setVisibility(View.GONE);
            ll_delete.setVisibility(View.GONE);
        }
    }

    private void showData() {
        CartProvider cartProvider = CartProvider.getInstance();

        List<GoodsBean> datas = cartProvider.getDataFromLocal();
        if (datas != null && datas.size() > 0) {
            tvShopcartEdit.setVisibility(View.VISIBLE);
            ll_empty_shopcart.setVisibility(View.GONE);
            adapter = new ShopCartAdapter(context, datas, tvShopcartTotal, cartProvider, checkboxAll, cb_all);
            recyclerview.setLayoutManager(new LinearLayoutManager(context));
            recyclerview.setAdapter(adapter);
        } else {
            //显示空的
            tvShopcartEdit.setVisibility(View.GONE);
            ll_empty_shopcart.setVisibility(View.VISIBLE);
            ll_check_all.setVisibility(View.GONE);
            ll_delete.setVisibility(View.GONE);
        }

    }

}