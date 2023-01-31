package com.example.madrassah_app;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etName, etRollNo, etSabaq, etSabqi, etManzil;
    Button btnSave, btnEdit, btnDelete, repo, view;

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.et_name);
        etRollNo = findViewById(R.id.et_roll_no);
        etSabaq = findViewById(R.id.et_sabaq);
        etSabqi = findViewById(R.id.et_sabqi);
        etManzil = findViewById(R.id.et_manzil);
        btnSave = findViewById(R.id.btn_save);
        btnEdit = findViewById(R.id.btn_edit);
        btnDelete = findViewById(R.id.btn_delete);
        repo = findViewById(R.id.btn_repository);
        view = findViewById(R.id.btn_view);

        db = new DBHandler(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String rollNo = etRollNo.getText().toString();
                String sabaq = etSabaq.getText().toString();
                String sabqi = etSabqi.getText().toString();
                String manzil = etManzil.getText().toString();

                if (name.isEmpty() || rollNo.isEmpty() || sabaq.isEmpty() || sabqi.isEmpty() || manzil.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
                    return;
                }
                Student student = new Student(name, rollNo, sabaq, sabqi, manzil);
                db.insertStudent(student);
                etName.setText("");
                etRollNo.setText("");
                etSabaq.setText("");
                etSabqi.setText("");
                etManzil.setText("");
                Toast.makeText(MainActivity.this, "Student added successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String rollNo = etRollNo.getText().toString();
                String sabaq = etSabaq.getText().toString();
                String sabqi = etSabqi.getText().toString();
                String manzil = etManzil.getText().toString();
                Student student = new Student(name, rollNo, sabaq, sabqi, manzil);
                db.updateStudent(student);
                Toast.makeText(MainActivity.this, "Student edited successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteStudent(etRollNo.getText().toString());
                Toast.makeText(MainActivity.this, "Student deleted successfully", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etRollNo.setText("");
                etSabaq.setText("");
                etSabqi.setText("");
                etManzil.setText("");
            }
        });

        repo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://github.com/fUmar3542/Madrassa_App/commits/main";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RefreshGrid();
            }
        });
    }
    public void RefreshGrid(){
//        List<Student> students = db.selectAllStudents();
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter<Student>(MainActivity.this, android.R.layout.simple_list_item_1,students);
        Intent intent = new Intent(MainActivity.this, Recycler.class);
        startActivity(intent);
    }
}