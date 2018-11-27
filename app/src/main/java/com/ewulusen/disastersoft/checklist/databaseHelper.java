package com.ewulusen.disastersoft.checklist;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
    public static final String isTableName = "items_table_CheckList";
    public String lang=null;
    public databaseHelper(Context paramContext)

    {

        super(paramContext, DatabaseName, null, 8);
        lang=Resources.getSystem().getConfiguration().locale.getLanguage();
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
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+isTableName+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT, IMAGE TEXT,LANG TEXT)");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
        paramSQLiteDatabase.execSQL("DROP TABLE if EXISTS "+isTableName);
        paramSQLiteDatabase.execSQL("DROP TABLE if EXISTS "+iTableName);
        paramSQLiteDatabase.execSQL("DROP TABLE if EXISTS "+uTableName);
        onCreate(paramSQLiteDatabase);
    }

    /**
     * az alkalmazás telepítésénél/upgradelnél feltöltjük azt az adat
     */
    public void fillTable()
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT * FROM "+isTableName+"";
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        if(localCursor.getCount()>0)
        {}
        else {
            ContentValues localContentValues = new ContentValues();
            String[] list = {"alap", "apple juice", "asparagus", "avocado", "banana", "banana split", "bean", "beer",
                    "bento", "birthday cake", "black pepper", "bolognese", "bonbon", "bread", "broccoli", "brochette",
                    "bun", "burrito", "butter", "cabbage", "candy", "canne", "carrot", "celery", "cheese", "cheezy sauce",
                    "cherry", "chicken bits", "chicken leg", "chiken", "chilli", "chinabox", "chips", "chopped meat",
                    "cinnamon roll", "cloudberry", "cloudberry jam", "coconut cocktail", "coffee", "coktail", "cookie",
                    "coriander", "corndog", "cotton candy", "cow", "crab", "crawfish", "croissant", "cucumber", "cupcake",
                    "dim sum", "doughnut", "dolmades", "dragon fruit", "dressing", "duck", "durian", "egg", "eggplant",
                    "espresso", "filleted fish", "fish", "french fries", "fried chicken", "garlic", "grapefruit",
                    "grapes", "green olive", "guava", "ham", "hamburger", "hazelnut", "honey", "hops", "hot chocolate",
                    "hot dog", "hot sauce", "ice", "icecream", "jelly", "kebab", "kechup", "kifli", "kiwi", "lasangne",
                    "leg steak", "lemon", "lettuce", "loin rack", "lollipop", "macaron", "mango", "mangosteen", "marmalade",
                    "mayo", "milch shake", "milk", "muffin", "mushroom", "muskmelon", "mustar", "nachos", "noodles",
                    "nut", "oats", "octopus", "oil", "olive oil", "onion", "onion sauce", "onion rings", "orange",
                    "orange juice", "oysters", "pancake", "paprika", "parma ham", "parsnip", "peach", "peanut",
                    "pear", "peas", "pepitas", "pie", "pig", "pinapple", "pizza", "plum", "pomegranate", "popcorn",
                    "potato", "prawn", "pretzel", "prime rib", "pumpkin", "rack of lamb", "rack of ribs", "radish",
                    "ramen", "raspberries", "rice", "rolled roast", "sace", "sack of flour", "salami",
                    "salate", "salmon", "salt", "salt pork", "sardinie", "sausage", "sesame", "sesame oil", "sheep",
                    "soda bottle", "soup", "soy", "spaghetti", "spinach", "steak", "strawberry", "sugar", "sugar cubes",
                    "sushi", "sweet potato", "sweetener", "taco", "tea", "tomato", "tortilla", "turnip", "vinagera",
                    "waffles", "watter melone", "whisky", "whortleberry", "whortleberry jelly", "wine bottle",
                    "wrap", "yoghurt", "zucchini","yoyo","tupper_ware","t-shirt","baking sheet","torch","toothbrush","tool",
                    "toilet_paper","toaster","gingerbread oven","thongs","sunglasses","stapler",
                    "spice","socks","liquid","soap","rinse","shovel","scissors","scale","rouge",
                    "rolling-pin","record","pushpin","powder","pottea","plunger","plate","pizza_cutter",
                    "pill_bottle","pet_dish","pencil","pen","peeler","parfume","pantyhose","pants",
                    "paint","oven","nail-polish","mincer","microwave","marking_pin","make_up","liquid-soap",
                    "knife","jack-o-lantern","house-cleaning","hook","hand-blender","handbag","gyufa","flowerpot",
                    "dish soap","glue","glass","ginger","fridge","fork","facial-tissue","facial","eyelash","eggclock",
                    "duster","cup","cosmetics","corkscrew","cooking_pot","cooking_spoon","cooker","containerglass",
                    "container","comb","color_pencils","coffee-pot","coffee-machine","cloves","chair","chain",
                    "can oppener","candel","bulb","brick","bottle","detergent","blender","batteryborrel","battery",
                    "basket","bandages","bag","nursing bottle","apron","aluminum-foil","accumulator"
            };
            String[] list_de={"alap", "apfelsaft", "spargel", "avocado", "banane", "banane split", "bohne", "bier","bento", "geburtstagskuchen", "schwarzer pfeffer", "bolognese", "bonbon", "brot", "brokkoli", "brochette",
                    "bur", "burrito", "butter", "kohl", "candy", "cannes", "karotte", "sellerie", "käse", "cheezy soße","chicken", "chicken bit", "chicken leg", "chiken", "chilli", "chinabox", "chips", "gehacktes fleisch",
                    "zimtschnecke", "moltebeere", "moltebeerenmarmelade", "kokosnusscocktail", "kaffee", "coktail", "keks","croissant", "corndog", "candy candy", "kuh", "krabbe", "krabbenfisch", "croissant", "gurke", "cupcake",
                    "dim sum", "donut", "dolmades", "drachenfrucht", "dressing", "ente", "durian", "ei", "aubergine","espresso", "filleted fisch", "fisch", "pommes frites", "brathähnchen", "knoblauch", "grapefruit",
                    "honig", "green olive", "guave", "schinken", "hamburger", "haselnuss", "honig", "hopfen", "hot chocolate","hot dog", "heiße sauce", "eis", "icecream", "gelee", "kebab", "kechup", "kifli", "kiwi", "lasangne",
                    "legs steak", "zitrone", "salat", "lendenständer", "lollipop", "macaron", "mango", "mangostan", "marmelade","mayo", "milch schütteln", "milch", "muffin", "pilz", "moschusmelone", "mustar", "nachos", "nudeln",
                    "nuss", "hafer", "oktopus", "öl", "olivenöl", "zwiebel", "zwiebelsauce", "zwiebelringe", "orange","orangensaft", "austern", "pfannkuchen", "paprika", "parmaschinken", "pastinake", "pfirsich", "erdnuss",
                    "pine", "erbsen", "pepitas", "pie", "pig", "pinapple", "pizza", "pflaume", "granatapfel", "popcorn","brezel", "garnele", "brezel", "prime rib", "kürbis", "rack of lamm", "ribbon of ribs", "radish",
                    "ramen", "himbeeren", "reis", "gerollt braten", "sace", "mehlsack", "salami","salate", "lachs", "salz", "salz schweinefleisch", "sardinie", "wurst", "sesam", "sesamöl", "schafe",
                    "soda bottle", "suppe", "soja", "spaghetti", "spinat", "steak", "erdbeere", "zucker", "zuckerwürfel","sushi", "süsskartoffel", "süßstoff", "taco", "tee", "tomaten", "tortilla", "rübe", "vinagera",
                    "waffeln", "wassermelone", "whisky", "heidelbeere", "heidelbeergelee", "weinflasche","wrap", "joghurt", "zucchini","yoyo","tupperware","t-shirt","kuchenblech","fackel","zahnbürste","werkzeug",
                    "toilettenpapier","toaster","lebkuchenofen","riemen","sonnenbrille","hefter","würzen","socken","flüssigkeit","seife","spüler","schaufel","schere","rahmen","rouge","nudelholz",
                    "aufzeichnung","druckbolzen","pulver","tea","kolben","teller","pizza-schneider","tablettendose","pet_dish","bleistift","stift","schäler","parfüm","strumpfhose","hose","farbe","ofen",
                    "nagelpolitur","fleischwolf","mikrowelle","markierungsstift","bilden","flüssigseife","messer","kürbislaterne","hausputz","haken","stabmixer","handtasche","streichhölzer","Blumentopf",
                    "Spülmittel","kleben","glas","ingwer","kühlschrank","gabel","kleenex","gesichts","wimper","eieruhr","staubtuch","tasse","kosmetika","korkenzieher","kochtopf","kochlöffel","kocher","behälterglas",
                    "container","kamm","buntstifte","kaffeetasse","kaffee-machine","nelken","stuhl","kette","dosenöffner","kerze","birne","backstein","flasche","Reinigungsmittel","mixer","batteriebatterie","batterie","korb",
                    "bandagen","tasche","babyflasche","schürze","aluminiumfolie","batterie"
            };
            String[] list_hun = {"alap", "almalé","spárga","avokádo","banán","banán split",
                    "bab","sör","bento","szülinapi trota","fekete bors","bolognese","bonbon",
                    "kenyér","brokkoli","brochette","cipó","burrito","vaj","fejes káposzta",
                    "cukorka","konzerv","sárgarépa","zeller","sajt","sajt szósz","cseresznye",
                    "csirke falatkák","csirke comb","csirke","chilli","kínai(étel)","chips",
                    "darálthús","fahéjas csiga","szeder","szeder lekvár","kókusz koktél",
                    "kávé","koktél","süti","koriander","corndog","vattacukor","tehén","rák",
                    "édesvízi rák","kroaszon","uborka","cupcake","tím szam","fánk","dolma",
                    "sárkány gyümölcs","öntet","kacsa","durián","tojás","padlizsán","espresso",
                    "hal filé","hal","sült krumpli","sült csirke","fokhagyma","grapefruit",
                    "szőlő","zöld oliva","guava","sonka","hamburger","mogyoró","méz","komló",
                    "forró csoki","csípős szósz","hotdog","jég","jégkrém","zselé","kebab",
                    "kechup","kifli","kiwi","lasangne","comb hús","citrom","kerti saláta",
                    "pirított borda","nyalóka","macaron","mango","mangosztán","lekvár",
                    "majonéz","tej shake","tej","muffin","gomba","sárgadinnye","mustár",
                    "nachos","tészta","diófélék","abrakzab","tintahal","sütő olaj",
                    "olivaolaj","hagyma","hagymaszósz","hagymakarikák","narancs",
                    "narancslé","osztriga","palacsinta","paprika","pármai sonka","pasztinák",
                    "barack","mogyoró","körte","borsó","tökmag","pite","sertés","ananász",
                    "pizza","szílva","gránátalma","popcorn","krumpli","rák","perec","borda",
                    "tök","bárány borda","oldalas","retek","ramen","málna","rizs",
                    "füstőlt rostélyos","szaké","liszt","szalámi","saláta","lazac","só",
                    "sózott disznóhús","szardínia","kolbász","szezám mag","szezám olaj",
                    "bárány hús","szóda","leves","szója","spagetti","spenót","steak","eper",
                    "cukor","kockacukor","sushi","édes burgonya","édesítőszer","taco","tea",
                    "paradicsom","tortilla","tarló répa","ecet","waffles","görög dinnyi",
                    "whisky","fekete áfonya","fekete áfonya lekvár","bor","wrap","joghurt",
                    "Cukkini","yoyo", "tupper_ware", "póló", "tepsi", "zseblámpa", "fogkefe",
                    "ragasztó pisztoly", "vécé papír", "kenyérpirító","melegszendvics sütő", "strandpapucs",
                    "napszemüveg", "tűzőgép", "fűszer", "zokni",
                    "folyékony", "szappan", "öblíto", "lapát", "olló", "konyha mérleg",
                    "rúzs", "sodrófa", "rekord", "rajzszeg", "por", "teáskanna", "búvárdugattyú",
                    "tányér", "pizza szelő", "gyógyszer", "állat eledel", "ceruza", "toll", "hámozókés",
                    "parfüm", "harisnyanadrág", "nadrág", "festék", "sütő", "körömlakk", "húsdaráló",
                    "mikrohullámú sütő", "szövegkiemelő", "smink", "folyékony szappan", "kés", "töklámpás",
                    "takarítás", "akasztó", "botmixer", "kézitáska", "gyufa", "virágcserép", "mosogatószer",
                    "ragasztó", "pohár", "gyömbér", "hűtőszekrény", "villa", "papírzsebkendő", "arc",
                    "szempilla", "konyhai óra", "poroló", "csésze", "kozmetikum", "dugóhúzó", "kondér",
                    "fakanál", "tűzhely", "üveg", "tartály", "fésű", "színes ceruzák",
                    "kávéskanna", "kávéfőző", "szegfűszeg", "szék", "lánc", "konzervnyitó",
                    "gyertya", "izzó", "lego", "ablakmosó", "mosószer", "turmixgép", "akous fúró", "akkumulátor",
                     "kosár", "kötszerek", "táska", "cumisüveg", "kötény", "alufólia","akkumulátor"};
            String[] images = {"alap", "apple_juice", "asparagus","avocado", "banana", "banana_split",
                    "bean", "beer","bento", "birthday_cake", "black_pepper",
                    "bolognese", "bonbon", "bread","broccoli", "brochette", "bun",
                    "burrito", "butter", "cabbage","candy", "canne", "carrot", "celery",
                    "cheese", "cheezy_sauce", "cherry","chicken_bits", "chicken_leg", "chiken", "chilli",
                    "chinabox", "chips", "chopped_meat","cinnamon_roll", "cloudberry", "cloudberry_jam",
                    "coconut_cocktail", "coffee", "coktail", "cookie","coriander", "corndog", "cotton_candy", "cow",
                    "crab", "crawfish", "croissant","cucumber", "cupcake", "dim_sum", "doghnout",
                    "dolmades", "dragon_fruit", "dressing","duck", "durian", "egg", "eggplant", "espresso",
                    "filleted_fish", "fish", "french_fries", "fried_chicken","garlic", "grapefruit", "grapes", "green_olive", "guava",
                    "ham", "hamburger", "hazelnut","honey", "hops", "hot_chocolate",
                    "hotdog", "hot_sauce", "ice", "icecream","jelly", "kebab", "kechup", "kifli",
                    "kiwi", "lasangne", "leg_steak", "lemon", "lettuce","loin_rack", "lollipop", "macaron",
                    "mango", "mangosteen", "marmalade","mayo", "milch_sake", "milk", "muffin", "mushroom",
                    "muskmelon", "mustar", "nachos", "noodles","nut", "oats", "octopus", "oil",
                    "olive_oil", "onion", "onion_sauce", "onion_rings","orange", "orange_juice", "oysters",
                    "pancake", "paprika", "parma_ham", "parsnip", "peach","peanuts", "pear", "peas", "pepitas", "pie", "pig",
                    "pinapple", "pizza", "plum", "pomegranate","popcorn", "potato", "prawn", "pretzel", "prime_rib",
                    "pumpkin", "rack_of_lamb", "rack_of_ribs","radish", "ramen", "raspberries", "rice",
                    "rolled_roast", "sace", "sack_of_flour","salami", "salate", "salmon", "salt",
                    "salt_pork", "sardinie", "sausage","sesame", "sesame_oil", "sheep",
                    "soda_bottle", "soup", "soy","spagetti", "spinach", "steak",
                    "strawberry", "sugar", "sugar_cubes", "sushi","sweet_potato", "sweetener", "taco",
                    "tea", "tomato", "tortilla", "turnip","vinagera", "waffles", "watter_melone", "whisky",
                    "whortleberry", "whortleberry_jelly", "wine_bottle","wrap", "yoghurt",
                    "zucchini","yoyo","tupper_ware","t_shirt","tray","torch","toothbrush",
                    "tool","toilet_paper","toaster","toast","thongs","sunglasses","stapler",
                    "spice","socks","liquid","soap","oblito","shovel","scissors","scale",
                    "rouge","rolling_pin","record","pushpin","powder","pottea",
                    "plunger","plate","pizza_cutter","pill_bottle","pet_dish","pencil",
                    "pen","peeler","parfume","pantyhose","pants","paint","oven","nail_polish",
                    "mincer","microwave","marking_pin","make_up","liquid_soap","knife",
                    "jack_o_lantern","house-cleaning","hook","hand-blender","handbag",
                    "gyufa","greenpot","green","glue","glass","ginger","fridge","fork",
                    "facial_tissue","facial","eyelash","eggclock","duster","cup","cosmetics",
                    "corkscrew","cooking_pot","cooking_spoon","cooker","containerglass",
                    "container","comb","color_pencils","coffee_pot","coffee_percolator",
                    "cloves","chair","chain","canoppener","candel","bulb","brick","bottle",
                    "blue","blender","batteryborrel","battery","basket","bandages","bag",
                    "baby","apron","aluminum_foil","accumulator"};
            SQLiteDatabase localSQLiteDatabasew = getWritableDatabase();
            for (int i = 0; i <images.length; i++) {
                localContentValues.put("Name", list[i]);
                localContentValues.put("IMAGE", images[i]);
                localContentValues.put("LANG", "en");
                localSQLiteDatabasew.insert(isTableName, null, localContentValues);
                localContentValues.clear();
            }
            for (int i = 0; i <images.length; i++) {
                localContentValues.put("Name", list_hun[i]);
                localContentValues.put("IMAGE", images[i]);
                localContentValues.put("LANG", "hu");
                localSQLiteDatabasew.insert(isTableName, null, localContentValues);
                localContentValues.clear();
            }
            for (int i = 0; i <images.length; i++) {
                localContentValues.put("Name", list_hun[i]);
                localContentValues.put("IMAGE", images[i]);
                localContentValues.put("LANG", "de");
                localSQLiteDatabasew.insert(isTableName, null, localContentValues);
                localContentValues.clear();
            }
        }
    }
    /**
     * Bejelentkezés/vagy regisztrál egy felhasználót
     * @param paramString1-email

     * @return Cursor
     */
    public String login(String paramString1)
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
        localCursor.moveToNext();
        String id=localCursor.getString(localCursor.getColumnIndex("ID"));
        return id;
    }
    public String getLists()
    {
        fillTable();
        String return_string="";
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT * FROM "+uTableName+"";
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        if(localCursor.getCount()>0){
        while(localCursor.moveToNext())
        {
            return_string=return_string+localCursor.getString(localCursor.getColumnIndex("userName"))+","+localCursor.getString(localCursor.getColumnIndex("ID"))+",";
            String id=localCursor.getString(localCursor.getColumnIndex("ID"));
            String str2="SELECT Name FROM "+iTableName+" where OWNER="+id+"";
            Cursor localCursor2 = localSQLiteDatabase.rawQuery(str2, null);
            return_string=return_string+localCursor2.getCount()+";";
        }}
        else
        {
            return_string=" , , , ;";
        }
        return return_string;
    }
    public  boolean getName(String id)
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT * FROM "+uTableName+" where userName='"+id+"'";
        //  Log.d("SQL", str1);
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        if(localCursor.getCount()>0)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    /**
     * A megadott item nevét beadva megkeressük a listában
     * @param name
     * @return images
     */
    public  String getItemName(String name)
    {
        String images;
        String language=lang;
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT IMAGE FROM "+isTableName+" where Name='"+name+"' and LANG='"+language+"'";
         Log.d("SQL", str1);
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        localCursor.moveToNext();
        if(localCursor.getCount()==0)
        {
            images="0";
        }
        else {
            images = localCursor.getString(localCursor.getColumnIndex("IMAGE"));
        }
        Log.d("kép",images);
        return images;
    }
    public  Cursor getItems(String id)
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT * FROM "+iTableName+" where OWNER='"+id+"' ORDER BY Name ASC";
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
            localContentValues.put("OWNER", id);
            localSQLiteDatabase.insert(iTableName, null, localContentValues);
            localContentValues.clear();
        }
        //databasePrinter(id);
    }
    public void databasePrinter()
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "SELECT * FROM "+iTableName+"";
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
        SQLiteDatabase localSQLiteDatabaseW = getWritableDatabase();
        localSQLiteDatabaseW.delete(iTableName,"OWNER='"+ id+"' and Name='"+name+"'",null);
    }
    public void deleteList(String name)
    {
        //Log.d("deleteList-->","-"+name+"-");
        SQLiteDatabase localSQLiteDatabaseW = getWritableDatabase();
        SQLiteDatabase localSQLiteDatabaseR = getReadableDatabase();
        String str1 = "SELECT ID FROM "+uTableName+" where userName='"+name+"'";
        Cursor localCursor = localSQLiteDatabaseR.rawQuery(str1, null);
        localCursor.moveToNext();
        String id=localCursor.getString(localCursor.getColumnIndex("ID"));
        localSQLiteDatabaseW.delete(iTableName,"OWNER='"+id+"'",null);
        localSQLiteDatabaseW.delete(uTableName,"userName='"+name+"'",null);
    }
    public boolean renameList(String name,String newName)
    {
        //Log.d("deleteList-->","-"+newName+"-");
        SQLiteDatabase localSQLiteDatabaseW = getWritableDatabase();
        SQLiteDatabase localSQLiteDatabaseR = getReadableDatabase();
        String str1 = "SELECT ID FROM "+uTableName+" where userName='"+newName+"'";
        Cursor localCursor = localSQLiteDatabaseR.rawQuery(str1, null);
        if(localCursor.getCount()>0)
        {
            return false;
        }
        else {
            ContentValues localContentValues = new ContentValues();
            localContentValues.put("userName",newName);
            localSQLiteDatabaseW.update(uTableName, localContentValues, "userName='"+name+"'", null);
            localContentValues.clear();
            return true;
        }
    }
    public String getId(String name)
    {
        String id=null;
        SQLiteDatabase localSQLiteDatabaseR = getReadableDatabase();
        String str1 = "SELECT ID FROM "+uTableName+" where userName='"+name+"'";
        Cursor localCursor = localSQLiteDatabaseR.rawQuery(str1, null);
        localCursor.moveToNext();
        id=localCursor.getString(localCursor.getColumnIndex("ID"));
        return id;
    }

    public void copyList(String name)
    {
        String id=null;
        SQLiteDatabase localSQLiteDatabaseR = getReadableDatabase();
        SQLiteDatabase localSQLiteDatabaseW = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        String str1 = "SELECT userName FROM "+uTableName+" where ID='"+name+"'";
        Cursor localCursor = localSQLiteDatabaseR.rawQuery(str1, null);
        localCursor.moveToNext();
        id=localCursor.getString(localCursor.getColumnIndex("userName"));
        Random r = new Random();
        int i1 = r.nextInt(100);
        String name1=id+i1;
        addData(name1);
        String id1=getId(name1);
        str1 = "SELECT * FROM "+iTableName+" where OWNER='"+name+"'";
        localCursor = localSQLiteDatabaseR.rawQuery(str1, null);
        while(localCursor.moveToNext())
        {
            localContentValues.put("Name", localCursor.getString(localCursor.getColumnIndex("Name")));
            localContentValues.put("OWNER", id1);
            localSQLiteDatabaseW.insert(iTableName, null, localContentValues);
            localContentValues.clear();
        }



    }
      }

