package com.gm.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import pojo.Curso;
import util.Util;

public class ActividadesProfesor extends AppCompatActivity implements View.OnClickListener {

    EditText edtIdActividad,edtTitulo, edtDescripcion, edtIndicaciones,edtPlazo;
    Button btnCrear;

    Util u;
    Curso curso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades_profesor);

        edtTitulo = findViewById(R.id.edtTitulo);
        edtDescripcion = findViewById(R.id.edtDescripcion);
        edtIndicaciones = findViewById(R.id.edtIndicaciones);
        edtPlazo = findViewById(R.id.edtPlazo);
        btnCrear = findViewById(R.id.btnCrear);

        btnCrear.setOnClickListener(this);

        Intent intent = getIntent();

        curso = intent.getParcelableExtra("curso");


    }

    @Override
    public void onClick(View v) {
        String Titulo = edtTitulo.getText().toString();
        String Descripcion = edtDescripcion.getText().toString();
        String Indicaciones = edtIndicaciones.getText().toString();
        String Plazo = edtPlazo.getText().toString();

        insertarActividad("InsertarActividad.php?" +
                "&titulo=" + Titulo +
                "&descripcion=" + Descripcion +
                "&indicaciones=" + Indicaciones +
                "&plazo_entrega=" + Plazo +
                "&id_curso=" + curso.getId_curso()
        );
    }

    private void insertarActividad(String ws){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url=u.RUTA+ ws;
        StringRequest stringRequest=
                new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ActividadesProfesor.this, "Actividad registrada", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(ActividadesProfesor.this, "Error no se registro", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(stringRequest);

    }

}