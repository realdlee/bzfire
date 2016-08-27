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
    public String getBusinessName() {
        return businessName;
    }

    public Integer getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getAbout() {
        return about;
    }

    public String getLocation() {
        return location;
    }

    Integer id;
    String thumbnailUrl;
    String businessName;
    String location;
    Integer score;
    String about;

    public Contractor() {
    }

    public Contractor(Integer id, String thumbnailUrl, String businessName, Integer score, String location, String about) {
        this.id = id;
        this.thumbnailUrl = thumbnailUrl;
        this.businessName = businessName;
        this.score = score;
        this.location = location;
        this.about = about;
    }

    public Contractor(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getInt("id");
            JSONObject thumbnail = (JSONObject) jsonObject.getJSONArray("thumbnails").get(0);
            this.thumbnailUrl = "http:" + thumbnail.getString("url");
            this.businessName = jsonObject.getString("business_name");
            this.score = jsonObject.getInt("score");
            this.location = jsonObject.getString("display_location");
            this.about = jsonObject.getString("about");
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
