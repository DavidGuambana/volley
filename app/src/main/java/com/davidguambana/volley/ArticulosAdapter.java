package com.davidguambana.volley;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ArticulosAdapter extends RecyclerView.Adapter<ArticulosAdapter.ViewHolder>{
    private ArrayList<Articulo>mDataSet;
    private final Context mContext;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // en este ejemplo cada elemento consta solo nombre
        public TextView textView;
        public ViewHolder(TextView tv) {
            super(tv);
            textView = tv;
        }
    }

    // Este es nuestro constructor (puede variar según lo que queremos mostrar)
    public ArticulosAdapter(Context context) {
        mDataSet = new ArrayList<>();
        mContext = context;
    }

    public void setDataSet(ArrayList<Articulo> dataSet){
        mDataSet = dataSet;
        notifyDataSetChanged();
    }

    // El layout manager invoca este método
    // para renderizar cada elemento del RecyclerView
    @Override
    public ArticulosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Creamos una nueva vista
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_articulo, parent, false);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);  // Añade esta línea para centrar verticalmente
        return new ViewHolder(tv);
    }

    // Asignar valores para cada elemento de la lista
    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.textView.setText((i+1)+". "+mDataSet.get(i).getNombre());
        holder.textView.setOnClickListener(l-> ver_Articulo(i));
    }
    public void ver_Articulo(int i){
        Articulo articulo = mDataSet.get(i);
        Intent intent = new Intent(mContext, ArticuloVista.class);
        intent.putExtra("objeto_articulo",articulo);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
