package com.dunkware.trade.service.web.server.storage.service;


import com.dunkware.trade.service.web.server.model.User;
import com.dunkware.trade.service.web.server.storage.entity.UserEntity;
import com.dunkware.trade.service.web.server.storage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StorageService {

    @Autowired
   private UserRepository userRepository;

   public User getUserById(Optional<Long> id, Optional<String> email, Optional<String> password) {

       Optional<UserEntity> userData = Optional.empty();
       if (email.isPresent() && password.isPresent()) {
           userData = userRepository.findByEmailAndPassword(email.get(), password.get());
       } else{
           userData = userRepository.findById(id.get());
       }
        if (userData.isPresent()) {
           return userData.stream().map( it -> {
                        User user = new User();
                        user.setUserId(it.getUserId());
                        user.setEmail(it.getEmail());
                        user.setFirstName(it.getFirstName());
                        user.setLastName(it.getLastName());
                        user.setAddress(it.getAddress());
                        return user;
                    }
            ).collect(Collectors.toList()).get(0);
        } else{
            throw new EntityNotFoundException("User not found");
        }
    }

    public void addUser(User user) {
       UserEntity entity = new UserEntity();
       entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setFirstName(user.getFirstName());
       entity.setLastName(user.getLastName());
       entity.setCreatedOn(LocalDateTime.now());
        entity.setUpdatedOn(LocalDateTime.now());
        entity.setAddress(user.getAddress());
        Optional<UserEntity> userData = userRepository.findByEmail(user.getEmail());
        if (userData.isPresent()) {
            throw new EntityNotFoundException("email already exits");
        }
        userRepository.save(entity);
    }
}
