package com.ewulusen.disastersoft.checklist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;

import java.net.MalformedURLException;
import java.net.URL;

public class loginScreen extends AppCompatActivity {
private EditText username;
private CardView login;
private databaseHelper userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        ConsentInformation consentInformation = ConsentInformation.getInstance(this);
        String[] publisherIds = {getString(R.string.pubid)};
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                // User's consent status successfully updated.
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.
            }
        });
        URL privacyUrl = null;
        try {
            // TODO: Replace with your app's privacy policy URL.
            privacyUrl = new URL("http://www.ewulusen.uw.hu/licensz.html");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Handle error.
        }
       final ConsentForm form = new ConsentForm.Builder(this, privacyUrl)
                .withListener(new ConsentFormListener() {
                    @Override
                    public void onConsentFormLoaded() {

                        Log.d("form","loaded");
                    }

                    @Override
                    public void onConsentFormOpened() {
                        Log.d("form","displayed");
                    }

                    @Override
                    public void onConsentFormClosed(
                            ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                        // Consent form was closed.
                    }

                    @Override
                    public void onConsentFormError(String errorDescription) {
                        Log.d("form_error",errorDescription);
                    }
                })
                .withPersonalizedAdsOption()
                .withNonPersonalizedAdsOption()
                .withAdFreeOption()
                .build();
        form.load();
        //form.show();

        username=findViewById(R.id.email);
        username.requestFocus();
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
