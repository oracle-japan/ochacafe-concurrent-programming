# demo3

## JDK Flight Recoder + JDK Mission Control

アプリケーションを動かし、そのプロセス ID を特定します。

```bash
jcmd -l
585864 com.oracle.jp.demo.demo3.VirtualThreadMoreSleepMain # こちらをプロファイリング対象にする
586113 jdk.jcmd/sun.tools.jcmd.JCmd -l
554833 ... omit
```

プロファイリング情報を収集します。

```bash
jcmd 587908 JFR.start duration=1m filename=demo-recording.jfr
```

ダンプしたプロファイリングデータ JDK Mission Control に取り込んで参照してください。
