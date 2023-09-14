package com.axay.admin.Adapter;

import static com.axay.admin.vars.listOfLaptop;

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

public class LaptopAdapter  extends RecyclerView.Adapter<LaptopAdapter.ViewHolder> {

    Context context;

    public LaptopAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LaptopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.laptopitem, parent, false);
        LaptopAdapter.ViewHolder viewHolder = new LaptopAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopAdapter.ViewHolder holder, int position) {

        holder.shopname.setText(listOfLaptop.get(position).get("shop_name").toString());
        holder.brand.setText(listOfLaptop.get(position).get("name").toString());
        holder.ram.setText(listOfLaptop.get(position).get("ram").toString());
        holder.ssd.setText(listOfLaptop.get(position).get("ssd").toString());
        holder.price.setText(listOfLaptop.get(position).get("price").toString());
        Glide.with(context).load(listOfLaptop.get(position).get("image")).into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {
        return listOfLaptop.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView shopname,brand,ram,ssd,price;

        public ViewHolder(View view) {
            super(view);
            imgProduct = (ImageView) view.findViewById(R.id.imgProduct);
            shopname = (TextView) view.findViewById(R.id.shopname);
            brand = (TextView) view.findViewById(R.id.brandname);
            ram = (TextView) view.findViewById(R.id.ram);
            ssd = (TextView) view.findViewById(R.id.ssd);
            price = (TextView) view.findViewById(R.id.price);
        }
    }
}
