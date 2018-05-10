package com.ewulusen.disastersoft.checklist;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
public class itemAdapter extends RecyclerView.Adapter<itemAdapter.MyViewHolder> {
    private List<item> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, menny, db;
        public ImageView img;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            menny = (TextView) view.findViewById(R.id.menn);
            db = (TextView) view.findViewById(R.id.db);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
            img = view.findViewById(R.id.image);
        }
    }


    public itemAdapter(List<item> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String[] list={"alap","apple","asparagus","avocado","bacon","banana","banana_split","bavarian_pretzel","beer","beet","bento","birthday_cake","	bread","broccoli","bull","cabbage","cafe","carrot","celery","checklist","cheese","cherry","chicken","chili_pepper","cinnamon_roll","citrus","coconut_cocktail","cookies","corn","cotton_candy","cow","crab","cucumber","cupcake","dim_sum","dolmades","doughnut","zolidragon_fruit","duck","durian","eggplant","eggs","filleted_fish","fish","french_fries","garlic","grapes","hamburger","hazelnut","honey","hops","horse","hot_chocolate","hot_dog","kebab","kiwi","lettuce","macaron","melon","milk","mushroom","nachos","noodles","nut","octopus","olive_oil","onion","pancake","paprika","peanuts","pear","peas","pig","pineapple","pizza","plum","pomegranate","potato","prawn","rack_of_lamb","raspberry","sack_of_flour","sesame","sheep","shellfish","soda_bottle","soy","spaghetti","steak","strawberry","sugar","sugar_cubes","sushi","sweet_potato","sweetener","salami","taco","tea","tomato","watermelon","wine_bottle","wrap"};
        int[] images={R.drawable.alap,R.drawable.apple,R.drawable.asparagus,R.drawable.avocado,R.drawable.bacon,R.drawable.banana,R.drawable.banana_split,R.drawable.bavarian_pretzel,R.drawable.beer,R.drawable.beet,R.drawable.bento,R.drawable.birthday_cake,R.drawable.	bread,R.drawable.broccoli,R.drawable.bull,R.drawable.cabbage,R.drawable.cafe,R.drawable.carrot,R.drawable.celery,R.drawable.checklist,R.drawable.cheese,R.drawable.cherry,R.drawable.chicken,R.drawable.chili_pepper,R.drawable.cinnamon_roll,R.drawable.citrus,R.drawable.coconut_cocktail,R.drawable.cookies,R.drawable.corn,R.drawable.cotton_candy,R.drawable.cow,R.drawable.crab,R.drawable.cucumber,R.drawable.cupcake,R.drawable.dim_sum,R.drawable.dolmades,R.drawable.doughnut,R.drawable.	dragon_fruit,R.drawable.duck,R.drawable.durian,R.drawable.eggplant,R.drawable.eggs,R.drawable.filleted_fish,R.drawable.fish,R.drawable.french_fries,R.drawable.garlic,R.drawable.grapes,R.drawable.hamburger,R.drawable.hazelnut,R.drawable.honey,R.drawable.hops,R.drawable.horse,R.drawable.hot_chocolate,R.drawable.hot_dog,R.drawable.kebab,R.drawable.kiwi,R.drawable.lettuce,R.drawable.macaron,R.drawable.melon,R.drawable.milk,R.drawable.mushroom,R.drawable.nachos,R.drawable.noodles,R.drawable.nut,R.drawable.octopus,R.drawable.olive_oil,R.drawable.onion,R.drawable.pancake,R.drawable.paprika,R.drawable.peanuts,R.drawable.pear,R.drawable.peas,R.drawable.pig,R.drawable.pineapple,R.drawable.pizza,R.drawable.plum,R.drawable.pomegranate,R.drawable.potato,R.drawable.prawn,R.drawable.rack_of_lamb,R.drawable.raspberry,R.drawable.sack_of_flour,R.drawable.sesame,R.drawable.sheep,R.drawable.shellfish,R.drawable.soda_bottle,R.drawable.soy,R.drawable.spaghetti,R.drawable.steak,R.drawable.strawberry,R.drawable.sugar,R.drawable.sugar_cubes,R.drawable.sushi,R.drawable.sweet_potato,R.drawable.sweetener,R.drawable.salami,R.drawable.taco,R.drawable.tea,R.drawable.tomato,R.drawable.watermelon,R.drawable.wine_bottle,R.drawable.wrap};
        item movie = moviesList.get(position);
        holder.name.setText(movie.getName());
        holder.menny.setText(movie.getMenny());
        holder.db.setText(movie.getDb());
        int found=0;
        for(int i=0;i<list.length;i++)
        {
          /*  Log.d("item",movie.getName());
            Log.d("item2",list[i]);*/
            if(list[i].equals(movie.getName())){
                found=i;
                break;
            }
           /// Log.d("found",found+"");
        }

       holder.img.setImageResource(images[found]);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void removeItem(int position) {
        moviesList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(item movie, int position) {
        moviesList.add(position, movie);
        // notify item added by position
        notifyItemInserted(position);
    }
}