package com.Messaging.Twilio.Service;

import com.Messaging.Twilio.Controller.ApiResponse;
import com.Messaging.Twilio.Dto.SmsRequest;

public interface SmsService {

    ApiResponse sendSms(SmsRequest request);
}
