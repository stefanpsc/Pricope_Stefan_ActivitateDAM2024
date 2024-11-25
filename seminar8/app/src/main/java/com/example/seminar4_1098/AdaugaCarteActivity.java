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

public class AdaugaCarteActivity extends AppCompatActivity {

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
            anCarteEt.setText(carte.getAnCarte());
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

                Carte carte = new Carte(numeCarte, autorCarte, anCarte, citita);
                Toast.makeText(AdaugaCarteActivity.this, carte.toString(), Toast.LENGTH_LONG).show();

                Intent it = new Intent();
                it.putExtra("carte", carte);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }

    }