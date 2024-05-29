package com.dunkware.stream.data.cassy.repository.session;

import org.springframework.stereotype.Repository;

import com.dunkware.stream.data.cassy.entity.sesion.StreamSessionKey;
import com.dunkware.stream.data.cassy.entity.sesion.StreamSessionRow;
import com.dunkware.stream.data.cassy.support.CustomCassandraRepository;

@Repository()
public interface StreamSessionRepo extends CustomCassandraRepository<StreamSessionRow, StreamSessionKey>, StreamSessionRepoCustom {


}
