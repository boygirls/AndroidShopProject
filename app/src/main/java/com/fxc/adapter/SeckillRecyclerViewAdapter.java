package com.fxc.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fxc.R;
import com.fxc.model.ResultBeanData;
import com.fxc.util.Constants;

import java.util.List;

/**
 * 作用：秒杀的适配器
 */
public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHodler> {

    private final List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list;
    private final Context mContext;

    public SeckillRecyclerViewAdapter(Context mContext, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {
        this.list = list;
        this.mContext  = mContext;

    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_seckill,null);
        return new ViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        //1.根据位置得到对应的数据
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = list.get(position);

        //2.绑定数据

        Glide.with(mContext).load(Constants.BASE_URl_IMAGE+listBean.getFigure()).into(holder.iv_figure);
        holder.tv_cover_price.setText(listBean.getCover_price());
        holder.tv_origin_price.setText(listBean.getOrigin_price());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder{
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHodler(View itemView) {
            super(itemView);
            iv_figure = (ImageView) itemView.findViewById(R.id.iv_figure);
            tv_cover_price = (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = (TextView) itemView.findViewById(R.id.tv_origin_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, "秒杀="+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    if(onSeckillRecyclerView != null){
                        onSeckillRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * 监听器
     */
    public interface OnSeckillRecyclerView{
        /**
         * 当某条被点击的时候回调
         * @param position
         */
        public void onItemClick(int position);
    }

    private  OnSeckillRecyclerView onSeckillRecyclerView;

    /**
     * 设置item的监听
     * @param onSeckillRecyclerView
     */
    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView) {
        this.onSeckillRecyclerView = onSeckillRecyclerView;
    }
}
