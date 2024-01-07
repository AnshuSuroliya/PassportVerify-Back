package com.example.passportVerify.passportVerifyBack.controller;

import com.example.passportVerify.passportVerifyBack.request.PassportDataRequest;
import com.example.passportVerify.passportVerifyBack.service.PassportService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PassportController {
@Autowired
    PassportService passportService;
@PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestBody PassportDataRequest passportDataRequest) throws TesseractException {
    return new ResponseEntity<>(passportService.registerPassport(passportDataRequest), HttpStatus.ACCEPTED);
}
}
