package com.dunkware.trade.service.web.server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.web.server.model.User;
import com.dunkware.trade.service.web.server.storage.service.StorageService;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {

    @Autowired
    StorageService storageService;

    @GetMapping(path = "/echo")
    public @ResponseBody() String echo() {
    	return "hello";
    }
    @GetMapping()
    public User geUser(@RequestParam(name = "userId", required = false) Optional<Long> userId, @RequestParam(name = "username", required = false) Optional<String> email, @RequestParam(name = "password", required = false) Optional<String> password) {
      return this.storageService.getUserById(userId, email, password);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        this.storageService.addUser(user);
    }
    
    @GetMapping(path = "/list")
    public List<User> userList() { 
    	// TODO: ADAR // help me here needs to call the new method on storageService
    	return  null;
    }
    
    
    // TODO: ADAR help me here api to delete a user 
}
