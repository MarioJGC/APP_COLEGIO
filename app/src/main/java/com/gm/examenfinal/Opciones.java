package com.gm.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import pojo.Estudiante;
import pojo.Profesor;
import util.Util;

public class Opciones extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Switch switch1;

    private Profesor profesor;
    private Estudiante estudiante;

    JsonObjectRequest jsonObjectRequest;
    RequestQueue requestQueue;

    Util util;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        util = new Util(); // Inicializando el objeto Util
        RadioButton rdbIngles = findViewById(R.id.rdbIngles);
        RadioButton rdbEspanol = findViewById(R.id.rdbEspanol);
        switch1 = findViewById(R.id.switch1);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                recreate();
            }
        });

        rdbEspanol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdbIngles.setChecked(false);
                saveLanguage("es"); // Guardar el idioma seleccionado en las preferencias compartidas
                restartApp(); // Reiniciar la aplicación
            }
        });

        rdbIngles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdbEspanol.setChecked(false);
                saveLanguage("en"); // Guardar el idioma seleccionado en las preferencias compartidas
                restartApp(); // Reiniciar la aplicación
            }
        });


        requestQueue = Volley.newRequestQueue(this);
    }

    private void saveLanguage(String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language", language);
        editor.apply();
    }

    private void restartApp() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        overridePendingTransition(0, 0);
    }



    public void Enviar_Respuesta(View view) {

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        float rating = ratingBar.getRating();
        int calificacion = (int) rating;

        Intent intent = getIntent();

        if (intent.hasExtra("profesor")) {
            profesor = intent.getParcelableExtra("profesor");
            Log.d("El profesor es: ", String.valueOf(profesor));
            cargarWebService(profesor.getNombre(),calificacion);
        }

        if (intent.hasExtra("estudiante")) {

            estudiante = intent.getParcelableExtra("estudiante");
            Log.d("El estudiante es: ", String.valueOf(estudiante));
            String nombre = estudiante.getNombre();
            cargarWebService(nombre,calificacion);
        }
    }

    private void cargarWebService(String nombre, int calificacion) {
        String url = util.RUTA  + "insertarcalificacioncolegio.php?nombre=" + nombre + "&calificacion=" + calificacion;
        Log.d("La ruta es: ", String.valueOf(url));
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, (JSONObject) null, this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Calificacion registrada!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
    }


}