package com.devildart.herokutestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devildart.herokutestapp.ListAdapter;
import com.devildart.herokutestapp.R;
import com.devildart.herokutestapp.pojo.TestDatum;

import java.util.ArrayList;

public class ListFrag implements ListAdapter.OnItemClickListener {

    private Context context;
    private ArrayList<TestDatum> list;
    private FrameLayout parent;
    private RecyclerView listView;
    private ListAdapter adapter;
    private OnItemClickInFrag clickInFrag;

    public ListFrag(Context context, ArrayList<TestDatum> list, FrameLayout parent, OnItemClickInFrag clickInFrag) {
        this.context = context;
        this.list = list;
        this.parent = parent;
        this.clickInFrag = clickInFrag;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.list_frag, parent, false);
        listView = view.findViewById(R.id.recycler_view);
        listView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ListAdapter(list, context, this);
        listView.setAdapter(adapter);
        parent.addView(view);
    }

    @Override
    public void onClick(TestDatum object) {
        clickInFrag.onClick(object);
    }

    interface OnItemClickInFrag {
        void onClick(TestDatum obj);
    }
}
