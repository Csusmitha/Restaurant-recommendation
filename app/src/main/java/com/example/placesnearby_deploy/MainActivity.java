package com.example.placesnearby_deploy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG= MainActivity.class.getName();
    private JSONArray jArr;
    JSONObject places[];
    private RequestQueue mRequestQueue;
    private StringRequest jsonObjectRequest;
    private JSONArray jSONArray;
    ListView lv;
    RestaurantAdapter adt;

    private String url = "https://su-res.herokuapp.com/placesnearby";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.rest);
        sendRequestAndPrintResponse();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                /*intent.putExtra("position",lv.getItemAtPosition(position).toString());
                intent.putExtra("jsonObject",places.toString());*/


                Bundle extras = new Bundle();
                try {
                    extras.putString("RESTAURANT NAME",places[position].getString("RESTAURANT NAME"));
                    extras.putString("ADDRESS",places[position].getString("ADDRESS"));
                    extras.putString("LOCATION",places[position].getString("LOCATION"));
                    extras.putString("LONGITUDE",places[position].getString("LONGITUDE"));
                    extras.putString("LATITUDE",places[position].getString("LATITUDE"));
                    extras.putString("CUISINES",places[position].getString("CUISINES"));
                    extras.putString("AVERAGE COST FOR 2   ",places[position].getString("AVERAGE COST FOR 2   "));
                    extras.putString("DINEBOOKING REQUIRED",places[position].getString("DINEBOOKING REQUIRED"));
                    extras.putString("RATING",places[position].getString("RATING"));
                    extras.putString("seperation",places[position].getString("seperation"));
                    extras.putString("COLOR ",places[position].getString("COLOR "));

                    intent.putExtras(extras);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void sendRequestAndPrintResponse() {
        mRequestQueue = Volley.newRequestQueue(this);

        jsonObjectRequest = new StringRequest(Request.Method.GET, url,new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            jArr = new JSONArray(response.toString());
                            places = new JSONObject[jArr.length()];
                            for (int count = 0; count < jArr.length(); count++) {
                                places[count] = jArr.getJSONObject(count);
                                System.out.println("name"+places[count].getString("RESTAURANT NAME"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println(places);
                        adt = new RestaurantAdapter(MainActivity.this,places);
                        lv.setAdapter(adt);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i(TAG,"Error : " + error.toString());

                    }
                });

        mRequestQueue.add(jsonObjectRequest);

    }
}

