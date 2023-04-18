package com.sawitpro.sawitpro.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sawitpro.sawitpro.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {
  
  @Value("${app.jwtSecret}")
	private String SECRET_KEY;

  @Value("${app.jwtExpirationMs}")
	private int EXPIRATION_TIME;

   public String generateToken(User user) {
    String token = "";
    try {
      Map<String, Object> claims = new HashMap<>();
      claims.put("id", user.getId());
      claims.put("phoneNumber", user.getPhoneNumber());
      
     token = Jwts.builder()
        .setSubject((user.getPhoneNumber()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();

      return token;
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return token;
   }

   public Long getUserIdFromToken(String token) {
      Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();

      return (Long) claims.get("id");
   }

   public boolean validateToken(String token) {
      try {
         Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
         return true;
      } catch (SignatureException ex) {
         System.out.println("Invalid JWT signature");
      } catch (MalformedJwtException ex) {
         System.out.println("Invalid JWT token");
      } catch (ExpiredJwtException ex) {
         System.out.println("Expired JWT token");
      } catch (UnsupportedJwtException ex) {
         System.out.println("Unsupported JWT token");
      } catch (IllegalArgumentException ex) {
         System.out.println("JWT claims string is empty");
      }
      return false;
   }

  public boolean validateToken(String token, UserDetails userDetails) {
    String phoneNumber = getUserNameFromJwtToken(token);
    return phoneNumber.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  String extractPhoneNumber(String token) {
    Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    return claims.get("phoneNumber", String.class);
  }
  
  private boolean isTokenExpired(String token) {
    Date expiration = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
    return expiration.before(new Date());
  }

  public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

  public String extractToken(String header) {
    String token = header.replace("Bearer ", "");

    String phoneNumber = getUserNameFromJwtToken(token);
    return phoneNumber;
  }
}
