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
package com.bugvm.apple.uikit;

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
import com.bugvm.apple.coreanimation.*;
import com.bugvm.apple.coregraphics.*;
import com.bugvm.apple.coredata.*;
import com.bugvm.apple.coreimage.*;
import com.bugvm.apple.coretext.*;
import com.bugvm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UICollectionViewTransitionLayout/*</name>*/ 
    extends /*<extends>*/UICollectionViewLayout/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UICollectionViewTransitionLayoutPtr extends Ptr<UICollectionViewTransitionLayout, UICollectionViewTransitionLayoutPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UICollectionViewTransitionLayout.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UICollectionViewTransitionLayout() {}
    protected UICollectionViewTransitionLayout(SkipInit skipInit) { super(skipInit); }
    public UICollectionViewTransitionLayout(UICollectionViewLayout currentLayout, UICollectionViewLayout newLayout) { super((SkipInit) null); initObject(init(currentLayout, newLayout)); }
    public UICollectionViewTransitionLayout(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "transitionProgress")
    public native @MachineSizedFloat double getTransitionProgress();
    @Property(selector = "setTransitionProgress:")
    public native void setTransitionProgress(@MachineSizedFloat double v);
    @Property(selector = "currentLayout")
    public native UICollectionViewLayout getCurrentLayout();
    @Property(selector = "nextLayout")
    public native UICollectionViewLayout getNextLayout();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithCurrentLayout:nextLayout:")
    protected native @Pointer long init(UICollectionViewLayout currentLayout, UICollectionViewLayout newLayout);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    @Method(selector = "updateValue:forAnimatedKey:")
    public native void updateValue(@MachineSizedFloat double value, String key);
    @Method(selector = "valueForAnimatedKey:")
    public native @MachineSizedFloat double getValue(String key);
    /*</methods>*/
}
