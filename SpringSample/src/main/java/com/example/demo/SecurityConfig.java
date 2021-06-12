package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// PasswordEncoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private DataSource dataSource;

	// SELECT UserId & Password SQL
	private static final String USER_SQL = "SELECT" + " user_id," + " password," + " true" + " FROM" + " m_user"
			+ " WHERE" + " user_id = ?";

	// SELECT USER's Role
	private static final String ROLE_SQL = "SELECT" + " user_id," + " role" + " FROM" + " m_user" + " WHERE"
			+ " user_id = ?";

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 静的リソースを除外
		// 静的リソースへのアクセスには、セキュリティを適用しない
		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 直リンクの禁止⇨ログイン不要ページの設定
		// webjars,css,login,signup以外は直リンクアクセス禁止
		// method chain
		http.authorizeRequests().antMatchers("/webjars/**").permitAll().antMatchers("/css/**").permitAll()
				.antMatchers("/login").permitAll().antMatchers("/signup").permitAll().antMatchers("/rest/**")
				.permitAll().antMatchers("/admin").hasAuthority("ROLE_ADMIN").anyRequest().authenticated();

		http.formLogin().loginProcessingUrl("/login").loginPage("/login").failureUrl("/login")
				.usernameParameter("userId").passwordParameter("password").defaultSuccessUrl("/home", true);

		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutUrl("/logout")
				.logoutSuccessUrl("/login");

		// CSRFを向こうにするURLを設定
		RequestMatcher csrfMatcher = new RestMatcher("/rest/**");

		// RESTのみCSRF対策を無効に設定
		http.csrf().requireCsrfProtectionMatcher(csrfMatcher);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// ログイン処理時のユーザー情報を、DBから取得する
		// PassWordEncoderをセットし、ログイン時にパスワードをSpringに複合してもらう
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(USER_SQL)
				.authoritiesByUsernameQuery(ROLE_SQL).passwordEncoder(passwordEncoder());
	}
}
