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

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerView> {

    private List<CustomerModel>customerModelList;
    private Context context;
    AuxiliarCustomer auxiliarCustomer;
    // private  ArrayList<CustomerModel>customerModelArrayList;

    public CustomerAdapter( AuxiliarCustomer auxiliar,Context context, List<CustomerModel> customerModelList) {

        this.customerModelList = customerModelList;
        this.context = context;
        this.auxiliarCustomer=auxiliar;
    }

    @NonNull
    @Override
    public CustomerView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mostrar,parent,false);
        return new CustomerView(view);
    }

    public void agregaUsuario(CustomerModel customer){
        customerModelList.add(customer);
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(CustomerView customerView, int position) {
        CustomerModel customer=customerModelList.get(position);
        customerView.txtNombreMostrar.setText(customer.getName());
        customerView.txtDirreccionMostrar.setText(customer.getDireccion());
        customerView.txtNumeroMostrar.setText(customer.getNumero());
        customerView.btnEliminar.setOnClickListener(new eventoEliminar(customer));
        customerView.btnEditar.setOnClickListener(new eventoEditar(customer));
    }
    class eventoEditar implements View.OnClickListener {

        private CustomerModel persona;

        public eventoEditar(CustomerModel persona) {
            this.persona = persona;
        }

        @Override
        public void onClick(View v) {
            auxiliarCustomer.OpcionEditar(persona);
        }
    }


    class eventoEliminar implements View.OnClickListener {
        private CustomerModel persona;

        public eventoEliminar(CustomerModel persona) {
            this.persona = persona;
        }

        @Override
        public void onClick(View v) {
            auxiliarCustomer.OpcionEliminar(persona);
        }
    }
    @Override
    public int getItemCount() {
        return customerModelList.size();
    }

    public class CustomerView extends RecyclerView.ViewHolder{

        private TextView txtNombreMostrar,txtDirreccionMostrar,txtNumeroMostrar;
        private Button btnEditar, btnEliminar;
        public CustomerView(@NonNull View itemView) {

            super(itemView);
            txtNombreMostrar = itemView.findViewById(R.id.txtNombreMostrar);
            txtDirreccionMostrar = itemView.findViewById(R.id.txtDireccionMostrar);
            txtNumeroMostrar = itemView.findViewById(R.id.txtNumeroMostrar);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}