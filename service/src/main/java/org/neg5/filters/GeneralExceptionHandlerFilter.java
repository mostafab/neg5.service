package org.neg5.filters;

import com.google.inject.Inject;
import org.neg5.ClientExceptionDTO;
import org.neg5.core.GsonProvider;

import static spark.Spark.exception;

public class GeneralExceptionHandlerFilter implements RequestFilter {

    @Inject private GsonProvider gsonProvider;

    @Override
    public void registerFilter() {
        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            ClientExceptionDTO clientException = new ClientExceptionDTO();
            clientException.setMessage("There was an internal server error.");
            response.body(gsonProvider.get().toJson(clientException));
        });
    }
}
