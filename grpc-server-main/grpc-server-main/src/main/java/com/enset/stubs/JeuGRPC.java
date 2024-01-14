package com.enset.stubs;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: jeu.proto")
public final class JeuGRPC {

  private JeuGRPC() {}

  public static final String SERVICE_NAME = "Jeu";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<Jeu.guessMsg,
          Jeu.repMsg> getRequestReplayMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "requestReplay",
      requestType = Jeu.guessMsg.class,
      responseType = Jeu.repMsg.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<Jeu.guessMsg,
          Jeu.repMsg> getRequestReplayMethod() {
    io.grpc.MethodDescriptor<Jeu.guessMsg, Jeu.repMsg> getRequestReplayMethod;
    if ((getRequestReplayMethod = JeuGRPC.getRequestReplayMethod) == null) {
      synchronized (JeuGRPC.class) {
        if ((getRequestReplayMethod = JeuGRPC.getRequestReplayMethod) == null) {
          JeuGRPC.getRequestReplayMethod = getRequestReplayMethod =
              io.grpc.MethodDescriptor.<Jeu.guessMsg, Jeu.repMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Jeu", "requestReplay"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                      Jeu.guessMsg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                      Jeu.repMsg.getDefaultInstance()))
                  .setSchemaDescriptor(new jeuMethodDescriptorSupplier("requestReplay"))
                  .build();
          }
        }
     }
     return getRequestReplayMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static jeuStub newStub(io.grpc.Channel channel) {
    return new jeuStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static jeuBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new jeuBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static jeuFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new jeuFutureStub(channel);
  }

  /**
   */
  public static abstract class jeuImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<Jeu.guessMsg> requestReplay(
        io.grpc.stub.StreamObserver<Jeu.repMsg> responseObserver) {
      return asyncUnimplementedStreamingCall(getRequestReplayMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRequestReplayMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                      Jeu.guessMsg,
                      Jeu.repMsg>(
                  this, METHODID_REQUEST_REPLAY)))
          .build();
    }
  }

  /**
   */
  public static final class jeuStub extends io.grpc.stub.AbstractStub<jeuStub> {
    private jeuStub(io.grpc.Channel channel) {
      super(channel);
    }

    private jeuStub(io.grpc.Channel channel,
                    io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected jeuStub build(io.grpc.Channel channel,
                            io.grpc.CallOptions callOptions) {
      return new jeuStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<Jeu.guessMsg> requestReplay(
        io.grpc.stub.StreamObserver<Jeu.repMsg> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getRequestReplayMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class jeuBlockingStub extends io.grpc.stub.AbstractStub<jeuBlockingStub> {
    private jeuBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private jeuBlockingStub(io.grpc.Channel channel,
                            io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected jeuBlockingStub build(io.grpc.Channel channel,
                                    io.grpc.CallOptions callOptions) {
      return new jeuBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class jeuFutureStub extends io.grpc.stub.AbstractStub<jeuFutureStub> {
    private jeuFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private jeuFutureStub(io.grpc.Channel channel,
                          io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected jeuFutureStub build(io.grpc.Channel channel,
                                  io.grpc.CallOptions callOptions) {
      return new jeuFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_REQUEST_REPLAY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final jeuImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(jeuImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REQUEST_REPLAY:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.requestReplay(
              (io.grpc.stub.StreamObserver<Jeu.repMsg>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class jeuBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    jeuBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Jeu.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Jeu");
    }
  }

  private static final class jeuFileDescriptorSupplier
      extends jeuBaseDescriptorSupplier {
    jeuFileDescriptorSupplier() {}
  }

  private static final class jeuMethodDescriptorSupplier
      extends jeuBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    jeuMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (JeuGRPC.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new jeuFileDescriptorSupplier())
              .addMethod(getRequestReplayMethod())
              .build();
        }
      }
    }
    return result;
  }
}
