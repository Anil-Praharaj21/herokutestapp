package com.devildart.herokutestapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private ArrayList<JSONObject> data;
    private Context context;
    private OnItemClickListener clickListener;

    public ListAdapter(ArrayList<JSONObject> data, Context context, OnItemClickListener clickListener) {
        this.data = data;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        try {
            final JSONObject object = data.get(position);
            if (object.has("name")) {
                holder.title.setText(object.getString("name"));
            }
            if (object.has("tag") && object.has("color")) {
                holder.subText.setText(object.getString("tag"));
                switch (object.getString("color")) {
                    case "green":
                        holder.subText.setTextColor(Color.GREEN);
                        break;

                    case "red":
                        holder.subText.setTextColor(Color.RED);
                        break;

                        default:
                            holder.subText.setTextColor(Color.YELLOW);
                }
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(object);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subText;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_text);
            subText = itemView.findViewById(R.id.sub_text);
        }
    }

    interface OnItemClickListener {
        void onClick(JSONObject object);
    }
}
