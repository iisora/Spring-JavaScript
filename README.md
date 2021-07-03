# Spring-JavaScript
Java(SpringBoot)でいろいろ開発する用。
FWとJava自体の理解を深めることとシステムを構築することに慣れるのが目的。（ついでにjavascriptとSQLも)

【SpringBoot+Vue.js+Dockerでサンプルを作成】
構成：
  フロントエンド：Vue.js
  バックエンド：SpringBoot
  
 SpringBoot×Vue.jsでSPA & docker
 https://qiita.com/fujita0830/items/71ae0278cf1236dbaf5a


【SpringBootとVue.jsでECサイト】
構成：
  DB: MySQL
  サーバー：SpringBoot(RESTサーバ)
    ・MybatisでDBをSQLをJavaオブジェクトにMapping
    ・SpringSecurityで認証周りを整備(API呼び出し時に認証トークンが無い呼び出しは弾く。ログイン画面とユーザ登録画面等認証トークンが無くてもアクセスできる)
    ・静的ファイルへのアクセスもSpring Securityの認証対象外にする
    
  クライアント:Vue.js
    ・SpringREST APIへAjaxリクエストもしくはaxios,APIレスポンスを画面表示
    ・画面遷移、画面表示のためのコントロールはサーバ側で持たない
    ・APIサーバという立ち位置なので、クライアントとは疎結合な関係(サーバーサイドをDjangoなどに置き換えても、影響ない作りにしたい)
    ・画面遷移(router)、画面操作に応じたREST API呼び出し(actions)/データの管理・更新(state,mutations)
    
    
 参考サイト
 Vue.jsとSpringBootでSPA作成
 https://www.slideshare.net/ssuser4d53ba/spring-boot-vuejsspa


    
    
   
