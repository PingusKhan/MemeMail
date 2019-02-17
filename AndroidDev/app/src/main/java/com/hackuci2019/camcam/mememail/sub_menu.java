package com.hackuci2019.camcam.mememail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class sub_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_menu);
    }
    protected void addGenre(View view) {
        String genre_to_add = findViewById(R.id.genre_input_editText).toString();

    }
}
