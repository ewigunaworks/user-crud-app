package com.sawitpro.sawitpro.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UpdateUserDto {
  @NotEmpty
  @Size(max = 60, message = "user name should have max 60 characters")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
