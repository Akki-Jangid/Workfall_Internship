package com.Messaging.Twilio.Controller;

import com.Messaging.Twilio.Dto.SmsRequest;
import com.Messaging.Twilio.ServiceImpl.SmsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/sms")
@Validated
public class SmsController {

    @Autowired
    private SmsServiceImpl smsService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse> sendSms(@Valid @RequestBody SmsRequest request){
        return ResponseEntity.ok(smsService.sendSms(request));

    }
}
