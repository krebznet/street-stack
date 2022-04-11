package com.dunkware.net.proto.stream.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.1)",
    comments = "Source: stream-service.proto")
public final class GStreamServiceGrpc {

  private GStreamServiceGrpc() {}

  public static final String SERVICE_NAME = "dunkware.stream.service.GStreamService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GStreamSpecsRequest,
      com.dunkware.net.proto.stream.GStreamSpecsResponse> getStreamSpecsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "streamSpecs",
      requestType = com.dunkware.net.proto.stream.GStreamSpecsRequest.class,
      responseType = com.dunkware.net.proto.stream.GStreamSpecsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GStreamSpecsRequest,
      com.dunkware.net.proto.stream.GStreamSpecsResponse> getStreamSpecsMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GStreamSpecsRequest, com.dunkware.net.proto.stream.GStreamSpecsResponse> getStreamSpecsMethod;
    if ((getStreamSpecsMethod = GStreamServiceGrpc.getStreamSpecsMethod) == null) {
      synchronized (GStreamServiceGrpc.class) {
        if ((getStreamSpecsMethod = GStreamServiceGrpc.getStreamSpecsMethod) == null) {
          GStreamServiceGrpc.getStreamSpecsMethod = getStreamSpecsMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.stream.GStreamSpecsRequest, com.dunkware.net.proto.stream.GStreamSpecsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.stream.service.GStreamService", "streamSpecs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GStreamSpecsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GStreamSpecsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GStreamServiceMethodDescriptorSupplier("streamSpecs"))
                  .build();
          }
        }
     }
     return getStreamSpecsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GAutCompleteRequest,
      com.dunkware.net.proto.stream.GAutoCompleteResponse> getAutoCompleteSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "autoCompleteSearch",
      requestType = com.dunkware.net.proto.stream.GAutCompleteRequest.class,
      responseType = com.dunkware.net.proto.stream.GAutoCompleteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GAutCompleteRequest,
      com.dunkware.net.proto.stream.GAutoCompleteResponse> getAutoCompleteSearchMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GAutCompleteRequest, com.dunkware.net.proto.stream.GAutoCompleteResponse> getAutoCompleteSearchMethod;
    if ((getAutoCompleteSearchMethod = GStreamServiceGrpc.getAutoCompleteSearchMethod) == null) {
      synchronized (GStreamServiceGrpc.class) {
        if ((getAutoCompleteSearchMethod = GStreamServiceGrpc.getAutoCompleteSearchMethod) == null) {
          GStreamServiceGrpc.getAutoCompleteSearchMethod = getAutoCompleteSearchMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.stream.GAutCompleteRequest, com.dunkware.net.proto.stream.GAutoCompleteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.stream.service.GStreamService", "autoCompleteSearch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GAutCompleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GAutoCompleteResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GStreamServiceMethodDescriptorSupplier("autoCompleteSearch"))
                  .build();
          }
        }
     }
     return getAutoCompleteSearchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GStreamServiceStub newStub(io.grpc.Channel channel) {
    return new GStreamServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GStreamServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GStreamServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GStreamServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GStreamServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class GStreamServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void streamSpecs(com.dunkware.net.proto.stream.GStreamSpecsRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GStreamSpecsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStreamSpecsMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GAutCompleteRequest> autoCompleteSearch(
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GAutoCompleteResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getAutoCompleteSearchMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStreamSpecsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.dunkware.net.proto.stream.GStreamSpecsRequest,
                com.dunkware.net.proto.stream.GStreamSpecsResponse>(
                  this, METHODID_STREAM_SPECS)))
          .addMethod(
            getAutoCompleteSearchMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.dunkware.net.proto.stream.GAutCompleteRequest,
                com.dunkware.net.proto.stream.GAutoCompleteResponse>(
                  this, METHODID_AUTO_COMPLETE_SEARCH)))
          .build();
    }
  }

  /**
   */
  public static final class GStreamServiceStub extends io.grpc.stub.AbstractStub<GStreamServiceStub> {
    private GStreamServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GStreamServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GStreamServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GStreamServiceStub(channel, callOptions);
    }

    /**
     */
    public void streamSpecs(com.dunkware.net.proto.stream.GStreamSpecsRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GStreamSpecsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStreamSpecsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GAutCompleteRequest> autoCompleteSearch(
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GAutoCompleteResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getAutoCompleteSearchMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class GStreamServiceBlockingStub extends io.grpc.stub.AbstractStub<GStreamServiceBlockingStub> {
    private GStreamServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GStreamServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GStreamServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GStreamServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.dunkware.net.proto.stream.GStreamSpecsResponse streamSpecs(com.dunkware.net.proto.stream.GStreamSpecsRequest request) {
      return blockingUnaryCall(
          getChannel(), getStreamSpecsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GStreamServiceFutureStub extends io.grpc.stub.AbstractStub<GStreamServiceFutureStub> {
    private GStreamServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GStreamServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GStreamServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GStreamServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.dunkware.net.proto.stream.GStreamSpecsResponse> streamSpecs(
        com.dunkware.net.proto.stream.GStreamSpecsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStreamSpecsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_STREAM_SPECS = 0;
  private static final int METHODID_AUTO_COMPLETE_SEARCH = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GStreamServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GStreamServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STREAM_SPECS:
          serviceImpl.streamSpecs((com.dunkware.net.proto.stream.GStreamSpecsRequest) request,
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GStreamSpecsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AUTO_COMPLETE_SEARCH:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.autoCompleteSearch(
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GAutoCompleteResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GStreamServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GStreamServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.dunkware.net.proto.stream.service.GStreamServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GStreamService");
    }
  }

  private static final class GStreamServiceFileDescriptorSupplier
      extends GStreamServiceBaseDescriptorSupplier {
    GStreamServiceFileDescriptorSupplier() {}
  }

  private static final class GStreamServiceMethodDescriptorSupplier
      extends GStreamServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GStreamServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GStreamServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GStreamServiceFileDescriptorSupplier())
              .addMethod(getStreamSpecsMethod())
              .addMethod(getAutoCompleteSearchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
