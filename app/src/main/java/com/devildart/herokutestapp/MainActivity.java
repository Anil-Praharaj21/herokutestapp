package com.devildart.herokutestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListAdapter.OnItemClickListener {

    private RecyclerView listView;
    private ArrayList<JSONObject> listArray;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.recycler_view);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listArray = new ArrayList<JSONObject>();
        adapter = new ListAdapter(listArray, this, this);
        listView.setAdapter(adapter);
        callAPI();
    }

    private void callAPI() {
        new NetworkHandler(new NetworkHandler.Response() {
            @Override
            public void onSucces(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        listArray.add(array.getJSONObject(i));
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).execute();
    }

    @Override
    public void onClick(JSONObject object) {
        try {
            findViewById(R.id.details_layout).setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            TextView subText = (TextView) findViewById(R.id.sub);
            if (object.has("name")) {
                ((TextView) findViewById(R.id.title)).setText(object.getString("name"));
            }
            if (object.has("tag") && object.has("color")) {
                subText.setText(object.getString("tag"));
                switch (object.getString("color")) {
                    case "green":
                        subText.setTextColor(Color.GREEN);
                        break;

                    case "red":
                        subText.setTextColor(Color.RED);
                        break;

                    default:
                        subText.setTextColor(Color.YELLOW);
                }
            }
            if (object.has("criteria")) {
                JSONArray array = object.getJSONArray("criteria");
                for (int i = 0; i < array.length(); i++) {
                    if (array.getJSONObject(i).has("text"))
                        setCriteria(array.getJSONObject(i).getString("text"), (LinearLayout) findViewById(R.id.details_layout));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCriteria(String str, LinearLayout parent) {
        TextView text = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        text.setLayoutParams(params);
        text.setText(str);
        text.setTextSize(18f);
        text.setTextColor(Color.WHITE);
        parent.addView(text);
    }

    @Override
    public void onBackPressed() {
        if (listView.getVisibility() == View.VISIBLE)
            super.onBackPressed();
        else {
            findViewById(R.id.details_layout).setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }

    }
}
