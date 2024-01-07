package com.example.passportVerify.passportVerifyBack.repository;

import com.example.passportVerify.passportVerifyBack.entity.PassportData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportDataRepository extends JpaRepository<PassportData,Long> {

}
