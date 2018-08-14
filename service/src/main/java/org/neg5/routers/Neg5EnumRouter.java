package org.neg5.routers;

import com.google.inject.Inject;
import org.neg5.core.EnumSerializer;
import org.neg5.enums.TossupAnswerType;

public class Neg5EnumRouter extends AbstractJsonRouter {

    private EnumSerializer enumSerializer;

    @Inject
    public Neg5EnumRouter(EnumSerializer enumSerializer) {
        this.enumSerializer = enumSerializer;
        addEnums();
    }

    @Override
    public void registerRoutes() {
        get("neg5-api/*/enums", (request, response) -> enumSerializer.serialize());
    }

    private void addEnums() {
        enumSerializer.addEnums(
                TossupAnswerType.class
        );
    }
}
