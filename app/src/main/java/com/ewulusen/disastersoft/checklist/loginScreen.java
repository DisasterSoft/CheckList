package com.ewulusen.disastersoft.checklist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class loginScreen extends AppCompatActivity {
private TextView username;
private CardView login;
private databaseHelper userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        username=findViewById(R.id.email);
        login=findViewById(R.id.email_sign_in_button);
        userDB = new databaseHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty())
                {
                    Toast.makeText(loginScreen.this, getString(R.string.plsFillUsername), Toast.LENGTH_LONG).show();
                }
                else
                {
                    Cursor localCursor=userDB.login(username.getText().toString());
                    localCursor.moveToNext();
                    Intent intent2 = null;
                    intent2 = new Intent(loginScreen.this, mainScreen.class);
                    String name=localCursor.getString(0);
                    intent2.putExtra("datas", name);
                    startActivity(intent2);
                    finish();
                }
            }
        });
    }
}
