## resolve-java-lib-version-conlifct-issue

```sh
$ mvn clean package dependency:tree
$ java -cp myapp/target/myapp-1.0.0.jar:legacylib/target/legacylib-1.0.0.jar:modernlib/target/modernlib-1.0.0.jar net.sambaiz.Main
Exception in thread "main" java.lang.NoSuchMethodError: com.google.common.io.ByteStreams.exhaust(Ljava/io/InputStream;)J
        at net.sambaiz.modernlib.Modern.bar(Modern.java:13)
        at net.sambaiz.Main.main(Main.java:10)

$ java -cp myapp/target/myapp-1.0.0.jar:modernlib/target/modernlib-1.0.0.jar:legacylib/target/legacylib-1.0.0.jar net.sambaiz.Main
modern ok
Exception in thread "main" java.lang.NoSuchMethodError: com.google.common.io.ByteStreams.asByteSource(Lcom/google/common/io/InputSupplier;)Lcom/google/common/io/ByteSource;
        at net.sambaiz.legacylib.Legacy.foo(Legacy.java:16)
        at net.sambaiz.Main.main(Main.java:11)
        
$ java -cp myapp/target/myapp-1.0.0.jar net.sambaiz.MyClassLoader
```