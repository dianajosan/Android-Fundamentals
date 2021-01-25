package com.example.travelproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Favorites extends Fragment {

    private RecyclerView recyclerView;
    private FavDB favDB;
    private List<FavItem> favItemList=new ArrayList<>();
    private FavAdapter favAdapter;

    private CheckBox checkBox;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstancesState){
        View root= inflater.inflate(R.layout.fragment_favorites, container, false);


        favDB=new FavDB(getActivity());
        recyclerView=root.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadData();
        return root;

    }

    private void loadData() {
        if(favItemList != null){
            favItemList.clear();
        }
        SQLiteDatabase db= favDB.getReadableDatabase();
        Cursor cursor=favDB.select_all_favorite_list();
        try{
            while(cursor.moveToNext()){
                String title=cursor.getString(cursor.getColumnIndex(FavDB.ITEM_TITLE));
                String id=cursor.getString(cursor.getColumnIndex(favDB.KEY_ID));
                int image=Integer.parseInt(cursor.getString(cursor.getColumnIndex(FavDB.ITEM_IMAGE)));
                FavItem favItem=new FavItem(title, id, image);
                favItemList.add(favItem);
            }
        } finally{
            if(cursor !=null && cursor.isClosed())
                cursor.close();
            db.close();
        }

        favAdapter=new FavAdapter(getActivity(), favItemList);
        recyclerView.setAdapter(favAdapter);
    }



}
