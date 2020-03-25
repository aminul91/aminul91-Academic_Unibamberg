package de.uniba.rz.io.rpc;

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
    value = "by gRPC proto compiler (version 1.20.0)",
    comments = "Source: ticketManagement.proto")
public final class TicketServiceGrpc {

  private TicketServiceGrpc() {}

  public static final String SERVICE_NAME = "TicketService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<de.uniba.rz.io.rpc.TicketRequest,
      de.uniba.rz.io.rpc.TicketResponse> getCreateNewTicketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateNewTicket",
      requestType = de.uniba.rz.io.rpc.TicketRequest.class,
      responseType = de.uniba.rz.io.rpc.TicketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<de.uniba.rz.io.rpc.TicketRequest,
      de.uniba.rz.io.rpc.TicketResponse> getCreateNewTicketMethod() {
    io.grpc.MethodDescriptor<de.uniba.rz.io.rpc.TicketRequest, de.uniba.rz.io.rpc.TicketResponse> getCreateNewTicketMethod;
    if ((getCreateNewTicketMethod = TicketServiceGrpc.getCreateNewTicketMethod) == null) {
      synchronized (TicketServiceGrpc.class) {
        if ((getCreateNewTicketMethod = TicketServiceGrpc.getCreateNewTicketMethod) == null) {
          TicketServiceGrpc.getCreateNewTicketMethod = getCreateNewTicketMethod = 
              io.grpc.MethodDescriptor.<de.uniba.rz.io.rpc.TicketRequest, de.uniba.rz.io.rpc.TicketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TicketService", "CreateNewTicket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  de.uniba.rz.io.rpc.TicketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  de.uniba.rz.io.rpc.TicketResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TicketServiceMethodDescriptorSupplier("CreateNewTicket"))
                  .build();
          }
        }
     }
     return getCreateNewTicketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<de.uniba.rz.io.rpc.msgToken,
      de.uniba.rz.io.rpc.TicketList> getGetAllTicketsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllTickets",
      requestType = de.uniba.rz.io.rpc.msgToken.class,
      responseType = de.uniba.rz.io.rpc.TicketList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<de.uniba.rz.io.rpc.msgToken,
      de.uniba.rz.io.rpc.TicketList> getGetAllTicketsMethod() {
    io.grpc.MethodDescriptor<de.uniba.rz.io.rpc.msgToken, de.uniba.rz.io.rpc.TicketList> getGetAllTicketsMethod;
    if ((getGetAllTicketsMethod = TicketServiceGrpc.getGetAllTicketsMethod) == null) {
      synchronized (TicketServiceGrpc.class) {
        if ((getGetAllTicketsMethod = TicketServiceGrpc.getGetAllTicketsMethod) == null) {
          TicketServiceGrpc.getGetAllTicketsMethod = getGetAllTicketsMethod = 
              io.grpc.MethodDescriptor.<de.uniba.rz.io.rpc.msgToken, de.uniba.rz.io.rpc.TicketList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TicketService", "GetAllTickets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  de.uniba.rz.io.rpc.msgToken.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  de.uniba.rz.io.rpc.TicketList.getDefaultInstance()))
                  .setSchemaDescriptor(new TicketServiceMethodDescriptorSupplier("GetAllTickets"))
                  .build();
          }
        }
     }
     return getGetAllTicketsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<de.uniba.rz.io.rpc.ProtoMessageDTO,
      de.uniba.rz.io.rpc.ProtoMessageDTO> getCreateTicketServiceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTicketService",
      requestType = de.uniba.rz.io.rpc.ProtoMessageDTO.class,
      responseType = de.uniba.rz.io.rpc.ProtoMessageDTO.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<de.uniba.rz.io.rpc.ProtoMessageDTO,
      de.uniba.rz.io.rpc.ProtoMessageDTO> getCreateTicketServiceMethod() {
    io.grpc.MethodDescriptor<de.uniba.rz.io.rpc.ProtoMessageDTO, de.uniba.rz.io.rpc.ProtoMessageDTO> getCreateTicketServiceMethod;
    if ((getCreateTicketServiceMethod = TicketServiceGrpc.getCreateTicketServiceMethod) == null) {
      synchronized (TicketServiceGrpc.class) {
        if ((getCreateTicketServiceMethod = TicketServiceGrpc.getCreateTicketServiceMethod) == null) {
          TicketServiceGrpc.getCreateTicketServiceMethod = getCreateTicketServiceMethod = 
              io.grpc.MethodDescriptor.<de.uniba.rz.io.rpc.ProtoMessageDTO, de.uniba.rz.io.rpc.ProtoMessageDTO>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TicketService", "CreateTicketService"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  de.uniba.rz.io.rpc.ProtoMessageDTO.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  de.uniba.rz.io.rpc.ProtoMessageDTO.getDefaultInstance()))
                  .setSchemaDescriptor(new TicketServiceMethodDescriptorSupplier("CreateTicketService"))
                  .build();
          }
        }
     }
     return getCreateTicketServiceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TicketServiceStub newStub(io.grpc.Channel channel) {
    return new TicketServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TicketServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TicketServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TicketServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TicketServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TicketServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void createNewTicket(de.uniba.rz.io.rpc.TicketRequest request,
        io.grpc.stub.StreamObserver<de.uniba.rz.io.rpc.TicketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateNewTicketMethod(), responseObserver);
    }

    /**
     */
    public void getAllTickets(de.uniba.rz.io.rpc.msgToken request,
        io.grpc.stub.StreamObserver<de.uniba.rz.io.rpc.TicketList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAllTicketsMethod(), responseObserver);
    }

    /**
     */
    public void createTicketService(de.uniba.rz.io.rpc.ProtoMessageDTO request,
        io.grpc.stub.StreamObserver<de.uniba.rz.io.rpc.ProtoMessageDTO> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateTicketServiceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateNewTicketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                de.uniba.rz.io.rpc.TicketRequest,
                de.uniba.rz.io.rpc.TicketResponse>(
                  this, METHODID_CREATE_NEW_TICKET)))
          .addMethod(
            getGetAllTicketsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                de.uniba.rz.io.rpc.msgToken,
                de.uniba.rz.io.rpc.TicketList>(
                  this, METHODID_GET_ALL_TICKETS)))
          .addMethod(
            getCreateTicketServiceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                de.uniba.rz.io.rpc.ProtoMessageDTO,
                de.uniba.rz.io.rpc.ProtoMessageDTO>(
                  this, METHODID_CREATE_TICKET_SERVICE)))
          .build();
    }
  }

  /**
   */
  public static final class TicketServiceStub extends io.grpc.stub.AbstractStub<TicketServiceStub> {
    private TicketServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TicketServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TicketServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TicketServiceStub(channel, callOptions);
    }

    /**
     */
    public void createNewTicket(de.uniba.rz.io.rpc.TicketRequest request,
        io.grpc.stub.StreamObserver<de.uniba.rz.io.rpc.TicketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateNewTicketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllTickets(de.uniba.rz.io.rpc.msgToken request,
        io.grpc.stub.StreamObserver<de.uniba.rz.io.rpc.TicketList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAllTicketsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createTicketService(de.uniba.rz.io.rpc.ProtoMessageDTO request,
        io.grpc.stub.StreamObserver<de.uniba.rz.io.rpc.ProtoMessageDTO> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateTicketServiceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TicketServiceBlockingStub extends io.grpc.stub.AbstractStub<TicketServiceBlockingStub> {
    private TicketServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TicketServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TicketServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TicketServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public de.uniba.rz.io.rpc.TicketResponse createNewTicket(de.uniba.rz.io.rpc.TicketRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateNewTicketMethod(), getCallOptions(), request);
    }

    /**
     */
    public de.uniba.rz.io.rpc.TicketList getAllTickets(de.uniba.rz.io.rpc.msgToken request) {
      return blockingUnaryCall(
          getChannel(), getGetAllTicketsMethod(), getCallOptions(), request);
    }

    /**
     */
    public de.uniba.rz.io.rpc.ProtoMessageDTO createTicketService(de.uniba.rz.io.rpc.ProtoMessageDTO request) {
      return blockingUnaryCall(
          getChannel(), getCreateTicketServiceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TicketServiceFutureStub extends io.grpc.stub.AbstractStub<TicketServiceFutureStub> {
    private TicketServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TicketServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TicketServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TicketServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<de.uniba.rz.io.rpc.TicketResponse> createNewTicket(
        de.uniba.rz.io.rpc.TicketRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateNewTicketMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<de.uniba.rz.io.rpc.TicketList> getAllTickets(
        de.uniba.rz.io.rpc.msgToken request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAllTicketsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<de.uniba.rz.io.rpc.ProtoMessageDTO> createTicketService(
        de.uniba.rz.io.rpc.ProtoMessageDTO request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateTicketServiceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_NEW_TICKET = 0;
  private static final int METHODID_GET_ALL_TICKETS = 1;
  private static final int METHODID_CREATE_TICKET_SERVICE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TicketServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TicketServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_NEW_TICKET:
          serviceImpl.createNewTicket((de.uniba.rz.io.rpc.TicketRequest) request,
              (io.grpc.stub.StreamObserver<de.uniba.rz.io.rpc.TicketResponse>) responseObserver);
          break;
        case METHODID_GET_ALL_TICKETS:
          serviceImpl.getAllTickets((de.uniba.rz.io.rpc.msgToken) request,
              (io.grpc.stub.StreamObserver<de.uniba.rz.io.rpc.TicketList>) responseObserver);
          break;
        case METHODID_CREATE_TICKET_SERVICE:
          serviceImpl.createTicketService((de.uniba.rz.io.rpc.ProtoMessageDTO) request,
              (io.grpc.stub.StreamObserver<de.uniba.rz.io.rpc.ProtoMessageDTO>) responseObserver);
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

  private static abstract class TicketServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TicketServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return de.uniba.rz.io.rpc.TicketManagement.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TicketService");
    }
  }

  private static final class TicketServiceFileDescriptorSupplier
      extends TicketServiceBaseDescriptorSupplier {
    TicketServiceFileDescriptorSupplier() {}
  }

  private static final class TicketServiceMethodDescriptorSupplier
      extends TicketServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TicketServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TicketServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TicketServiceFileDescriptorSupplier())
              .addMethod(getCreateNewTicketMethod())
              .addMethod(getGetAllTicketsMethod())
              .addMethod(getCreateTicketServiceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
