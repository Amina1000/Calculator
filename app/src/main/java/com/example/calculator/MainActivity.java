package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    public static final String TAG ="@@@Calculator";
    public static final String INCREMENT_KEY = "increment_key";
    private int increment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate");

        if (savedInstanceState!= null && savedInstanceState.containsKey(INCREMENT_KEY)){
            increment = savedInstanceState.getInt(INCREMENT_KEY);
        }else{
            increment =0;
        }

        ProgressBar progressBar = findViewById(R.id.progressBar1);
        Intent intent = new Intent(this, Calculator.class);
        for (int i = 0; i < 10; i++) {
            increment = i;
            progressBar.setProgress(increment);
            if (increment == 9) {
                intent.putExtra(INCREMENT_KEY,increment);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(INCREMENT_KEY,increment);
        super.onSaveInstanceState(outState);
    }
}