package com.hackuci2019.camcam.mememail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sub_menu extends AppCompatActivity {
    private Map<String, Map> stored_subs;

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

    public void subscribeMenu(View view) {
        String email_to = email_input_to_editText.getText().toString();
        String email_from = email_input_from_editText.getText().toString();
        String hours = interval_hours_editText.getText().toString();
        String minutes = interval_mins_editText.getText().toString();
        Intent goToMainMenu = new Intent(this, main_menu.class);
        Map<String, Object> new_sub = new HashMap<>();

        new_sub.put("email_to", email_to);
        new_sub.put("email_from", email_from);
        new_sub.put("hours", hours);
        new_sub.put("minutes", minutes);
        new_sub.put("genres", arrayList_genres);

        DataHolder.getInstance().setData(new_sub);

        startActivity(goToMainMenu);
    }

}
