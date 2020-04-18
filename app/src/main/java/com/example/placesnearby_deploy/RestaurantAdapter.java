package com.example.placesnearby_deploy;
import java.util.Iterator;
import java.util.StringTokenizer;
import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class RestaurantAdapter extends ArrayAdapter {

    private final Activity context;
    private JSONObject places[];
    public RestaurantAdapter(Activity context,JSONObject places[]){
        super(context,R.layout.activity_main,places);
        this.context = context;
        this.places = places;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.res_layout,null,true);
        TextView name = (TextView)rowView.findViewById(R.id.res_name);
        TextView cui = (TextView)rowView.findViewById(R.id.cuisine);
        TextView rate = (TextView)rowView.findViewById(R.id.rating);
        TextView cost = (TextView)rowView.findViewById(R.id.price);
        /*ImageView pic = (ImageView)rowView.findViewById(R.id.res_img);*/

        try {
            String s1= new String();
            String s2= new String();
            int i=1;
            StringTokenizer st = new StringTokenizer(places[position].getString("CUISINES"),",");
            while(st.hasMoreElements())
            {
                if(i==1)
                s1=st.nextToken();
                if(i==2) {
                    s2 = st.nextToken();
                    break;
                }
                i++;
            }
            name.setText(places[position].getString("RESTAURANT NAME"));
            cui.setText(s1+","+s2);
            rate.setText(places[position].getString("RATING"));
            System.out.println(places);
            cost.setText(places[position].getString("AVERAGE COST FOR 2   "));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //pic.setImageResource(R.mipmap.pic);
        return rowView;
    }
}