package com.dunkware.net.proto.cluster.service;

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
    comments = "Source: cluster-service.proto")
public final class GClusterServiceGrpc {

  private GClusterServiceGrpc() {}

  public static final String SERVICE_NAME = "dunkware.cluster.GClusterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.cluster.GNodePing,
      com.dunkware.net.proto.cluster.GNodePing> getNodePingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "nodePing",
      requestType = com.dunkware.net.proto.cluster.GNodePing.class,
      responseType = com.dunkware.net.proto.cluster.GNodePing.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.cluster.GNodePing,
      com.dunkware.net.proto.cluster.GNodePing> getNodePingMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.cluster.GNodePing, com.dunkware.net.proto.cluster.GNodePing> getNodePingMethod;
    if ((getNodePingMethod = GClusterServiceGrpc.getNodePingMethod) == null) {
      synchronized (GClusterServiceGrpc.class) {
        if ((getNodePingMethod = GClusterServiceGrpc.getNodePingMethod) == null) {
          GClusterServiceGrpc.getNodePingMethod = getNodePingMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.cluster.GNodePing, com.dunkware.net.proto.cluster.GNodePing>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.cluster.GClusterService", "nodePing"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.cluster.GNodePing.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.cluster.GNodePing.getDefaultInstance()))
                  .setSchemaDescriptor(new GClusterServiceMethodDescriptorSupplier("nodePing"))
                  .build();
          }
        }
     }
     return getNodePingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GClusterServiceStub newStub(io.grpc.Channel channel) {
    return new GClusterServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GClusterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GClusterServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GClusterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GClusterServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class GClusterServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<com.dunkware.net.proto.cluster.GNodePing> nodePing(
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.cluster.GNodePing> responseObserver) {
      return asyncUnimplementedStreamingCall(getNodePingMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getNodePingMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.dunkware.net.proto.cluster.GNodePing,
                com.dunkware.net.proto.cluster.GNodePing>(
                  this, METHODID_NODE_PING)))
          .build();
    }
  }

  /**
   */
  public static final class GClusterServiceStub extends io.grpc.stub.AbstractStub<GClusterServiceStub> {
    private GClusterServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GClusterServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GClusterServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GClusterServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.dunkware.net.proto.cluster.GNodePing> nodePing(
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.cluster.GNodePing> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getNodePingMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class GClusterServiceBlockingStub extends io.grpc.stub.AbstractStub<GClusterServiceBlockingStub> {
    private GClusterServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GClusterServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GClusterServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GClusterServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class GClusterServiceFutureStub extends io.grpc.stub.AbstractStub<GClusterServiceFutureStub> {
    private GClusterServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GClusterServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GClusterServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GClusterServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_NODE_PING = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GClusterServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GClusterServiceImplBase serviceImpl, int methodId) {
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
        case METHODID_NODE_PING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.nodePing(
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.cluster.GNodePing>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GClusterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GClusterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.dunkware.net.proto.cluster.service.GClusterServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GClusterService");
    }
  }

  private static final class GClusterServiceFileDescriptorSupplier
      extends GClusterServiceBaseDescriptorSupplier {
    GClusterServiceFileDescriptorSupplier() {}
  }

  private static final class GClusterServiceMethodDescriptorSupplier
      extends GClusterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GClusterServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (GClusterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GClusterServiceFileDescriptorSupplier())
              .addMethod(getNodePingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
