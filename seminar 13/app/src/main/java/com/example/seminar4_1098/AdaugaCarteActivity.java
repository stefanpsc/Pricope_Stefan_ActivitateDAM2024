package com.example.seminar4_1098;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AdaugaCarteActivity extends AppCompatActivity {

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

            EditText numeCarteEt = findViewById(R.id.numeCarte);
            EditText autorCarteEt = findViewById(R.id.autorCarte);
            EditText anCarteEt = findViewById(R.id.anCarteEt);
            CheckBox cititaCb = findViewById(R.id.cititaCb);

            numeCarteEt.setText(carte.getNumeCarte());
            autorCarteEt.setText(carte.getAutorCarte());
            anCarteEt.setText(""+carte.getAnCarte());
            cititaCb.setChecked(carte.isCitita());
        }
        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText numeCarteEt = findViewById(R.id.numeCarte);
                String numeCarte = numeCarteEt.getText().toString();

                EditText autorCarteEt = findViewById(R.id.autorCarte);
                String autorCarte = autorCarteEt.getText().toString();

                EditText anCarteEt = findViewById(R.id.anCarteEt);
                String anCarteS = anCarteEt.getText().toString();
                int anCarte = Integer.parseInt(anCarteS);

                CheckBox cititaCb = findViewById(R.id.cititaCb);
                Boolean citita =cititaCb.isChecked();

                CheckBox disponibilOnlineCb = findViewById(R.id.checkBoxDisponibilOnline);
                boolean disponibilOnline = disponibilOnlineCb.isChecked();

                Carte carte = new Carte(numeCarte, autorCarte, anCarte, citita);
                Toast.makeText(AdaugaCarteActivity.this, carte.toString(), Toast.LENGTH_LONG).show();

                database= Room.databaseBuilder(getApplicationContext(),BazaDeDateCarti.class,"carti.db").build();

                //FirebaseDatabase databaseFirebase = FirebaseDatabase.getInstance();
                //DatabaseReference myRef = databaseFirebase.getReference("message");

                //myRef.setValue("Hello, World!");

                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            FileOutputStream file = openFileOutput("obiecteNoi.txt", MODE_APPEND);
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

                        if (disponibilOnline) {
                            FirebaseDatabase databaseFirebase = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = databaseFirebase.getReference("carti");


                            myRef.push().setValue(carte);
                        }
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