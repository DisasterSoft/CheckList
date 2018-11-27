package com.ewulusen.disastersoft.checklist;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import android.widget.Button;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class mainScreen extends AppCompatActivity {
    public static Intent intent;
    public static String id, idC, seged;
    public static String[] seged_a, seged_b;
    databaseHelper userDB;
    private Button makeList, viewLists;
    private ListView lista;
    private AdView mAdView;
    EditText input;
    private ArrayAdapter iAdapter;
    public List<String> listList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        intent = getIntent();
        id = intent.getStringExtra("datas");
        userDB = new databaseHelper(this);
        seged = userDB.getLists();
        seged_a = seged.split(";");
        Log.d("listák", seged);
        lista = findViewById(R.id.lista);
        iAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listList);
        lista.setAdapter(iAdapter);
        fillAdapter(seged_a);
        makeList = findViewById(R.id.MakeList);
        makeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = null;
                intent2 = new Intent(mainScreen.this, makeList.class);
                startActivity(intent2);
                finish();
            }
        });


        MobileAds.initialize(this, getString(R.string.admod));
        Bundle extras = new Bundle();
        extras.putString("npa", "1");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();
        mAdView.loadAd(adRequest);
        registerForContextMenu(lista);
        lista.setLongClickable(false);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);

                // Display the selected item text on TextView
                idC = selectedItem;
                lista.showContextMenuForChild(view);

            }
        });

    }

    public void fillAdapter(String[] arrayL) {
        if(arrayL.length==1)
        {

            seged_b = arrayL[0].split(",");
            Log.d("array",seged_b[0]+"-");
            if(seged_b[0].equals(" "))
            {

            }
            else
            {
listList.add(seged_b[0] + "                                                      " + seged_b[2] + " " + getString(R.string.item));
            }
        }
        else {
            for (int i = 0; i < arrayL.length; i++) {
                seged_b = arrayL[i].split(",");
listList.add(seged_b[0] + "                                                      " + seged_b[2] + " " + getString(R.string.item));
            }
        }
        iAdapter.notifyDataSetChanged();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        // super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.ct_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        final MenuItem item2 = item;
        if (item2.getTitle().equals(getString(R.string.delete))) {
            String[] dumy=idC.split("                                                      ");
           userDB.deleteList(dumy[0]);
           iAdapter.clear();
           seged = userDB.getLists();
           seged_a = seged.split(";");
           fillAdapter(seged_a);
        }
        if (item2.getTitle().equals(getString(R.string.rename))) {
            String[] dumy = idC.split("                                                      ");
            final String fdumy=dumy[0];
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.rename);
            builder.setMessage(R.string.itemNotNamed);
            input = new EditText(this);
            builder.setView(input);
            //Positiv button
            builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog,int which) {
                  String txt=input.getText().toString();
                    if(userDB.renameList(fdumy,txt))
                    {
                        Toast.makeText(mainScreen.this, getString(R.string.itemSaved), Toast.LENGTH_SHORT).show();
                        iAdapter.clear();
                        seged = userDB.getLists();
                        seged_a = seged.split(";");
                        fillAdapter(seged_a);
                    }
                    else
                    {
                        Toast.makeText(mainScreen.this, getString(R.string.duplicateName), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        //Negatív button
            builder.setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                }
            });
            //Create dialog
            AlertDialog ad=builder.create();
            ad.show();


        }
        if (item2.getTitle().equals(getString(R.string.edit))) {
            String[] dumy = idC.split("                                                      ");
            String idk=userDB.getId(dumy[0]);
            Intent intent2 = null;
            intent2 = new Intent(mainScreen.this, makeList.class);
            intent2.putExtra("datas",idk);
            startActivity(intent2);
            finish();

        }
        if (item2.getTitle().equals(getString(R.string.viewList))) {
            String[] dumy = idC.split("                                                      ");
            String idk=userDB.getId(dumy[0]);
            Intent intent2 = null;
            intent2 = new Intent(mainScreen.this, viewList.class);
            intent2.putExtra("datas",idk);
            startActivity(intent2);
            finish();

        }
        if (item2.getTitle().equals(getString(R.string.copy))) {
            String[] dumy = idC.split("                                                      ");
            String idk=userDB.getId(dumy[0]);
            userDB.copyList(idk);
            iAdapter.clear();
            seged = userDB.getLists();
            seged_a = seged.split(";");
            fillAdapter(seged_a);




        }


        return true;
    }
}

