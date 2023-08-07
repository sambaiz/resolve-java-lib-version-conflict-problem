package net.sambaiz.legacylib;

import com.google.common.io.ByteStreams;
import com.google.common.io.InputSupplier;
import com.google.common.io.OutputSupplier;
import com.google.common.util.concurrent.SimpleTimeLimiter;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Legacy {
    public static void foo() throws IOException {
        InputSupplier<InputStream> inputSupplier = () -> new ByteArrayInputStream("legacy ok".getBytes()); // interface removed in guava 20
        byte[] bytes = ByteStreams.asByteSource(inputSupplier).read(); // method removed in guava 18
        System.out.println(new String(bytes, "UTF-8"));
    }
}