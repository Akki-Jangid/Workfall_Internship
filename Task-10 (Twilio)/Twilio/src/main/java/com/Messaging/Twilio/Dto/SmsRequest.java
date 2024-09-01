package com.Messaging.Twilio.Dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class SmsRequest {

    @Size(min = 10, message = "Phone Number must have 10 digits (Without Country Code)")
    private String destinationPhoneNumber;

    @Size(max = 50, message = "Message Body size must be less than 50")
    private String smsBody;
}
