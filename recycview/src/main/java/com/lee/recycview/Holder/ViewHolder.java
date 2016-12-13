package com.lee.recycview.Holder;

import android.view.View;
import android.widget.TextView;

import com.lee.recycview.R;

/**
 * Created by android on 2016/12/12.
 */
public class ViewHolder extends BaseViewHolder {

    public TextView tv;

    public ViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv);
    }
}
