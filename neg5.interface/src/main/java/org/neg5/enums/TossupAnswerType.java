package org.neg5.enums;

public enum TossupAnswerType implements StringIdentifiable {

    BASE("Base"),
    POWER("Power"),
    NEG("Neg");

    private String id;

    TossupAnswerType(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
