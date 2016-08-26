package com.codepath.apps.bzfire.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by lee on 8/26/16.
 */
@Parcel
public class Contractor {
    Integer id;
    String thumbnailUrl;
    String businessName;
    Integer score;

    public Contractor() {

    }

    public Contractor(Integer id, String thumbnailUrl, String businessName, Integer score) {
        this.id = id;
        this.thumbnailUrl = thumbnailUrl;
        this.businessName = businessName;
        this.score = score;
    }

    public Contractor(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getInt("id");
            JSONObject thumbnail = (JSONObject) jsonObject.getJSONArray("thumbnails").get(0);
            this.thumbnailUrl = thumbnail.getString("url");
            this.businessName = jsonObject.getString("business_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Contractor> fromJSONArray(JSONArray array) {
        ArrayList<Contractor> results = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new Contractor(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }
}
