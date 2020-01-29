package com.devildart.herokutestapp;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
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
                setCriteria(array.get(i).getText(), (LinearLayout) view.findViewById(R.id.details_layout), i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        parent.addView(view);
    }

    private void setCriteria(String str, LinearLayout parent, final int index) {
        TextView text = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        SpannableString ss = new SpannableString(str);
        for (int i = 1; i <= 5; i++) {
            final int finalI = i;
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    switch (finalI) {
                        case 1:
                            createVarList(object.getCriteria().get(index).getVariable().get$1().getValues());
                            break;

                        case 2:
                            createVarList(object.getCriteria().get(index).getVariable().get$2().getValues());
                            break;

                        case 3:
                            createVarFloatList(object.getCriteria().get(index).getVariable().get$3().getValues());
                            break;

                    }
                }
            };
            if (str.contains("$"+i))
                ss.setSpan(clickableSpan, str.indexOf("$"+i), str.indexOf("$"+i) + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        text.setLayoutParams(params);
        text.setText(ss);
        text.setTextSize(18f);
        text.setTextColor(Color.WHITE);
        text.setMovementMethod(LinkMovementMethod.getInstance());
        parent.addView(text);
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
                View view = LayoutInflater.from(context).inflate(R.layout.variable_value, parent, false);
                ((TextView) view.findViewById(R.id.var_text)).setText(list.get(i) + "");
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
}
