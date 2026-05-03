package com.liberty.demo.service.exception;

public class RedisOperationNotFound extends RuntimeException {
    public RedisOperationNotFound(String message) {
        super(message);
    }
}
