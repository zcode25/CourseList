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

public class EditorActivity extends AppCompatActivity {

    private EditText editNim, editName, editEmail, editPhone;
    private Button btnSave, btnBack;
    private Helper db = new Helper(this);
    private String nim, name, email, phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editNim = findViewById(R.id.edit_nim);
        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        editPhone = findViewById(R.id.edit_phone);
        btnSave = findViewById(R.id.btn_save);
        btnBack = findViewById(R.id.btn_back);

        nim = getIntent().getStringExtra("nim");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");

        if (nim == null || nim.equals("")) {
            setTitle("Create Student");
        } else {
            setTitle("Edit Student");
            editNim.setText(nim);
            editName.setText(name);
            editEmail.setText(email);
            editPhone.setText(phone);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (nim == null || nim.equals("")) {
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
        if (String.valueOf(editNim.getText()).equals("") || String.valueOf(editName.getText()).equals("") || String.valueOf(editEmail.getText()).equals("") || String.valueOf(editPhone.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill in all data", Toast.LENGTH_SHORT).show();
        } else {
            db.insert(Integer.parseInt(editNim.getText().toString()), editName.getText().toString(), editEmail.getText().toString(), editPhone.getText().toString());
            finish();
        }
    }

    private void edit() {
        if (String.valueOf(editNim.getText()).equals("") || String.valueOf(editName.getText()).equals("") || String.valueOf(editEmail.getText()).equals("") || String.valueOf(editPhone.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill in all data", Toast.LENGTH_SHORT).show();
        } else {
            db.update(Integer.parseInt(editNim.getText().toString()), editName.getText().toString(), editEmail.getText().toString(), editPhone.getText().toString());
            finish();
        }
    }
}