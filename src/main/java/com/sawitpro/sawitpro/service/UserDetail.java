package com.sawitpro.sawitpro.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetail extends User {
  private String phoneNumber;

    public UserDetail(String phoneNumber, String password, Collection<? extends GrantedAuthority> authorities) {
        super(phoneNumber, password, authorities);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
