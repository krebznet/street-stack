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
  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntitySignalQuery,
      com.dunkware.net.proto.chart.Grid> getTestSignalGridMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "testSignalGrid",
      requestType = com.dunkware.net.proto.stream.GEntitySignalQuery.class,
      responseType = com.dunkware.net.proto.chart.Grid.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntitySignalQuery,
      com.dunkware.net.proto.chart.Grid> getTestSignalGridMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntitySignalQuery, com.dunkware.net.proto.chart.Grid> getTestSignalGridMethod;
    if ((getTestSignalGridMethod = GWebServiceGrpc.getTestSignalGridMethod) == null) {
      synchronized (GWebServiceGrpc.class) {
        if ((getTestSignalGridMethod = GWebServiceGrpc.getTestSignalGridMethod) == null) {
          GWebServiceGrpc.getTestSignalGridMethod = getTestSignalGridMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.stream.GEntitySignalQuery, com.dunkware.net.proto.chart.Grid>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.web.GWebService", "testSignalGrid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GEntitySignalQuery.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.chart.Grid.getDefaultInstance()))
                  .setSchemaDescriptor(new GWebServiceMethodDescriptorSupplier("testSignalGrid"))
                  .build();
          }
        }
     }
     return getTestSignalGridMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntityQuery,
      com.dunkware.net.proto.chart.Grid> getTestEntityGridMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "testEntityGrid",
      requestType = com.dunkware.net.proto.stream.GEntityQuery.class,
      responseType = com.dunkware.net.proto.chart.Grid.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntityQuery,
      com.dunkware.net.proto.chart.Grid> getTestEntityGridMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntityQuery, com.dunkware.net.proto.chart.Grid> getTestEntityGridMethod;
    if ((getTestEntityGridMethod = GWebServiceGrpc.getTestEntityGridMethod) == null) {
      synchronized (GWebServiceGrpc.class) {
        if ((getTestEntityGridMethod = GWebServiceGrpc.getTestEntityGridMethod) == null) {
          GWebServiceGrpc.getTestEntityGridMethod = getTestEntityGridMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.stream.GEntityQuery, com.dunkware.net.proto.chart.Grid>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.web.GWebService", "testEntityGrid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GEntityQuery.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.chart.Grid.getDefaultInstance()))
                  .setSchemaDescriptor(new GWebServiceMethodDescriptorSupplier("testEntityGrid"))
                  .build();
          }
        }
     }
     return getTestEntityGridMethod;
  }

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

  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntitySignalSearchRequest,
      com.dunkware.net.proto.stream.GEntitySignalSearchResponse> getSignalSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "signalSearch",
      requestType = com.dunkware.net.proto.stream.GEntitySignalSearchRequest.class,
      responseType = com.dunkware.net.proto.stream.GEntitySignalSearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntitySignalSearchRequest,
      com.dunkware.net.proto.stream.GEntitySignalSearchResponse> getSignalSearchMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntitySignalSearchRequest, com.dunkware.net.proto.stream.GEntitySignalSearchResponse> getSignalSearchMethod;
    if ((getSignalSearchMethod = GWebServiceGrpc.getSignalSearchMethod) == null) {
      synchronized (GWebServiceGrpc.class) {
        if ((getSignalSearchMethod = GWebServiceGrpc.getSignalSearchMethod) == null) {
          GWebServiceGrpc.getSignalSearchMethod = getSignalSearchMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.stream.GEntitySignalSearchRequest, com.dunkware.net.proto.stream.GEntitySignalSearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.web.GWebService", "signalSearch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GEntitySignalSearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GEntitySignalSearchResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GWebServiceMethodDescriptorSupplier("signalSearch"))
                  .build();
          }
        }
     }
     return getSignalSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntitySignalStreamRequest,
      com.dunkware.net.proto.stream.GEntitySignalStreamResponse> getSignalStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "signalStream",
      requestType = com.dunkware.net.proto.stream.GEntitySignalStreamRequest.class,
      responseType = com.dunkware.net.proto.stream.GEntitySignalStreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntitySignalStreamRequest,
      com.dunkware.net.proto.stream.GEntitySignalStreamResponse> getSignalStreamMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntitySignalStreamRequest, com.dunkware.net.proto.stream.GEntitySignalStreamResponse> getSignalStreamMethod;
    if ((getSignalStreamMethod = GWebServiceGrpc.getSignalStreamMethod) == null) {
      synchronized (GWebServiceGrpc.class) {
        if ((getSignalStreamMethod = GWebServiceGrpc.getSignalStreamMethod) == null) {
          GWebServiceGrpc.getSignalStreamMethod = getSignalStreamMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.stream.GEntitySignalStreamRequest, com.dunkware.net.proto.stream.GEntitySignalStreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.web.GWebService", "signalStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GEntitySignalStreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GEntitySignalStreamResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GWebServiceMethodDescriptorSupplier("signalStream"))
                  .build();
          }
        }
     }
     return getSignalStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntitySearchRequest,
      com.dunkware.net.proto.stream.GEntitySearchResponse> getEntitySearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "entitySearch",
      requestType = com.dunkware.net.proto.stream.GEntitySearchRequest.class,
      responseType = com.dunkware.net.proto.stream.GEntitySearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntitySearchRequest,
      com.dunkware.net.proto.stream.GEntitySearchResponse> getEntitySearchMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntitySearchRequest, com.dunkware.net.proto.stream.GEntitySearchResponse> getEntitySearchMethod;
    if ((getEntitySearchMethod = GWebServiceGrpc.getEntitySearchMethod) == null) {
      synchronized (GWebServiceGrpc.class) {
        if ((getEntitySearchMethod = GWebServiceGrpc.getEntitySearchMethod) == null) {
          GWebServiceGrpc.getEntitySearchMethod = getEntitySearchMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.stream.GEntitySearchRequest, com.dunkware.net.proto.stream.GEntitySearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.web.GWebService", "entitySearch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GEntitySearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GEntitySearchResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GWebServiceMethodDescriptorSupplier("entitySearch"))
                  .build();
          }
        }
     }
     return getEntitySearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntityStreamRequest,
      com.dunkware.net.proto.stream.GEntityStreamResponse> getEntityStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "entityStream",
      requestType = com.dunkware.net.proto.stream.GEntityStreamRequest.class,
      responseType = com.dunkware.net.proto.stream.GEntityStreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntityStreamRequest,
      com.dunkware.net.proto.stream.GEntityStreamResponse> getEntityStreamMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GEntityStreamRequest, com.dunkware.net.proto.stream.GEntityStreamResponse> getEntityStreamMethod;
    if ((getEntityStreamMethod = GWebServiceGrpc.getEntityStreamMethod) == null) {
      synchronized (GWebServiceGrpc.class) {
        if ((getEntityStreamMethod = GWebServiceGrpc.getEntityStreamMethod) == null) {
          GWebServiceGrpc.getEntityStreamMethod = getEntityStreamMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.stream.GEntityStreamRequest, com.dunkware.net.proto.stream.GEntityStreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.web.GWebService", "entityStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GEntityStreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GEntityStreamResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GWebServiceMethodDescriptorSupplier("entityStream"))
                  .build();
          }
        }
     }
     return getEntityStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GInstrumentVarsRequest,
      com.dunkware.net.proto.stream.GInstrumentVarsResponse> getInstrumentVarsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "instrumentVars",
      requestType = com.dunkware.net.proto.stream.GInstrumentVarsRequest.class,
      responseType = com.dunkware.net.proto.stream.GInstrumentVarsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GInstrumentVarsRequest,
      com.dunkware.net.proto.stream.GInstrumentVarsResponse> getInstrumentVarsMethod() {
    io.grpc.MethodDescriptor<com.dunkware.net.proto.stream.GInstrumentVarsRequest, com.dunkware.net.proto.stream.GInstrumentVarsResponse> getInstrumentVarsMethod;
    if ((getInstrumentVarsMethod = GWebServiceGrpc.getInstrumentVarsMethod) == null) {
      synchronized (GWebServiceGrpc.class) {
        if ((getInstrumentVarsMethod = GWebServiceGrpc.getInstrumentVarsMethod) == null) {
          GWebServiceGrpc.getInstrumentVarsMethod = getInstrumentVarsMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.stream.GInstrumentVarsRequest, com.dunkware.net.proto.stream.GInstrumentVarsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.web.GWebService", "instrumentVars"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GInstrumentVarsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GInstrumentVarsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GWebServiceMethodDescriptorSupplier("instrumentVars"))
                  .build();
          }
        }
     }
     return getInstrumentVarsMethod;
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
    if ((getAutoCompleteSearchMethod = GWebServiceGrpc.getAutoCompleteSearchMethod) == null) {
      synchronized (GWebServiceGrpc.class) {
        if ((getAutoCompleteSearchMethod = GWebServiceGrpc.getAutoCompleteSearchMethod) == null) {
          GWebServiceGrpc.getAutoCompleteSearchMethod = getAutoCompleteSearchMethod = 
              io.grpc.MethodDescriptor.<com.dunkware.net.proto.stream.GAutCompleteRequest, com.dunkware.net.proto.stream.GAutoCompleteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "dunkware.web.GWebService", "autoCompleteSearch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dunkware.net.proto.stream.GAutCompleteRequest.getDefaultInstance()))
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
    public void testSignalGrid(com.dunkware.net.proto.stream.GEntitySignalQuery request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.Grid> responseObserver) {
      asyncUnimplementedUnaryCall(getTestSignalGridMethod(), responseObserver);
    }

    /**
     */
    public void testEntityGrid(com.dunkware.net.proto.stream.GEntityQuery request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.Grid> responseObserver) {
      asyncUnimplementedUnaryCall(getTestEntityGridMethod(), responseObserver);
    }

    /**
     */
    public void getStreamSpec(com.dunkware.net.proto.stream.GStreamSpecRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GStreamSpecResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStreamSpecMethod(), responseObserver);
    }

    /**
     */
    public void signalSearch(com.dunkware.net.proto.stream.GEntitySignalSearchRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GEntitySignalSearchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSignalSearchMethod(), responseObserver);
    }

    /**
     */
    public void signalStream(com.dunkware.net.proto.stream.GEntitySignalStreamRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GEntitySignalStreamResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSignalStreamMethod(), responseObserver);
    }

    /**
     */
    public void entitySearch(com.dunkware.net.proto.stream.GEntitySearchRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GEntitySearchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEntitySearchMethod(), responseObserver);
    }

    /**
     */
    public void entityStream(com.dunkware.net.proto.stream.GEntityStreamRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GEntityStreamResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEntityStreamMethod(), responseObserver);
    }

    /**
     */
    public void instrumentVars(com.dunkware.net.proto.stream.GInstrumentVarsRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GInstrumentVarsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInstrumentVarsMethod(), responseObserver);
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
            getTestSignalGridMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.dunkware.net.proto.stream.GEntitySignalQuery,
                com.dunkware.net.proto.chart.Grid>(
                  this, METHODID_TEST_SIGNAL_GRID)))
          .addMethod(
            getTestEntityGridMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.dunkware.net.proto.stream.GEntityQuery,
                com.dunkware.net.proto.chart.Grid>(
                  this, METHODID_TEST_ENTITY_GRID)))
          .addMethod(
            getGetStreamSpecMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.dunkware.net.proto.stream.GStreamSpecRequest,
                com.dunkware.net.proto.stream.GStreamSpecResponse>(
                  this, METHODID_GET_STREAM_SPEC)))
          .addMethod(
            getSignalSearchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.dunkware.net.proto.stream.GEntitySignalSearchRequest,
                com.dunkware.net.proto.stream.GEntitySignalSearchResponse>(
                  this, METHODID_SIGNAL_SEARCH)))
          .addMethod(
            getSignalStreamMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.dunkware.net.proto.stream.GEntitySignalStreamRequest,
                com.dunkware.net.proto.stream.GEntitySignalStreamResponse>(
                  this, METHODID_SIGNAL_STREAM)))
          .addMethod(
            getEntitySearchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.dunkware.net.proto.stream.GEntitySearchRequest,
                com.dunkware.net.proto.stream.GEntitySearchResponse>(
                  this, METHODID_ENTITY_SEARCH)))
          .addMethod(
            getEntityStreamMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.dunkware.net.proto.stream.GEntityStreamRequest,
                com.dunkware.net.proto.stream.GEntityStreamResponse>(
                  this, METHODID_ENTITY_STREAM)))
          .addMethod(
            getInstrumentVarsMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.dunkware.net.proto.stream.GInstrumentVarsRequest,
                com.dunkware.net.proto.stream.GInstrumentVarsResponse>(
                  this, METHODID_INSTRUMENT_VARS)))
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
    public void testSignalGrid(com.dunkware.net.proto.stream.GEntitySignalQuery request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.Grid> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTestSignalGridMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void testEntityGrid(com.dunkware.net.proto.stream.GEntityQuery request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.Grid> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTestEntityGridMethod(), getCallOptions()), request, responseObserver);
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
    public void signalSearch(com.dunkware.net.proto.stream.GEntitySignalSearchRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GEntitySignalSearchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSignalSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void signalStream(com.dunkware.net.proto.stream.GEntitySignalStreamRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GEntitySignalStreamResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSignalStreamMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void entitySearch(com.dunkware.net.proto.stream.GEntitySearchRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GEntitySearchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEntitySearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void entityStream(com.dunkware.net.proto.stream.GEntityStreamRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GEntityStreamResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getEntityStreamMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void instrumentVars(com.dunkware.net.proto.stream.GInstrumentVarsRequest request,
        io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GInstrumentVarsResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getInstrumentVarsMethod(), getCallOptions()), request, responseObserver);
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
    public com.dunkware.net.proto.chart.Grid testSignalGrid(com.dunkware.net.proto.stream.GEntitySignalQuery request) {
      return blockingUnaryCall(
          getChannel(), getTestSignalGridMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.dunkware.net.proto.chart.Grid testEntityGrid(com.dunkware.net.proto.stream.GEntityQuery request) {
      return blockingUnaryCall(
          getChannel(), getTestEntityGridMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.dunkware.net.proto.stream.GStreamSpecResponse getStreamSpec(com.dunkware.net.proto.stream.GStreamSpecRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetStreamSpecMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.dunkware.net.proto.stream.GEntitySignalSearchResponse signalSearch(com.dunkware.net.proto.stream.GEntitySignalSearchRequest request) {
      return blockingUnaryCall(
          getChannel(), getSignalSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.dunkware.net.proto.stream.GEntitySignalStreamResponse> signalStream(
        com.dunkware.net.proto.stream.GEntitySignalStreamRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getSignalStreamMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.dunkware.net.proto.stream.GEntitySearchResponse entitySearch(com.dunkware.net.proto.stream.GEntitySearchRequest request) {
      return blockingUnaryCall(
          getChannel(), getEntitySearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.dunkware.net.proto.stream.GEntityStreamResponse> entityStream(
        com.dunkware.net.proto.stream.GEntityStreamRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getEntityStreamMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.dunkware.net.proto.stream.GInstrumentVarsResponse> instrumentVars(
        com.dunkware.net.proto.stream.GInstrumentVarsRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getInstrumentVarsMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.dunkware.net.proto.chart.Grid> testSignalGrid(
        com.dunkware.net.proto.stream.GEntitySignalQuery request) {
      return futureUnaryCall(
          getChannel().newCall(getTestSignalGridMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.dunkware.net.proto.chart.Grid> testEntityGrid(
        com.dunkware.net.proto.stream.GEntityQuery request) {
      return futureUnaryCall(
          getChannel().newCall(getTestEntityGridMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.dunkware.net.proto.stream.GStreamSpecResponse> getStreamSpec(
        com.dunkware.net.proto.stream.GStreamSpecRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetStreamSpecMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.dunkware.net.proto.stream.GEntitySignalSearchResponse> signalSearch(
        com.dunkware.net.proto.stream.GEntitySignalSearchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSignalSearchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.dunkware.net.proto.stream.GEntitySearchResponse> entitySearch(
        com.dunkware.net.proto.stream.GEntitySearchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEntitySearchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TEST_SIGNAL_GRID = 0;
  private static final int METHODID_TEST_ENTITY_GRID = 1;
  private static final int METHODID_GET_STREAM_SPEC = 2;
  private static final int METHODID_SIGNAL_SEARCH = 3;
  private static final int METHODID_SIGNAL_STREAM = 4;
  private static final int METHODID_ENTITY_SEARCH = 5;
  private static final int METHODID_ENTITY_STREAM = 6;
  private static final int METHODID_INSTRUMENT_VARS = 7;
  private static final int METHODID_AUTO_COMPLETE_SEARCH = 8;

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
        case METHODID_TEST_SIGNAL_GRID:
          serviceImpl.testSignalGrid((com.dunkware.net.proto.stream.GEntitySignalQuery) request,
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.Grid>) responseObserver);
          break;
        case METHODID_TEST_ENTITY_GRID:
          serviceImpl.testEntityGrid((com.dunkware.net.proto.stream.GEntityQuery) request,
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.chart.Grid>) responseObserver);
          break;
        case METHODID_GET_STREAM_SPEC:
          serviceImpl.getStreamSpec((com.dunkware.net.proto.stream.GStreamSpecRequest) request,
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GStreamSpecResponse>) responseObserver);
          break;
        case METHODID_SIGNAL_SEARCH:
          serviceImpl.signalSearch((com.dunkware.net.proto.stream.GEntitySignalSearchRequest) request,
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GEntitySignalSearchResponse>) responseObserver);
          break;
        case METHODID_SIGNAL_STREAM:
          serviceImpl.signalStream((com.dunkware.net.proto.stream.GEntitySignalStreamRequest) request,
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GEntitySignalStreamResponse>) responseObserver);
          break;
        case METHODID_ENTITY_SEARCH:
          serviceImpl.entitySearch((com.dunkware.net.proto.stream.GEntitySearchRequest) request,
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GEntitySearchResponse>) responseObserver);
          break;
        case METHODID_ENTITY_STREAM:
          serviceImpl.entityStream((com.dunkware.net.proto.stream.GEntityStreamRequest) request,
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GEntityStreamResponse>) responseObserver);
          break;
        case METHODID_INSTRUMENT_VARS:
          serviceImpl.instrumentVars((com.dunkware.net.proto.stream.GInstrumentVarsRequest) request,
              (io.grpc.stub.StreamObserver<com.dunkware.net.proto.stream.GInstrumentVarsResponse>) responseObserver);
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
              .addMethod(getTestSignalGridMethod())
              .addMethod(getTestEntityGridMethod())
              .addMethod(getGetStreamSpecMethod())
              .addMethod(getSignalSearchMethod())
              .addMethod(getSignalStreamMethod())
              .addMethod(getEntitySearchMethod())
              .addMethod(getEntityStreamMethod())
              .addMethod(getInstrumentVarsMethod())
              .addMethod(getAutoCompleteSearchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
