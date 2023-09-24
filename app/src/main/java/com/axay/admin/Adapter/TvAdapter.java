package com.axay.admin.Adapter;

import static com.axay.admin.vars.listOfLaptop;
import static com.axay.admin.vars.listOfTV;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.axay.admin.R;
import com.axay.admin.UpdateRecord;
import com.bumptech.glide.Glide;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {
    List<HashMap<String, Object>> list;
    Context context;


    public TvAdapter(Context context) {

        this.list = listOfTV;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_item, parent, false);


        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.shopname.setText(list.get(position).get("shop_name").toString());
        holder.brand.setText(list.get(position).get("name").toString());
        holder.os_type.setText(list.get(position).get("os_type").toString());
        holder.sound.setText(list.get(position).get("sound").toString());
        holder.display.setText(list.get(position).get("display").toString());
        holder.price.setText(list.get(position).get("price").toString());
        Glide.with(context).load(list.get(position).get("image")).into(holder.imgProduct);
        holder.btnDelete.setOnClickListener(v -> {
            deleteProduct(listOfTV.get(position).get("id").toString());
            listOfTV.remove(position);
            notifyItemRemoved(position);
        });
        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateRecord.class);
                intent.putExtra("Data",listOfTV.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        Button btnDelete,btnupdate;
        TextView shopname,brand, os_type, sound, display, price;

        public ViewHolder(View view) {
            super(view);
            imgProduct = (ImageView) view.findViewById(R.id.imgProduct);
            shopname = (TextView) view.findViewById(R.id.shopname);
            brand = (TextView) view.findViewById(R.id.brandname);
            os_type = (TextView) view.findViewById(R.id.os_type);
            sound = (TextView) view.findViewById(R.id.sound);
            display = (TextView) view.findViewById(R.id.display);
            price = (TextView) view.findViewById(R.id.price);
            btnDelete = (Button) view.findViewById(R.id.btnDelete);
            btnupdate = (Button) view.findViewById(R.id.btnupdate);
        }
    }
    private void deleteProduct(String id) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.29.224:80/product/delete_product.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Success",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Failed",error.getMessage().toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
