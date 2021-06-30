package com.dev.personal.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.dev.personal.validator.UniqueLogin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2, max = 20)
	@UniqueLogin // 自作バリデーション
	private String username;

	@Size(min = 4, max = 255)
	private String password;

	@NotBlank
	@Email
	private String email;

	private int gender;

	private boolean admin;
	private String role;

}
