package com.dunkware.net.proto.cluster.node;

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
    comments = "Source: cluster-node.proto")
public final class GClusterNodeServiceGrpc {

  private GClusterNodeServiceGrpc() {}

  public static final String SERVICE_NAME = "dunkware.cluster.node.GClusterNodeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.cluster.GNodeStatsRequest,
      com.dunkware.net.proto.cluster.GNodeStatsResponse> getGetStatsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getStats",
      requestType = com.dunkware.net.proto.cluster.GNodeStatsRequest.class,
      responseType = com.dunkware.net.proto.cluster.GNodeStatsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.cluster.GNodeStatsRequest,
      com.dunkware.net.proto.cluster.GNodeStatsResponse> getGetStatsMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.cluster.GNodeStatsRequest, com.dunkware.net.proto.cluster.GNodeStatsResponse> getGetStatsMethod;
    if ((getGetStatsMethod = GClusterNodeServiceGrpc.getGetStatsMethod) == null) {
      synchronized (GClusterNodeServiceGrpc.class) {
        if ((getGetStatsMethod = GClusterNodeServiceGrpc.getGetStatsMethod) == null) {
          GClusterNodeServiceGrpc.getGetStatsMethod = getGetStatsMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.cluster.GNodeStatsRequest, com.dunkware.net.proto.cluster.GNodeStatsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.cluster.node.GClusterNodeService", "getStats"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.cluster.GNodeStatsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.cluster.GNodeStatsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GClusterNodeServiceMethodDescriptorSupplier("getStats"))
                  .build();
          }
        }
     }
     return getGetStatsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GClusterNodeServiceStub newStub(io.grpc.Channel channel) {
    return new GClusterNodeServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GClusterNodeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GClusterNodeServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GClusterNodeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GClusterNodeServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class GClusterNodeServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<com.dunkware.net.proto.cluster.GNodeStatsRequest> getStats(
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.cluster.GNodeStatsResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetStatsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetStatsMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.dunkware.net.proto.cluster.GNodeStatsRequest,
                com.dunkware.net.proto.cluster.GNodeStatsResponse>(
                  this, METHODID_GET_STATS)))
          .build();
    }
  }

  /**
   */
  public static final class GClusterNodeServiceStub extends io.grpc.stub.AbstractStub<GClusterNodeServiceStub> {
    private GClusterNodeServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GClusterNodeServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GClusterNodeServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GClusterNodeServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.dunkware.net.proto.cluster.GNodeStatsRequest> getStats(
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.cluster.GNodeStatsResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getGetStatsMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class GClusterNodeServiceBlockingStub extends io.grpc.stub.AbstractStub<GClusterNodeServiceBlockingStub> {
    private GClusterNodeServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GClusterNodeServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GClusterNodeServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GClusterNodeServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class GClusterNodeServiceFutureStub extends io.grpc.stub.AbstractStub<GClusterNodeServiceFutureStub> {
    private GClusterNodeServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GClusterNodeServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GClusterNodeServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GClusterNodeServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_STATS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GClusterNodeServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GClusterNodeServiceImplBase serviceImpl, int methodId) {
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
        case METHODID_GET_STATS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getStats(
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.cluster.GNodeStatsResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GClusterNodeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GClusterNodeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.dunkware.net.proto.cluster.node.GClusterNodeProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GClusterNodeService");
    }
  }

  private static final class GClusterNodeServiceFileDescriptorSupplier
      extends GClusterNodeServiceBaseDescriptorSupplier {
    GClusterNodeServiceFileDescriptorSupplier() {}
  }

  private static final class GClusterNodeServiceMethodDescriptorSupplier
      extends GClusterNodeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GClusterNodeServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (GClusterNodeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GClusterNodeServiceFileDescriptorSupplier())
              .addMethod(getGetStatsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
