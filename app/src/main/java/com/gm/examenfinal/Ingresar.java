package com.gm.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import org.json.JSONException;
import org.json.JSONObject;

import pojo.Estudiante;
import pojo.Profesor;
import util.Util;

public class Ingresar extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener, View.OnClickListener {

    EditText edtEmail;
    EditText edtpass;
    Button btnIngresar;
    String tipoUsuario;

    private Estudiante estudiante;
    private Profesor profesor;

    Util util;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        edtEmail = findViewById(R.id.edtEmail);
        edtpass = findViewById(R.id.editPass);
        btnIngresar = findViewById(R.id.btnEntrar);
        btnIngresar.setOnClickListener(this);


        requestQueue = Volley.newRequestQueue(this);

        util = new Util(); // Inicializando el objeto Util
    }



    @Override
    public void onClick(View view) {
        String email = edtEmail.getText().toString();
        String pass = edtpass.getText().toString();
        cargarWebService(email, pass);
    }

    private void cargarWebService(String email, String pass) {
        String url = util.RUTA + "consultarusuariocolegio.php?email=" + email + "&password=" + pass;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
        Log.e("jsonObjectRequest ", jsonObjectRequest.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            if (response.has("usuario")) {
                JSONArray jsonArray = response.getJSONArray("usuario");
                if (jsonArray.length() > 0) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    tipoUsuario = jsonObject.optString("tipo");

                    switch (tipoUsuario) {
                        case "estudiante":
                            estudiante = new Estudiante();
                            estudiante.setId_estudiante(jsonObject.optInt("id_estudiante"));
                            estudiante.setId_seccion(jsonObject.optInt("id_seccion"));
                            estudiante.setNombre(jsonObject.optString("nombre"));
                            estudiante.setEmail(jsonObject.optString("email"));
                            estudiante.setContraseña(jsonObject.optString("contrasena"));
                            estudiante.setTipo(tipoUsuario);
                            Intent intentEstudiante = new Intent(this, MenuEstudiante.class);
                            intentEstudiante.putExtra("estudiante", estudiante);
                            startActivity(intentEstudiante);
                            break;
                        case "profesor":
                            profesor = new Profesor();
                            profesor.setId_profesor(jsonObject.optInt("id_profesor"));
                            profesor.setNombre(jsonObject.optString("nombre"));
                            profesor.setEmail(jsonObject.optString("email"));
                            profesor.setContraseña(jsonObject.optString("contrasena"));
                            profesor.setTipo(tipoUsuario);
                            Intent j = new Intent(this, MenuProfesor.class);
                            j.putExtra("profesor", profesor);
                            startActivity(j);
                            break;
                    }
                } else {
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Respuesta no válida", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error en la respuesta JSON", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("EL ERROR DEL VOLLEY ERROR ES: ", error.toString());
    }

    public void Opciones(View view) {
        Intent intent = new Intent(this, Opciones.class);
        intent.putExtra("estudiante", estudiante);
        startActivity(intent);
    }
}
