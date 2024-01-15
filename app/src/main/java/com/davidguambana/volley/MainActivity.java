package com.davidguambana.volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    RequestQueue queue;
    //10.0.2.2
    String url = "http://10.0.2.2:8080/api/articulos";

    public ArticulosAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);

        RecyclerView rv_articulos = findViewById(R.id.rv_articulos);
        rv_articulos.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_articulos.setLayoutManager(layoutManager);

        mAdapter = new ArticulosAdapter(this);
        rv_articulos.setAdapter(mAdapter);

        getData();
    }


    private void getData(){
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.length() > 0) {
                        ArrayList<Articulo> articulos = new ArrayList<>();

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            // Parsea el objeto JSON y crea instancias de Articulo
                            Articulo articulo = new Articulo();
                            articulo.setCodigo(obj.getInt("codigo"));
                            articulo.setNombre(obj.getString("nombre"));
                            articulo.setCategoria(obj.getString("categoria"));
                            articulo.setStock(obj.getInt("stock"));
                            articulo.setId_proveedor(obj.getInt("id_proveedor"));
                            articulo.setCantidad_vendida(obj.getInt("cantidad_vendida"));

                            articulos.add(articulo);
                        }
                        Log.d("onResponse users", "Size of articles: " + articulos.size());
                        mAdapter.setDataSet(articulos);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(request);
    }
}