package com.example.book.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceType,String identifier,Long id)
    {
        super("Resources of type '"+resourceType+"' with identifier '"+identifier+"'not found.");
    }
}
