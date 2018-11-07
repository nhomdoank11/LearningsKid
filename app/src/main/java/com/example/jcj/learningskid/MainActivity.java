package com.example.jcj.learningskid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
// day la login page
    Button LogInButton, RegisterButton;
    EditText Name, Password;
    String NameHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String TempPassword = "NOT_FOUND";
    public static final String UserName = "";
    public static  final FirebaseFirestore learningskid = FirebaseFirestore.getInstance();
    public static final String TAG = "MainActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        LogInButton = findViewById(R.id.buttonLogin);
        RegisterButton = findViewById(R.id.buttonRegister);
        Name = findViewById(R.id.editName);
        Password = findViewById(R.id.editPassword);

        sqLiteHelper = new SQLiteHelper(this);

        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextStatus();

                LoginFunction();
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    
        
    public void LoginFunction() {

        if (EditTextEmptyHolder) {

            sqLiteDatabase = sqLiteHelper.getWritableDatabase();

            
            cursor = sqLiteDatabase.query(SQLiteHelper.tableName, null, " " + SQLiteHelper.Table_Column_1_Name + "=?", new String[]{NameHolder}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();

                    // Storing Password associated with entered name.
                    TempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Password));

                    cursor.close();
                }
            }

            CheckFinalResult();

        } else {

            Toast.makeText(MainActivity.this, "Please Enter Username or Password.", Toast.LENGTH_LONG).show();

        }
    }

    
    public void CheckEditTextStatus() {

        NameHolder = Name.getText().toString();
        PasswordHolder = Password.getText().toString();

        if (TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(PasswordHolder)) {

            EditTextEmptyHolder = false;

        } else {

            EditTextEmptyHolder = true;
        }
    }

    public void CheckFinalResult() {
        if(NameHolder.equals("admin") && PasswordHolder.equals("admin") ) {
            Intent intent = new Intent(MainActivity.this, AdminHomeContent.class);
            intent.putExtra(UserName, NameHolder);
            startActivity(intent);

        }
        if (TempPassword.equalsIgnoreCase(PasswordHolder)) {

            Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, UserHomeContent.class);
            intent.putExtra(UserName, NameHolder);
            startActivity(intent);


        } else {
            Toast.makeText(MainActivity.this, "UserName or Password is Wrong, Please Try Again.", Toast.LENGTH_LONG).show();
        }
        TempPassword = "NOT_FOUND";
    }

//    public void demoGrammar(View v){
//        Intent intent = new Intent(getApplicationContext(), UserEnglishContentGrammar.class);
//
//        startActivity(intent);
//        finish();
//    }
//    public void demoAdminGrammar(View v){
//        Intent intent = new Intent(getApplicationContext(), AdminEnglishContentGrammarList.class);
//
//        startActivity(intent);
//        finish();
//    }
//    public void moveToMyActivity (View v){
//        Intent intent = new Intent(getApplicationContext(),UserSearchDictionary.class);
//        intent.putExtra( "color",0);
//        startActivity(intent);
//    }
//    public void moveToMyActivity2 (View v){
//        Intent intent = new Intent(getApplicationContext(),UserDictionary.class);
//        intent.putExtra( "color",0);
//        startActivity(intent);
//    }
}
