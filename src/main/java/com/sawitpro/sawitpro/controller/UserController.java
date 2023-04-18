package com.sawitpro.sawitpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sawitpro.sawitpro.dto.UpdateUserDto;
import com.sawitpro.sawitpro.model.User;
import com.sawitpro.sawitpro.repository.UserRepository;
import com.sawitpro.sawitpro.response.ErrorBase;
import com.sawitpro.sawitpro.response.GetUsername;
import com.sawitpro.sawitpro.response.UpdateUser;
import com.sawitpro.sawitpro.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @PostMapping("/update")
  public ResponseEntity<?> updateUser(@RequestBody UpdateUserDto userDto, HttpServletRequest request) {
    String header = request.getHeader("Authorization");

    String phoneNumber = jwtTokenUtil.extractToken(header);
    User userData = userRepository.findByPhoneNumber(phoneNumber);
    if (userData != null) {
      userData.setName(userDto.getName());
    } else {
      ErrorBase res = new ErrorBase();
      res.setSuccess(false);
      res.setMessage("Phone number is not exist");
      return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    userRepository.save(userData);

    UpdateUser res = new UpdateUser();
    res.setId(userData.getId());
    res.setName(userData.getName());
    res.setPhoneNumber(userData.getPhoneNumber());
    res.setSuccess(true);
    res.setMessage("Update user success");

    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @GetMapping("/get-name")
  public ResponseEntity<?> getUser(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    
    String phoneNumber = jwtTokenUtil.extractToken(header);
    User userData = userRepository.findByPhoneNumber(phoneNumber);

    if (userData != null) {
      GetUsername res = new GetUsername();
      res.setName(userData.getName());
      res.setSuccess(true);
      res.setMessage("Get user success");
      return new ResponseEntity<>(res, HttpStatus.OK);
    } else {
      ErrorBase res = new ErrorBase();
      res.setSuccess(false);
      res.setMessage("Phone number is not exist");
      return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
  }
}
