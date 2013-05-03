// Copyright 2013 Square, Inc.
package com.squareup.mimecraft;

import java.io.ByteArrayOutputStream;
import org.junit.Test;

import static com.squareup.mimecraft.Utils.UTF_8;
import static org.fest.assertions.api.Assertions.assertThat;

public class FormWriterTest {
  @Test public void urlEncoding() throws Exception {
    FormEncoding fe = new FormEncoding.Builder() //
        .addPart("a&b", "c=d") //
        .addPart("space, the", "final frontier") //
        .build();

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    fe.writeBodyTo(out);
    String actual = new String(out.toByteArray(), UTF_8);
    assertThat(actual).isEqualTo("a%26b=c%3Dd&space%2C+the=final+frontier");
  }

  @Test public void encodedPairs() throws Exception {
    FormEncoding fe1 = new FormEncoding.Builder() //
        .addPart("sim", "ple") //
        .build();

    ByteArrayOutputStream out1 = new ByteArrayOutputStream();
    fe1.writeBodyTo(out1);
    String actual1 = new String(out1.toByteArray(), UTF_8);
    assertThat(actual1).isEqualTo("sim=ple");

    FormEncoding fe2 = new FormEncoding.Builder() //
        .addPart("sim", "ple") //
        .addPart("hey", "there") //
        .addPart("help", "me") //
        .build();

    ByteArrayOutputStream out2 = new ByteArrayOutputStream();
    fe2.writeBodyTo(out2);
    String actual2 = new String(out2.toByteArray(), UTF_8);
    assertThat(actual2).isEqualTo("sim=ple&hey=there&help=me");
  }
}
