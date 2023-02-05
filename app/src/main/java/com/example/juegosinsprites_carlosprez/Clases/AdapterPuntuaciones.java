package com.example.juegosinsprites_carlosprez.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juegosinsprites_carlosprez.R;

import java.util.ArrayList;

public class AdapterPuntuaciones extends RecyclerView.Adapter<AdapterPuntuaciones.ViewHolder> {
    private ArrayList<Puntuacion> dataSet;
    private Context context;

    public AdapterPuntuaciones(ArrayList<Puntuacion> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }


    @Override
    public AdapterPuntuaciones.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.puntuacion_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPuntuaciones.ViewHolder holder, int position) {
        holder.txtNombre.setText(dataSet.get(position).getNombre());
        holder.txtPuntuacion.setText(""+dataSet.get(position).getCaidas());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtNombre, txtPuntuacion;


        public ViewHolder(View itemView) {
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.nombrePuntuacion);
            txtPuntuacion = (TextView) itemView.findViewById(R.id.numPuntuacion);
        }
    }
}
