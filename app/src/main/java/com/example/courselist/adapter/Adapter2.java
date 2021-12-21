package com.example.courselist.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.courselist.R;
import com.example.courselist.model.Data2;

import java.util.List;

public class Adapter2 extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data2> lists;

    public Adapter2(Activity activity, List<Data2> lists) {
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
            view = inflater.inflate(R.layout.list_subject, null);
        }
        if (view != null) {
            TextView subjectCode = view.findViewById(R.id.text_subject_code);
            TextView subjectName = view.findViewById(R.id.text_subject_name);
            TextView subjectCredit = view.findViewById(R.id.text_subject_credit);
            Data2 data = lists.get(i);
            subjectCode.setText(data.getSubject_code());
            subjectName.setText(data.getSubject_name());
            subjectCredit.setText(data.getSubject_credit());
        }
        return view;
    }
}
