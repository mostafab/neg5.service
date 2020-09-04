package org.neg5.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Provider;

import java.time.LocalDate;

public class GsonProvider implements Provider<Gson> {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new IdentifiableTypeAdapterFactory())
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .create();

    @Override
    public Gson get() {
        return gson;
    }
}
