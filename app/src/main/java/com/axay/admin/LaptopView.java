package com.axay.admin;

import static com.axay.admin.vars.listOfLaptop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.axay.admin.Adapter.LaptopAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LaptopView extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.laptop_view);
        recyclerView = findViewById(R.id.idRVItem);

        getProducts();


    }

    private void getProducts() {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://192.168.29.224:80/product/get_product.php?type=1", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listOfLaptop.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject responseObj = response.getJSONObject(i);
                        HashMap<String, Object> item = new HashMap<>();
                        item.put("shop_name", responseObj.getString("shop_name"));
                        item.put("name", responseObj.getString("product_name"));
                        item.put("ram", responseObj.getString("ram"));
                        item.put("ssd", responseObj.getString("SSD"));
                        item.put("price", responseObj.getString("price"));
                        item.put("image", responseObj.getString("image"));
                        item.put("id", responseObj.getString("id"));
                        listOfLaptop.add(item);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                setLaptopList();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("", "");
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }

    private void setLaptopList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        LaptopAdapter laptopAdapter = new LaptopAdapter(this);
        recyclerView.setAdapter(laptopAdapter);
    }

}