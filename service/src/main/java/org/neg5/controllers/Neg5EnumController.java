package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.core.EnumSerializer;
import org.neg5.enums.MatchResult;
import org.neg5.enums.TossupAnswerType;

public class Neg5EnumController extends AbstractJsonController {

    private final EnumSerializer enumSerializer;

    @Inject
    public Neg5EnumController(EnumSerializer enumSerializer) {
        this.enumSerializer = enumSerializer;
        addEnums();
    }

    @Override
    public void registerRoutes() {
        get("neg5-api/*/enums", (request, response) -> enumSerializer.serialize());
    }

    private void addEnums() {
        enumSerializer
                .add(TossupAnswerType.class)
                .add(MatchResult.class);
    }
}
