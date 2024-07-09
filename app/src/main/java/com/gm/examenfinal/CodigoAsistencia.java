package com.gm.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import pojo.Seccion;
import util.Util;

public class CodigoAsistencia extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener, View.OnClickListener {

    ProgressDialog progressDialog;
    RequestQueue request;

    ImageView QR;
    JsonObjectRequest jsonObjectRequest;
    private Seccion seccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_asistencia);

        QR = findViewById(R.id.imgQr);

        Intent intent = getIntent();

        seccion = intent.getParcelableExtra("seccion");

        request = Volley.newRequestQueue(this);


        cargarWebService();
    }

    private void cargarWebService() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Consultando datos");
        progressDialog.show();
        String url = Util.RUTA + "consultarcodigossecciones.php?id_seccion=" + seccion.getId_seccion();
        url = url.replace(" ", "&20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progressDialog.hide();
        Toast.makeText(this, "Error al consultar" + error, Toast.LENGTH_SHORT).show();
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

                //Convertimos la imagen en un arreglo bitmap
                String qrBase64 = jsonObject.optString("qr");
                byte[] qrBytes = Base64.decode(qrBase64, Base64.DEFAULT);
                Bitmap qrBitmap = BitmapFactory.decodeByteArray(qrBytes, 0, qrBytes.length);
                seccion.setQr(qrBitmap);


                QR.setImageBitmap(qrBitmap);

            }

        } catch (Exception e) {
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