package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.SiteUserRepository;

/**
 * 自作バリデーション: 同名のユーザを登録できないようにする。 ConstraintValidatorインターフェースを実装する
 **/
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

	private final SiteUserRepository userRepository;

	public UniqueLoginValidator() {
		this.userRepository = null;
	}

	// コンストラクタが２件以上ある場合、@Autowiredアノテーションは省略できない
	@Autowired
	public UniqueLoginValidator(SiteUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext contet) {
		// チェックエラーの場合 return falseを返すようにする
		return userRepository == null || userRepository.findByUsername(value) == null;
	}
}
