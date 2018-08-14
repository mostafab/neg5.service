package org.neg5.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Provider;

public class GsonProvider implements Provider<Gson> {

    private Gson gson = new GsonBuilder().create();

    @Override
    public Gson get() {
        return gson;
    }
}
