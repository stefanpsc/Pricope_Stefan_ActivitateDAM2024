package com.example.anime2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ListaAnime extends AppCompatActivity {

    private List<Anime> anime = null;

    private int idModificat = 0;

    private AnimeAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_anime);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        List<Anime> anime = it.getParcelableArrayListExtra("anime");

        ListView lv = findViewById(R.id.listAnime);

        adapter = new AnimeAdapter(anime,R.layout.row_layout, getApplicationContext());
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentModifica = new Intent(getApplicationContext(), AdaugaActivity.class);
                intentModifica.putExtra("anime", anime.get(position));
                idModificat =1;
                startActivityForResult(intentModifica, 234);
                Toast.makeText(getApplicationContext(), anime.get(position).toString(),Toast.LENGTH_SHORT).show();
            }


        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==234 && resultCode == RESULT_OK){
            anime.set(idModificat, data.getParcelableExtra("anime"));
            adapter.notifyDataSetChanged();
        }
    }
}