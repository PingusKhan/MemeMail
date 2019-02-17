package com.hackuci2019.camcam.mememail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class sub_menu extends AppCompatActivity {
    private ListView listView_genres;
    private ArrayList<String> arrayList_genres;
    private ArrayAdapter<String> arrayAdapter_genres;
    private EditText genre_input_editText;
    private EditText email_input_from_editText;
    private EditText email_input_to_editText;
    private EditText interval_hours_editText;
    private EditText interval_mins_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_menu);

        //HERE IS WHERE THE VARIABLES ARE ASSIGNED TO VIEW IDS
        genre_input_editText = findViewById(R.id.genre_input_editText);
        email_input_from_editText = findViewById(R.id.email_input_from_editText);
        email_input_to_editText = findViewById(R.id.email_input_to_editText);
        interval_hours_editText = findViewById(R.id.interval_hours_editText);
        interval_mins_editText = findViewById(R.id.interval_mins_editText);
        listView_genres = findViewById(R.id.genres_listview);
        //-------------------------------------------------------------

        arrayList_genres = new ArrayList<>();

        arrayAdapter_genres = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arrayList_genres);

        listView_genres.setAdapter(arrayAdapter_genres);
    }

    public void addGenre(View view) {
        String genre_to_add = genre_input_editText.getText().toString();
        if (!genre_to_add.equals("")) {
            arrayAdapter_genres.add(genre_to_add);
            genre_input_editText.setText("");
        }
    }

    public void subscribeMenu(MenuItem menuItem) {
        String email_to = email_input_to_editText.getText().toString();
        String email_from = email_input_from_editText.getText().toString();
        //arrayList_genres
        String hours = interval_hours_editText.getText().toString();
        String minutes = interval_mins_editText.getText().toString();

        clear();
        Intent goToMainMenu = new Intent(this, main_menu.class);
        startActivity(goToMainMenu);
    }

    public void clear() {
        genre_input_editText.setText("");
        email_input_from_editText.setText("");
        email_input_to_editText.setText("");
        interval_hours_editText.setText("");
        interval_mins_editText.setText("");
        arrayList_genres.clear();

    }
}
