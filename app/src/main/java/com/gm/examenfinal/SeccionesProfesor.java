package com.gm.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import pojo.Profesor;
import pojo.Seccion;
import pojo.SeccionAdapter;
import util.Util;

public class SeccionesProfesor extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener, View.OnClickListener {

    RecyclerView recyclerSecciones;
    ArrayList<Seccion> listaSecciones;
    ProgressDialog progressDialog;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    private Seccion seccion;
    private Profesor profesor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secciones_profesor);

        Intent intent = getIntent();

        profesor = intent.getParcelableExtra("profesor");

        listaSecciones = new ArrayList<>();
        recyclerSecciones =findViewById(R.id.recycleSecciones);
        recyclerSecciones.setLayoutManager(new LinearLayoutManager(this));
        recyclerSecciones.setHasFixedSize(true);
        request = Volley.newRequestQueue(this);


        cargarWebService();
    }

    private void cargarWebService() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Consultando datos");
        progressDialog.show();
        String url = Util.RUTA + "consultarsecciones.php?id_profesor="+ profesor.getId_profesor();
        url = url.replace(" ", "&20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progressDialog.hide();
        Toast.makeText(this,"Error al consultar" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        progressDialog.hide();
        Seccion seccion = null;
        JSONArray json = response.optJSONArray("seccion");
        try {

            for (int i = 0; i < json.length(); i++) {
                seccion = new Seccion();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                seccion.setId_seccion(jsonObject.optInt("id_seccion"));
                seccion.setDescripcion(jsonObject.optString("descripcion"));
                listaSecciones.add(seccion);
            }
            progressDialog.hide();

            SeccionAdapter adapter = new SeccionAdapter(listaSecciones);
            recyclerSecciones.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "No conexion con el servidor",
                    Toast.LENGTH_SHORT).show();
            progressDialog.hide();
        }
    }

    @Override
    public void onClick(View v) {
        //Aun no
    }
}