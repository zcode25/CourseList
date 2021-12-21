package com.example.courselist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.courselist.adapter.Adapter;
import com.example.courselist.adapter.Adapter2;
import com.example.courselist.helper.Helper;
import com.example.courselist.model.Data;
import com.example.courselist.model.Data2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubjectActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data2> lists = new ArrayList<>();
    Adapter2 adapter;
    Helper db = new Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        db = new Helper(getApplicationContext());
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubjectActivity.this, EditorActivity2.class);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.list_item2);
        adapter = new Adapter2(SubjectActivity.this, lists);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String subjectCode = lists.get(i).getSubject_code();
                final String subjectName = lists.get(i).getSubject_name();
                final String subjectCredit = lists.get(i).getSubject_credit();
                final CharSequence[] dialogItem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(SubjectActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(SubjectActivity.this, EditorActivity2.class);
                                intent.putExtra("subject_code", subjectCode);
                                intent.putExtra("subject_name", subjectName);
                                intent.putExtra("subject_credit", subjectCredit);
                                startActivity(intent);
                                break;
                            case 1:
                                db.delete2(Integer.parseInt(subjectCode));
                                lists.clear();
                                getData();
                                count();
                                count2();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getData();
        count();
        count2();
    }

    private void getData() {
        ArrayList<HashMap<String, String>> rows = db.getAll2();

        for (int i = 0; i < rows.size(); i++) {
            String subjectCode = rows.get(i).get("subject_code");
            String subjectName = rows.get(i).get("subject_name");
            String subjectCredit = rows.get(i).get("subject_credit");

            Data2 data = new Data2();
            data.setSubject_code(subjectCode);
            data.setSubject_name(subjectName);
            data.setSubject_credit(subjectCredit);
            lists.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    private void count() {
        TextView subject = findViewById(R.id.text_subject);
        String count = db.count();
        subject.setText(count);
    }

    private void count2() {
        TextView student = findViewById(R.id.text_student);
        String count2 = db.count2();
        student.setText(count2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lists.clear();
        getData();
        count();
        count2();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.student){
            startActivity(new Intent(this, MainActivity.class));
        } else if (item.getItemId() == R.id.subject) {
            startActivity(new Intent(this, SubjectActivity.class));
        }
        return true;
    }
}