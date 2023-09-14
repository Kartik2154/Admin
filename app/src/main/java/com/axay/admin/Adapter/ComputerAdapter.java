package com.axay.admin.Adapter;

import static com.axay.admin.vars.listOfComputer;


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

public class ComputerAdapter extends RecyclerView.Adapter<ComputerAdapter.ViewHolder> {
    List<HashMap<String, Object>> list;
    Context context;


    public ComputerAdapter(Context context) {

        this.list = listOfComputer;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.computer_item, parent, false);


        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.shopname.setText(list.get(position).get("shop_name").toString());
        holder.brand.setText(list.get(position).get("name").toString());
        holder.ram.setText(list.get(position).get("ram").toString());
        holder.ssd.setText(list.get(position).get("ssd").toString());
        holder.color.setText(list.get(position).get("color").toString());
//        holder.display.setText(list.get(position).get("display").toString());
        holder.price.setText(list.get(position).get("price").toString());
        Glide.with(context).load(list.get(position).get("image")).into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView shopname,brand, ram, ssd, display, color, price;

        public ViewHolder(View view) {
            super(view);
            imgProduct = (ImageView) view.findViewById(R.id.imgProduct);
            shopname = (TextView) view.findViewById(R.id.shopname);
            brand = (TextView) view.findViewById(R.id.brandname);
            ram = (TextView) view.findViewById(R.id.ram);
            ssd = (TextView) view.findViewById(R.id.ssd);
            color = (TextView) view.findViewById(R.id.pcolors);
            display = (TextView) view.findViewById(R.id.display);
            price = (TextView) view.findViewById(R.id.price);
        }
    }
}

