package com.example.demo.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// バリデーションを使える場所を指定。メソッドとフィールド。
@Target({ ElementType.METHOD, ElementType.FIELD })
// 保持期間(ポリシー）を指定。実行時にVMに保持＝いつでも使える。
@Retention(RetentionPolicy.RUNTIME)
// 制約（チェック）対象を指定する。
@Constraint(validatedBy = UniqueLoginValidator.class)
// インターフェースにつける。クラス名をアノテーションとして使うことができる。
public @interface UniqueLogin {
	String message() default "{unique.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
