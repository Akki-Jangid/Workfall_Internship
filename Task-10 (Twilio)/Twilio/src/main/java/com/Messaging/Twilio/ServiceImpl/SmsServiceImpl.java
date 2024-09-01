package com.Messaging.Twilio.ServiceImpl;

import com.Messaging.Twilio.Configuration.TwilioConfig;
import com.Messaging.Twilio.Controller.ApiResponse;
import com.Messaging.Twilio.Dto.SmsRequest;
import com.Messaging.Twilio.Exception.TwilioException.TwilioCustomException;
import com.Messaging.Twilio.Service.SmsService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Autowired
    private TwilioConfig twilioConfig;


    @Override
    public ApiResponse sendSms(SmsRequest request) {

        request.setDestinationPhoneNumber("+91"+request.getDestinationPhoneNumber());
        try{
            Message.creator( new PhoneNumber(request.getDestinationPhoneNumber()),
                    new PhoneNumber(twilioConfig.getTwilioPhoneNumber()),
                    request.getSmsBody()).create();
        }catch (Exception e){
            throw new TwilioCustomException(e.getMessage());
        }

        return new ApiResponse(true, "SMS Sent Successfully!");
    }
}
