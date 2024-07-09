package com.gm.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jakewharton.threetenabp.AndroidThreeTen;

import pojo.Profesor;

public class MenuProfesor extends AppCompatActivity  {

    private Profesor profesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
        setContentView(R.layout.activity_menu_profesor);

        Intent intent = getIntent();
        profesor = intent.getParcelableExtra("profesor");

    }
    public void Llamar_asignaturas(View view) {

        Intent i = new Intent(this, SeccionesProfesor.class);
        i.putExtra("profesor", profesor);
        startActivity(i);
    }

    public void Llamar_asistencia(View view) {

        Intent j = new Intent(this, ListaAsistencia.class);
        startActivity(j);
    }

    public void Llamar_Qrs(View view) {
        Intent k = new Intent(this, SeccionesCodigosProfesor.class);
        k.putExtra("profesor", profesor);
        startActivity(k);
    }

    public void Opciones(View view) {
        Intent o = new Intent(this, Opciones.class);
        o.putExtra("profesor", profesor);
        startActivity(o);
    }
}