// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ticketManagement.proto

package de.uniba.rz.io.rpc;

/**
 * Protobuf enum {@code Status}
 */
public enum Status
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>NEW = 0;</code>
   */
  NEW(0),
  /**
   * <code>ACCEPTED = 1;</code>
   */
  ACCEPTED(1),
  /**
   * <code>REJECTED = 2;</code>
   */
  REJECTED(2),
  /**
   * <code>CLOSED = 3;</code>
   */
  CLOSED(3),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>NEW = 0;</code>
   */
  public static final int NEW_VALUE = 0;
  /**
   * <code>ACCEPTED = 1;</code>
   */
  public static final int ACCEPTED_VALUE = 1;
  /**
   * <code>REJECTED = 2;</code>
   */
  public static final int REJECTED_VALUE = 2;
  /**
   * <code>CLOSED = 3;</code>
   */
  public static final int CLOSED_VALUE = 3;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static Status valueOf(int value) {
    return forNumber(value);
  }

  public static Status forNumber(int value) {
    switch (value) {
      case 0: return NEW;
      case 1: return ACCEPTED;
      case 2: return REJECTED;
      case 3: return CLOSED;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<Status>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      Status> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<Status>() {
          public Status findValueByNumber(int number) {
            return Status.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return de.uniba.rz.io.rpc.TicketManagement.getDescriptor().getEnumTypes().get(2);
  }

  private static final Status[] VALUES = values();

  public static Status valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private Status(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:Status)
}

