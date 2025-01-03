package com.example.cartev2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Carte> listaCarti = new ArrayList<Carte>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button adaugaBtn = findViewById(R.id.btnAdauga);
        adaugaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AdaugaCarte.class);
                startActivityForResult(it, 123);
            }
        });

        Button btnLista = findViewById(R.id.button4);
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ListaCarti.class);
                it.putParcelableArrayListExtra("carte",(ArrayList<? extends Parcelable>) listaCarti);
                startActivity(it);
            }
        });

        Button btnImagini = findViewById(R.id.buttonImagini);
        btnImagini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ListaImagini.class);
                startActivity(it);
            }
        });

        Button btnApi = findViewById(R.id.buttonApi);
        btnApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ApiActivity.class);
                startActivity(it);
            }
        });

         Button btnShared = findViewById(R.id.buttonShared);
         btnShared.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent it = new Intent(getApplicationContext(), SharedPreferencesActivity.class);
                 startActivity(it);
             }
         });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==123 && resultCode ==RESULT_OK){
            Carte carteNoua = data.getParcelableExtra("carte");
            listaCarti.add(carteNoua);
        }
    }


}