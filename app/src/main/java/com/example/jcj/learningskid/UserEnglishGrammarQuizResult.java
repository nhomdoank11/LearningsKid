package com.example.jcj.learningskid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserEnglishGrammarQuizResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_english_grammar_quiz_result);

        Intent intent =getIntent();
        double result =  intent.getDoubleExtra("gramResult",0);
    }
}
