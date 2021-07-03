package com.dev.personal.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.personal.model.MyUser;
import com.dev.personal.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Spring Securityのユーザ検索用のサービスの実装クラス
 * DataSourceの引数として指定することで認証にDBを利用できるようになる
 */
@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private UserRepository repository;

	
    /**
     * UserDetailsServiceインタフェースの実装メソッド
     * フォームから取得したユーザ名でDBを検索し、合致するものが存在したとき、
     * パスワード、権限情報と共にUserDetailsオブジェクトを生成
     * コンフィグクラスで上入力値とDBから取得したパスワードと比較し、ログイン判定を行う
     */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		MyUser user = repository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username + "が見つかりません。");
		}
		return createUserDetails(user);
	}

	public User createUserDetails(MyUser user) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
}
