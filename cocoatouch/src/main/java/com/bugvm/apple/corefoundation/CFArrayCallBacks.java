/*
 * Copyright (C) 2013-2015 RoboVM AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bugvm.apple.corefoundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import com.bugvm.objc.*;
import com.bugvm.objc.annotation.*;
import com.bugvm.objc.block.*;
import com.bugvm.rt.*;
import com.bugvm.rt.annotation.*;
import com.bugvm.rt.bro.*;
import com.bugvm.rt.bro.annotation.*;
import com.bugvm.rt.bro.ptr.*;
import com.bugvm.apple.foundation.*;
import com.bugvm.apple.dispatch.*;
import com.bugvm.apple.coreservices.*;
import com.bugvm.apple.coremedia.*;
import com.bugvm.apple.uikit.*;
import com.bugvm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*//*</visibility>*/ class /*<name>*/CFArrayCallBacks/*</name>*/ 
    extends /*<extends>*/Struct<CFArrayCallBacks>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFArrayCallBacksPtr extends Ptr<CFArrayCallBacks, CFArrayCallBacksPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CFArrayCallBacks() {}
    public CFArrayCallBacks(@MachineSizedSInt long version, FunctionPtr retain, FunctionPtr release, FunctionPtr copyDescription, FunctionPtr equal) {
        this.setVersion(version);
        this.setRetain(retain);
        this.setRelease(release);
        this.setCopyDescription(copyDescription);
        this.setEqual(equal);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedSInt long getVersion();
    @StructMember(0) public native CFArrayCallBacks setVersion(@MachineSizedSInt long version);
    @StructMember(1) public native FunctionPtr getRetain();
    @StructMember(1) public native CFArrayCallBacks setRetain(FunctionPtr retain);
    @StructMember(2) public native FunctionPtr getRelease();
    @StructMember(2) public native CFArrayCallBacks setRelease(FunctionPtr release);
    @StructMember(3) public native FunctionPtr getCopyDescription();
    @StructMember(3) public native CFArrayCallBacks setCopyDescription(FunctionPtr copyDescription);
    @StructMember(4) public native FunctionPtr getEqual();
    @StructMember(4) public native CFArrayCallBacks setEqual(FunctionPtr equal);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
