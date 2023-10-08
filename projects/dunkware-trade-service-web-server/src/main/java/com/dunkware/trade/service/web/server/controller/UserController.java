package com.dunkware.trade.service.web.server.controller;

import com.dunkware.trade.service.web.server.exception.EmailAlreadyExistsException;
import com.dunkware.trade.service.web.server.exception.PasswordMismatchException;
import com.dunkware.trade.service.web.server.model.UserCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dunkware.trade.service.web.server.storage.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/echo")
    public @ResponseBody() String echo() {
    	return "hello";
    }

    @GetMapping("/log-in")
    public ResponseEntity logIn(@RequestParam(name = "username") String email,
                                 @RequestParam String password) {
        try {
            return new ResponseEntity<>(this.userService.logIn(email, password),
                    HttpStatus.OK);
        } catch (EntityNotFoundException | PasswordMismatchException ex) {
            return new ResponseEntity<>(ex.getMessage(),
                    HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@Valid @RequestBody UserCreationRequest request) {
        try {
            return new ResponseEntity<>(this.userService.signUp(request),
                    HttpStatus.OK);
        } catch (EmailAlreadyExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(path = "/find-all")
    public ResponseEntity findAll() {
    	return new ResponseEntity<>(this.userService.findAll(),
                HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteById(@PathVariable Long userId) {
        this.userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
