// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ticketManagement.proto

package de.uniba.rz.io.rpc;

/**
 * Protobuf enum {@code Priority}
 */
public enum Priority
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>CRITICAL = 0;</code>
   */
  CRITICAL(0),
  /**
   * <code>MAJOR = 1;</code>
   */
  MAJOR(1),
  /**
   * <code>MINOR = 2;</code>
   */
  MINOR(2),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>CRITICAL = 0;</code>
   */
  public static final int CRITICAL_VALUE = 0;
  /**
   * <code>MAJOR = 1;</code>
   */
  public static final int MAJOR_VALUE = 1;
  /**
   * <code>MINOR = 2;</code>
   */
  public static final int MINOR_VALUE = 2;


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
  public static Priority valueOf(int value) {
    return forNumber(value);
  }

  public static Priority forNumber(int value) {
    switch (value) {
      case 0: return CRITICAL;
      case 1: return MAJOR;
      case 2: return MINOR;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<Priority>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      Priority> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<Priority>() {
          public Priority findValueByNumber(int number) {
            return Priority.forNumber(number);
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
    return de.uniba.rz.io.rpc.TicketManagement.getDescriptor().getEnumTypes().get(1);
  }

  private static final Priority[] VALUES = values();

  public static Priority valueOf(
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

  private Priority(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:Priority)
}

