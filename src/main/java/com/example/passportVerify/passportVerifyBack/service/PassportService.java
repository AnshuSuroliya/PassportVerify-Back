package com.example.passportVerify.passportVerifyBack.service;

import com.example.passportVerify.passportVerifyBack.entity.PassportData;
import com.example.passportVerify.passportVerifyBack.repository.PassportDataRepository;
import com.example.passportVerify.passportVerifyBack.request.PassportDataRequest;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PassportService {
    @Autowired
    PassportDataRepository passportDataRepository;
    public String registerPassport(PassportDataRequest passportDataRequest) throws TesseractException {
        File image = new File("src/main/resources/images/OIP.jpg");
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/main/resources/tessdata");
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        String result = tesseract.doOCR(image);
        if(result.contains(passportDataRequest.getFirstName()) && result.contains(passportDataRequest.getLastName()) && result.contains(passportDataRequest.getPassportNumber())){
            PassportData passportData=new PassportData();
            passportData.setFirstName(passportDataRequest.getFirstName());
            passportData.setLastName(passportDataRequest.getLastName());
            passportData.setPassportNumber(passportDataRequest.getPassportNumber());
            passportData.setPhoneNumber(passportDataRequest.getPhoneNumber());
            passportData.setAge(passportDataRequest.getAge());
            passportData.setAddress(passportDataRequest.getAddress());
            passportData.setEmail(passportDataRequest.getEmail());
            passportDataRepository.save(passportData);
            return "Registered Successfully";
        }
        System.out.println(result);
        return "Invalid Details";
    }
}
