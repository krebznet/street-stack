package com.dunkware.net.cluster.node.grpc;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dunkware.net.proto.cluster.node.GClusterNodeServiceGrpc.GClusterNodeServiceImplBase;

import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService()
@Service
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrpcNodeService extends GClusterNodeServiceImplBase {

	
	@PostConstruct
	public void load() { 
		System.out.println("here!");
	}
	
	
	

}
