package com.example.lloyd.Ukite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.lloyd.Ukite.R.id;
import static com.example.lloyd.Ukite.R.layout;
import static com.example.lloyd.Ukite.R.string;

public class MainActivity extends AppCompatActivity implements LocationListAdapter.OnItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_URL = "imageURL";
    public static final String EXTRA_LOC = "location";
    public static final String EXTRA_SPEED = "speed";
    public static final String EXTRA_DIR = "direction";
    public static final String EXTRA_TEMP = "temperature";
    public static final String EXTRA_URL_WIND = "windURL";
    public static final String EXTRA_URL_SWELL = "swellURL";
    private static final int NUM_LIST_ITEMS = 100;
    private LocationListAdapter mAdapter;
    private ArrayList<LocationItem> mLocationList;
    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;
    private CardView mCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        mRecyclerView = findViewById(id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCardView = findViewById(id.cardView);
        mLocationList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        setupSharedPreferences();
        parseJSON();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setDarkMode(sharedPreferences.getBoolean(getString(string.pref_night_mode_key), false));
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void setDarkMode(boolean b){
        if(b){
            mRecyclerView.setBackgroundColor(Color.BLACK);
        }
        else{
            mRecyclerView.setBackgroundColor(Color.DKGRAY);
        }
    }


    public void openChildActivity(){
        Intent intent = new Intent(this, ChildActivity.class);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if (menuItemThatWasSelected == id.action_settings) {
            Log.i(TAG, "onOptionsItemSelected: settings");

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void parseJSON() {
        String url = "http://www.mocky.io/v2/5ae863142d0000d3077b4816";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("spots");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject spot = jsonArray.getJSONObject(i);

                                String locationName = spot.getString("location");
                                String imageUrl = spot.getString("imageURL");
                                String windUrl = spot.getString("windURL");
                                String swellUrl = spot.getString("swellURL");
                                int speed = spot.getInt("speed");
                                int temp = spot.getInt("temperature");
                                String direction = spot.getString("direction");

                                mLocationList.add(new LocationItem(imageUrl, locationName, speed,direction, temp, windUrl, swellUrl));
                            }

                            mAdapter = new LocationListAdapter(MainActivity.this, mLocationList);
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }


    @Override
    public void OnItemClick(int position) {
        Intent detailIntent = new Intent(this, ChildActivity.class);
        LocationItem clickedLocation = mLocationList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedLocation.getImageUrl());
        detailIntent.putExtra(EXTRA_URL_WIND, clickedLocation.getWindUrl());
        detailIntent.putExtra(EXTRA_URL_SWELL, clickedLocation.getSwellUrl());
        detailIntent.putExtra(EXTRA_SPEED, clickedLocation.getSpeed());
        detailIntent.putExtra(EXTRA_LOC, clickedLocation.getLocation());
        detailIntent.putExtra(EXTRA_DIR, clickedLocation.getDirection());
        detailIntent.putExtra(EXTRA_TEMP, clickedLocation.getTemp());
        startActivity(detailIntent);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(string.pref_night_mode_key))){
            Toast.makeText(this, "Night modus enabled", Toast.LENGTH_LONG).show();
            //setDarkMode(sharedPreferences.getBoolean(getString(string.pref_night_mode_key), false));
        }
    }
}