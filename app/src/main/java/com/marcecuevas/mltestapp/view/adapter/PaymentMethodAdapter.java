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

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter
        .PaymentMethodViewHolder> {

    private Context context;
    private Listener listener;
    private List<PaymentMethodDTO> items;

    public PaymentMethodAdapter(Context context,Listener listener){
        this.context = context;
        this.listener = listener;
        this.items = new ArrayList<>();
    }

    public void update(List<PaymentMethodDTO> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_method,parent,false);
        return new PaymentMethodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class PaymentMethodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView methodNameTV;
        private ImageView methodImageView;

        PaymentMethodViewHolder(View itemView) {
            super(itemView);

            methodNameTV = itemView.findViewById(R.id.methodNameTV);
            methodImageView = itemView.findViewById(R.id.methodImageView);

            itemView.setOnClickListener(this);

            setupViews();
        }

        void bind(PaymentMethodDTO item){
            if(item.getThumbnail() != null){
                Glide.with(context).load(item.getThumbnail()).into(methodImageView);
            }
            if(item.getName() != null){
                methodNameTV.setText(item.getName());
            }
        }

        private void setupViews(){
            methodNameTV.setTextSize(14);
            methodNameTV.setTextColor(Color.BLACK);
            MLFont.applyFont(context,methodNameTV, MLFontVariable.light);
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
