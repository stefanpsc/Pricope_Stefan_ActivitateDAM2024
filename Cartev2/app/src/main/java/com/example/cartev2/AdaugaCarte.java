package com.example.cartev2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AdaugaCarte extends AppCompatActivity {
    //private List<Carte> carte = null;

    BazaDeDateCarti database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_carte);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        if(it.hasExtra("carte")){

            Carte carte = it.getParcelableExtra("carte");
            EditText numeCarte = findViewById(R.id.numeCarte);
            EditText anCarte = findViewById(R.id.anCarteEt);
            EditText autorCarte = findViewById(R.id.autorCarte);
            CheckBox cititCb = findViewById(R.id.cititaCb);

            numeCarte.setText(carte.getNumeCarte());
            anCarte.setText(carte.getAnCarte());
            autorCarte.setText(carte.getAutorCarte());
            cititCb.setChecked(carte.isCitita());

        }

        Button btnAdauga = findViewById(R.id.button2);
        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText numeCarte = findViewById(R.id.numeCarte);
                EditText anCarte = findViewById(R.id.anCarteEt);
                EditText autorCarte = findViewById(R.id.autorCarte);
                CheckBox cititCb = findViewById(R.id.cititaCb);

                String nume = numeCarte.getText().toString();
                String an = anCarte.getText().toString();
                int anNr = Integer.parseInt(an);
                String autor = autorCarte.getText().toString();
                Boolean citit = cititCb.isChecked();

                Carte carte = new Carte(nume, citit, anNr, autor);
                Toast.makeText(AdaugaCarte.this, carte.toString(), Toast.LENGTH_SHORT).show();

                database = Room.databaseBuilder(getApplicationContext(),BazaDeDateCarti.class, "carti.db").build();

                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            FileOutputStream file = openFileOutput("carte.txt", MODE_APPEND);
                            OutputStreamWriter output = new OutputStreamWriter(file);
                            BufferedWriter writer = new BufferedWriter(output);
                            writer.write(carte.toString());
                            writer.close();
                            output.close();
                            file.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        database.getDaoObject().insert(carte);
                    }
                });
                Intent it = new Intent();
                it.putExtra("carte", carte);
                setResult(RESULT_OK, it);
                finish();

            }
        });

    }


}