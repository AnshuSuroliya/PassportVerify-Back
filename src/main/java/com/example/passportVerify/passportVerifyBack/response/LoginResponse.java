package com.example.passportVerify.passportVerifyBack.response;


public class LoginResponse {
  private String jwt;
  private Long id;
    public LoginResponse(){

    }
    public LoginResponse(String jwt, Long id) {
        this.jwt = jwt;
        this.id = id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
