package com.hackuci2019.camcam.mememail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class main_menu extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }
    public void onSubscribe(View view){
        Intent goToNewSubscribe = new Intent(this, sub_menu.class);
        startActivity(goToNewSubscribe);
    }

}
