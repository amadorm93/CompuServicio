package com.example.proyectofinal4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReciboAdapter extends RecyclerView.Adapter<ReciboAdapter.ReciboView>{
    private List<RecibosModel>reciboModelList;
    private Context context;
    AuxiliarRecibo auxiliar;
    public ReciboAdapter(AuxiliarRecibo auxiliar,Context context, List<RecibosModel> reciboModelList){
        this.reciboModelList=reciboModelList;
        this.context= context;
        this.auxiliar=auxiliar;
    }

    @NonNull
    @Override
    public ReciboView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recibo,parent,false);
        return new ReciboView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReciboView holder, int position) {
        RecibosModel recibo=reciboModelList.get(position);
        holder.txtNombreMostrar.setText(recibo.getName());
        holder.txtMesMostrar.setText(recibo.getMes());
        holder.txtMontoMostrar.setText(recibo.getMonto());
        holder.btnEliminar.setOnClickListener(new eventoEliminar(recibo));
        holder.btnEditar.setOnClickListener(new eventoEditar(recibo));
    }
    class eventoEditar implements View.OnClickListener {

        private RecibosModel persona;

        public eventoEditar(RecibosModel persona) {
            this.persona = persona;
        }

        @Override
        public void onClick(View v) {
            auxiliar.OpcionEditar(persona);
        }
    }


    class eventoEliminar implements View.OnClickListener {
        private RecibosModel persona;

        public eventoEliminar(RecibosModel persona) {
            this.persona = persona;
        }

        @Override
        public void onClick(View v) {
            auxiliar.OpcionEliminar(persona);
        }
    }

    @Override
    public int getItemCount() {
        return reciboModelList.size();
    }
    public class ReciboView extends RecyclerView.ViewHolder{
        private TextView txtNombreMostrar,txtMesMostrar,txtMontoMostrar;
        private Button btnEditar, btnEliminar;

        public ReciboView(@NonNull View itemView) {
            super(itemView);
            txtNombreMostrar = itemView.findViewById(R.id.txtNombreMostrar2);
            txtMesMostrar = itemView.findViewById(R.id.txtMesMostrar);
            txtMontoMostrar = itemView.findViewById(R.id.txtMontoMostrar);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
