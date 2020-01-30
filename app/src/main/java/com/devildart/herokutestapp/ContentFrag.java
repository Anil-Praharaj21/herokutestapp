package com.devildart.herokutestapp;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devildart.herokutestapp.pojo.Criterium;
import com.devildart.herokutestapp.pojo.TestDatum;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContentFrag {

    private Context context;
    private TestDatum object;
    private FrameLayout parent;
    private String selected = null;
    private String privous = null;

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
                setCriteria(array.get(i).getText(), (LinearLayout) view.findViewById(R.id.details_layout), i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        parent.addView(view);
    }

    private void setCriteria(String str, LinearLayout parent, final int index) {
        String oldStr = str;
        TextView text = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        SpannableString ss = new SpannableString(str);
        try {
            Gson gson = new Gson();
            if (object.getCriteria().get(index).getVariable() != null) {
                final JSONObject var = new JSONObject(gson.toJson(object.getCriteria().get(index).getVariable()));
                Iterator<String> keysItr = var.keys();
                String key;
                ArrayList<Integer> start = new ArrayList<>();
                ArrayList<Integer> length = new ArrayList<>();
                ArrayList<ClickableSpan> clickableSpanList = new ArrayList<>();
                while (keysItr.hasNext()) {
                    key = keysItr.next();
                    final String finalKey = key;

                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(View textView) {
                            try {
                                privous = ((TextView) textView).getText().toString();
                                if (var.getJSONObject(finalKey).getString("type").equals("value")) {
                                    List<Integer> list = new ArrayList<>();
                                    for (int i = 0; i < var.getJSONObject(finalKey).getJSONArray("values").length(); i++) {
                                        list.add(var.getJSONObject(finalKey).getJSONArray("values").getInt(i));
                                    }
                                    createVarList(list);
                                } else if (var.getJSONObject(finalKey).getString("type").equals("indicator"))
                                    createVarEdit(object.getName(), var.getJSONObject(finalKey).getString("parameter_name"), var.getJSONObject(finalKey).getString("default_value"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    if (str.contains(key)) {
                        if (var.getJSONObject(finalKey).getString("type").equals("value")) {
                            str = str.replace(key, "(" + var.getJSONObject(finalKey).getJSONArray("values").getString(0) + ")");
                            start.add(str.indexOf("(" + var.getJSONObject(finalKey).getJSONArray("values").getString(0) + ")"));
                            length.add(("(" + var.getJSONObject(finalKey).getJSONArray("values").getString(0) + ")").length());
                        } else if (var.getJSONObject(finalKey).getString("type").equals("indicator")) {
                            str = str.replace(key, "(" + var.getJSONObject(finalKey).getString("default_value") + ")");
                            start.add(str.indexOf("(" + var.getJSONObject(finalKey).getString("default_value") + ")"));
                            length.add(("(" + var.getJSONObject(finalKey).getString("default_value") + ")").length());
                        }
                    }
                    clickableSpanList.add(clickableSpan);
                }
                ss = new SpannableString(str);
                for (int i = 0; i < start.size(); i++) {
                    ss.setSpan(clickableSpanList.get(i), start.get(i), start.get(i) + length.get(i), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            text.setLayoutParams(params);
            text.setText(ss);
            text.setTextSize(18f);
            text.setTextColor(Color.WHITE);
            text.setMovementMethod(LinkMovementMethod.getInstance());
            parent.addView(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createVarList(List<Integer> list) {
        if (list != null) {
            parent.removeAllViews();
            LinearLayout layout = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(params);
            parent.addView(layout);
            for (int i = 0; i < list.size(); i++) {
                final View view = LayoutInflater.from(context).inflate(R.layout.variable_value, parent, false);
                ((TextView) view.findViewById(R.id.var_text)).setText(list.get(i) + "");
                view.findViewById(R.id.var_text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selected = ((TextView) view.findViewById(R.id.var_text)).getText().toString();
                        parent.removeAllViews();
                        init();
                    }
                });
                layout.addView(view);
            }
        }
    }

    private void createVarFloatList(List<Float> list) {
        if (list != null) {
            parent.removeAllViews();
            LinearLayout layout = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(params);
            parent.addView(layout);
            for (int i = 0; i < list.size(); i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.variable_value, parent, false);
                ((TextView) view.findViewById(R.id.var_text)).setText(list.get(i) + "");
                layout.addView(view);
            }
        }
    }

    private void createVarEdit(String title, String text, String def) {
        parent.removeAllViews();
        View view = LayoutInflater.from(context).inflate(R.layout.variable_edit, parent, false);
        ((TextView) view.findViewById(R.id.title_template)).setText(title);
        ((TextView) view.findViewById(R.id.parameter_name)).setText(text);
        ((EditText) view.findViewById(R.id.value)).setText(def);
        parent.addView(view);
    }
}
