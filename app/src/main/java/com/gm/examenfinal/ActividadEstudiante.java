package com.gm.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import pojo.Actividad;
import pojo.ActividadAdapter;
import pojo.Curso;
import util.Util;

public class ActividadEstudiante extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener, View.OnClickListener {

    RecyclerView recyclerActividades;
    ArrayList<Actividad> listaActividad;
    ProgressDialog progressDialog;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    private Curso curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_estudiante);

        listaActividad = new ArrayList<>();
        recyclerActividades =findViewById(R.id.recycleActividad);
        recyclerActividades.setLayoutManager(new LinearLayoutManager(this));
        recyclerActividades.setHasFixedSize(true);
        request = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        curso = intent.getParcelableExtra("curso");

        Log.d("CursosAdapter", "id_curso: " + curso.getId_curso());
        Log.d("CursosAdapter", "id_seccion: " + curso.getId_seccion());

        String id_curso = String.valueOf(curso.getId_curso());
        String id_seccion = String.valueOf(curso.getId_seccion());

        cargarWebService(id_curso,id_seccion);
    }

    private void cargarWebService(String idCurso, String nombreCurso) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Consultando datos");
        progressDialog.show();
        String url = Util.RUTA + "consultaractividadporcursocolegio.php?id_curso="+ idCurso + "&id_seccion=" + nombreCurso;
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
        Actividad actividad = null;
        JSONArray json = response.optJSONArray("actividad");
        try {

            for (int i = 0; i < json.length(); i++) {
                actividad = new Actividad();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                actividad.setTitulo(jsonObject.optString("titulo"));
                actividad.setDescripcion(jsonObject.optString("descripcion"));
                actividad.setIndicaciones(jsonObject.optString("indicaciones"));
                actividad.setPlazo_entrega(jsonObject.optString("plazo_entrega"));
                listaActividad.add(actividad);
            }
            progressDialog.hide();

            ActividadAdapter adapter = new ActividadAdapter(listaActividad);
            recyclerActividades.setAdapter(adapter);
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