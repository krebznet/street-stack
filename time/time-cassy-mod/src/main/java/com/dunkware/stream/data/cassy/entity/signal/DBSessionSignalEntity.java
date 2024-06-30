package com.dunkware.stream.data.cassy.entity.signal;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("session_signal_entity")
public class DBSessionSignalEntity {

	@PrimaryKey
    private DBSessionSignalTypeKey key;
	
	@Column("data")
	@CassandraType(type = Name.BLOB)
	private String data;

	@Column("entity")
	private int signal;
	

	

}
