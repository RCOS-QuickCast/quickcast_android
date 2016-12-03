package com.rcos.quickcast.dota2;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcos.quickcast.DrilldownElement;
import com.rcos.quickcast.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Dan on 10/25/2016.
 */

public class TowerElement extends DrilldownElement {
    private String radiantName;
    private String direName;
    private ArrayList<String> radiantTowers;
    private ArrayList<String> direTowers;

    public TowerElement(JSONObject details) throws JSONException {
        mSport = "DOTA2";
        JSONObject radiant  = (JSONObject) details.get("radiant_team");
        JSONObject dire     = (JSONObject) details.get("dire_team");

        radiantName = radiant.getString("team_name");
        direName    = dire.getString("team_name");

        for(int i=0; i<2; i++) {
            //JSONObject tower = (JSONObject) towers.get(j); <==something like this?
            //get tower status info, if 0 then radiant, if 1 then dire
        }

        Log.d("QUICKCAST!!!!", "constructed tower element");
    }

    public View getView(Context context, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View element = inflater.inflate(R.layout.dota2_team_element, parent, false);

        TextView tableRadiant = (TextView) element.findViewById(R.id.tableRadiant);
        tableRadiant.setText(String.format("Radiant: %s", radiantName));

        TextView tableDire = (TextView) element.findViewById(R.id.tableDire);
        tableDire.setText(String.format("Dire: %s", direName));

        //add tower stuff
        //tower element is essentially checking with bools which towers are down and coloring those red.
        //its very basic, but it gives the necessary information and is very readable.

        return element;
    }

    TowerElement(Parcel in){
        //...
    }


    public static final Parcelable.Creator<TowerElement> CREATOR = new Parcelable.Creator<TowerElement>() {
        public TowerElement createFromParcel(Parcel in) {
            return new TowerElement(in);
        }
        public TowerElement[] newArray(int size) {
            return new TowerElement[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        //...
    }

    public void writeToParcel(Parcel dest, int flags) {
        //...
    }
}
