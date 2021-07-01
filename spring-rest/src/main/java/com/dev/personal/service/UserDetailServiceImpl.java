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

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private UserRepository repository;

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
