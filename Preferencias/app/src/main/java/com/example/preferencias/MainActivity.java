package com.example.preferencias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button crear=findViewById(R.id.crear);
        Button leer=findViewById(R.id.leer);
        Button path=findViewById(R.id.path);
        Button modificar=findViewById(R.id.modificar);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearArchivo();
            }
        });
        leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
    private void crearArchivo(){
        try {
            File nuevaCarpeta = new File(Environment.getExternalStorageDirectory(), "Carpeta");
            if (!nuevaCarpeta.exists()) {
                nuevaCarpeta.mkdir();
            }
            try {
                File file = new File(nuevaCarpeta, "Archivo" + ".txt");
                file.createNewFile();
            } catch (Exception ex) {
                Log.e("Error", "ex: " + ex);
            }
        } catch (Exception e) {
            Log.e("Error", "e: " + e);
        }
    }
}