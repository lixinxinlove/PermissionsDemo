package com.lee.recycview.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by android on 2016/12/15.
 */
public class LeeCardLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        detachAndScrapAttachedViews(recycler);

        for(int i=0;i<getItemCount();i++){

            View child=recycler.getViewForPosition(i);

            measureChildWithMargins(child,0,0);
            addView(child);
            int w=getDecoratedMeasuredWidth(child);
            int h=getDecoratedMeasuredHeight(child);

            layoutDecorated(child,0,0,w,h);

            if(i<getItemCount()-1){
                child.setScaleX(0.8f);
                child.setScaleY(0.8f);
            }

        }

    }
}
