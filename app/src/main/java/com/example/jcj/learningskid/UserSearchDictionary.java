package com.example.jcj.learningskid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

public class UserSearchDictionary extends AppCompatActivity {
    private ListView listView;
    AutoCompleteTextView autoCompleteTextView;
    MultiAutoCompleteTextView multiAutoCompleteTextView;
    private MyHelperDictionary myHelperDictionary = null;
    private List<Dictionary> dictionaryList = new ArrayList<>();
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__user__search__dictionary);
        Intent intent = getIntent();
        int action = intent.getIntExtra(  "searchDictionary", 0);
        listView   = findViewById(R.id.listView);
        autoCompleteTextView = findViewById(R.id.autoComplate);
        myHelperDictionary = new MyHelperDictionary(getApplicationContext(),"learningskid",2);
        myHelperDictionary.getReadableDatabase();
        select();
    }
    public void select (){
        ArrayList<String> dictionaryEnglishList = new ArrayList<>();
        if(myHelperDictionary == null){
            myHelperDictionary = new MyHelperDictionary(getApplicationContext(),"learningskid",2);
        }
        SQLiteDatabase db = myHelperDictionary.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Dictionary",null);
        while (cursor.moveToNext()){
            String english = cursor.getString(cursor.getColumnIndex("english"));
            dictionaryEnglishList.add(english);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,dictionaryEnglishList);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setDropDownHeight(400);
    }
    public void select2(View view) {
        SQLiteDatabase db = myHelperDictionary.getWritableDatabase();
        String a = autoCompleteTextView.getText().toString();
        String query = "SELECT * FROM Dictionary WHERE english LIKE '%"+ a+"%'";
        Cursor cursor = db.rawQuery(query, null);
        dictionaryList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String vietName = cursor.getString(cursor.getColumnIndex("vietNam"));
            String english = cursor.getString(cursor.getColumnIndex("english"));
            String example = cursor.getString(cursor.getColumnIndex("example"));

            dictionaryList.add(new Dictionary(id,vietName,english,example));
        }
        DictionaryAdapter adapter = new DictionaryAdapter(this, dictionaryList);
        listView.setAdapter(adapter);
    }
    public void moveToAddNewRord (View v){
        Intent intent = new Intent(getApplicationContext(),UserDictionary.class);
        intent.putExtra( "addNewWord",0);
        startActivity(intent);
    }
}
