package com.example.cartev3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaCarte extends AppCompatActivity {

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
            CheckBox citit = findViewById(R.id.cititaCb);

            numeCarte.setText(carte.getNumeCarte());
            anCarte.setText(""+carte.getAnCarte());
            autorCarte.setText(carte.getAutorCarte());
            citit.setChecked(carte.getCitit());
        }

        Button btnAdauga = findViewById(R.id.button2);
        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText numeCarte = findViewById(R.id.numeCarte);
                EditText anCarte = findViewById(R.id.anCarteEt);
                EditText autorCarte = findViewById(R.id.autorCarte);
                CheckBox citit = findViewById(R.id.cititaCb);

                String nume = numeCarte.getText().toString();
                String an = anCarte.getText().toString();
                int anNr = Integer.parseInt(an);
                String autor = autorCarte.getText().toString();
                Boolean citita = citit.isChecked();

                Carte carte = new Carte(nume, anNr, autor, citita);
                Toast.makeText(AdaugaCarte.this, carte.toString(),Toast.LENGTH_SHORT).show();
                Intent it = new Intent();
                it.putExtra("carte", carte);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
}