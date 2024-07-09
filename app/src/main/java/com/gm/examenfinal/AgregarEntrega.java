package com.gm.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import pojo.Actividad;

public class AgregarEntrega extends AppCompatActivity {
    private Actividad actividad;
    TextView textTitulo,txtDescripcion,textFecha;
    ScrollView scrollLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_entrega);

        textTitulo = findViewById(R.id.textTitulo);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        textFecha = findViewById(R.id.textFecha);
        scrollLink = findViewById(R.id.link_entrega);

        Intent intent = getIntent();
        actividad = intent.getParcelableExtra("actividad");
        insertarDatos();

    }

    private void insertarDatos() {
        textTitulo.setText(actividad.getTitulo());
        txtDescripcion.setText(actividad.getDescripcion());
        textFecha.setText("Vence: "+actividad.getPlazo_entrega());
        TextView textView = new TextView(this);
        textView.setText(actividad.getIndicaciones());
        scrollLink.addView(textView);
    }
}