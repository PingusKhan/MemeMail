package com.hackuci2019.camcam.mememail;

import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class DataHolder {
    private Map<String, Map> data;

    public Map<String, Map> getData() {
        return data;
    }

    public void setData(Map<String, Object> new_sub) {
        String email_key = new_sub.get("email_to").toString();
        if (data == null) {
            data = new HashMap<>();
            data.put(email_key, new_sub);
        }
        else {
            if (data.containsKey(email_key)) {
                data.remove(email_key);
                data.put(email_key, new_sub);
            }
            else {
                data.put(email_key, new_sub);
            }
        }
    }

    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance() {
        return holder;
    }
}
