package com.example.proyectofinal4;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AgregaRecibo extends AppCompatActivity{
    private DatabaseReference mDatabaseRef;
    Intent i;
    Button add;
    EditText t_name, t_fecha, t_monto, t_mes;
    RecibosModel recibo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregarecibo);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("recibos");

        add=(Button)findViewById(R.id.btn_add2);
        t_name=(EditText)findViewById(R.id.t_name2);
        t_mes=(EditText)findViewById(R.id.t_mes);
        t_fecha=(EditText)findViewById(R.id.t_fecha2);
        t_monto=(EditText)findViewById(R.id.t_monto);

        Bundle datos=this.getIntent().getExtras();
        if(datos!=null){
            recibo= (RecibosModel) datos.getSerializable("recibo");
            t_name.setText(recibo.getName());
            t_mes.setText(recibo.getMes());
            t_fecha.setText(recibo.getFecha());
            t_monto.setText(recibo.getMonto());
            add.setText("Editar Recibo");
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String,Object> customerMap=new HashMap<>();
                    customerMap.put("name",t_name.getText().toString().trim());
                    customerMap.put("mes",t_mes.getText().toString().trim());
                    customerMap.put("fecha",t_fecha.getText().toString().trim());
                    customerMap.put("monto",t_monto.getText().toString().trim());
                    mDatabaseRef.child(recibo.getKey()).updateChildren(customerMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AgregaRecibo.this, "Editado Correctamente", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        else
        {
            add.setOnClickListener(v -> {
                RecibosModel recibosModel;
                recibosModel= new RecibosModel(-1,t_name.getText().toString(),t_fecha.getText().toString(),t_monto.getText().toString(),t_mes.getText().toString());
                mDatabaseRef.push().setValue(recibosModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AgregaRecibo.this, "Chupi papu", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AgregaRecibo.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                i= new Intent(this,MenuRecibos.class);
                startActivity(i);
            });
        }
    }
}
