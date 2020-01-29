package com.devildart.herokutestapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devildart.herokutestapp.R;
import com.devildart.herokutestapp.pojo.Criterium;
import com.devildart.herokutestapp.pojo.TestDatum;

import java.util.ArrayList;
import java.util.List;

public class ContentFrag {

    private Context context;
    private TestDatum object;
    private FrameLayout parent;

    public ContentFrag(Context context, TestDatum object, FrameLayout parent) {
        this.context = context;
        this.object = object;
        this.parent = parent;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.content_frag, parent, false);
        try {
            TextView subText = (TextView) view.findViewById(R.id.sub);
            ((TextView) view.findViewById(R.id.title)).setText(object.getName());
            subText.setText(object.getTag());
            switch (object.getColor()) {
                case "green":
                    subText.setTextColor(Color.GREEN);
                    break;

                case "red":
                    subText.setTextColor(Color.RED);
                    break;

                default:
                    subText.setTextColor(Color.YELLOW);
            }
            List<Criterium> array = object.getCriteria();
            int childCount = ((LinearLayout) view.findViewById(R.id.details_layout)).getChildCount();
            if (childCount > 2) {
                for (int i = 2; i < childCount; i++) {
                    ((LinearLayout) view.findViewById(R.id.details_layout)).removeViewAt(2);
                }
            }
            for (int i = 0; i < array.size(); i++) {
                setCriteria(array.get(i).getText(), (LinearLayout) view.findViewById(R.id.details_layout));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        parent.addView(view);
    }

    private void setCriteria(String str, LinearLayout parent) {
        TextView text = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        text.setLayoutParams(params);
        text.setText(str);
        text.setTextSize(18f);
        text.setTextColor(Color.WHITE);
        parent.addView(text);
    }
}
