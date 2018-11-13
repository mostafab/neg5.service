package org.neg5.util;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.core.GsonProvider;
import spark.Request;

@Singleton
public class RequestHelper {

    @Inject private GsonProvider gsonProvider;

    public <T> T readFromRequest(Request request, Class<T> bodyClazz) {
        return gsonProvider.get().fromJson(request.body(), bodyClazz);
    }
}
