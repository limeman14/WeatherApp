package com.gurenko.superweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class LoadingActivity extends AppCompatActivity {

    private static final int DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ProgressBar progressBar = new ProgressBar(LoadingActivity.this);
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.VISIBLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        //Showing loading screen for DELAY/1000 seconds
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent goToMainLayout = new Intent(LoadingActivity.this, WeatherActivity.class);
                startActivity(goToMainLayout);
                finish();
            }
        }, DELAY);



    }
}