package org.neg5;

public class ScoresheetPlayerDTO {

    private String playerId;
    private boolean active;
    private int tuh;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public boolean getIsActive() {
        return active;
    }

    public void setIsActive(boolean active) {
        this.active = active;
    }

    public int getTuh() {
        return tuh;
    }

    public void setTuh(int tuh) {
        this.tuh = tuh;
    }
}
