package com.fxc.type.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fxc.R;
import com.fxc.type.model.TagBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/2.
 */
public class TagGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<TagBean.ResultBean> result;
    private int[] colors = {Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"),
            Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"), Color.parseColor("#f0a420"),
            Color.parseColor("#f0839a"), Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2")
    };

    public TagGridViewAdapter(Context mContext, List<TagBean.ResultBean> result) {
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
            convertView = View.inflate(mContext, R.layout.item_tab_gridview, null);
            holder = new ViewHolder();
            holder.tvTag = (TextView)convertView.findViewById(R.id.tv_tag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TagBean.ResultBean resultBean = result.get(position);
        holder.tvTag.setText(resultBean.getName());
        holder.tvTag.setTextColor(colors[position % colors.length]);

        return convertView;
    }

    static class ViewHolder {
        TextView tvTag;

//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
        ViewHolder() {

        }
    }
}
