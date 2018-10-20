package org.neg5.enums;

public enum MatchResult implements StringIdentifiable {

    WIN("W"),
    LOSS("L"),
    TIE("T");

    private String id;

    MatchResult(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
