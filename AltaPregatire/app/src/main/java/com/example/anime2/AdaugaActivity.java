package com.example.anime2;

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

public class AdaugaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_anime);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it =getIntent();
        if(it.hasExtra("anime")){
            Anime anime =it.getParcelableExtra("anime");

            EditText denumireEt =findViewById(R.id.editTextDenumire);
            EditText anAparitieEt= findViewById(R.id.editTextAnAparitie);
            EditText genreEt = findViewById(R.id.editTextGenre);
            CheckBox cbF = findViewById(R.id.checkBoxFinished);
            EditText erEpEt = findViewById(R.id.editTextNrEpisoade);

            denumireEt.setText(anime.getDenumire());
            anAparitieEt.setText(anime.getAnAparitie());
            genreEt.setText(anime.getGenre());
            cbF.setChecked(anime.isFinished());
            erEpEt.setText(anime.getNrEpisoade());
        }

        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etDenumire = findViewById(R.id.editTextDenumire);
                String denumire = etDenumire.getText().toString();

                EditText etAnAparitie = findViewById(R.id.editTextAnAparitie);
                String sAnAparitie = etAnAparitie.getText().toString();
                int anAparitie = Integer.parseInt(sAnAparitie);

                EditText etGenre = findViewById(R.id.editTextGenre);
                String genre = etGenre.getText().toString();

                CheckBox cbF = findViewById(R.id.checkBoxFinished);
                Boolean finished = cbF.isChecked();

                EditText etNrEp = findViewById(R.id.editTextNrEpisoade);
                String sNrEp = etNrEp.getText().toString();
                int nrEp = Integer.parseInt(sNrEp);

                Anime anime = new Anime(denumire, genre, anAparitie, finished, nrEp);
                Toast.makeText(AdaugaActivity.this, anime.toString(), Toast.LENGTH_SHORT).show();

                Intent it = new Intent();
                it.putExtra("anime", anime);
                setResult(RESULT_OK, it);
                finish();
            }
        });

    }
}