package com.codepath.apps.bzfire.models;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.codepath.apps.bzfire.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    SwipeFlingAdapterView flingContainer;
    private ArrayList<String> al = new ArrayList<>();
    ;
    private ArrayAdapter<String> arrayAdapter;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contractorSearch();

        //add the view via xml or programmatically
        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        al.add("php");
        al.add("c");
        al.add("python");
        al.add("java");

        //choose your favorite adapter
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.item, R.id.helloText, al);

        //set the listener and the adapter
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(MainActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "Right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                al.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(MainActivity.this, "Clicked!");
            }
        });

    }

    static void makeToast(Context ctx, String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }
//    sort=buildzoom_score&page=1&radius=metro&keywords=General+Contractors
// &page_size=25&location_id=16261&mile_radius=60&search_type=filtered&location_state=CA

    public void contractorSearch() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://www.buildzoom.com/api/v1/search_contractors/san-diego-ca/general-contractors";
        RequestParams params = new RequestParams();

        params.put("page_size", 1);
        params.put("mile_radius", 60);
        params.put("keywords", "general contractors");
        params.put("location_id", 16261);
        params.put("location_state", "CA");

        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.e(TAG, response.toString());
                try {
                    JSONArray results = response.getJSONObject("results").getJSONArray("contractors");
                    Log.e(TAG, results.toString());
                    Contractor.fromJSONArray(results);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                super.onSuccess(statusCode, headers, response);
            }
        });

    }
}
