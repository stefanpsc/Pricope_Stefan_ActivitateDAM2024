package com.example.seminar4_1098;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class APIActivity extends AppCompatActivity {

        private EditText editTextAPI;
        private Button buttonTrimitereApI;
        private TextView rezultatText;
        private Spinner spinnerZile;
        private String optiuneSelectata ="1 zi";

//287719
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_apiactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextAPI =findViewById(R.id.editTextAPI);
        buttonTrimitereApI =findViewById(R.id.buttonTrimitereAPI);
        rezultatText =findViewById(R.id.rezultatText);

        spinnerZile = findViewById(R.id.spinnerZile);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.optiuni_zile, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZile.setAdapter(adapter);

        spinnerZile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                optiuneSelectata = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonTrimitereApI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeOras = editTextAPI.getText().toString().trim();
                if(!numeOras.isEmpty()){
                    String apiKey = "SHkyoy8hKRJHpiwLAjoxBPpn2jFdjB05";  // API Key-ul tău
                    String apiUrl = "https://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiKey + "&q=" + numeOras;

                    Executor executor = Executors.newSingleThreadExecutor();
                    Handler handler = new Handler(Looper.myLooper());


                    executor.execute(new Runnable() {
                        @Override
                        public void run() {

                            HttpURLConnection con =null;
                            try{
                                URL url =new URL(apiUrl);
                                con = (HttpURLConnection) url.openConnection();
                                InputStream is =con.getInputStream();
                                InputStreamReader isr = new InputStreamReader(is) ;
                                BufferedReader br = new BufferedReader(isr);

                                StringBuilder sb = new StringBuilder();
                                String linie;
                                while((linie =br.readLine()) !=null){
                                    sb.append(linie);

                                }
                                br.close();
                                JSONArray jsonArray =new JSONArray(sb.toString());
                                if(jsonArray.length() >0){
                                    JSONObject obiect= jsonArray.getJSONObject(0);
                                    String cheieOras = obiect.getString("Key");

                                    String vremeApiUrl;
                                    if(optiuneSelectata.equals("1 zi")){
                                        vremeApiUrl = "https://dataservice.accuweather.com/forecasts/v1/daily/1day/" + cheieOras +"?apikey=" +apiKey;
                                    }else{
                                        vremeApiUrl = "https://dataservice.accuweather.com/forecasts/v1/daily/5day/" + cheieOras +"?apikey=" +apiKey;
                                    }


                                    URL vremeUrl =new URL(vremeApiUrl);
                                    con =(HttpURLConnection) vremeUrl.openConnection();
                                    InputStream vremeis = con.getInputStream();
                                    InputStreamReader vremeisr = new InputStreamReader(vremeis);
                                    BufferedReader vremebr = new BufferedReader(vremeisr);

                                    StringBuilder vremesb = new StringBuilder();
                                    while((linie =vremebr.readLine()) !=null){
                                        vremesb.append(linie);
                                    }
                                    vremebr.close();
                                    JSONObject vremeJson = new JSONObject(vremesb.toString());
                                    JSONArray dailyForecasts = vremeJson.getJSONArray("DailyForecasts");

                                    StringBuilder rezultat = new StringBuilder();
                                    int numarZile = optiuneSelectata.equals("1 zi") ? 1:5;

                                    for(int i=0;i<numarZile;i++) {
                                        JSONObject dailyForecast = dailyForecasts.getJSONObject(0);
                                        JSONObject temperature = dailyForecast.getJSONObject("Temperature");
                                        JSONObject min = temperature.getJSONObject("Minimum");
                                        JSONObject max = temperature.getJSONObject("Maximum");

                                        int minTemp = min.getInt("Value");
                                        int maxTemp = max.getInt("Value");
                                            rezultat.append("Ziua: " + (i +1) +"Temperatura minima: " + minTemp+ "°F\nTemperatura Maxima: " + maxTemp);
                                    }
                                    //handler.post(() -> rezultatText.setText("Cheie Oras: " + cheieOras + "\n"));
                                    handler.post(()-> rezultatText.setText(rezultat.toString()));
                                }else{
                                    handler.post(()-> rezultatText.setText("Orasul nu a fost gasit"));
                                }
                            }
                            catch (MalformedURLException e){
                                e.printStackTrace();
                                handler.post(() -> rezultatText.setText("Eroare la formarea URL-ului."));
                            }catch (IOException e){
                                e.printStackTrace();
                                handler.post(() -> rezultatText.setText("Eroare la conexiune"));
                            }catch(JSONException e){
                                e.printStackTrace();
                                handler.post(() -> rezultatText.setText("Eroare la Parsare"));
                            }
                        }
                    });
                }
            }
        });
    }
}