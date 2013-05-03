// Copyright 2013 Square, Inc.
package com.squareup.mimecraft;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import static com.squareup.mimecraft.Utils.UTF_8;

public class TestPart implements Part {
  private final byte[] content;

  public TestPart(String content) {
    this.content = content.getBytes(UTF_8);
  }

  @Override public List<String> getHeaders() {
    return Collections.emptyList();
  }

  @Override public void writeBodyTo(OutputStream out) throws IOException {
    out.write(content);
  }
}
