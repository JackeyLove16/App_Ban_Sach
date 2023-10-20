package com.example.app_ban_sach.model;

import java.util.List;

public class sachcuModel {
    boolean success;
    String message;
    List<sachcu> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<sachcu> getResult() {
        return result;
    }

    public void setResult(List<sachcu> result) {
        this.result = result;
    }
}
