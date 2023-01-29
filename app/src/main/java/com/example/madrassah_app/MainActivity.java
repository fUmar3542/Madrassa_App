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
    Button btnSave, btnEdit, btnDelete, repo;

    DBHandler db;
    ListView listView;

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

        db = new DBHandler(this);
        listView = findViewById(R.id.list_view);
        RefreshGrid();

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
                RefreshGrid();
                etName.setText("");
                etRollNo.setText("");
                etSabaq.setText("");
                etSabqi.setText("");
                etManzil.setText("");
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
                RefreshGrid();
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
                RefreshGrid();
            }
        });

        repo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://github.com/fUmar3542/Quran_App/commits/main";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
    public void RefreshGrid(){
        List<Student> students = db.selectAllStudents();

        ArrayAdapter arrayAdapter = new ArrayAdapter<Student>(MainActivity.this, android.R.layout.simple_list_item_1,students);
        listView.setAdapter(arrayAdapter);
    }
}