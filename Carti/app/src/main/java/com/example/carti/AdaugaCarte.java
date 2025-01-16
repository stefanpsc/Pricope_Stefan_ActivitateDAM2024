package com.example.carti;

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

            EditText denumireEt = findViewById(R.id.editTextDenumire);
            EditText autorEt = findViewById(R.id.editTextAutor);
            EditText anEt = findViewById(R.id.anCarteEt);

            CheckBox cititCb = findViewById(R.id.cititaCb);

            denumireEt.setText(carte.getNumeCarte());
            autorEt.setText(carte.getAutorCarte());
            anEt.setText(carte.getAnCarte());
            cititCb.setChecked(carte.isCitit());


        }

        Button btnAdauga = findViewById(R.id.button2);
        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText denumireEt = findViewById(R.id.editTextDenumire);
                String denumire = denumireEt.getText().toString();

                EditText autorEt = findViewById(R.id.editTextAutor);
                String autor = autorEt.getText().toString();

                EditText anEt = findViewById(R.id.anCarteEt);
                String anS =anEt.getText().toString();
                int an = Integer.parseInt(anS);

                CheckBox cititCb = findViewById(R.id.cititaCb);
                Boolean citit = cititCb.isChecked();

                Carte carte = new Carte(denumire, an, autor, citit);
                Toast.makeText(AdaugaCarte.this,carte.toString(),Toast.LENGTH_LONG).show();

                Intent it = new Intent();
                it.putExtra("carte", carte);
                setResult(RESULT_OK, it);
                finish();

            }
        });
    }
}