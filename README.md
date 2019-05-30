

# 実行メモ

```bash
$ sbt assembly
$ cd target/scala-2.12
$ $GRAALVM_HOME/bin/native-image \
   --initialize-at-build-time \
   -jar examples_bigtable-assembly-0.1.jar \
   -H:IncludeResources=".*.xml|.*.conf|.*.proto" \
   -H:+ReportUnsupportedElementsAtRuntime \
   -H:Name=app \
   --verbose
$  ./app 
  Exception in thread "main" java.lang.IllegalStateException: Could not find an appropriate constructor for com.google.cloud.bigtable.hbase1_x.BigtableConnection
          at com.google.cloud.bigtable.hbase.BigtableConfiguration.connect(BigtableConfiguration.java:183)
          at com.google.cloud.bigtable.hbase.BigtableConfiguration.connect(BigtableConfiguration.java:156)
          at Example$.main(Example.scala:18)
          at Example.main(Example.scala)
  Caused by: java.lang.NoSuchMethodException: com.google.cloud.bigtable.hbase1_x.BigtableConnection.<init>org.apache.hadoop.conf.Configuration
          at java.lang.Class.getConstructor0(DynamicHub.java:3082)
          at java.lang.Class.getConstructor(DynamicHub.java:1825)
          at com.google.cloud.bigtable.hbase.BigtableConfiguration.connect(BigtableConfiguration.java:180)
```
    

# 改善できそうな記事
[GraalVM の native image を使って Java で爆速 Lambda の夢を見る#リフレクション定義ファイルの自作](https://qiita.com/kencharos/items/69e43965515f368bc4a3#%E3%83%AA%E3%83%95%E3%83%AC%E3%82%AF%E3%82%B7%E3%83%A7%E3%83%B3%E5%AE%9A%E7%BE%A9%E3%83%95%E3%82%A1%E3%82%A4%E3%83%AB%E3%81%AE%E8%87%AA%E4%BD%9C)

