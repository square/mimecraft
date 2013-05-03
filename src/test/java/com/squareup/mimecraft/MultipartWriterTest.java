// Copyright 2013 Square, Inc.
package com.squareup.mimecraft;

import java.io.ByteArrayOutputStream;
import org.junit.Test;

import static com.squareup.mimecraft.Utils.UTF_8;
import static org.fest.assertions.api.Assertions.assertThat;

public class MultipartWriterTest {
  @Test(expected = IllegalStateException.class)
  public void onePartRequired() throws Exception {
    new Multipart.Builder().build();
  }

  @Test public void singlePart() throws Exception {
    String expected = "" //
        + "--123\r\n" //
        + "\r\n" //
        + "Hello, World!\r\n" //
        + "--123--";

    Multipart m = new Multipart.Builder("123")
        .addPart(new TestPart("Hello, World!"))
        .build();

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    m.writeBodyTo(out);
    String actual = new String(out.toByteArray(), UTF_8);
    assertThat(actual).isEqualTo(expected);
    assertThat(m.getHeaders()).containsExactly("Content-Type: multipart/mixed; boundary=123");
  }

  @Test public void threeParts() throws Exception {
    String expected = ""
        + "--123\r\n"
        + "\r\n"
        + "Quick\r\n"
        + "--123\r\n"
        + "\r\n"
        + "Brown\r\n"
        + "--123\r\n"
        + "\r\n"
        + "Fox\r\n"
        + "--123--";

    Multipart m = new Multipart.Builder("123")
        .addPart(new TestPart("Quick"))
        .addPart(new TestPart("Brown"))
        .addPart(new TestPart("Fox"))
        .build();

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    m.writeBodyTo(out);
    String actual = new String(out.toByteArray(), UTF_8);
    assertThat(actual).isEqualTo(expected);
    assertThat(m.getHeaders()).containsExactly("Content-Type: multipart/mixed; boundary=123");
  }
}
