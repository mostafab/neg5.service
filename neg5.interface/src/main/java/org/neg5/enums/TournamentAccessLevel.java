package org.neg5.enums;

/**
 * Represents the levels of access a person can have for a tournament
 */
public enum TournamentAccessLevel {

    NONE(-999),
    COLLABORATOR(1),
    ADMIN(2),
    OWNER(3);

    private final int level;

    TournamentAccessLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
