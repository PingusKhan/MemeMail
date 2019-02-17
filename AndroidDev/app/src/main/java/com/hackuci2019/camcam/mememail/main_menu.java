package com.hackuci2019.camcam.mememail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class main_menu extends AppCompatActivity {
    private ListView listView_subs;
    public JSONObject subscriber_json;
    ArrayList<String> arrayList_subscribers;
    ArrayAdapter<String> arrayAdapter_subscribers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
       listView_subs = findViewById(R.id.subscribers_listView);


        Map<String, Map> data = DataHolder.getInstance().getData();
        if (!(data == null)) {
            arrayList_subscribers = new ArrayList<>();
            arrayAdapter_subscribers = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1);
            listView_subs.setAdapter(arrayAdapter_subscribers);

            Iterator<String> email_keys = data.keySet().iterator();
            while (email_keys.hasNext()) {
                Map<String, Object> sub_data = data.get(email_keys.next());
                arrayAdapter_subscribers.add(sub_data.get("email_to") + "\nInterval: "
                        + sub_data.get("hours") + " hrs, " + sub_data.get("minutes") + " minutes\n"
                        + "Genres: " + sub_data.get("genres").toString());
            }
        }
    }

    public void onSubscribe(View view){
        Intent goToNewSubscribe = new Intent(this, sub_menu.class);
        startActivity(goToNewSubscribe);
    }

}
