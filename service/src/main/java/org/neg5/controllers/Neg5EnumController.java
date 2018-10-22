package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.annotations.Controller;
import org.neg5.core.EnumSerializer;
import org.neg5.enums.MatchResult;
import org.neg5.enums.TossupAnswerType;

@Controller("/neg5-api")
public class Neg5EnumController extends AbstractJsonController {

    private final EnumSerializer enumSerializer;

    @Inject
    public Neg5EnumController(EnumSerializer enumSerializer) {
        this.enumSerializer = enumSerializer;
        addEnums();
    }

    @Override
    public void registerRoutes() {
        get("/*/enums", (request, response) -> enumSerializer.serialize());
    }

    private void addEnums() {
        enumSerializer
                .add(TossupAnswerType.class)
                .add(MatchResult.class);
    }
}
