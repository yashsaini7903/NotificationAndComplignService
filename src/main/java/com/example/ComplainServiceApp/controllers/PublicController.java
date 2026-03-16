package com.example.ComplainServiceApp.controllers;

import com.example.ComplainServiceApp.entity.User;
import com.example.ComplainServiceApp.services.UserDetailServiceImp;
import com.example.ComplainServiceApp.services.UserSevice;
import com.example.ComplainServiceApp.utility.JwtUtilities;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
    @Autowired
    private UserSevice userSevice;
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailServiceImp userDetailService;

  @Autowired
  private JwtUtilities jwtUtilities;

    @GetMapping("/")
    public ResponseEntity<?> healthCheck(){
       return new ResponseEntity<>("okkh", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user,HttpServletResponse response){
        try{
            userSevice.saveNewUser(user);

            String jwt = jwtUtilities.generateToken(user.getUsername());
            Cookie cokkie = new Cookie("jwt", jwt);
            cokkie.setHttpOnly(true);
            cokkie.setSecure(false);
            cokkie.setPath("/");
            response.addCookie(cokkie);

            return new ResponseEntity<>("signup sucseessful", HttpStatus.OK);
        } catch (Exception e) {
            log.error("expecction during signup",e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtilities.generateToken(userDetails.getUsername());

            Cookie cookie = new Cookie("jwt", jwt);
            cookie.setHttpOnly(true);     // JS cannot access (security)
            cookie.setSecure(false);      // true in HTTPS production
            cookie.setPath("/");          // send for all APIs
           // cookie.setMaxAge(24 * 60 * 60); // 1 day

            response.addCookie(cookie);
            return new ResponseEntity<>(jwt,HttpStatus.OK);
        } catch (Exception e) {
            log.error("exeption during login",e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
