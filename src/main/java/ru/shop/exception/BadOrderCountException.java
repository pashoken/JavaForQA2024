package ru.shop.exception;

public class BadOrderCountException extends Exception{
    public BadOrderCountException(Long count) {
        super("Order count must be positive.Order count is = " +  count);
    }
}
