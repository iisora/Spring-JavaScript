package com.dev.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.personal.model.MyUser;

/*
 * Spring Frameworkのデータ検索を行うための仕組み。
 * DIに登録しておくことでデータ検索が可能になる。引数には＜エンティティクラス,　IDタイプとなる＞
 */
public interface UserRepository extends JpaRepository<MyUser, Long> {
	MyUser findByUsername(String username);

	boolean existsByUsername(String username);
}
