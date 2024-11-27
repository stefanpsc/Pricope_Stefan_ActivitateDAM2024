package com.example.seminar4_1098;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class ListaCarte extends AppCompatActivity {

    private List<Carte> carte = null;

    private int idModificat =0;

    private CarteAdapter adapter = null;


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

        Intent it =getIntent();
        List<Carte> carte = it.getParcelableArrayListExtra("carte");

        ListView lv = findViewById(R.id.lvCarte);

        adapter = new CarteAdapter(carte,getApplicationContext(), R.layout.adapterlayout);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentModifica = new Intent(getApplicationContext(), AdaugaCarteActivity.class);
                intentModifica.putExtra("carte",carte.get(position) );
                idModificat =1;
                startActivityForResult(intentModifica, 234);
                Toast.makeText(getApplicationContext(),carte.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 234 && resultCode == RESULT_OK){
            carte.set(idModificat,data.getParcelableExtra("carte") );
            adapter.notifyDataSetChanged();
        }
    }
}