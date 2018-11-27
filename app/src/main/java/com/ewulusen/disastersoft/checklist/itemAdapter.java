package com.ewulusen.disastersoft.checklist;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private Context mContext;
    databaseHelper userDB;
    ViewGroup parent2=null;
    View view2;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, menny, db;
        public ImageView img;
        public RelativeLayout viewBackground, viewForeground;
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            menny = (TextView) view.findViewById(R.id.menn);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
            img = view.findViewById(R.id.image);
            view2=view;
        }
    }


    public itemAdapter(List<item> moviesList,Context mContext) {
        this.mContext=mContext;
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

        item movie = moviesList.get(position);
        holder.name.setText(movie.getName());
        String[] expl=movie.getName().split(" ");
        userDB=new databaseHelper(mContext);
        String imageName="0";
        imageName=userDB.getItemName(movie.getName());
        if(!imageName.equals("0"))
        {
            holder.name.setText(movie.getName());
            holder.menny.setText("");
        }
        else {
            int i = 0;
            for (i = 0; i < expl.length; i++) {
                //Log.d("hossz",expl[i]);
                imageName = userDB.getItemName(expl[i]);
                if (!imageName.equals("0")) {
                    holder.name.setText(expl[i]);
                    holder.menny.setText(movie.getName().replace(expl[i], ""));
                    break;
                } else {
                    imageName = "alap";
                }
            }
        }

        holder.img.setImageResource(mContext.getResources().getIdentifier("com.ewulusen.disastersoft.checklist:drawable/"+imageName,null,null));


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