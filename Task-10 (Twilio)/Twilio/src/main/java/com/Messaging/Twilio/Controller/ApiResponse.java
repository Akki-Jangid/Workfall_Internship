package com.Messaging.Twilio.Controller;

import lombok.Data;

@Data
public class ApiResponse {

    private boolean status;
    private String message;

    public ApiResponse(boolean status, String message){
        this.status = status;
        this.message = message;
    }
}
