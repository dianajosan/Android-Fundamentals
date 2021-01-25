package com.example.curssapte;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataSource {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://githubusercontent.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public void getPersons(PersonListener listener) {
        GithubApi githubApi = getRetrofit().create(GithubApi.class);
        githubApi.getPersons()
                .enqueue(new Callback<List<Person>>() {
                    @Override
                    public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                        if (response.isSuccessful()) {
                            Log.d("tag", "response was successful");
                            listener.onPersonsFetchedFromServer(response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Person>> call, Throwable throwable) {
                    }
                });
    }

    public List<String> getCars() {
        List<String> cars = new ArrayList<>();
        cars.add("BMW");
        cars.add("Porsche");
        cars.add("Dacia");
        cars.add("VW");
        cars.add("Audi");
        cars.add("Tesla");
        cars.add("Toyota");
        cars.add("Mini");
        cars.add("Lada");
        cars.add("Citroen");
        cars.add("Renault");
        cars.add("Ferarri");
        cars.add("Maseratti");
        cars.add("Alfa romeo");
        cars.add("Lambo");
        cars.add("Ford");

        return cars;

    }
}

