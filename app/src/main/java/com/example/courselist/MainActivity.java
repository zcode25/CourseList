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
import com.example.courselist.helper.Helper;
import com.example.courselist.model.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> lists = new ArrayList<>();
    Adapter adapter;
    Helper db = new Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Helper(getApplicationContext());
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.list_item);
        adapter = new Adapter(MainActivity.this, lists);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String nim = lists.get(i).getNim();
                final String name = lists.get(i).getName();
                final String email = lists.get(i).getEmail();
                final String phone = lists.get(i).getPhone();
                final CharSequence[] dialogItem = {"Detail", "Edit", "Delete"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent2 = new Intent(MainActivity.this, DetailActivity.class);
                                intent2.putExtra("nim", nim);
                                intent2.putExtra("name", name);
                                intent2.putExtra("email", email);
                                intent2.putExtra("phone", phone);
                                startActivity(intent2);
                                break;
                            case 1:
                                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                                intent.putExtra("nim", nim);
                                intent.putExtra("name", name);
                                intent.putExtra("email", email);
                                intent.putExtra("phone", phone);
                                startActivity(intent);
                                break;
                            case 2:
                                db.delete(Integer.parseInt(nim));
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
        ArrayList<HashMap<String, String>> rows = db.getAll();
        for (int i = 0; i < rows.size(); i++) {
            String nim = rows.get(i).get("nim");
            String name = rows.get(i).get("name");
            String email = rows.get(i).get("email");
            String phone = rows.get(i).get("phone");

            Data data = new Data();
            data.setNim(nim);
            data.setName(name);
            data.setEmail(email);
            data.setPhone(phone);
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