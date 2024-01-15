package com.davidguambana.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ArticuloVista extends AppCompatActivity {

    TextView d1, d2, d3, d4, d5, d6;
    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articulo);

        d1 = findViewById(R.id.tv_codigo);
        d2 = findViewById(R.id.tv_nombre);
        d3 = findViewById(R.id.tv_categoria);
        d4 = findViewById(R.id.tv_stock);
        d5 = findViewById(R.id.tv_id_proveedor);
        d6 = findViewById(R.id.tv_cantidad_vendida);
        regresar = findViewById(R.id.regresar);

        Intent intent = getIntent();

        // Verifica si hay datos extras en el Intent y obtén el objeto Usuario
        if (intent != null && intent.hasExtra("objeto_articulo")) {
            Articulo articulo = (Articulo) intent.getSerializableExtra("objeto_articulo");
            d1.setText("Código de artículo: "+articulo.getCodigo());
            d2.setText(articulo.getNombre());
            d3.setText(articulo.getCategoria());
            d4.setText(articulo.getStock()+"");
            d5.setText(articulo.getId_proveedor()+"");
            d6.setText(articulo.getCantidad_vendida()+"");
        }

        regresar.setOnClickListener(l -> this.finish());
    }
}