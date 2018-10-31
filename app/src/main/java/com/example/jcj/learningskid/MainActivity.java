package com.example.jcj.learningskid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
// day la login page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void demoGrammar(View v){
        Intent intent = new Intent(getApplicationContext(), UserEnglishContentGrammar.class);

        startActivity(intent);
        finish();
    }
}
