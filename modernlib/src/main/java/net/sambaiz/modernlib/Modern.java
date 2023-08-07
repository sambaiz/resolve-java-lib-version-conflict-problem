package net.sambaiz.modernlib;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.google.common.io.ByteStreams;

public class Modern {
    public static void bar() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("aaaaa".getBytes());
        ByteStreams.exhaust(inputStream); // method added in guava 20.0
        System.out.println("modern ok");
    }
}