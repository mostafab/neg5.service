package org.neg5.transformers;

import com.google.inject.Inject;
import org.neg5.core.GsonProvider;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    @Inject private GsonProvider gsonProvider;

    @Override
    public String render(Object o) {
        return gsonProvider.get().toJson(o);
    }
}
