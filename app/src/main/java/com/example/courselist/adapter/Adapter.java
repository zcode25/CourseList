package com.example.courselist.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.courselist.R;
import com.example.courselist.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> lists;

    public Adapter(Activity activity, List<Data> lists) {
        this.activity = activity;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null && inflater != null) {
            view = inflater.inflate(R.layout.list_student, null);
        }
        if (view != null) {
            TextView nim = view.findViewById(R.id.text_nim);
            TextView name = view.findViewById(R.id.text_name);
            TextView email = view.findViewById(R.id.text_email);
            TextView phone = view.findViewById(R.id.text_phone);
            Data data = lists.get(i);
            nim.setText(data.getNim());
            name.setText(data.getName());
            email.setText(data.getEmail());
            phone.setText(data.getPhone());
        }
        return view;
    }
}
