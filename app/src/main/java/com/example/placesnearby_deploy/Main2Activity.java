package com.example.placesnearby_deploy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    String new_json;
    JSONObject places;
    String p;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        extras=intent.getExtras();
        String restaurant_name = extras.getString("RESTAURANT NAME");
        String address = extras.getString("ADDRESS");
        String location = extras.getString("LOCATION");
        final String longitude = extras.getString("LONGITUDE");
        final String latitude = extras.getString("LATITUDE");
        String cuisines = extras.getString("CUISINES");
        String cost = extras.getString("AVERAGE COST FOR 2   ");
        String booking = extras.getString("DINEBOOKING REQUIRED");
        String rating = extras.getString("RATING");
        String sep = extras.getString("seperation");
        String color = extras.getString("COLOR ");

        TextView rname = (TextView)findViewById(R.id.res_names);
        TextView cui = (TextView)findViewById(R.id.cui);
        TextView rate = (TextView)findViewById(R.id.ratings);
        TextView fortwo = (TextView)findViewById(R.id.costs);
        TextView addr = (TextView)findViewById(R.id.add);


        rname.setText(restaurant_name);
        cui.setText(cuisines);
        rate.setText(rating);
        fortwo.setText(cost);
        addr.setText(address);

        Button b1 = (Button) findViewById(R.id.angry_btn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Main2Activity.this,NavigationActivity.class);
                intent1.putExtra("longitude",longitude);
                intent1.putExtra("latitude",latitude);
                startActivity(intent1);
            }
        });


        /*p=intent.getExtras().getString("position");
        System.out.println(p);
        /*new_json=intent.getExtras().getString("jsonObject");*/
        /*System.out.println(new_json);*/
        /*System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");*/
        /*if (new_json != null) {
            try {

                System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiibefore");
                places = new JSONObject(p);

                System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiafter");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/

       /* System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiieveyr");
        System.out.println(places);*/

    }
}
