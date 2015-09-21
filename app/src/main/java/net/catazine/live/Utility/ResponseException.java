package net.catazine.live.Utility;

import java.io.IOException;

public class ResponseException extends IOException {

    private final int statusCode;

    public ResponseException(int statusCode, final String body) {
        super(body);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

}
