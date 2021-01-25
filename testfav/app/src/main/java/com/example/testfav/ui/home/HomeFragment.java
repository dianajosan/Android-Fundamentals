package com.example.testfav.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfav.CountriesAdapter;
import com.example.testfav.CountriesItem;
import com.example.testfav.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<CountriesItem> countriesItems=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView=root.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CountriesAdapter(countriesItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        countriesItems.add(new CountriesItem(R.drawable.london, "London", "0", "0"));
        countriesItems.add(new CountriesItem(R.drawable.paris_2, "Paris", "1", "0"));
        countriesItems.add(new CountriesItem(R.drawable.rome, "Rome", "2", "0"));
        countriesItems.add(new CountriesItem(R.drawable.prague, "Prague", "3", "0"));
        countriesItems.add(new CountriesItem(R.drawable.barcelona, "Barcelona", "4", "0"));
        countriesItems.add(new CountriesItem(R.drawable.amsterdam, "Amsterdam", "5", "0"));
        countriesItems.add(new CountriesItem(R.drawable.copenhagen, "Copenhagen", "6", "0"));
        countriesItems.add(new CountriesItem(R.drawable.frankfurt, "Frankfurt", "7", "0"));

        return root;
    }
}