package com.dunkware.trade.service.data.tester;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;

public class CassandraConfig extends AbstractCassandraConfiguration {


    @Override
    protected String getKeyspaceName() {
        return "dunkstreet";
    }

    @Bean
    @Override
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean cassandraSession = super.cassandraSession();//super session should be called only once
        InetSocketAddress sockaddr = new InetSocketAddress("testrock1.dunkware.net", 31994);
        Collection<InetSocketAddress> adrs =  new ArrayList<InetSocketAddress>();
        adrs.add(sockaddr);
        cassandraSession.setContactPoints(adrs);
        cassandraSession.setUsername("cassandra");
        cassandraSession.setPassword("FjqO0vMuGUSr");
        return cassandraSession;
    }
}
