package com.dunkware.trade.service.web.server.storage.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dunkware.trade.service.web.server.storage.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

}
