package com.sawitpro.sawitpro.response;

public class ErrorBase {
  private Boolean success;
  private String message;


  public Boolean isSuccess() {
    return this.success;
  }

  public Boolean getSuccess() {
    return this.success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
