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
public final class GClustererviceGrpc {

  private GClustererviceGrpc() {}

  public static final String SERVICE_NAME = "dunkware.cluster.GClusterervice";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.cluster.GNodeUpdateRequest,
      com.dunkware.net.proto.cluster.GNodeUpdateResponse> getNodeUpdateStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "nodeUpdateStream",
      requestType = com.dunkware.net.proto.cluster.GNodeUpdateRequest.class,
      responseType = com.dunkware.net.proto.cluster.GNodeUpdateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.cluster.GNodeUpdateRequest,
      com.dunkware.net.proto.cluster.GNodeUpdateResponse> getNodeUpdateStreamMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.cluster.GNodeUpdateRequest, com.dunkware.net.proto.cluster.GNodeUpdateResponse> getNodeUpdateStreamMethod;
    if ((getNodeUpdateStreamMethod = GClustererviceGrpc.getNodeUpdateStreamMethod) == null) {
      synchronized (GClustererviceGrpc.class) {
        if ((getNodeUpdateStreamMethod = GClustererviceGrpc.getNodeUpdateStreamMethod) == null) {
          GClustererviceGrpc.getNodeUpdateStreamMethod = getNodeUpdateStreamMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.cluster.GNodeUpdateRequest, com.dunkware.net.proto.cluster.GNodeUpdateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.cluster.GClusterervice", "nodeUpdateStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.cluster.GNodeUpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.cluster.GNodeUpdateResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GClustererviceMethodDescriptorSupplier("nodeUpdateStream"))
                  .build();
          }
        }
     }
     return getNodeUpdateStreamMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GClustererviceStub newStub(io.grpc.Channel channel) {
    return new GClustererviceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GClustererviceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GClustererviceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GClustererviceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GClustererviceFutureStub(channel);
  }

  /**
   */
  public static abstract class GClustererviceImplBase implements io.grpc.BindableService {

    /**
     */
    public void nodeUpdateStream(com.dunkware.net.proto.cluster.GNodeUpdateRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.cluster.GNodeUpdateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNodeUpdateStreamMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getNodeUpdateStreamMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.dunkware.net.proto.cluster.GNodeUpdateRequest,
                com.dunkware.net.proto.cluster.GNodeUpdateResponse>(
                  this, METHODID_NODE_UPDATE_STREAM)))
          .build();
    }
  }

  /**
   */
  public static final class GClustererviceStub extends io.grpc.stub.AbstractStub<GClustererviceStub> {
    private GClustererviceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GClustererviceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GClustererviceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GClustererviceStub(channel, callOptions);
    }

    /**
     */
    public void nodeUpdateStream(com.dunkware.net.proto.cluster.GNodeUpdateRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.cluster.GNodeUpdateResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getNodeUpdateStreamMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GClustererviceBlockingStub extends io.grpc.stub.AbstractStub<GClustererviceBlockingStub> {
    private GClustererviceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GClustererviceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GClustererviceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GClustererviceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.dunkware.net.proto.cluster.GNodeUpdateResponse> nodeUpdateStream(
        com.dunkware.net.proto.cluster.GNodeUpdateRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getNodeUpdateStreamMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GClustererviceFutureStub extends io.grpc.stub.AbstractStub<GClustererviceFutureStub> {
    private GClustererviceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GClustererviceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GClustererviceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GClustererviceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_NODE_UPDATE_STREAM = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GClustererviceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GClustererviceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_NODE_UPDATE_STREAM:
          serviceImpl.nodeUpdateStream((com.dunkware.net.proto.cluster.GNodeUpdateRequest) request,
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.cluster.GNodeUpdateResponse>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GClustererviceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GClustererviceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.dunkware.net.proto.cluster.service.GClusterServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GClusterervice");
    }
  }

  private static final class GClustererviceFileDescriptorSupplier
      extends GClustererviceBaseDescriptorSupplier {
    GClustererviceFileDescriptorSupplier() {}
  }

  private static final class GClustererviceMethodDescriptorSupplier
      extends GClustererviceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GClustererviceMethodDescriptorSupplier(String methodName) {
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
      synchronized (GClustererviceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GClustererviceFileDescriptorSupplier())
              .addMethod(getNodeUpdateStreamMethod())
              .build();
        }
      }
    }
    return result;
  }
}
