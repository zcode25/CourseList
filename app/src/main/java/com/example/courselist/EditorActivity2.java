package com.example.courselist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.courselist.helper.Helper;

public class EditorActivity2 extends AppCompatActivity {

    private EditText editSubjectCode, editSubjectName, editSubjectCredit;
    private Button btnSave2, btnBack2;
    private Helper db = new Helper(this);
    private String subjectCode, subjectName, subjectCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor2);

        editSubjectCode = findViewById(R.id.edit_subject_code);
        editSubjectName = findViewById(R.id.edit_subject_name);
        editSubjectCredit = findViewById(R.id.edit_subject_credit);
        btnSave2 = findViewById(R.id.btn_save2);
        btnBack2 = findViewById(R.id.btn_back2);

        subjectCode = getIntent().getStringExtra("subject_code");
        subjectName = getIntent().getStringExtra("subject_name");
        subjectCredit = getIntent().getStringExtra("subject_credit");

        if (subjectCode == null || subjectCode.equals("")) {
            setTitle("Create Subject");
        } else {
            setTitle("Edit Subject");
            editSubjectCode.setText(subjectCode);
            editSubjectName.setText(subjectName);
            editSubjectCredit.setText(subjectCredit);
        }

        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditorActivity2.this, SubjectActivity.class);
                startActivity(intent);
            }
        });

        btnSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (subjectCode == null || subjectCode.equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    Log.e("Saving", e.getMessage());
                }
            }
        });
    }

    private void save() {
        if (String.valueOf(editSubjectCode.getText()).equals("") || String.valueOf(editSubjectName.getText()).equals("") || String.valueOf(editSubjectCredit.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill in all data", Toast.LENGTH_SHORT).show();
        } else {
            db.insert2(Integer.parseInt(editSubjectCode.getText().toString()), editSubjectName.getText().toString(), Integer.parseInt(editSubjectCredit.getText().toString()));
            finish();
        }
    }

    private void edit() {
        if (String.valueOf(editSubjectCode.getText()).equals("") || String.valueOf(editSubjectName.getText()).equals("") || String.valueOf(editSubjectCredit.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill in all data", Toast.LENGTH_SHORT).show();
        } else {
            db.update2(Integer.parseInt(editSubjectCode.getText().toString()), editSubjectName.getText().toString(), Integer.parseInt(editSubjectCredit.getText().toString()));
            finish();
        }
    }

}