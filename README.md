# Spring-JavaScript
Java(SpringBoot)でいろいろ開発する用。
FWとJava自体の理解を深めることとシステムを構築することに慣れるのが目的。（ついでにjavascriptとSQLも)


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
 spring boot + vue + nginx realizes separation of front and back ends
 https://www.programmersought.com/article/30776117090/
    


    
    
   
