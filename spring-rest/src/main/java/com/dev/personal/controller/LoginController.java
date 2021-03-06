package com.dev.personal.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dev.personal.model.MyUser;
import com.dev.personal.repository.UserRepository;
import com.dev.personal.util.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LoginController {

	private final UserRepository repository;
	private final BCryptPasswordEncoder passwordEncoder;

	@GetMapping({ "/", "/login" })
	public String getLogin(Model model) {
		return "login";

	}

	@PostMapping("/login")
	public String postLogin(Model model) {
		return "redirect:/list";
	}

	@GetMapping("/register")
	public String register(@ModelAttribute("user") MyUser user) {
		return "register";
	}

	@PostMapping("/register")
	public String process(@Validated @ModelAttribute("user") MyUser user, BindingResult result) {

		if (result.hasErrors()) {
			return "register";
		}

		// rawDataのパスワードは渡すことができないので、暗号化
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		if (user.isAdmin()) {
			user.setRole(Role.ADMIN.name());
		} else {
			user.setRole(Role.USER.name());
		}

		repository.save(user);

		return "redirect:/login?register";
	}
}
