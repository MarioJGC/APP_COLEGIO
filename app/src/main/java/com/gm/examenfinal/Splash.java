package com.gm.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    TextView logo;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        esperarYcerrar(3000);
    }
    public void esperarYcerrar(int milisegundos) {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                llamarIndex();
            }
        }, milisegundos);
    }

    void llamarIndex(){
        //Intent Implicito
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClassName(this, Ingresar.class.getName());
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }
}