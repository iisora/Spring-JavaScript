package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

	private final CommentRepository repository;

	// @RequiredArgsConstructorが以下を自動生成する
//	public DataLoader(CommentRepository repository) {
//		this.repository = repository;
//	}

	@Override
	public void run(String... args) throws Exception {
		Comment comment = new Comment();
		comment.setContent("こんにちは");

		repository.save(comment);
		comment = new Comment();
		comment.setContent("test Comment");
		repository.save(comment);
	}

}
