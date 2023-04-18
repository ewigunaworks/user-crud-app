package com.sawitpro.sawitpro.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {
  @NotEmpty
  @Size(max = 60, message = "user name should have max 60 characters")
  private String name;

  @NotEmpty
  @Size(min=10, max = 13, message = "phone number should have 10-13 characters")
  @Pattern(regexp="/(08)\\d/g",message="should start with 08")  
  private String phoneNumber;

  @NotEmpty
  @Size(min=1, max = 16, message = "password should have 1-16 characters")
  @Pattern(regexp="/[0-9A-Z]{1,16}/g")  
  private String password;

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
}
