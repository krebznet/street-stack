package com.dunkware.net.proto.data.service;

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
    comments = "Source: data-service.proto")
public final class GDataServiceGrpc {

  private GDataServiceGrpc() {}

  public static final String SERVICE_NAME = "dunkware.data.service.GDataService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.netstream.GNetClientMessage,
      com.dunkware.net.proto.netstream.GNetServerMessage> getStreamClientMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "streamClient",
      requestType = com.dunkware.net.proto.netstream.GNetClientMessage.class,
      responseType = com.dunkware.net.proto.netstream.GNetServerMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.netstream.GNetClientMessage,
      com.dunkware.net.proto.netstream.GNetServerMessage> getStreamClientMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.netstream.GNetClientMessage, com.dunkware.net.proto.netstream.GNetServerMessage> getStreamClientMethod;
    if ((getStreamClientMethod = GDataServiceGrpc.getStreamClientMethod) == null) {
      synchronized (GDataServiceGrpc.class) {
        if ((getStreamClientMethod = GDataServiceGrpc.getStreamClientMethod) == null) {
          GDataServiceGrpc.getStreamClientMethod = getStreamClientMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.netstream.GNetClientMessage, com.dunkware.net.proto.netstream.GNetServerMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.data.service.GDataService", "streamClient"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.netstream.GNetClientMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.netstream.GNetServerMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new GDataServiceMethodDescriptorSupplier("streamClient"))
                  .build();
          }
        }
     }
     return getStreamClientMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GDataServiceStub newStub(io.grpc.Channel channel) {
    return new GDataServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GDataServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GDataServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GDataServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GDataServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class GDataServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<com.dunkware.net.proto.netstream.GNetClientMessage> streamClient(
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.netstream.GNetServerMessage> responseObserver) {
      return asyncUnimplementedStreamingCall(getStreamClientMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStreamClientMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.dunkware.net.proto.netstream.GNetClientMessage,
                com.dunkware.net.proto.netstream.GNetServerMessage>(
                  this, METHODID_STREAM_CLIENT)))
          .build();
    }
  }

  /**
   */
  public static final class GDataServiceStub extends io.grpc.stub.AbstractStub<GDataServiceStub> {
    private GDataServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GDataServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GDataServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GDataServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.dunkware.net.proto.netstream.GNetClientMessage> streamClient(
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.netstream.GNetServerMessage> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getStreamClientMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class GDataServiceBlockingStub extends io.grpc.stub.AbstractStub<GDataServiceBlockingStub> {
    private GDataServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GDataServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GDataServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GDataServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class GDataServiceFutureStub extends io.grpc.stub.AbstractStub<GDataServiceFutureStub> {
    private GDataServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GDataServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GDataServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GDataServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_STREAM_CLIENT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GDataServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GDataServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STREAM_CLIENT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.streamClient(
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.netstream.GNetServerMessage>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GDataServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GDataServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.dunkware.net.proto.data.service.GDataServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GDataService");
    }
  }

  private static final class GDataServiceFileDescriptorSupplier
      extends GDataServiceBaseDescriptorSupplier {
    GDataServiceFileDescriptorSupplier() {}
  }

  private static final class GDataServiceMethodDescriptorSupplier
      extends GDataServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GDataServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (GDataServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GDataServiceFileDescriptorSupplier())
              .addMethod(getStreamClientMethod())
              .build();
        }
      }
    }
    return result;
  }
}
