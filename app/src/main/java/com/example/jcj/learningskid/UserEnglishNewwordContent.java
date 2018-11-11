package com.example.jcj.learningskid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UserEnglishNewwordContent extends AppCompatActivity {
    private ListView newwordList;
    private SQLiteHelper myHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_english_newword_content);
        newwordList = findViewById(R.id.listWord);
        myHelper = new SQLiteHelper(getApplicationContext(),"LearningKid", 1);
        myHelper.getReadableDatabase();
        AdapterUser_englishNewword_Content adapter = new AdapterUser_englishNewword_Content(this, select());
        newwordList.setAdapter((AdapterUser_englishNewword_Content)adapter);
    }
    public List<EnglishNewwordContent> select(){
        List<EnglishNewwordContent> list = new ArrayList<>();
        if(myHelper == null){
            myHelper = new SQLiteHelper(getApplicationContext(),"LearningKid", 1);
        }
        SQLiteDatabase db = myHelper.getReadableDatabase();
        Cursor cursor = db.query("UserEnglishNewwordContent", new String[]{"id", "word","detail"},
                null, null, null, null, null);
        while (cursor.moveToNext()){
            EnglishNewwordContent content = new EnglishNewwordContent(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("word")),
                    cursor.getString(cursor.getColumnIndex("detail")));
            list.add(content);
        }
        return list;
    }
}
