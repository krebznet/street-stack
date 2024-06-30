package com.dunkware.trade.service.web.server.storage.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.dunkware.trade.service.web.server.exception.EmailAlreadyExistsException;
import com.dunkware.trade.service.web.server.exception.PasswordMismatchException;
import com.dunkware.trade.service.web.server.model.UserCreationRequest;
import com.dunkware.trade.service.web.server.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.web.server.model.UserDto;
import com.dunkware.trade.service.web.server.storage.entity.UserEntity;
import com.dunkware.trade.service.web.server.storage.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserDto logIn(String email, String password) throws EntityNotFoundException,
																PasswordMismatchException {
		Optional<UserEntity> isUserEntity = userRepository.findByEmail(email);

		if(isUserEntity.isEmpty())
			throw new EntityNotFoundException("User doesn't exist");


		if (! new BCryptPasswordEncoder().matches(password, isUserEntity.get().getPassword()))
			throw new PasswordMismatchException("Incorrect password");

		return Mapper.INSTANCE.map(isUserEntity.get());
	}

	public UserDto signUp(UserCreationRequest request) throws EmailAlreadyExistsException {
		if (userRepository.findByEmail(request.getEmail()).isPresent())
			throw new EmailAlreadyExistsException("Email already exists");

		UserEntity user = Mapper.INSTANCE.map(request);
		user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));

		user = userRepository.save(user);
		return Mapper.INSTANCE.map(user);
	}

	
	public List<UserDto> findAll() {
		return Mapper.INSTANCE.map(this.userRepository.findAll());
	}

	public void deleteById(Long userId) {
		this.userRepository.deleteById(userId);
	}

}
