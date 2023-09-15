package com.axay.admin.Adapter;

import static com.axay.admin.vars.listOfLaptop;

import android.content.Context;
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
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

public class LaptopAdapter  extends RecyclerView.Adapter<LaptopAdapter.ViewHolder> {

    Context context;

    public LaptopAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LaptopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_laptop_item, parent, false);
        LaptopAdapter.ViewHolder viewHolder = new LaptopAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.shopname.setText(listOfLaptop.get(position).get("shop_name").toString());
        holder.brand.setText(listOfLaptop.get(position).get("name").toString());
        holder.ram.setText(listOfLaptop.get(position).get("ram").toString());
        holder.ssd.setText(listOfLaptop.get(position).get("ssd").toString());
        holder.price.setText(listOfLaptop.get(position).get("price").toString());

        Glide.with(context).load(listOfLaptop.get(position).get("image")).into(holder.imgProduct);

        holder.btnDelete.setOnClickListener(v -> {
            deleteProduct(listOfLaptop.get(position).get("id").toString());
            listOfLaptop.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return listOfLaptop.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        Button btnDelete;
        TextView shopname,brand,ram,ssd,price;

        public ViewHolder(View view) {
            super(view);
            imgProduct = (ImageView) view.findViewById(R.id.imgProduct);
            shopname = (TextView) view.findViewById(R.id.shopname);
            brand = (TextView) view.findViewById(R.id.brandname);
            ram = (TextView) view.findViewById(R.id.ram);
            ssd = (TextView) view.findViewById(R.id.ssd);
            price = (TextView) view.findViewById(R.id.price);
            btnDelete = (Button) view.findViewById(R.id.btnDelete);
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
