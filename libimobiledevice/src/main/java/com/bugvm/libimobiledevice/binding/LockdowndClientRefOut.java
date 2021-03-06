/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.bugvm.libimobiledevice.binding;

public class LockdowndClientRefOut {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected LockdowndClientRefOut(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(LockdowndClientRefOut obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        LibIMobileDeviceJNI.delete_LockdowndClientRefOut(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public LockdowndClientRef getValue() {
    long cPtr = LibIMobileDeviceJNI.LockdowndClientRefOut_value_get(swigCPtr, this);
    return (cPtr == 0) ? null : new LockdowndClientRef(cPtr, false);
  }

  public LockdowndClientRefOut() {
    this(LibIMobileDeviceJNI.new_LockdowndClientRefOut(), true);
  }

}
