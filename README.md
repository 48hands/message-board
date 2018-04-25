# PlayFramework 練習

PlayFrameworkでメッセージボードを作成

## 事前準備

### データベースセットアップ

```
$ ./create-local-mysql-db.sh
```

### マイグレーション実行

```
$ sbt flywayMigrate
```

```
$ mysql -u root
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 58
Server version: 5.7.19 Homebrew

Copyright (c) 2000, 2017, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> use message_board
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed

mysql> show tables;
+-------------------------+
| Tables_in_message_board |
+-------------------------+
| messages                |
| schema_version          |
+-------------------------+
2 rows in set (0.00 sec)

mysql> select * from messages;
+----+----------+---------------------+---------------------+
| id | body     | create_at           | update_at           |
+----+----------+---------------------+---------------------+
|  1 | test-1   | 2018-04-24 21:47:16 | 2018-04-24 21:47:16 |
|  2 | test-2   | 2018-04-24 21:47:16 | 2018-04-24 21:47:16 |
|  3 | test-3   | 2018-04-24 21:47:16 | 2018-04-24 21:47:16 |
|  4 | test-4   | 2018-04-24 21:47:16 | 2018-04-24 21:47:16 |
|  5 | test-5   | 2018-04-24 21:47:16 | 2018-04-24 21:47:16 |
|  6 | test-6   | 2018-04-24 21:47:16 | 2018-04-24 21:47:16 |
+----+----------+---------------------+---------------------+
6 rows in set (0.00 sec)
```

## アプリの起動

`sbt run`で起動する。

```
$ sbt run
[warn] Executing in batch mode.
[warn]   For better performance, hit [ENTER] to switch to interactive mode, or
[warn]   consider launching sbt without any commands, or explicitly passing 'shell'
[info] Loading global plugins from /Users/nagakuray/.sbt/0.13/plugins
[info] Loading project definition from /Users/nagakuray/Desktop/message-board/project
[info] Set current project to message-board (in build file:/Users/nagakuray/Desktop/message-board/)

--- (Running the application, auto-reloading is enabled) ---

[info] p.c.s.NettyServer - Listening for HTTP on /0:0:0:0:0:0:0:0:9000

(Server started, use Ctrl+D to stop and go back to the console...)
```

起動後に以下のURLに接続
http://localhost:9000/messages