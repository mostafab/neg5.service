package org.neg5.transformers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new GsonBuilder()
            .create();

    @Override
    public String render(Object o) {
        return gson.toJson(o);
    }
}
