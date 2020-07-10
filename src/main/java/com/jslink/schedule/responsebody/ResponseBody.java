package com.jslink.schedule.responsebody;

import lombok.Data;

@Data
public class ResponseBody<T> {
    private boolean result;
    private String message;
    private T data;

    public ResponseBody() {
    }

    public ResponseBody(boolean result) {
        this.result = result;
    }

    public ResponseBody(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public ResponseBody(boolean result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }
}
