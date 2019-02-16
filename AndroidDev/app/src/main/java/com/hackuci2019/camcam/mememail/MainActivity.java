package com.hackuci2019.camcam.mememail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onclick_clicky(View view){
        if(count % 2 == 0) {
            TextView DeathButton = findViewById(R.id.DeathButton);
            DeathButton.setText("Hello Life!");
            count += 1;
        }
        else{
            TextView DeathButton = findViewById(R.id.DeathButton);
            DeathButton.setText("Hello DEATH!");
            count += 1;
        }
    }

}
