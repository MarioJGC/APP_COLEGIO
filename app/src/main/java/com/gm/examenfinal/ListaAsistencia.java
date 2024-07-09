package com.gm.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import pojo.Asistencia;
import pojo.AsistenciaAdapter;
import util.Util;

public class ListaAsistencia extends AppCompatActivity implements  Response.Listener<JSONObject>,
        Response.ErrorListener, View.OnClickListener {

    RecyclerView recyclerAsistencia;

    ArrayList<Asistencia> listaAsistencia;

    ProgressDialog progressDialog;

    RequestQueue request;

    JsonObjectRequest jsonObjectRequest;

    EditText edtFecha, edtSeccion;

    Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_asistencia);

        edtFecha = findViewById(R.id.edtFecha);
        edtSeccion = findViewById(R.id.edtSeccion);
        btnBuscar = findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(this);

        listaAsistencia = new ArrayList<>();
        recyclerAsistencia =findViewById(R.id.recyclerAsistencia);

        recyclerAsistencia.setLayoutManager(new LinearLayoutManager(this));
        recyclerAsistencia.setHasFixedSize(true);
        request = Volley.newRequestQueue(this);


    }

    private void cargarWebService() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Consultando datos");
        progressDialog.show();

        listaAsistencia.clear();

        String fecha =  edtFecha.getText().toString();
        String seccion = edtSeccion.getText().toString();

        String url = Util.RUTA + "consultarasistencia.php?fecha=" + fecha + "&seccion=" + seccion;
        url = url.replace(" ", "&20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                (JSONObject) null, this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        progressDialog.hide();
        Toast.makeText(this,"No se encontraron registros", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {

        progressDialog.hide();
        Asistencia asistencia = null;
        JSONArray json = response.optJSONArray("asistencia");
        try {

            for (int i = 0; i < json.length(); i++) {
                asistencia = new Asistencia();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                asistencia.setId_asistencia(jsonObject.optInt("id_asistencia"));
                asistencia.setNombreEstudiante(jsonObject.optString("nombre"));
                asistencia.setFecha(jsonObject.optString("fecha"));
                asistencia.setEstado(jsonObject.optInt("estado"));
                asistencia.setSeccion(jsonObject.optString("seccion"));
                listaAsistencia.add(asistencia);
            }
            progressDialog.hide();

            AsistenciaAdapter adapter = new AsistenciaAdapter (listaAsistencia);
            recyclerAsistencia.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "No conexion con el servidor",
                    Toast.LENGTH_SHORT).show();
            progressDialog.hide();
        }



    }

    @Override
    public void onClick(View v) {

        cargarWebService();

    }
}