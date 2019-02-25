package com.marcecuevas.mltestapp.view.adapter.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.utils.MLFont;
import com.marcecuevas.mltestapp.utils.MLFontVariable;

public abstract class SimpleViewHolder<T> extends RecyclerView.ViewHolder{

    protected Context context;
    protected TextView titleTV;
    protected ImageView imageView;

    public SimpleViewHolder(Context context,View itemView) {
        super(itemView);
        this.context = context;

        titleTV = itemView.findViewById(R.id.titleTV);
        imageView = itemView.findViewById(R.id.imageView);

        titleTV.setTextSize(14);
        titleTV.setTextColor(Color.BLACK);
        MLFont.applyFont(context,titleTV, MLFontVariable.light);
    }

    public abstract void bind(T element);
}