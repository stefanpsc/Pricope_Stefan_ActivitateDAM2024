package com.example.seminar4_1098;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class ListaImagini extends AppCompatActivity {

    private List<Bitmap> imagini =null;

    private List<ImaginiDomeniu>  lista =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imagini = new ArrayList<>();
        List<String>linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://media.istockphoto.com/id/1326978045/ro/fotografie/copac-singuratic-mare-%C3%AEn-cre%C8%99tere-pe-c%C4%83r%C8%9Bi-antice-ca-un-tablou-%C3%AEn-literatur%C4%83.jpg?s=612x612&w=0&k=20&c=iODnaixf2f3BimS4y68yCwHHMJgX4x8Sawa9QowLY-s=");
        linkuriImagini.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdh8lxJpg6F-smtarLJMQSZsFftmICArr7Kg&s");
        linkuriImagini.add("https://www.promovareaonline.ro/wp-content/uploads/2019/03/word-image-14.jpeg");
        linkuriImagini.add("https://cdn.pixabay.com/photo/2024/03/12/18/25/ai-generated-8629326_640.png");
        linkuriImagini.add("https://media.istockphoto.com/id/1087508538/ro/fotografie/carte-deschis%C4%83-cu-peisaj-desenat-manual.jpg?s=612x612&w=0&k=20&c=ggojPjtD6hjjJwEIUqEgFXTauELi6d-78u-5EI16C6w=");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(String link:linkuriImagini){
                    HttpURLConnection con =null;
                    try{
                        URL url =new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    }
                    catch (MalformedURLException e){
                        throw new RuntimeException(e);
                    }catch (IOException e){
                        throw  new RuntimeException();
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lista = new ArrayList<>();
                        lista.add(new ImaginiDomeniu("Scarile Levantului: ","https://www.libris.ro/top-10-216-scarile-levantului-amin-maalouf-pol978-973-46-4695-1.html?srsltid=AfmBOooWd7zKR5iOLYesNxjH9welAjReOjSotS2EoL-olb30tRXhfFn5", imagini.get(0)));
                        lista.add(new ImaginiDomeniu("Puterea Prezentului:", " https://www.elefant.ro/puterea-prezentului-editia-a-vi-a_106c2d35-75f8-415a-bda9-a3b66aa23c1c?gad_source=1&gclid=CjwKCAiA3ZC6BhBaEiwAeqfvypsyUJzhcu9zHnUA_SiNPuLx5RUGFW-ngxP_KH1NXDZg1OYVSQgd5xoCgWoQAvD_BwE", imagini.get(1)));
                        lista.add(new ImaginiDomeniu("Regele, Inteleptul si Bufonul: ", "https://nemira.ro/regele-inteleptul-si-bufonul",imagini.get(2)));
                        lista.add(new ImaginiDomeniu("Cimitirul din Praga: ", "https://polirom.ro/esential/6624-cimitirul-din-praga.html", imagini.get(3)));
                        lista.add(new ImaginiDomeniu("Decameronul: ", "https://ro.wikipedia.org/wiki/Decameronul",imagini.get(4)));

                    }
                });

                ListView lv = findViewById(R.id.imagini);
                ImagineAdapter adapter = new ImagineAdapter(lista, getApplicationContext(),R.layout.imagine_layout);
                lv.setAdapter(adapter);
            }
        });

        ListView lv = findViewById(R.id.imagini);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                it.putExtra("link", lista.get(position).getLink());
                startActivity(it);

            }
        });
    }
}