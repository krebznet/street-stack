package com.dunkware.net.proto.web;

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
    comments = "Source: web.proto")
public final class GWebServiceGrpc {

  private GWebServiceGrpc() {}

  public static final String SERVICE_NAME = "dunkware.web.GWebService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GStreamSpecRequest,
      com.dunkware.net.proto.stream.GStreamSpecResponse> getGetStreamSpecMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getStreamSpec",
      requestType = com.dunkware.net.proto.stream.GStreamSpecRequest.class,
      responseType = com.dunkware.net.proto.stream.GStreamSpecResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GStreamSpecRequest,
      com.dunkware.net.proto.stream.GStreamSpecResponse> getGetStreamSpecMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GStreamSpecRequest, com.dunkware.net.proto.stream.GStreamSpecResponse> getGetStreamSpecMethod;
    if ((getGetStreamSpecMethod = GWebServiceGrpc.getGetStreamSpecMethod) == null) {
      synchronized (GWebServiceGrpc.class) {
        if ((getGetStreamSpecMethod = GWebServiceGrpc.getGetStreamSpecMethod) == null) {
          GWebServiceGrpc.getGetStreamSpecMethod = getGetStreamSpecMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.stream.GStreamSpecRequest, com.dunkware.net.proto.stream.GStreamSpecResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.web.GWebService", "getStreamSpec"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GStreamSpecRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GStreamSpecResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GWebServiceMethodDescriptorSupplier("getStreamSpec"))
                  .build();
          }
        }
     }
     return getGetStreamSpecMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GAutoCompleteRequest,
      com.dunkware.net.proto.stream.GAutoCompleteResponse> getAutoCompleteSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "autoCompleteSearch",
      requestType = com.dunkware.net.proto.stream.GAutoCompleteRequest.class,
      responseType = com.dunkware.net.proto.stream.GAutoCompleteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GAutoCompleteRequest,
      com.dunkware.net.proto.stream.GAutoCompleteResponse> getAutoCompleteSearchMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GAutoCompleteRequest, com.dunkware.net.proto.stream.GAutoCompleteResponse> getAutoCompleteSearchMethod;
    if ((getAutoCompleteSearchMethod = GWebServiceGrpc.getAutoCompleteSearchMethod) == null) {
      synchronized (GWebServiceGrpc.class) {
        if ((getAutoCompleteSearchMethod = GWebServiceGrpc.getAutoCompleteSearchMethod) == null) {
          GWebServiceGrpc.getAutoCompleteSearchMethod = getAutoCompleteSearchMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.stream.GAutoCompleteRequest, com.dunkware.net.proto.stream.GAutoCompleteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.web.GWebService", "autoCompleteSearch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GAutoCompleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GAutoCompleteResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GWebServiceMethodDescriptorSupplier("autoCompleteSearch"))
                  .build();
          }
        }
     }
     return getAutoCompleteSearchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GWebServiceStub newStub(io.grpc.Channel channel) {
    return new GWebServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GWebServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GWebServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GWebServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GWebServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class GWebServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getStreamSpec(com.dunkware.net.proto.stream.GStreamSpecRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GStreamSpecResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStreamSpecMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GAutoCompleteRequest> autoCompleteSearch(
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GAutoCompleteResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getAutoCompleteSearchMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetStreamSpecMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.dunkware.net.proto.stream.GStreamSpecRequest,
                com.dunkware.net.proto.stream.GStreamSpecResponse>(
                  this, METHODID_GET_STREAM_SPEC)))
          .addMethod(
            getAutoCompleteSearchMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.dunkware.net.proto.stream.GAutoCompleteRequest,
                com.dunkware.net.proto.stream.GAutoCompleteResponse>(
                  this, METHODID_AUTO_COMPLETE_SEARCH)))
          .build();
    }
  }

  /**
   */
  public static final class GWebServiceStub extends io.grpc.stub.AbstractStub<GWebServiceStub> {
    private GWebServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GWebServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GWebServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GWebServiceStub(channel, callOptions);
    }

    /**
     */
    public void getStreamSpec(com.dunkware.net.proto.stream.GStreamSpecRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GStreamSpecResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetStreamSpecMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GAutoCompleteRequest> autoCompleteSearch(
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GAutoCompleteResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getAutoCompleteSearchMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class GWebServiceBlockingStub extends io.grpc.stub.AbstractStub<GWebServiceBlockingStub> {
    private GWebServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GWebServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GWebServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GWebServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.dunkware.net.proto.stream.GStreamSpecResponse getStreamSpec(com.dunkware.net.proto.stream.GStreamSpecRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetStreamSpecMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GWebServiceFutureStub extends io.grpc.stub.AbstractStub<GWebServiceFutureStub> {
    private GWebServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GWebServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GWebServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GWebServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.dunkware.net.proto.stream.GStreamSpecResponse> getStreamSpec(
        com.dunkware.net.proto.stream.GStreamSpecRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetStreamSpecMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_STREAM_SPEC = 0;
  private static final int METHODID_AUTO_COMPLETE_SEARCH = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GWebServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GWebServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_STREAM_SPEC:
          serviceImpl.getStreamSpec((com.dunkware.net.proto.stream.GStreamSpecRequest) request,
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GStreamSpecResponse>) responseObserver);
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

  private static abstract class GWebServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GWebServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.dunkware.net.proto.web.GWebProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GWebService");
    }
  }

  private static final class GWebServiceFileDescriptorSupplier
      extends GWebServiceBaseDescriptorSupplier {
    GWebServiceFileDescriptorSupplier() {}
  }

  private static final class GWebServiceMethodDescriptorSupplier
      extends GWebServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GWebServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (GWebServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GWebServiceFileDescriptorSupplier())
              .addMethod(getGetStreamSpecMethod())
              .addMethod(getAutoCompleteSearchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
