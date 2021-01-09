package com.example.proyectofinal4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AgregaUsuario extends AppCompatActivity{
    private DatabaseReference mDatabaseRef;
    Intent i;
    Button add;
    CustomerModel customer;
    EditText t_name, t_direccion, t_fecha, t_phone;
    Switch sw_activecustomer;
    DataBaseHelper databasehelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregausuario);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("clientes");

        add=(Button)findViewById(R.id.btn_add);
        t_name=(EditText)findViewById(R.id.t_name);
        t_direccion=(EditText)findViewById(R.id.t_direccion);
        t_fecha=(EditText)findViewById(R.id.t_fecha);
        t_phone=(EditText)findViewById(R.id.t_numero);

        sw_activecustomer=(Switch)findViewById(R.id.sw_activecustomer);
        databasehelper = new DataBaseHelper(AgregaUsuario.this);
        Bundle datos=this.getIntent().getExtras();
        if(datos!=null){
            customer= (CustomerModel) datos.getSerializable("customer");
            t_name.setText(customer.getName());
            t_direccion.setText(customer.getDireccion());
            t_fecha.setText(customer.getFecha());
            t_phone.setText(customer.getNumero());
            add.setText("Editar Cliente");
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String,Object> customerMap=new HashMap<>();
                    customerMap.put("name",t_name.getText().toString().trim());
                    customerMap.put("direccion",t_direccion.getText().toString().trim());
                    customerMap.put("fecha",t_fecha.getText().toString().trim());
                    customerMap.put("numero",t_phone.getText().toString().trim());
                    mDatabaseRef.child(customer.getKey()).updateChildren(customerMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AgregaUsuario.this, "Editado Correctamente", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        else
        {
            add.setOnClickListener(v -> {

                CustomerModel customermodel;
           /* try {

                // Toast.makeText(MainActivity.this,customermodel.toString(),Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(AgregaUsuario.this,"Error al crear al cliente",Toast.LENGTH_SHORT).show();
                customermodel = new CustomerModel(-1,"error","error","error","error",false);
            }

            DataBaseHelper databasehelper=new DataBaseHelper(AgregaUsuario.this);
            boolean success = databasehelper.addOne(customermodel);
            ShowCustomerOnListView(databasehelper);*/
                // Toast.makeText(AgregaUsuario.this,"Success "+ success,Toast.LENGTH_SHORT).show();
                customermodel = new CustomerModel(-1,t_name.getText().toString(),t_fecha.getText().toString(),t_direccion.getText().toString(),t_phone.getText().toString(),sw_activecustomer.isChecked());
                mDatabaseRef.push().setValue(customermodel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AgregaUsuario.this, "Chupi papu", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AgregaUsuario.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                i= new Intent(this,MenuClientes.class);
                startActivity(i);

            });
        }


    }
}
