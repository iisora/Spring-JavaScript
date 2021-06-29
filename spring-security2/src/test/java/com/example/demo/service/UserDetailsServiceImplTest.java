package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.repository.SiteUserRepository;

// JUnit5のクラスやメソッドは、アクセス修飾子をつける必要なし
@SpringBootTest // JUnit5では、RunWith(SpringRunner.class)は必要ない。@SpringBootTestに同等のアノテーションが含まれている
@Transactional // 各テスト後に、データを初期化(ロールバック）する。
class UserDetailsServiceImplTest {

	@Autowired
	SiteUserRepository repository;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Test
	@DisplayName("ユーザ名が存在しない場合、例外をスローする")
	void whenUsernameDoesNotExist_throwException() {
		// Act & Assert
		// ラムダ式、ユーザ名が存在しない場合、例外をスローするかを検証
		assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("Nagatomo"));
	}

}
