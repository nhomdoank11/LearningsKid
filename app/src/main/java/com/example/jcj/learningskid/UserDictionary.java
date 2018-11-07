package com.example.jcj.learningskid;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class UserDictionary extends AppCompatActivity {
    private MyHelperDictionary myHelperDictionary = null;
    private EditText edtVietNam,edtEnglish,edtExample;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dictionary);
        edtVietNam = findViewById(R.id.edTVietNam);
        edtEnglish = findViewById(R.id.edTEnglish);
        edtExample = findViewById(R.id.edtExample);
        Intent intent = getIntent();
        int action = intent.getIntExtra(  "color", 0);
        myHelperDictionary = new MyHelperDictionary(getApplicationContext(),"learningskid",2);
        myHelperDictionary.getReadableDatabase();
    }

    public void addNewWord(View view){
        if(myHelperDictionary == null){
            myHelperDictionary = new MyHelperDictionary(getApplicationContext(),"learningskid",2);
        }
        SQLiteDatabase db = myHelperDictionary.getWritableDatabase();
        ContentValues valuses = new ContentValues();
        valuses.put("vietNam",edtVietNam.getText().toString());
        valuses.put("english",edtEnglish.getText().toString());
        valuses.put("example",edtExample.getText().toString());
        db.insert("Dictionary",null,valuses);
        Intent intent = new Intent(getApplicationContext(),UserSearchDictionary.class);
        intent.putExtra( "color",0);
        startActivity(intent);
    }

    public void addnew(View view){

        Dictionary valuses = new Dictionary();
        valuses.setVietNam(edtVietNam.getText().toString());
        valuses.setEnglish(edtEnglish.getText().toString());
        valuses.setExample(edtExample.getText().toString());
        MainActivity.learningskid.collection("Dictionary").document().set(valuses)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UserDictionary.this,"SUCCESSFULLY",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserDictionary.this,"ERROR!",Toast.LENGTH_LONG).show();
                        Log.d(MainActivity.TAG,e.toString());
                    }
                });
    }


}
