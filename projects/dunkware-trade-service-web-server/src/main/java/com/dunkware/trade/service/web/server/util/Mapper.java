package com.dunkware.trade.service.web.server.util;

import com.dunkware.trade.service.web.server.model.UserCreationRequest;
import com.dunkware.trade.service.web.server.model.UserDto;
import com.dunkware.trade.service.web.server.storage.entity.UserEntity;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper
public interface Mapper {

    Mapper INSTANCE = Mappers.getMapper(Mapper.class);


    UserDto map(UserEntity userEntity);

    List<UserDto> map(Iterable<UserEntity> userEntities);

    @Mapping(ignore = true, target = "userId")
    UserEntity map(UserCreationRequest request);

}
