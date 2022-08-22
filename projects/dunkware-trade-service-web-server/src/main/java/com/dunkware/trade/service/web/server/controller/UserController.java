package com.dunkware.trade.service.web.server.controller;

import com.dunkware.trade.service.web.server.model.User;
import com.dunkware.trade.service.web.server.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/v1/api/user")
public class UserController {

    @Autowired
    StorageService storageService;

    @GetMapping()
    public User geUser(@RequestParam(name = "userId", required = false) Optional<Long> userId, @RequestParam(name = "username", required = false) Optional<String> email, @RequestParam(name = "password", required = false) Optional<String> password) {
      return this.storageService.getUserById(userId, email, password);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        this.storageService.addUser(user);
    }
}
