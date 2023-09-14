package com.axay.admin.Adapter;

import static com.axay.admin.vars.listOfWC;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.axay.admin.R;
import com.bumptech.glide.Glide;


import java.util.HashMap;
import java.util.List;


public class WcAdapter extends RecyclerView.Adapter<WcAdapter.ViewHolder> {
        List<HashMap<String, Object>> list;
        Context context;

    public WcAdapter(Context context) {

        this.list = listOfWC;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wc_item, parent, false);


        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.shopname.setText(list.get(position).get("shop_name").toString());
        holder.brand.setText(list.get(position).get("name").toString());
        holder.capacity.setText(list.get(position).get("capacity").toString());
        holder.star.setText(list.get(position).get("star").toString());
        holder.color.setText(list.get(position).get("color").toString());
        holder.price.setText(list.get(position).get("price").toString());
        Glide.with(context).load(list.get(position).get("image")).into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView shopname,brand, capacity, star,color,price;

        public ViewHolder(View view) {
            super(view);
            imgProduct = (ImageView) view.findViewById(R.id.imgProduct);
            shopname = (TextView) view.findViewById(R.id.shopname);
            brand = (TextView) view.findViewById(R.id.brandname);
            capacity = (TextView) view.findViewById(R.id.washingcapacity);
            star = (TextView) view.findViewById(R.id.rating);
            color = (TextView) view.findViewById(R.id.pcolors);
            price = (TextView) view.findViewById(R.id.price);
        }
    }
}




