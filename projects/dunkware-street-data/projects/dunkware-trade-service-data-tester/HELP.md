# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.0/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#boot-features-jpa-and-spring-data)

## grpc client communication 
```
Test.proto 

service HelloWorldService {
  rpc hello(HelloRequest) returns (HelloResponse) {}
}

message HelloRequest {
  string text = 1;
}

message HelloResponse {
  string text = 1;
}

// stream.proto file 

service HelloStreamService {
    rpc helloStream(stream HelloStreamRequest) returns (stream HelloStreamResponse) {}
    rpc helloStream2(HelloStreamRequest) returns (stream HelloStreamResponse) {}

}

message HelloStreamRequest {
    string text = 1;
}

message HelloStreamResponse {
  string text = 1;
}
```


```
  public static void main(String[] args){
       SpringApplication.run(GrpcClientApplication.class, args);
       //need to config for grpc server address
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9897).usePlaintext().build();
        //doUnaryCall(channel);
        //serverStreamAndClientUnaryCall(channel);
        serverStreamAndClientStream(channel);
    }

  // this when client and server expect single request and response as like rest server
  //Refer - Test.proto -   rpc hello(HelloRequest) returns (HelloResponse) {}
   public static void doUnaryCall(ManagedChannel channel){
       //input
       HelloWorldServiceGrpc.HelloWorldServiceBlockingStub stub = HelloWorldServiceGrpc.newBlockingStub(channel);
       Test.HelloResponse helloResponse = stub
               .hello(Test.HelloRequest.newBuilder().setText("test").build());
       //response
       System.out.println(helloResponse.getText());
   }

    // this when client is Unary and server is stream  request
    //Refer - TestStream.proto -     rpc helloStream2(HelloStreamRequest) returns (stream HelloStreamResponse) {}
    public static void serverStreamAndClientUnaryCall(ManagedChannel channel){
        //input
        HelloStreamServiceGrpc.HelloStreamServiceBlockingStub stub = HelloStreamServiceGrpc.newBlockingStub(channel);

        final HelloStreamRequest greetManyTimesRequest =
                HelloStreamRequest.newBuilder()
                        .setText("test")
                        .build();
        stub.helloStream2(greetManyTimesRequest).forEachRemaining( response -> {
            System.out.println(response.getText());
        });
    }

    //bidirectional
    //    rpc helloStream(stream HelloStreamRequest) returns (stream HelloStreamResponse) {}
    public static void serverStreamAndClientStream(ManagedChannel channel) {
        HelloStreamServiceGrpc.HelloStreamServiceStub ayncRequest = HelloStreamServiceGrpc.newStub(channel);
        CountDownLatch latch = new CountDownLatch(1);
        final StreamObserver<HelloStreamRequest> streamObserver =
                ayncRequest.helloStream(new StreamObserver<HelloStreamResponse>() {
                    @Override
                    public void onNext(HelloStreamResponse helloStreamResponse) {
                        System.out.println(helloStreamResponse.getText());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("error");
                        latch.countDown();
                    }

                    @Override
                    public void onCompleted() {
                        latch.countDown();
                    }
                });

        Arrays.asList("result1","result2").stream().forEach(
                it ->{
                    final HelloStreamRequest greetManyTimesRequest =
                            HelloStreamRequest.newBuilder()
                                    .setText(it)
                                    .build();
                    streamObserver.onNext(greetManyTimesRequest);
                }
        );
    }

```
### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

