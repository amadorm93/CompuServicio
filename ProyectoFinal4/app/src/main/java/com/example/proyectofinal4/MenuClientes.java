package com.example.proyectofinal4;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuClientes extends AppCompatActivity implements AuxiliarCustomer {

    RecyclerView recyclerView;
    CustomerAdapter adaptador;
    private List<CustomerModel> lista;
    private DatabaseReference mDatabaseRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuclientes);

        recyclerView=findViewById(R.id.recyclervi);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));


        lista=new ArrayList<>();
        adaptador= new CustomerAdapter(this,MenuClientes.this,lista);
        recyclerView.setAdapter(adaptador);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("clientes");

        readData(mDatabaseRef, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                //whatever you need to do with the data
                lista.clear();
                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()){

                    CustomerModel upload = postSnapShot.getValue(CustomerModel.class);
                    upload.setKey(postSnapShot.getKey());
                    lista.add(upload);
                }
                adaptador.notifyDataSetChanged();
            }
            @Override
            public void onStart() {
                //whatever you need to do onStart

            }

            @Override
            public void onFailure() {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irsealaverga();
            }
        });

    }
    public void irsealaverga(){
        Intent i;
        i= new Intent(this,AgregaUsuario.class);
        startActivity(i);
    }
    public void irsealaverga2(){
        Intent i;
        i= new Intent(this,MenuClientes.class);
        startActivity(i);
    }

    @Override
    public void OpcionEditar(CustomerModel customer) {
            Intent intent;
            Bundle extras;
            extras= new Bundle();
            extras.putSerializable("customer",customer);
            intent=new Intent(this,AgregaUsuario.class);
            intent.putExtras(extras);
            startActivity(intent);
    }

    @Override
    public void OpcionEliminar(CustomerModel customer) {
        Intent intent;
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Mensaje");
        alerta.setMessage("Esta seguro que desea Eliminar? " + customer.getName());
        alerta.setCancelable(false);
        alerta.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDatabaseRef.child(customer.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MenuClientes.this, "Eliminado con exito", Toast.LENGTH_SHORT).show();
                        irsealaverga2();
                    }
                });
            }
        });
        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta.show();
    }

    public interface OnGetDataListener {
        //make new interface for call back
        void onSuccess(DataSnapshot dataSnapshot);
        void onStart();
        void onFailure();
    }
    public void readData(DatabaseReference ref, final OnGetDataListener listener) {
        listener.onStart();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure();
            }
        });

    }

}
