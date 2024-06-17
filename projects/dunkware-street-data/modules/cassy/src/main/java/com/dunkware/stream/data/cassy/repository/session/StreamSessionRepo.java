package com.dunkware.stream.data.cassy.repository.session;

import org.springframework.stereotype.Repository;

import com.dunkware.stream.data.cassy.entity.sesion.DBStreamSessionKey;
import com.dunkware.stream.data.cassy.entity.sesion.DBStreamSessionRow;
import com.dunkware.stream.data.cassy.support.CustomCassandraRepository;

@Repository()
public interface StreamSessionRepo extends CustomCassandraRepository<DBStreamSessionRow, DBStreamSessionKey>, StreamSessionRepoCustom {


}
