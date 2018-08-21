/*
 * Copyright (c) 2017. Real Time Genomics Limited.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the
 *    distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.rtg.reader;

import com.rtg.mode.DnaUtils;
import com.rtg.util.PortableRandom;
import com.rtg.util.test.RandomDna;

import junit.framework.TestCase;

public class FullReadTrimmerTest extends TestCase {

  private void check(String raw) {
    final FullReadTrimmer t = new FullReadTrimmer();
    final byte[] read = DnaUtils.encodeString(RandomDna.random(raw.length(), new PortableRandom(42)));
    final byte[] quals = FastaUtils.asciiToRawQuality(raw);
    assertEquals(0, t.trimRead(read, quals, raw.length()));
  }

  public void testExamples() {
    check(";88/;5=8??<??;?=;2222*274=???0?;????????3????3????>8?7>>>=>?>?>==:97*744*7799/=6=<975)'''+,',/");
    check("??<?89.43---+3,79=?5?;??=??????????>??;>>?9;8441,,+14479;9976,2//-,,,-/),13++.43/12");
    check("::::18:5:4:458:::':::8664/.(1,,''.22(262/.");
    check("3.1-'''''))'11+2.6811::88:6::484,440111*))'--''**1+4.55.32(*--''''-')*1+0+23++45661885");
    check(">???>??>???");  //shorter than the window length!
  }

}

