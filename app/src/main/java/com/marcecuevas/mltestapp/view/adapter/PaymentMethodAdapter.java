package com.marcecuevas.mltestapp.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.model.dto.PaymentMethodDTO;
import com.marcecuevas.mltestapp.utils.MLFont;
import com.marcecuevas.mltestapp.utils.MLFontVariable;
import com.marcecuevas.mltestapp.view.adapter.viewholder.SimpleViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodAdapter extends SimpleImageTextAdapter<PaymentMethodDTO,PaymentMethodAdapter.PaymentMethodViewHolder> {

    private Listener listener;

    public PaymentMethodAdapter(Context context, Listener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    PaymentMethodViewHolder getViewHolder(View view) {
        return new PaymentMethodViewHolder(context,view);
    }

    class PaymentMethodViewHolder extends SimpleViewHolder<PaymentMethodDTO> implements View.OnClickListener {

        PaymentMethodViewHolder(Context context, View itemView) {
            super(context, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void bind(PaymentMethodDTO item) {
            if(item.getThumbnail() != null){
                Glide.with(context).load(item.getThumbnail()).into(imageView);
            }
            if(item.getName() != null){
                titleTV.setText(item.getName());
            }
        }

        @Override
        public void onClick(View view) {
            if(listener != null){
                listener.onPaymentMethodSelected(items.get(
                        getLayoutPosition()));
            }
        }

    }

    public interface Listener {
        void onPaymentMethodSelected(PaymentMethodDTO paymentMethod);
    }
}
