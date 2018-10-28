package org.neg5.core;

public class CurrentUserContext {

    private final ThreadLocal<String> currentUser;

    protected CurrentUserContext() {
        currentUser = new ThreadLocal<>();
    }

    public String getUserData() {
        return currentUser.get();
    }

    public void clear() {
        currentUser.remove();
    }

    public void set(String user) {
        this.currentUser.set(user);
    }

}
