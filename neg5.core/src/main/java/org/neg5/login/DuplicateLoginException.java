package org.neg5.login;

public class DuplicateLoginException extends Exception {

    public DuplicateLoginException(String message) {
        super(message);
    }
}
