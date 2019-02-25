package com.marcecuevas.mltestapp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.view.adapter.viewholder.SimpleViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleImageTextAdapter<T,VH extends SimpleViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context context;
    protected List<T> items;

    SimpleImageTextAdapter(Context context){
        this.context = context;
        this.items = new ArrayList<>();
    }

    public void update(List<T> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_method,parent,false);
        return getViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    abstract VH getViewHolder(View view);
}

