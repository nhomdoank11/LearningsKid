package com.example.jcj.learningskid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserEnglishContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_english_content);
    }
    public void goUserEnglishContentGrammar(View v){
        Intent intent = new Intent(getApplicationContext(), UserEnglishContentGrammar.class);

        startActivity(intent);
        finish();
    }
    public void moveToSearchDictionary (View v){
        Intent intent = new Intent(getApplicationContext(),UserSearchDictionary.class);
        intent.putExtra( "searchDictionary",0);
        startActivity(intent);
    }
}
