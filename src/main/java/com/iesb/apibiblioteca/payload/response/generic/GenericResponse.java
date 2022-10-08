package com.iesb.apibiblioteca.payload.response.generic;

public class GenericResponse {
    private String message;

    public GenericResponse(String m) {
        message = m;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
