package com.dev.personal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dev.personal.util.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// @Autowiredしないこの書き方の場合、コンストラクタが必要だが、@RequiredArgsConstructorで自動的に作成
	private final UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// セキュリティ設定を、無視（ignoring)するパスを指定します
		// 通常、cssやjs,imgなどの静的リソースを指定します
		web.ignoring().antMatchers("js/**", "/css/**", "/img/**", "/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 「/login」と「/register」をアクセス可能にします。(registerをアクセス可能にしないと登録できない。)
				.antMatchers("/login", "/register").permitAll()
				// 「/admin」は、ADMINユーザだけアクセス可能にする
				.antMatchers("/admin/**").hasRole(Role.ADMIN.name()).anyRequest().authenticated().and().formLogin()
				// ログイン時のURLを指定
				.loginPage("/login")
				// 認証後にリダイレクトする場所を指定
				.defaultSuccessUrl("/list").and()
				// ログアウトの設定
				.logout()
				// ログアウト時のURLを指定
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and()
				// Remember-Meの認証を許可
				// これを設定すると、ブラウザを閉じて、
				// 再度開いた場合でも「ログインしたまま」にできる
				.rememberMe();
	}

	/**
	 * 認証時に利用するデータソースを定義する設定メソッド
	 * AuthenticationManagerBuilderは認証系の機能を持つ。
	 * userDetailsServiceはフォームに入力されたユーザが使用可能か判断する。
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// UserDetailsServiceを使用して、DBからユーザを参照できるようにする
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

		// メモリ内認証(インメモリ：実装中はこっちの設定で進める)
		auth.inMemoryAuthentication()
				// ユーザ名「admin」と「user」を用意
				// パスワードは両方とも「password」
				.withUser("admin").password(passwordEncoder().encode("password")).authorities("ROLE_ADMIN").and()
				.withUser("user").password(passwordEncoder().encode("password")).authorities("ROLE_USER");
	}

}
