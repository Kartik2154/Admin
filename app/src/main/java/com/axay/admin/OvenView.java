package com.axay.admin;

import static com.axay.admin.vars.listOfOven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.axay.admin.Adapter.OvenAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class OvenView extends AppCompatActivity {
    ImageButton btnback;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oven_view);
        recyclerView = findViewById(R.id.idRVItem);
        btnback = findViewById(R.id.btnback);

        getProducts();

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OvenView.this, OvenInsert.class);
                startActivity(intent);
            }
        });
    }
    private void getProducts() {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://192.168.29.224:80/product/get_product.php?type=6", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listOfOven.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject responseObj = response.getJSONObject(i);
                        HashMap<String, Object> item = new HashMap<>();
                        item.put("shop_name", responseObj.getString("shop_name"));
                        item.put("name", responseObj.getString("product_name"));
                        item.put("capacity", responseObj.getString("capacity"));
                        item.put("star", responseObj.getString("star"));
                        item.put("color", responseObj.getString("color"));
                        item.put("price", responseObj.getString("price"));
                        item.put("image", responseObj.getString("image"));
                        listOfOven.add(item);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                setOvenList();
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

    private void setOvenList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        OvenAdapter ovenAdapter = new OvenAdapter(this);
        recyclerView.setAdapter(ovenAdapter);
    }
}