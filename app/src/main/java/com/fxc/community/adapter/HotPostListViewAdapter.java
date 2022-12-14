package com.fxc.community.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.fxc.R;
import com.fxc.community.model.HotPostBean;
import com.fxc.util.BitmapUtils;
import com.fxc.util.Constants;
import com.fxc.util.DensityUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


import java.text.SimpleDateFormat;
import java.util.List;


import butterknife.Bind;
import butterknife.ButterKnife;


public class HotPostListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<HotPostBean.ResultBean> result;

    public HotPostListViewAdapter(Context mContext, List<HotPostBean.ResultBean> result) {
        this.mContext = mContext;
        this.result = result;

    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return result.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hotpost_listview, null);

            //holder = new ViewHolder(convertView);
            holder = new ViewHolder();
            holder.tvHotUsername = (TextView)convertView.findViewById(R.id.tv_hot_username);
            holder.tvHotAddtime =  (TextView)convertView.findViewById(R.id.tv_hot_addtime);
            holder.ivHotFigure =  (ImageView)convertView.findViewById(R.id.iv_hot_figure);
            holder.llHotPost =  (LinearLayout)convertView.findViewById(R.id.ll_hot_post);

            holder.tvHotSaying = (TextView)convertView.findViewById(R.id.tv_hot_saying);
            holder.tvHotLikes = (TextView)convertView.findViewById(R.id.tv_hot_likes);
            holder.tvHotComments = (TextView)convertView.findViewById(R.id.tv_hot_comments);
            holder.ibNewPostAvatar = (ImageButton)convertView.findViewById(R.id.ib_new_post_avatar);

//        @Bind(R.id.tv_hot_username)
//        TextView tvHotUsername;

//        @Bind(R.id.tv_hot_addtime)
//        TextView tvHotAddtime;

//        @Bind(R.id.iv_hot_figure)
//        ImageView ivHotFigure;

//        @Bind(R.id.ll_hot_post)
//        LinearLayout llHotPost;

//        @Bind(R.id.tv_hot_saying)
//        TextView tvHotSaying;

//        @Bind(R.id.tv_hot_likes)
//        TextView tvHotLikes;

//        @Bind(R.id.tv_hot_comments)
//        TextView tvHotComments;

//        @Bind(R.id.ib_new_post_avatar)
//        ImageButton ibNewPostAvatar;

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }





        HotPostBean.ResultBean resultBean = result.get(position);
        holder.tvHotUsername.setText(resultBean.getUsername());

        SimpleDateFormat myFmt = new SimpleDateFormat("MM-dd HH:mm");
        holder.tvHotAddtime.setText(myFmt.format(Integer.parseInt(resultBean.getAdd_time())));

        Glide.with(mContext).load(Constants.BASE_URl_IMAGE +resultBean.getFigure()).into(holder.ivHotFigure);
        holder.tvHotSaying.setText(resultBean.getSaying());
        holder.tvHotLikes.setText(resultBean.getLikes());
        holder.tvHotComments.setText(resultBean.getComments());

        Picasso.with(mContext).load(resultBean.getAvatar()).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap bitmap) {
                //????????????????????????
                //Bitmap zoom = BitmapUtils.zoom(bitmap, DensityUtil.dip2px(mContext, 62), DensityUtil.dip2px(mContext, 62));
                Bitmap zoom = BitmapUtils.zoom(bitmap, 70, 70);
                //??????????????????Bitmap??????????????????
                Bitmap ciceBitMap = BitmapUtils.circleBitmap(zoom);
                bitmap.recycle();//????????????????????????????????????
                return ciceBitMap;
            }

            @Override
            public String key() {
                return " ";
            }
        }).into(holder.ibNewPostAvatar);

        String is_top = resultBean.getIs_top();

        if ("1".equals(is_top)) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(mContext);
            textView.setText("??????");
            textViewLp.setMargins(DensityUtil.dip2px(mContext, 10), 0, DensityUtil.dip2px(mContext, 5), 0);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(R.drawable.is_top_shape);
            textView.setPadding(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            holder.llHotPost.removeAllViews();
            holder.llHotPost.addView(textView, textViewLp);
        }
        String is_hot = resultBean.getIs_hot();
        if ("1".equals(is_hot)) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(mContext);
            textViewLp.setMargins(0, 0, DensityUtil.dip2px(mContext, 5), 0);
            textView.setText("??????");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            textView.setPadding(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            textView.setBackgroundResource(R.drawable.is_hot_shape);
            holder.llHotPost.addView(textView, textViewLp);
        }
        String is_essence = resultBean.getIs_essence();
        if ("1".equals(is_essence)) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textViewLp.setMargins(0, 0, DensityUtil.dip2px(mContext, 5), 0);
            TextView textView = new TextView(mContext);
            textView.setText("??????");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            textView.setPadding(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            textView.setBackgroundResource(R.drawable.is_essence_shape);
            holder.llHotPost.addView(textView, textViewLp);
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tvHotUsername;
        TextView tvHotAddtime;
        ImageView ivHotFigure;
        LinearLayout llHotPost;
        TextView tvHotSaying;
        TextView tvHotLikes;
        TextView tvHotComments;
        ImageButton ibNewPostAvatar;



//        @Bind(R.id.tv_hot_username)
//        TextView tvHotUsername;
//        @Bind(R.id.tv_hot_addtime)
//        TextView tvHotAddtime;
//        @Bind(R.id.iv_hot_figure)
//        ImageView ivHotFigure;
//        @Bind(R.id.ll_hot_post)
//        LinearLayout llHotPost;
//        @Bind(R.id.tv_hot_saying)
//        TextView tvHotSaying;
//        @Bind(R.id.tv_hot_likes)
//        TextView tvHotLikes;
//        @Bind(R.id.tv_hot_comments)
//        TextView tvHotComments;
//        @Bind(R.id.ib_new_post_avatar)
//        ImageButton ibNewPostAvatar;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
        ViewHolder( ) {
        }

    }
}
