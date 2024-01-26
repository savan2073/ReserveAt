package com.example.ReserveAt.Response;

public class UnavailableEmployeeException extends RuntimeException{

    public UnavailableEmployeeException(String message) {
        super(message);
    }
}
