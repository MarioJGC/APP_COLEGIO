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

import pojo.Curso;
import pojo.CursosAdapter;
import pojo.Estudiante;
import util.Util;

public class CursoEstudiante extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener, View.OnClickListener {

    RecyclerView recyclerCursos;
    ArrayList<Curso> listaCursos;
    ProgressDialog progressDialog;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    private Estudiante estudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso_estudiante);

        // Obtener el Intent que inició esta Activity
        Intent intent = getIntent();
        // Obtener el objeto Estudiante de los extras del Intent
        estudiante = intent.getParcelableExtra("estudiante");

        listaCursos = new ArrayList<>();
        recyclerCursos =findViewById(R.id.recycleCursos);


        recyclerCursos.setLayoutManager(new LinearLayoutManager(this));
        recyclerCursos.setHasFixedSize(true);
        request = Volley.newRequestQueue(this);



        cargarWebService();
    }

    private void cargarWebService() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Consultando datos");
        progressDialog.show();

        String url = Util.RUTA + "consultarlistacursoscolegio.php?seccion=" + estudiante.getId_seccion();
        url = url.replace(" ", "&20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                (JSONObject) null, this, this);
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
        Curso curso = null;
        JSONArray json = response.optJSONArray("curso");
        try {

            for (int i = 0; i < json.length(); i++) {
                curso = new Curso();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                curso.setId_curso(jsonObject.optInt("id_curso"));
                curso.setId_seccion(jsonObject.optInt("id_seccion"));
                curso.setNombre_curso(jsonObject.optString("nombre_curso"));
                listaCursos.add(curso);
            }
            progressDialog.hide();
            CursosAdapter adapter = new CursosAdapter(listaCursos);
            recyclerCursos.setAdapter(adapter);


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