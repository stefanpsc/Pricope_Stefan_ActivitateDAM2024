package com.example.a1;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf("activitate", "onCreate");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //stiva de activitati


    @Override
    protected void onStart() {
        super.onStart();
        Log.w("activitate", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("activitate", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("activitate", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("activitate", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("activitate", "onDestroy");
    }
}