Mime Craft
==========

Utility for creating RFC-compliant multipart and form-encoded HTTP request bodies.



Download
--------

Download [the latest .jar][1] or depend via Maven:

```xml
<dependency>
  <groupId>com.squareup.mimecraft</groupId>
  <artifactId>mimecraft</artifactId>
  <version>(insert latest version)</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.squareup.mimecraft:mimecraft:1.1.1'
```


Examples
--------

Form-encoded content:
```java
FormEncoding fe = new FormEncoding.Builder()
    .add("name", "Lorem Ipsum")
    .add("occupation", "Filler Text")
    .build();
```

Multipart content:
```java
Multipart m = new Multipart.Builder()
    .addPart(new Part.Builder()
        .contentType("image/png")
        .body(new File("/foo/bar/baz.png"))
        .build())
    .addPart(new Part.Builder()
        .contentType("text/plain")
        .body("The quick brown fox jumps over the lazy dog.")
        .build())
    .build();
```

Content can be written to an output stream using the `writeBodyTo` method. For best performance, use
a `BufferedOutputStream`.

Header values such as `Content-Type` and `Content-Length` are available by calling `getHeaders()`.



License
=======

    Copyright 2013 Square, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


 [1]: http://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.squareup.mimecraft&a=mimecraft&v=LATEST
