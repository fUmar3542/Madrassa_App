package com.example.madrassah_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "students.db";
    private static final String TABLE_NAME = "students";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ROLLNO = "roll_no";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SABAQ = "sabaq";
    private static final String COLUMN_SABQI = "sabqi";
    private static final String COLUMN_MANZIL = "manzil";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_ROLLNO + " TEXT,"
                + COLUMN_SABAQ + " TEXT,"
                + COLUMN_SABQI + " TEXT,"
                + COLUMN_MANZIL + " TEXT"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_ROLLNO, student.getRollNo());
        values.put(COLUMN_SABAQ, student.getSabaq());
        values.put(COLUMN_SABQI, student.getSabqi());
        values.put(COLUMN_MANZIL, student.getManzil());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_ROLLNO, student.getRollNo());
        values.put(COLUMN_SABAQ, student.getSabaq());
        values.put(COLUMN_SABQI, student.getSabqi());
        values.put(COLUMN_MANZIL, student.getManzil());

        db.update(TABLE_NAME, values, COLUMN_ROLLNO + " = ?", new String[] {student.getRollNo()});
        db.close();
    }

    public void deleteStudent(String rollNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ROLLNO + " = ?", new String[] {rollNo});
        db.close();
    }


    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        /*
        * if (cursorCourses.moveToFirst()) {
            do {
                studentArrayList.add(new StudentModel(cursorCourses.getString(1),
                      cursorCourses.getInt(2),
                        cursorCourses.getInt(3) == 1 ? true : false));
            } while (cursorCourses.moveToNext());
        }
        * */

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String rollNo = cursor.getString(cursor.getColumnIndex(COLUMN_ROLLNO));
                String sabaq = cursor.getString(cursor.getColumnIndex(COLUMN_SABAQ));
                String sabqi = cursor.getString(cursor.getColumnIndex(COLUMN_SABQI));
                String manzil = cursor.getString(cursor.getColumnIndex(COLUMN_MANZIL));
                students.add(new Student(name, rollNo, sabaq, sabqi, manzil));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return students;
    }
}