package com.example.cartev2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.google.android.material.progressindicator.BaseProgressIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListaCarti extends AppCompatActivity {

    private List<Carte> carte = null;

    private CarteAdapter adapter;

    BazaDeDateCarti database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_carti);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        carte = new ArrayList<>();
        //Intent it = getIntent();
        //List<Carte> carte = it.getParcelableArrayListExtra("carte");

        ListView lv = findViewById(R.id.lvCarte);

        database = Room.databaseBuilder(getApplicationContext(),BazaDeDateCarti.class,"carti.db").build();

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                carte =database.getDaoObject().getAllCarti();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        CarteAdapter adapter = new CarteAdapter(carte, R.layout.adapter_layout,getApplicationContext());
                        //ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,carte);
                        lv.setAdapter(adapter);
                    }
                });
            }

        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                SharedPreferences sp = getSharedPreferences("carte",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(carte.get(position).getKey(), carte.get(position).toString());
                editor.commit();
                return false;
            }
        });

    }
}