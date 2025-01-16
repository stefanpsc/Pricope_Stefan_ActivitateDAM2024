package com.example.seminar4_1098;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBaseListActivity extends AppCompatActivity {

    private ListView listViewFavorites;
    private ArrayAdapter<String> adapter;
    private List<String> favoriteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fire_base_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listViewFavorites = findViewById(R.id.lvFireBase);
        favoriteList = new ArrayList<>();


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, favoriteList);
        listViewFavorites.setAdapter(adapter);


        FirebaseDatabase databaseFirebase = FirebaseDatabase.getInstance();
        DatabaseReference cartiRef = databaseFirebase.getReference("carti");


        cartiRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                favoriteList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Carte carte = data.getValue(Carte.class);
                    if (carte != null && carte.isCitita()) {
                        favoriteList.add(carte.getNumeCarte() + " - " + carte.getAutorCarte());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(FireBaseListActivity.this, "Eroare la citirea din Firebase: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}