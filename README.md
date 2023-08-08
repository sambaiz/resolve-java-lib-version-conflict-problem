## resolve-java-lib-version-conflict-problem

- (ja) [Javaでの互換性のないライブラリのバージョン衝突問題をrelocationや独自のクラスローダーによって解消する - sambaiz-net](https://www.sambaiz.net/article/443/)
- (en) [Solve an incompatible version conflict problem in Java by relocation and custom ClassLoader - sambaiz-net](https://www.sambaiz.net/en/article/443/)

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
jdk.internal.loader.ClassLoaders$AppClassLoader@277050dc
findClass net.sambaiz.legacylib.Legacy
findClass com.google.common.io.InputSupplier
findClass com.google.common.io.ByteStreams
...
legacy ok
findClass net.sambaiz.modernlib.Modern
findClass com.google.common.io.ByteStreams
findClass com.google.common.io.ByteStreams$1
findClass com.google.common.io.ByteStreams$LimitedInputStream
findClass com.google.common.io.ByteArrayDataInput
findClass com.google.common.io.ByteArrayDataOutput
modern ok
```