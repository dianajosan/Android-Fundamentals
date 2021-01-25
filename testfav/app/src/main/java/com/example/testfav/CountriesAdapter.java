package com.example.testfav;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {
    private ArrayList<CountriesItem> countriesItems;
    private Context context;
    private FavDB favDB;

    public CountriesAdapter(ArrayList<CountriesItem> countriesItems, Context context){
        this.countriesItems=countriesItems;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDB=new FavDB(context);
        //create table on first
        SharedPreferences prefs=context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart=prefs.getBoolean("firstStart", true);
        if(firstStart){
            createTableOfFirstStart();
        }
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,
                parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CountriesAdapter.ViewHolder holder, int position) {

        final CountriesItem countriesItem=countriesItems.get(position);
        readCursorData(countriesItem, holder);
        holder.imageView.setImageResource(countriesItem.getImageResource());
        holder.titleTextView.setText(countriesItem.getTitle());
    }

    @Override
    public int getItemCount() {

        return countriesItems.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView titleTextView;
        Button favBtn;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            titleTextView=itemView.findViewById(R.id.textView);
            favBtn=itemView.findViewById(R.id.favBtn);

            //add to favBtn
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    CountriesItem countriesItem=countriesItems.get(position);

                    if(countriesItem.getFavStatus().equals("0")){
                        countriesItem.setFavStatus("1");
                        favDB.insertIntoDatabase(countriesItem.getTitle(), countriesItem.getImageResource(),
                                countriesItem.getKey_id(), countriesItem.getFavStatus());
                        favBtn.setBackgroundResource(R.drawable.ic_avorite_red);
                    }else {
                        countriesItem.setFavStatus("0");
                        favDB.remove_fav(countriesItem.getKey_id());
                        favBtn.setBackgroundResource(R.drawable.ic_avorite_shadow);
                    }
                }
            });
        }

    }

    private void createTableOfFirstStart() {
        favDB.insertEmpty();

        SharedPreferences prefs=context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorData(CountriesItem countriesItem, ViewHolder viewHolder){
        Cursor cursor=favDB.read_all_data(countriesItem.getKey_id());
        SQLiteDatabase db=favDB.getReadableDatabase();
        try{
            while (cursor.moveToNext()){
                String item_fav_status=cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS));
                countriesItem.setFavStatus(item_fav_status);

                //check fav status
                if(item_fav_status !=null && item_fav_status.equals("1")){
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_avorite_red);
                }else if(item_fav_status !=null && item_fav_status.equals("0")){
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_avorite_shadow);
                }
            }
        } finally {
            if(cursor !=null && cursor.isClosed())
                cursor.close();
            db.close();
        }
    }
}

