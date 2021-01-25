package com.example.curssapte;

import java.util.List;

public interface PersonListener {
    public void onPersonsFetchedFromServer(List<Person> personList);

    void onPersonFetchedFromServer(List<Person> personList);
}
