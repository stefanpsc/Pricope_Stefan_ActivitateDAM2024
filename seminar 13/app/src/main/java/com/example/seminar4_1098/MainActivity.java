package com.example.seminar4_1098;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Carte> listaCarti = new ArrayList<Carte>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        listaCarti = new ArrayList<>();


        FirebaseDatabase databaseFirebase = FirebaseDatabase.getInstance();
        DatabaseReference cartiRef = databaseFirebase.getReference("carti");


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), AdaugaCarteActivity.class);
                startActivityForResult(it, 123);
            }
        });

        Button btnListaCarte = findViewById(R.id.buttonlistaCarte);
        btnListaCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ListaCarte.class);
                it.putParcelableArrayListExtra("carte", (ArrayList<? extends Parcelable>) listaCarti);
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

        Button btnAPI = findViewById(R.id.buttonAPI);
        btnAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), APIActivity.class);
                startActivity(it);
            }
        });

        Button btnFireBase = findViewById(R.id.buttonFireBase);
        btnFireBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), FireBaseListActivity.class);
                startActivity(it);
            }
        });

        Button btnSP = findViewById(R.id.button3);
        btnSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), SharedPreferencesActivity.class);
                startActivity(it);
            }
        });

        cartiRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                Toast.makeText(MainActivity.this, "Baza de date a fost actualizată!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity.this, "Eroare la citirea din baza de date: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123 && resultCode == RESULT_OK & data != null) {
            Carte carteNoua = data.getParcelableExtra("carte");
            listaCarti.add(carteNoua);

        }


    }
}


