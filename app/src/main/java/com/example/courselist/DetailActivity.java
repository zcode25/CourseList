package com.example.courselist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.courselist.helper.Helper;

public class DetailActivity extends AppCompatActivity {

    private TextView nim, name, email, phone;
    private String nimString, nameString, emailString, phoneString;
    Helper db = new Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nim = findViewById(R.id.text_nim);
        name = findViewById(R.id.text_name);
        email = findViewById(R.id.text_email);
        phone = findViewById(R.id.text_phone);

        nimString = getIntent().getStringExtra("nim");
        nameString = getIntent().getStringExtra("name");
        emailString = getIntent().getStringExtra("email");
        phoneString = getIntent().getStringExtra("phone");

        setTitle("Student Info & Taken Subject");
        nim.setText(nimString);
        name.setText(nameString);
        email.setText(emailString);
        phone.setText(phoneString);

        TextView subject = findViewById(R.id.text_subject);
        String count = db.count();
        subject.setText(count);

        TextView student = findViewById(R.id.text_student);
        String count2 = db.count2();
        student.setText(count2);
    }
}