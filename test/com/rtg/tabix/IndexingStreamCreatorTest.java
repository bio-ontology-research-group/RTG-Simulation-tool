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
package com.rtg.tabix;

import java.io.File;
import java.io.IOException;

import com.rtg.util.io.TestDirectory;
import com.rtg.vcf.DefaultVcfWriter;
import com.rtg.vcf.VcfRecord;
import com.rtg.vcf.VcfWriter;
import com.rtg.vcf.header.VcfHeader;

import junit.framework.TestCase;

/**
 */
public class IndexingStreamCreatorTest extends TestCase {

  public void testIt() throws IOException {
    try (TestDirectory dir = new TestDirectory("isct")) {
      final File vcfFile = new File(dir, "thingy.vcf.gz");
      final VcfHeader header = new VcfHeader();
      header.addCommonHeader();
      header.addSampleName("sample");
      try (IndexingStreamCreator streamHandler = new IndexingStreamCreator(vcfFile, System.out, true, new TabixIndexer.VcfIndexerFactory(), true)) {
        try (VcfWriter writer = new DefaultVcfWriter(header, streamHandler.createStreamsAndStartThreads())) {
          final VcfRecord rec = new VcfRecord("seq", 100, "A");
          rec.addAltCall("T");
          rec.setNumberOfSamples(1);
          rec.addFormatAndSample("GT", "1/0");
          writer.write(rec);
        }
      }
      assertTrue(new File(dir, "thingy.vcf.gz.tbi").exists());
    }
  }
}
