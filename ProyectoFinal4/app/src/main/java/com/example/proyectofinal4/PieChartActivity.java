package com.example.proyectofinal4;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        PieChart pieChart = findViewById(R.id.pieChart);

        ArrayList<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry(65, "Enero"));
        visitors.add(new PieEntry(60, "Febrero"));
        visitors.add(new PieEntry(71, "Marzo"));
        visitors.add(new PieEntry(39, "Abril"));
        visitors.add(new PieEntry(52, "Mayo"));
        visitors.add(new PieEntry(45,"Junio"));
        visitors.add(new PieEntry(43,"Julio"));
        visitors.add(new PieEntry(37,"Agosto"));
        visitors.add(new PieEntry(49,"Septiembre"));
        visitors.add(new PieEntry(46,"Octubre"));
        visitors.add(new PieEntry(71,"Noviembre"));
        visitors.add(new PieEntry(68,"Diciembre"));

        //Creamos un nuevo set de datos con el arreglo y una etiqueta
        PieDataSet pieDataSet = new PieDataSet(visitors,"Meses");

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        //Establecemos un texto en el centro de la grafica
        pieChart.setCenterText("Consumo en GB del 2020");
        //Descripcion deshabilitada
        pieChart.getDescription().setEnabled(false);
        //Animacion
        pieChart.animate();
    }
}






