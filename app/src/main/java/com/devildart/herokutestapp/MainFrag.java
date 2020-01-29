package com.devildart.herokutestapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.devildart.herokutestapp.pojo.TestDatum;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainFrag extends Fragment implements ListFrag.OnItemClickInFrag {

    private FrameLayout container;
    private ArrayList<TestDatum> listArray = new ArrayList<>();
    private Boolean onList = true;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.main_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        container = view.findViewById(R.id.frag_container);
        callAPI();
    }

    private void callAPI() {
        new NetworkHandler(new NetworkHandler.Response() {
            @Override
            public void onSucces(String response) {
                try {
                    List<TestDatum> list;
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<TestDatum>>() {}.getType();
                    list = gson.fromJson(response, type);
                    listArray.addAll(list);
                    createList();
                    onList = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).execute();
    }

    private void createList() {
        removePre();
        new ListFrag(getContext(), listArray, container, this);
    }

    @Override
    public void onClick(TestDatum obj) {
        removePre();
        new ContentFrag(getContext(), obj, container);
        onList = false;
    }

    private void removePre() {
        if (container.getChildCount() > 0) {
            container.removeAllViews();
        }
    }

    public Boolean onBackPress() {
        createList();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onList = true;
            }
        }, 30);
        return onList;
    }
}
