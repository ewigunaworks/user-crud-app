package com.sawitpro.sawitpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sawitpro.sawitpro.dto.UserDto;
import com.sawitpro.sawitpro.model.User;
import com.sawitpro.sawitpro.repository.UserRepository;
import com.sawitpro.sawitpro.response.ErrorBase;
import com.sawitpro.sawitpro.response.Login;
import com.sawitpro.sawitpro.response.SaveUser;
import com.sawitpro.sawitpro.utils.JwtTokenUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
	PasswordEncoder encoder;
  
  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
    if (userRepository.findByPhoneNumber(userDto.getPhoneNumber()) != null) {
      ErrorBase res = new ErrorBase();
      res.setSuccess(false);
      res.setMessage("Phone number already exists");
      return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
   }

    User user = new User();
    user.setName(userDto.getName());
    user.setPhoneNumber(userDto.getPhoneNumber());
    user.setPassword(encoder.encode(userDto.getPassword()));
    userRepository.save(user);

    SaveUser res = new SaveUser();
    res.setId(user.getId());
    res.setName(user.getName());
    res.setPhoneNumber(user.getPhoneNumber());
    res.setSuccess(true);
    res.setMessage("User signup success");

    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<?> signin(@RequestBody UserDto userDto) {
    User user = userRepository.findByPhoneNumber(userDto.getPhoneNumber());

    if (user == null || !encoder.matches(userDto.getPassword(), user.getPassword())) {
      ErrorBase res = new ErrorBase();
      res.setSuccess(false);
      res.setMessage("Incorrect phone number or password");
      return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    String token = "";
    if(encoder.matches(userDto.getPassword(), user.getPassword())) {
      token = jwtTokenUtil.generateToken(user);
    }

    Login res = new Login();
    res.setSuccess(true);
    res.setMessage("Login success");
    res.setToken(token);
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
}
