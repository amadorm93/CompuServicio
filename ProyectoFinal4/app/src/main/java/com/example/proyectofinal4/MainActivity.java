package com.example.proyectofinal4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnClientes,btnRecibos,btnEstadistica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClientes=findViewById(R.id.btnClientes);
        btnRecibos=findViewById(R.id.btnRecibos);
        btnEstadistica=findViewById(R.id.btnEstadistica);

        btnClientes.setOnClickListener(this);
        btnEstadistica.setOnClickListener(this);
        btnRecibos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.btnClientes:
            i=new Intent(this,MenuClientes.class);
                startActivity(i);
                break;
            case R.id.btnRecibos:
                i=new Intent(this,MenuRecibos.class);
                startActivity(i);
                break;
            case R.id.btnEstadistica:
                i=new Intent(this,PieChartActivity.class);
                startActivity(i);
                break;
        }

    }
}