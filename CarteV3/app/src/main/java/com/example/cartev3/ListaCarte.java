package com.example.cartev3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ListaCarte extends AppCompatActivity {

    private List<Carte > carte = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_carte);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        carte = new ArrayList<>();
        Intent it = getIntent();
        List<Carte> carte = it.getParcelableArrayListExtra("carte");
        ListView lv = findViewById(R.id.lvCarti);
        ArrayAdapter adapter= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, carte);
        lv.setAdapter(adapter);
    }
}