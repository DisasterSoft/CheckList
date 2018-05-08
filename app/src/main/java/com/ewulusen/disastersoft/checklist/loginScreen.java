package com.ewulusen.disastersoft.checklist;

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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty())
                {
                    Toast.makeText(loginScreen.this, getString(R.string.plsFillUsername), Toast.LENGTH_LONG).show();
                }
                else
                {
                        userDB.login(username.getText().toString());
                }
            }
        });
    }
}
