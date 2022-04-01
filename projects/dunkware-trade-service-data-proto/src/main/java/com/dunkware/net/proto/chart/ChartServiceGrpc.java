package com.dunkware.net.proto.chart;

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
    comments = "Source: chart.proto")
public final class ChartServiceGrpc {

  private ChartServiceGrpc() {}

  public static final String SERVICE_NAME = "dunkware.chart.ChartService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.chart.ChartRequest,
      com.dunkware.net.proto.chart.ChartResponse> getGetChartMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getChart",
      requestType = com.dunkware.net.proto.chart.ChartRequest.class,
      responseType = com.dunkware.net.proto.chart.ChartResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.chart.ChartRequest,
      com.dunkware.net.proto.chart.ChartResponse> getGetChartMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.chart.ChartRequest, com.dunkware.net.proto.chart.ChartResponse> getGetChartMethod;
    if ((getGetChartMethod = ChartServiceGrpc.getGetChartMethod) == null) {
      synchronized (ChartServiceGrpc.class) {
        if ((getGetChartMethod = ChartServiceGrpc.getGetChartMethod) == null) {
          ChartServiceGrpc.getGetChartMethod = getGetChartMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.chart.ChartRequest, com.dunkware.net.proto.chart.ChartResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.chart.ChartService", "getChart"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.chart.ChartRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.chart.ChartResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ChartServiceMethodDescriptorSupplier("getChart"))
                  .build();
          }
        }
     }
     return getGetChartMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.chart.GridRequest,
      com.dunkware.net.proto.chart.Grid> getGetGridMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getGrid",
      requestType = com.dunkware.net.proto.chart.GridRequest.class,
      responseType = com.dunkware.net.proto.chart.Grid.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.chart.GridRequest,
      com.dunkware.net.proto.chart.Grid> getGetGridMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.chart.GridRequest, com.dunkware.net.proto.chart.Grid> getGetGridMethod;
    if ((getGetGridMethod = ChartServiceGrpc.getGetGridMethod) == null) {
      synchronized (ChartServiceGrpc.class) {
        if ((getGetGridMethod = ChartServiceGrpc.getGetGridMethod) == null) {
          ChartServiceGrpc.getGetGridMethod = getGetGridMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.chart.GridRequest, com.dunkware.net.proto.chart.Grid>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.chart.ChartService", "getGrid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.chart.GridRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.chart.Grid.getDefaultInstance()))
                  .setSchemaDescriptor(new ChartServiceMethodDescriptorSupplier("getGrid"))
                  .build();
          }
        }
     }
     return getGetGridMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.chart.GridSubscriptionRequest,
      com.dunkware.net.proto.chart.GridTransaction> getGetGridSubscriptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getGridSubscription",
      requestType = com.dunkware.net.proto.chart.GridSubscriptionRequest.class,
      responseType = com.dunkware.net.proto.chart.GridTransaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.chart.GridSubscriptionRequest,
      com.dunkware.net.proto.chart.GridTransaction> getGetGridSubscriptionMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.chart.GridSubscriptionRequest, com.dunkware.net.proto.chart.GridTransaction> getGetGridSubscriptionMethod;
    if ((getGetGridSubscriptionMethod = ChartServiceGrpc.getGetGridSubscriptionMethod) == null) {
      synchronized (ChartServiceGrpc.class) {
        if ((getGetGridSubscriptionMethod = ChartServiceGrpc.getGetGridSubscriptionMethod) == null) {
          ChartServiceGrpc.getGetGridSubscriptionMethod = getGetGridSubscriptionMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.chart.GridSubscriptionRequest, com.dunkware.net.proto.chart.GridTransaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.chart.ChartService", "getGridSubscription"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.chart.GridSubscriptionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.chart.GridTransaction.getDefaultInstance()))
                  .setSchemaDescriptor(new ChartServiceMethodDescriptorSupplier("getGridSubscription"))
                  .build();
          }
        }
     }
     return getGetGridSubscriptionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChartServiceStub newStub(io.grpc.Channel channel) {
    return new ChartServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChartServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChartServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChartServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChartServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ChartServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getChart(com.dunkware.net.proto.chart.ChartRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.ChartResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetChartMethod(), responseObserver);
    }

    /**
     */
    public void getGrid(com.dunkware.net.proto.chart.GridRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.Grid> responseObserver) {
      asyncUnimplementedUnaryCall(getGetGridMethod(), responseObserver);
    }

    /**
     */
    public void getGridSubscription(com.dunkware.net.proto.chart.GridSubscriptionRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.GridTransaction> responseObserver) {
      asyncUnimplementedUnaryCall(getGetGridSubscriptionMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetChartMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.dunkware.net.proto.chart.ChartRequest,
                com.dunkware.net.proto.chart.ChartResponse>(
                  this, METHODID_GET_CHART)))
          .addMethod(
            getGetGridMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.dunkware.net.proto.chart.GridRequest,
                com.dunkware.net.proto.chart.Grid>(
                  this, METHODID_GET_GRID)))
          .addMethod(
            getGetGridSubscriptionMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.dunkware.net.proto.chart.GridSubscriptionRequest,
                com.dunkware.net.proto.chart.GridTransaction>(
                  this, METHODID_GET_GRID_SUBSCRIPTION)))
          .build();
    }
  }

  /**
   */
  public static final class ChartServiceStub extends io.grpc.stub.AbstractStub<ChartServiceStub> {
    private ChartServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChartServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChartServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChartServiceStub(channel, callOptions);
    }

    /**
     */
    public void getChart(com.dunkware.net.proto.chart.ChartRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.ChartResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetChartMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getGrid(com.dunkware.net.proto.chart.GridRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.Grid> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetGridMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getGridSubscription(com.dunkware.net.proto.chart.GridSubscriptionRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.GridTransaction> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetGridSubscriptionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ChartServiceBlockingStub extends io.grpc.stub.AbstractStub<ChartServiceBlockingStub> {
    private ChartServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChartServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChartServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChartServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.dunkware.net.proto.chart.ChartResponse> getChart(
        com.dunkware.net.proto.chart.ChartRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetChartMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.dunkware.net.proto.chart.Grid getGrid(com.dunkware.net.proto.chart.GridRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetGridMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.dunkware.net.proto.chart.GridTransaction> getGridSubscription(
        com.dunkware.net.proto.chart.GridSubscriptionRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetGridSubscriptionMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ChartServiceFutureStub extends io.grpc.stub.AbstractStub<ChartServiceFutureStub> {
    private ChartServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChartServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChartServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChartServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.dunkware.net.proto.chart.Grid> getGrid(
        com.dunkware.net.proto.chart.GridRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetGridMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CHART = 0;
  private static final int METHODID_GET_GRID = 1;
  private static final int METHODID_GET_GRID_SUBSCRIPTION = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ChartServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ChartServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CHART:
          serviceImpl.getChart((com.dunkware.net.proto.chart.ChartRequest) request,
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.ChartResponse>) responseObserver);
          break;
        case METHODID_GET_GRID:
          serviceImpl.getGrid((com.dunkware.net.proto.chart.GridRequest) request,
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.Grid>) responseObserver);
          break;
        case METHODID_GET_GRID_SUBSCRIPTION:
          serviceImpl.getGridSubscription((com.dunkware.net.proto.chart.GridSubscriptionRequest) request,
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.GridTransaction>) responseObserver);
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

  private static abstract class ChartServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChartServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.dunkware.net.proto.chart.ChartProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ChartService");
    }
  }

  private static final class ChartServiceFileDescriptorSupplier
      extends ChartServiceBaseDescriptorSupplier {
    ChartServiceFileDescriptorSupplier() {}
  }

  private static final class ChartServiceMethodDescriptorSupplier
      extends ChartServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ChartServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ChartServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChartServiceFileDescriptorSupplier())
              .addMethod(getGetChartMethod())
              .addMethod(getGetGridMethod())
              .addMethod(getGetGridSubscriptionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
