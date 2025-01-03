package com.example.cartev2;

import android.content.SharedPreferences;
import android.net.IpConfiguration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ApiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_api);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnApi = findViewById(R.id.buttonTrimitereApi);
        EditText orasEt = findViewById(R.id.editTextApi);
        TextView raspunsTv = findViewById(R.id.textViewAPI);

        btnApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeOras = orasEt.getText().toString().trim();
                if(!numeOras.isEmpty()){
                    String apiKey = "SHkyoy8hKRJHpiwLAjoxBPpn2jFdjB05";  // API Key-ul tău
                    String apiUrl = "https://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiKey + "&q=" + numeOras;

                    Executor executor = Executors.newSingleThreadExecutor();
                    Handler handler = new Handler(Looper.myLooper());
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            HttpURLConnection con = null;
                            try{
                                URL url = new URL(apiUrl);
                                con = (HttpURLConnection) url.openConnection();
                                InputStream is = con.getInputStream();
                                InputStreamReader isr = new InputStreamReader(is);
                                BufferedReader br = new BufferedReader(isr);
                                StringBuilder sb = new StringBuilder();
                                String linie;
                                while((linie =br.readLine())!=null){
                                    sb.append(linie);
                                }
                                br.close();
                                JSONArray jsonArray = new JSONArray(sb.toString());
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String cheieOras = jsonObject.getString("Key");

                                String vremeUrl ="https://dataservice.accuweather.com/forecasts/v1/daily/1day/" + cheieOras +"?apikey=" +apiKey;

                                URL urlVreme = new URL(vremeUrl);
                                con = (HttpURLConnection) urlVreme.openConnection();
                                InputStream isVreme = con.getInputStream();
                                InputStreamReader isrVreme = new InputStreamReader(isVreme);
                                BufferedReader brVreme = new BufferedReader(isrVreme);
                                StringBuilder sbVreme = new StringBuilder();
                                while((linie=brVreme.readLine())!=null){
                                    sbVreme.append(linie);
                                }
                                brVreme.close();

                                JSONObject jsonObject1 = new JSONObject(sbVreme.toString());
                                JSONArray jsonArray1 = jsonObject1.getJSONArray("DailyForecasts");
                                JSONObject dailyForecast = jsonArray1.getJSONObject(0);
                                JSONObject temperature = dailyForecast.getJSONObject("Temperature");
                                JSONObject min = temperature.getJSONObject("Minimum");
                                JSONObject max = temperature.getJSONObject("Maximum");

                                handler.post(()->raspunsTv.setText("Cheie Oras: "+ cheieOras+ min + max));
                            }catch (IOException e){
                                e.printStackTrace();
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }
}