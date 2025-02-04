package com.auth1.auth.learning.controller;

import com.auth1.auth.learning.dtos.GetUserDto;
import com.auth1.auth.learning.dtos.LoginRequestDto;
import com.auth1.auth.learning.dtos.SignupRequestDto;
import com.auth1.auth.learning.dtos.UserEmailDto;
import com.auth1.auth.learning.model.Token;
import com.auth1.auth.learning.model.User;
import com.auth1.auth.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User signUp(@RequestBody SignupRequestDto requestDto){
        return userService.signUp(requestDto.getEmail(),
                requestDto.getPassword(), requestDto.getName());
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto requestDto){
        return userService.login(requestDto.getEmail(),
                requestDto.getPassword());
    }

    @PostMapping("/logout/{token}")
    public ResponseEntity<Void> logout(@PathVariable("token") String token){
        userService.logout(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/validateuser")
    public ResponseEntity<GetUserDto> getUser(@RequestBody UserEmailDto request){
        Optional<User> user = userService.getUser(request.getEmail());
        //
        // return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.badRequest().body(null));
        if(user.isPresent())
        return ResponseEntity.ok(GetUserDto.builder().user(user.get()).build());

        return ResponseEntity.ok(GetUserDto.builder().user(null).error("not found").build());

    }

    @PostMapping("validateToken/{token}")
    public boolean validate(@PathVariable("token") String token){
        return userService.validateToken(token);
    }
}
