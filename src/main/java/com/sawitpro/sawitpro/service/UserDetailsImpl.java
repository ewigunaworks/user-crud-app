package com.sawitpro.sawitpro.service;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sawitpro.sawitpro.model.User;

public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

  private String phoneNumber;

  @JsonIgnore
	private String password;

  public UserDetailsImpl(Long id, String username, String phoneNumber, String password) {
		this.id = id;
		this.name = username;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

  public static UserDetailsImpl build(User user) {
		return new UserDetailsImpl(
				user.getId(), 
				user.getName(), 
				user.getPhoneNumber(),
				user.getPassword()
    );
	}

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
    // TODO Auto-generated method stub
  }

  @Override
  public String getPassword() {
    // TODO Auto-generated method stub
    return password;
  }

  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return phoneNumber;
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
