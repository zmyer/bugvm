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
package com.bugvm.bindings.AudioUnit;

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
import com.bugvm.apple.audiotoolbox.*;
import com.bugvm.apple.corefoundation.*;
import com.bugvm.apple.coreaudio.*;
import com.bugvm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsUnsignedIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/AUParameterUnit/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Generic(0L),
    Indexed(1L),
    Boolean(2L),
    Percent(3L),
    Seconds(4L),
    SampleFrames(5L),
    Phase(6L),
    Rate(7L),
    Hertz(8L),
    Cents(9L),
    RelativeSemiTones(10L),
    MIDINoteNumber(11L),
    MIDIController(12L),
    Decibels(13L),
    LinearGain(14L),
    Degrees(15L),
    EqualPowerCrossfade(16L),
    MixerFaderCurve1(17L),
    Pan(18L),
    Meters(19L),
    AbsoluteCents(20L),
    Octaves(21L),
    BPM(22L),
    Beats(23L),
    Milliseconds(24L),
    Ratio(25L),
    CustomUnit(26L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AUParameterUnit/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AUParameterUnit/*</name>*/ valueOf(long n) {
        for (/*<name>*/AUParameterUnit/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AUParameterUnit/*</name>*/.class.getName());
    }
}
