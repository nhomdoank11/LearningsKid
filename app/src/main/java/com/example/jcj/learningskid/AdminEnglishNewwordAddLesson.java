package com.example.jcj.learningskid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminEnglishNewwordAddLesson extends AppCompatActivity {
    private SQLiteHelper myHelper = null;
    private EditText LessonName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_english_newword_add_lesson);
        LessonName = findViewById(R.id.editTextLesson);
        myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);
        myHelper.getReadableDatabase();
    }
    public void addLesson(View v){
        if (myHelper == null) {
            myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);
        }
        if(LessonName.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Can't be blank", Toast.LENGTH_SHORT).show();
        }else{
            SQLiteDatabase db = myHelper.getWritableDatabase();
            ContentValues valueslesson = new ContentValues();
            valueslesson.put("name",LessonName.getText().toString());
            db.insert("UserEnglishNewwordLesson", null, valueslesson);
            db.close();
            Toast.makeText(getApplicationContext(), "Add successfully", Toast.LENGTH_SHORT).show();
        }
    }
    public void backadminenglish(View v){
        Intent intent2 = new Intent(getApplicationContext(),AdminEnglishNewword.class);
        startActivity(intent2);
        finish();
    }
}
