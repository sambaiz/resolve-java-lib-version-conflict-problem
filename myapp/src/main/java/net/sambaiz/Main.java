package net.sambaiz;

import net.sambaiz.legacylib.Legacy;
import net.sambaiz.modernlib.Modern;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Modern.bar();
        Legacy.foo();
    }
}