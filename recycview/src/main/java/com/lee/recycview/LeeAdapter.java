package com.lee.recycview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lee.recycview.Holder.BaseViewHolder;
import com.lee.recycview.Holder.FooterHolder;
import com.lee.recycview.Holder.HandHolder;
import com.lee.recycview.Holder.ViewHolder;

import java.util.List;

/**
 * Created by android on 2016/12/12.
 */
public class LeeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final static int ITEM_HANDER = 0;
    private final static int ITEM_NOLMOR = 1;
    private final static int ITEM_FOOTER = 2;


    private View handView;
    private View footView;


    private List<String> mData;
    private Context mContext;

    public LeeAdapter(List<String> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }


    public void setHanfView(View handView) {
        this.handView = handView;
    }

    public void setFootView(View footView) {
        this.footView = footView;
    }

    @Override
    public int getItemCount() {
        return mData.size() + 2;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_HANDER) {
            return new HandHolder(handView);
        } else if (viewType == ITEM_FOOTER) {
            return new FooterHolder(footView);
        } else {
            View view = View.inflate(mContext, R.layout.item_view, null);
            return new ViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_HANDER;
        } else if (position == mData.size() + 1) {
            return ITEM_FOOTER;
        } else {
            return ITEM_NOLMOR;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        if (getItemViewType(position) == ITEM_HANDER) {
            return;
        } else if (getItemViewType(position) == ITEM_FOOTER) {
            return;
        }
        ViewHolder mHolder = (ViewHolder) holder;
        mHolder.tv.setText(mData.get(position - 1));

    }
}
