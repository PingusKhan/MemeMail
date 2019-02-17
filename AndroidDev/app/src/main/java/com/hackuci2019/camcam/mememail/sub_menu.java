package com.hackuci2019.camcam.mememail;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import okhttp3.MediaType;

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

        arrayAdapter_genres = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrayList_genres) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);

                textView.setTextColor(Color.WHITE);

                return view;
            }
        };

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

        JSONObject sub_to_send = new JSONObject(new_sub);
        String url = "http://169.234.88.216:5000/hello_world";
        String charset = "UTF-8";

//        String server_post_response = server_post(url, sub_to_send).toString();

        new ServerPost().execute(sub_to_send);

        startActivity(goToMainMenu);
    }

    private static class ServerPost extends AsyncTask<JSONObject, Void, JSONObject> {
        protected JSONObject doInBackground(JSONObject...json) {
            JSONObject jsonObjectResp = null;
            try {

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                OkHttpClient client = new OkHttpClient();

                okhttp3.RequestBody body = RequestBody.create(JSON, json.toString());
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url("http://169.234.88.216:5000/hello_world")
                        .post(body)
                        .build();

                okhttp3.Response response = client.newCall(request).execute();

                String networkResp = response.body().string();
                if (!networkResp.isEmpty()) {
                    jsonObjectResp = parseJSONStringToJSONObject(networkResp);
                }
            } catch (Exception ex) {
                String err = String.format("{\"result\":\"false\",\"error\":\"%s\"}", ex.getMessage());
                jsonObjectResp = parseJSONStringToJSONObject(err);
            }

            return jsonObjectResp;
        }

            private JSONObject parseJSONStringToJSONObject(final String strr) {

                JSONObject response = null;
                try {
                    response = new JSONObject(strr);
                } catch (Exception ex) {
                    //  Log.e("Could not parse malformed JSON: \"" + json + "\"");
                    try {
                        response = new JSONObject();
                        response.put("result", "failed");
                        response.put("data", strr);
                        response.put("error", ex.getMessage());
                    } catch (Exception exx) {
                    }
                }
                return response;
            }
        }
    }