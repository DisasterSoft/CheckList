package com.ewulusen.disastersoft.checklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by diszterhoft.zoltan on 2018.04.03
 * Ebben a javafájlban fogom létrehozni az adatbázisokat amivel dolgozni fogunk
 */

public class databaseHelper extends SQLiteOpenHelper {
    /**
     * Előszőr is létrhozzuk az összes változót amivel dolgozni fogunk.
     */
    public static final String DatabaseName = "checkListUsers.db";
    public static final String uTableName = "users_table_ChekList";
    public static final String iTableName = "item_table_CheckList";
    public databaseHelper(Context paramContext)

    {
        super(paramContext, DatabaseName, null, 3);
    }

    /**
     * hozzá ad egy sort a felhasználó táblához
     * @param paramString1-email
     * @return boolen
     */
    public boolean addData(String paramString1)
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("userName", paramString1);
        return localSQLiteDatabase.insert(uTableName, null, localContentValues) != -1L;
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+uTableName+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,  userName TEXT, password TEXT)");
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+iTableName+" (Name TEXT, DB TEXT, TYPE TEXT,OWNER TEXT)");

    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
        paramSQLiteDatabase.execSQL("DROP TABLE if EXISTS "+uTableName);
        paramSQLiteDatabase.execSQL("DROP TABLE if EXISTS "+iTableName);
        onCreate(paramSQLiteDatabase);
    }
    /**
     * Bejelentkezés/vagy regisztrál egy felhasználót
     * @param paramString1-email

     * @return Cursor
     */
    public Cursor login(String paramString1)
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        // Log.d("SQL", paramString2);
        String str1 = "SELECT * FROM "+uTableName+" where userName='"+paramString1+"'";
        //Log.d("SQL", str1);
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        if (localCursor.getCount() == 0)
        {
            addData(paramString1);
            String str2 = "SELECT * FROM "+uTableName+" where userName='"+paramString1+"'";
            localCursor = localSQLiteDatabase.rawQuery(str2, null);
        }

        return localCursor;
    }
    public  Cursor getName(String id)
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT userName FROM "+uTableName+" where ID='"+id+"'";
        //  Log.d("SQL", str1);
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        return localCursor;
    }
    public  Cursor getItems(String id)
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT * FROM "+iTableName+" where OWNER='"+id+"'";
        //  Log.d("SQL", str1);
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        return localCursor;
    }
    public  void saveData(List lista, String id)
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        localSQLiteDatabase.delete(iTableName,"OWNER="+ id,null);
        ContentValues localContentValues = new ContentValues();
        for(int i=0;i<lista.size();i++) {
            item it = (item) lista.get(i);
            localContentValues.put("Name", it.getName());
            localContentValues.put("DB", it.getDb());
            localContentValues.put("TYPE", it.getMenny());
            localContentValues.put("OWNER", id);
            localSQLiteDatabase.insert(iTableName, null, localContentValues);
            localContentValues.clear();
        }
        //databasePrinter(id);
    }
    public void databasePrinter(String id)
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT * FROM "+iTableName+" where OWNER='"+id+"'";
        Cursor cursor = localSQLiteDatabase.rawQuery(str1, null);
            while (cursor.moveToNext()) {
                Log.d("tartalom",str1);
                String line="";
                for(int i=0; i<cursor.getColumnCount();i++)
                {
                    line=line+(cursor.getString(i).toString())+"->"+cursor.getColumnName(i)+",";
                }

                  Log.d("tartalom",line);
        }
    }
    public void deleteItem(String name, String id)
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        localSQLiteDatabase.delete(iTableName,"OWNER='"+ id+"' and Name='"+name+"'",null);
    }
      }

