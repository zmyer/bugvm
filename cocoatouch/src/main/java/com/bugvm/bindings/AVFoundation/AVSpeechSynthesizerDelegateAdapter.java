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
package com.bugvm.bindings.AVFoundation;

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
import com.bugvm.apple.corefoundation.*;
import com.bugvm.apple.dispatch.*;
import com.bugvm.apple.coreanimation.*;
import com.bugvm.apple.coreimage.*;
import com.bugvm.apple.coregraphics.*;
import com.bugvm.apple.coreaudio.*;
import com.bugvm.apple.coremedia.*;
import com.bugvm.apple.corevideo.*;
import com.bugvm.apple.mediatoolbox.*;
import com.bugvm.apple.audiotoolbox.*;
import com.bugvm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVSpeechSynthesizerDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements AVSpeechSynthesizerDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("speechSynthesizer:didStartSpeechUtterance:")
    public void didStartSpeechUtterance(AVSpeechSynthesizer synthesizer, AVSpeechUtterance utterance) {}
    @NotImplemented("speechSynthesizer:didFinishSpeechUtterance:")
    public void didFinishSpeechUtterance(AVSpeechSynthesizer synthesizer, AVSpeechUtterance utterance) {}
    @NotImplemented("speechSynthesizer:didPauseSpeechUtterance:")
    public void didPauseSpeechUtterance(AVSpeechSynthesizer synthesizer, AVSpeechUtterance utterance) {}
    @NotImplemented("speechSynthesizer:didContinueSpeechUtterance:")
    public void didContinueSpeechUtterance(AVSpeechSynthesizer synthesizer, AVSpeechUtterance utterance) {}
    @NotImplemented("speechSynthesizer:didCancelSpeechUtterance:")
    public void didCancelSpeechUtterance(AVSpeechSynthesizer synthesizer, AVSpeechUtterance utterance) {}
    @NotImplemented("speechSynthesizer:willSpeakRangeOfSpeechString:utterance:")
    public void willSpeak(AVSpeechSynthesizer synthesizer, @ByVal NSRange characterRange, AVSpeechUtterance utterance) {}
    /*</methods>*/
}
