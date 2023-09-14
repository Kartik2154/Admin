package com.axay.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Insertproduct extends AppCompatActivity {
    Button insert,ViewAll;
    EditText product_name,ram,SSD,price,image,shop_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertproduct);
        insert = findViewById(R.id.insert);
        ViewAll=findViewById(R.id.ViewAll);
        product_name = findViewById(R.id.product_name);
        ram = findViewById(R.id.ram);
        SSD = findViewById(R.id.SSD);
        price = findViewById(R.id.price);
        shop_name = findViewById(R.id.shop_name);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "working", Toast.LENGTH_LONG).show();
                addProduct();
            }
        });

        ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Insertproduct.this, ViewProduct.class);
                startActivity(intent);
            }
        });
    }

    private void addProduct() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.29.224:80/product/insert_product.php", new Response.Listener<String>() {
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
                params.put("product_type", "1");
                params.put("product_name",product_name.getText().toString());
                params.put("ram", ram.getText().toString());
                params.put("SSD", SSD.getText().toString());
                params.put("price", price.getText().toString());
                params.put("image", "");
                params.put("shop_name", shop_name.getText().toString());


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
