package com.gm.examenfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import pojo.Estudiante;

import util.Util;


public class MenuEstudiante extends AppCompatActivity implements View.OnClickListener, Response.Listener<JSONObject>, Response.ErrorListener {
    private Estudiante estudiante;
    Button btnScan;
    String seccion;
    int estado;
    String Fecha;
    ProgressDialog progressDialog;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
        setContentView(R.layout.activity_menu_estudiante);


        btnScan = findViewById(R.id.btnScanQR);
        btnScan.setOnClickListener(this);

        // Obtener el Intent que inició esta Activity
        Intent intent = getIntent();
        // Obtener el objeto Estudiante de los extras del Intent
        estudiante = intent.getParcelableExtra("estudiante");
    }

    public void Opciones(View view) {
        Intent i = new Intent(this, Opciones.class);
        i.putExtra("estudiante", estudiante);
        startActivity(i);
    }

    public void Asignatura(View view) {
        Intent ia = new Intent(this, CursoEstudiante.class);
        ia.putExtra("estudiante", estudiante);
        startActivity(ia);
    }

    @Override
    public void onClick(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Lector - CDP");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Lectora Cancelada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                seccion = result.getContents();
                estado = 1;
                // Capturando la fecha actual y convirtiéndola a String
                LocalDate fechaActual = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fechaFormateada = fechaActual.format(formatter);
                Fecha = fechaFormateada;

                verificarSeccion();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void verificarSeccion() {
        request = Volley.newRequestQueue(this);

        String url = Util.RUTA + "buscarseccionalumnocolegio.php?id_estudiante=" + estudiante.getId_seccion();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                (JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String descripcion = response.getString("descripcion");

                    if (descripcion.equals(seccion)) {
                        insertarAsistencia(Fecha, seccion, estado);
                    } else {
                        Toast.makeText(MenuEstudiante.this, "No es tu seccion", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, this);
        request.add(jsonObjectRequest);
    }

    private void insertarAsistencia(String fecha, String seccion, int estado) {
        request = Volley.newRequestQueue(this);

        String url = Util.RUTA + "insertarasistenciacolegio.php?id_estudiante=" + estudiante.getId_estudiante()
                + "&fecha=" + fecha + "&estado=" + estado + "&seccion=" + seccion;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                (JSONObject) null, this, this);
        request.add(jsonObjectRequest);

        Log.e("URL DE LA WEB SERVICES", String.valueOf(jsonObjectRequest));
    }
    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Registro exitoso!", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se ingresaron los datos", Toast.LENGTH_SHORT).show();
    }
}