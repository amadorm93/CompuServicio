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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuRecibos extends AppCompatActivity implements  AuxiliarRecibo{
    RecyclerView recyclerView;
    ReciboAdapter adaptador;
    private List<RecibosModel> lista;
    private DatabaseReference mDatabaseRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menurecibos);

        recyclerView=findViewById(R.id.recyclervi2);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        lista=new ArrayList<>();
        adaptador= new ReciboAdapter(this,MenuRecibos.this,lista);
        recyclerView.setAdapter(adaptador);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("recibos");

        readData(mDatabaseRef, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                //whatever you need to do with the data
                lista.clear();
                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()){

                    RecibosModel upload = postSnapShot.getValue(RecibosModel.class);
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

        FloatingActionButton fab = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irsealaverga();
            }
        });


    }
    public void irsealaverga(){
        Intent i;
        i= new Intent(this,AgregaRecibo.class);
        startActivity(i);
    }
    public void irsealaverga2(){
        Intent i;
        i= new Intent(this,MenuRecibos.class);
        startActivity(i);
    }
    @Override
    public void OpcionEditar(RecibosModel recibo) {
        Intent intent;
        Bundle extras;
        extras= new Bundle();
        extras.putSerializable("recibo",recibo);
        intent=new Intent(this,AgregaRecibo.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void OpcionEliminar(RecibosModel customer) {
        Intent intent;
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Mensaje");
        alerta.setMessage("Esta seguro que desea eliminar este recibo? ");
        alerta.setCancelable(false);
        alerta.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDatabaseRef.child(customer.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MenuRecibos.this, "Eliminado con exito", Toast.LENGTH_SHORT).show();
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
    public void readData(DatabaseReference ref, final MenuRecibos.OnGetDataListener listener) {
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
