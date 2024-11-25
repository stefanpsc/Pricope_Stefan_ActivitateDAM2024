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

    //https://media.istockphoto.com/id/1326978045/ro/fotografie/copac-singuratic-mare-%C3%AEn-cre%C8%99tere-pe-c%C4%83r%C8%9Bi-antice-ca-un-tablou-%C3%AEn-literatur%C4%83.jpg?s=612x612&w=0&k=20&c=iODnaixf2f3BimS4y68yCwHHMJgX4x8Sawa9QowLY-s=
    //https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdh8lxJpg6F-smtarLJMQSZsFftmICArr7Kg&s
    //https://www.promovareaonline.ro/wp-content/uploads/2019/03/word-image-14.jpeg
    //https://cdn.pixabay.com/photo/2024/03/12/18/25/ai-generated-8629326_640.png
    //https://media.istockphoto.com/id/1087508538/ro/fotografie/carte-deschis%C4%83-cu-peisaj-desenat-manual.jpg?s=612x612&w=0&k=20&c=ggojPjtD6hjjJwEIUqEgFXTauELi6d-78u-5EI16C6w=
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 234 && resultCode == RESULT_OK){
            carte.set(idModificat,data.getParcelableExtra("carte") );
            adapter.notifyDataSetChanged();
        }
    }
}