package com.dev.personal.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dev.personal.model.Colleague;
import com.dev.personal.service.ColleagueService;

import lombok.RequiredArgsConstructor;

/*
 * 【実装テスト用】
 * CommandLineRunnerを実装したクラスはコマンドラインで実装する。
 * 引数を扱わないなら、どちらでも構わないが、引数を扱う場合はApplicationRunnerを推奨する
 * 起動時に一度だけ実行したい処理があるときに使う
 */
@RequiredArgsConstructor
@Component // コンポーネントスキャンの対象にする
public class DataLoader implements CommandLineRunner {

	private final ColleagueService service;

	/*
	 * Springアプリケーション起動時にrunメソッドが実行される
	 */
	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 16; i++) {
			Colleague colleague = new Colleague();
			colleague.setUserName("テストユーザ" + String.valueOf(i + 1));
			colleague.setEmail("test" + String.valueOf(i + 1) + "@example.com");
			service.save(colleague);
		}
	}

}
