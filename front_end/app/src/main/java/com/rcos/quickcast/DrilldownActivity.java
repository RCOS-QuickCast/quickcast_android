package com.rcos.quickcast;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rcos.quickcast.dota2.TeamElement;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DrilldownActivity extends ListActivity {

    private String mSport;
    private String mMatchID;
    private JSONObject mJSONData;
    private String mRequestURL;
    private DrilldownProvider mDrilldownProvider;
    private ArrayList<DrilldownElement> mElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drilldown);
        Intent intent = getIntent();
        Bundle args = intent.getExtras();

        mRequestURL = "http://quickcast.farkinator.c9users.io";
        mSport = args.getString("sport");
        mMatchID = args.getString("matchid");
        mJSONData = null;
        makeRequest();

        TextView tagID = (TextView) findViewById(R.id.tagID);
        tagID.setText(String.format("Match ID: %s", mMatchID));

        mElements = new ArrayList<>();
        mDrilldownProvider = new DrilldownProvider(this, mElements);
        setListAdapter(mDrilldownProvider);

    }

    private void makeRequest() {
        RequestQueue mRequestQueue;
        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);
        // Start the queue
        mRequestQueue.start();

        Log.d(" QUICKCAST!!!!", "making request ...");
		JsonObjectRequest request = new JsonObjectRequest
		(String.format("%s/%s/live/%s", mRequestURL, "dota", mMatchID), null, new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
                Log.d(" QUICKCAST!!!!", "got response??????");
                mJSONData = response;
                try {
                    setDrilldownElementArray(mSport);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("QUICKAST!!!!", "elements " + mElements.size());
                mDrilldownProvider.notifyDataSetChanged();
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.d(" QUICKCAST!!!!", "Drilldown, Bad response from makeRequest.");
            }
		});
        Log.d(" QUICKCAST!!!!", "finished making request to " + String.format("%s/%s/live/%s", mRequestURL, "dota", mMatchID));
        mRequestQueue.add(request);
    }

    public void setDrilldownElementArray(String sport) throws JSONException{
        mElements.clear();
        switch (sport) {
            case "DOTA2":
                Log.d("QUICKCAST!!!!", "making dota list");
                mElements.add(new TeamElement(mJSONData));
                break;
            default:
        }
    }

}