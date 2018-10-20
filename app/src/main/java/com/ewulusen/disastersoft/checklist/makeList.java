package com.ewulusen.disastersoft.checklist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class makeList extends AppCompatActivity implements recyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    public static Intent intent;
    public static String id;
    databaseHelper userDB;
    public AutoCompleteTextView item;
    private Button add,back,save;
    private RecyclerView lista;
    private itemAdapter iAdapter;
    public List<item> movieList = new ArrayList<>();
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_list);
        intent = getIntent();
        id = intent.getStringExtra("datas");
        userDB = new databaseHelper(this);
        item=findViewById(R.id.item);
        add=findViewById(R.id.add);
        back=findViewById(R.id.back);
        save=findViewById(R.id.save);
        lista=findViewById(R.id.lista);
        String[] items = getResources().getStringArray(R.array.list_of_items);
        ArrayAdapter itemAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,items);
        item.setAdapter(itemAdapter);
        iAdapter=new itemAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        lista.setLayoutManager(mLayoutManager);
        lista.setItemAnimator(new DefaultItemAnimator());
        lista.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        lista.setAdapter(iAdapter);
        fillAdapter();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addElem(item.getText().toString());
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = null;
                intent2 = new Intent(makeList.this,mainScreen.class);
                intent2.putExtra("datas", id);
                startActivity(intent2);
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new recyclerItemTouchHelper(0, ItemTouchHelper.LEFT, (recyclerItemTouchHelper.RecyclerItemTouchHelperListener) this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(lista);
        MobileAds.initialize(this,getString(R.string.admod));
        Bundle extras = new Bundle();
        extras.putString("npa", "1");
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();
        mAdView.loadAd(adRequest);
    }
    private void addElem(String items)
    {
        if(items.isEmpty())
        {
            Toast.makeText(makeList.this, getString(R.string.plsFillitem), Toast.LENGTH_LONG).show();
        }
        else
        {
            item i=new item(items);
            movieList.add(i);
            iAdapter.notifyDataSetChanged();
            Toast.makeText(makeList.this, getString(R.string.itemAdded), Toast.LENGTH_SHORT).show();
            this.item.setText("");
        }
    }
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof itemAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = movieList.get(viewHolder.getAdapterPosition()).getName();

            // backup of removed item for undo purpose
            final item deletedItem = movieList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            iAdapter.removeItem(viewHolder.getAdapterPosition());


        }
    }
    public void saveData()
    {
        userDB.saveData(movieList,id);
       // userDB.databasePrinter(id);
        Toast.makeText(makeList.this, getString(R.string.itemSaved), Toast.LENGTH_SHORT).show();

    }
    public void fillAdapter() {
        Log.d("id",id);
        Cursor lcursor = userDB.getItems(id);
        Log.d("listcoursecount",lcursor.getCount()+"");

        if (lcursor.getCount() > 0) {
            while (lcursor.moveToNext()) {
                item i = new item(lcursor.getString(lcursor.getColumnIndex("Name")));
                movieList.add(i);

            }
            iAdapter.notifyDataSetChanged();
        }

    }
}
