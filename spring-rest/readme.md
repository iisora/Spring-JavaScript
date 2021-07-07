Spring Securityを使っている場合、Thymeleafでformをレンダリングするとcsrfトークンを含むinputタグが埋め込まれますが、jsでフォームをレンダリングする場合は埋め込まれないため自分で埋め込む必要がある。

###jarファイル生成
$ cd /Users/〇〇/git/Spring-JavaScript/spring-rest
$ mvn package spring-boot:repackage


// mvn install
mvn clean install
// run the app
###jarファイル実行
$ cd target
$ ls
$ java -jar spring-boot2-1.0.0.jar

$ cd /Users/〇〇/git/Spring-JavaScript/spring-rest/web
$ npm run build

port kill

$ lsof -i :8080
$ kill -9 [PID]




// java API (Terminal 1)
$cd /Users/*****/git/Spring-JavaScript/spring-rest
$mvn clean install
$java -jar target/<jar file name>
// Vue app (Terminal 2)
$cd /Users/*****/git/Spring-JavaScript/spring-rest/web (change it to app directory)
$npm run serve
