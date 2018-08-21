/*
 * Copyright (c) 2014. Real Time Genomics Limited.
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

package com.rtg.util;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

/**
 */
public class MD5UtilsTest extends TestCase {

  //Long default buffer
  public void testLong() throws IOException {
    final InputStream is = Resources.getResourceAsStream("com/rtg/util/resources/md5utils.txt");
    //taken from the manifest file which was generated by "md5 -r" on MacOSX
    assertEquals("762b36734794180251a4a46aefde8a68", MD5Utils.md5(is));
    is.close();
  }

  //Short buffer
  public void testShort() throws IOException {
    final InputStream is = Resources.getResourceAsStream("com/rtg/util/resources/md5utils.txt");
    //taken from the manifest file which was generated by "md5 -r" on MacOSX
    assertEquals("762b36734794180251a4a46aefde8a68", MD5Utils.md5(is, 10));
    is.close();
  }

  public void testString() {
    assertEquals("bb1ce698abbc629090bfdb4961343bb4", MD5Utils.md5("fluffy bunnies are fluffy"));
  }
}
