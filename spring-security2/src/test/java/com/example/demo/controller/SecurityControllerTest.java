package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.SiteUser;
import com.example.demo.util.Role;

@SpringBootTest
// MockMvcの自動構成を有効にする
@AutoConfigureMockMvc
@Transactional
class SecurityControllerTest {

	@Autowired
	// MockMvc: Spring MVCのテストができるクラス
	MockMvc mockMvc;

	@Test
	@DisplayName("登録エラーがある時、エラーを表示することを期待する")
	void whenThereIsRegistrationError_expectToSeeErrors() throws Exception {

		mockMvc
				// リクエストを実行
				.perform(
						// HTTPのPOSTリクエストを使用する
						post("/register")
								// 入力の属性を設定(Controller側の@ModelAttributeのuserに対して値を設定する）
								.flashAttr("user", new SiteUser())
								// CSRFトークンを自動挿入(基本的にPOSTリクエストには必須）
								.with(csrf()))
				// エラーがあることを検証する
				.andExpect(model().hasErrors())
				// 指定したHTMLを表示しているか検証する
				.andExpect(view().name("register"));
	}

	@Test
	@DisplayName("管理者ユーザとして登録する時、成功することを期待する")
	void whenRegisteringAsAdminUser_expectToSucceed() throws Exception {
		SiteUser user = new SiteUser();
		user.setUsername("管理者ユーザ");
		user.setPassword("password");
		user.setEmail("admin@example.com");
		user.setGender(0);
		user.setAdmin(true);
		user.setRole(Role.ADMIN.name());

		mockMvc.perform(post("/register").flashAttr("user", user).with(csrf()))
				// エラーがないことを検証する
				.andExpect(model().hasErrors())
				// 指定したURLに、リダイレクトすることを検証する
				.andExpect(redirectedUrl("/login?register"))
				// ステータスコードが、Found(302)であることを検証する
				.andExpect(status().isFound());
	}

	@Test
	@DisplayName("管理者ユーザでログイン時、ユーザ一覧を表示することを期待する")
	// モックユーザでログインする
	@WithMockUser(username = "admin", roles = "ADMIN")
	void whenLoggedInAsAdminUser_expectToSeeListOfUsers() throws Exception {
		mockMvc.perform(get("/admin/list"))
				// ステータスコードが、OK(200)であることを検証する
				.andExpect(status().isOk())
				// HTMLの表示内容に、指定した文字列を含んでいるか検証する
				.andExpect(content().string(containsString("ユーザ一覧"))).andExpect(view().name("list"));
	}

}
