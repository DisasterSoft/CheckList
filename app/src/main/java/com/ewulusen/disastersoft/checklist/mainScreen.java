package com.ewulusen.disastersoft.checklist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class mainScreen extends AppCompatActivity {
    public static Intent intent;
    public static String id,idC,seged;
    public static String[] seged_a;
    databaseHelper userDB;
    private CardView makeList,viewLists;
    private TextView welcome;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        intent = getIntent();
        id = intent.getStringExtra("datas");
        userDB = new databaseHelper(this);
        Cursor loCursor=userDB.getName(id);
        loCursor.moveToNext();
        welcome=findViewById(R.id.welcome);
        makeList=findViewById(R.id.MakeList);
        viewLists=findViewById(R.id.viewList);
        welcome.setText(getString(R.string.welcome)+" "+loCursor.getString(0));
        makeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = null;
                intent2 = new Intent(mainScreen.this, makeList.class);
                intent2.putExtra("datas", id);
                startActivity(intent2);
                finish();
            }
        });
        viewLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = null;
                intent2 = new Intent(mainScreen.this, viewList.class);
                intent2.putExtra("datas", id);
                startActivity(intent2);
                finish();
            }
        });
        MobileAds.initialize(this,getString(R.string.admod));

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
}
