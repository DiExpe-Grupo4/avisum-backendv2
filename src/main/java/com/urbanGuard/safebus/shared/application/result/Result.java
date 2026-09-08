package com.urbanGuard.safebus.shared.application.result;

public record Result<T, E>(T value, E error) {
    public static <T, E> Result<T, E> ok(T value) {
        return new Result<>(value, null);
    }
    public static <T, E> Result<T, E> err(E error) {
        return new Result<>(null, error);
    }
    public boolean isOk() { return error == null; }
    public boolean isErr() { return error != null; }
}
