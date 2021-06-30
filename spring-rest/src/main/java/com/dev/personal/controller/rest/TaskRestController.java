package com.dev.personal.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.personal.model.Task;
import com.dev.personal.repository.TaskRepository;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@RestController
public class TaskRestController {

	private final TaskRepository repository;

	// CORSを有効化する
	@CrossOrigin
	// HTTPメソッドの操作説明を記述する
	@Operation(summary = "タスクを全件取得する")
	@GetMapping("/")
	List<Task> findAll() {
		return repository.findAll();
	}

	@Operation(summary = "タスクを登録する")
	@PostMapping("/")
	// @RequestBody: HTTPリクエストのボディ部分を引数に格納する
	Task save(@RequestBody Task task) {
		// taskに値が格納してあるので、そのまま保存できる
		return repository.save(task);
	}

	// CORSを有効化する
	@CrossOrigin({ "http://example,com", "https;//localhost:8080" })
	@Operation(summary = "タスクを1件取得する")
	@GetMapping("/{id}")
	Task findById(@PathVariable Long id) {
		// idに値が格納してあるので、そのまま検索に利用できる
		return repository.findById(id).get();
	}

	@Operation(summary = "タスクを更新する")
	@PutMapping("/{id}")
	Task save(@RequestBody Task newTask, @PathVariable Long id) {

		return repository.findById(id).map(task -> {
			// 該当IDが存在する場合、更新する
			task.setName(newTask.getName());
			task.setCompleted(newTask.getCompleted());
			return repository.save(task);
		}).orElseGet(() -> {
			// 該当IDが存在しない場合、登録する
			newTask.setId(id);
			return repository.save(newTask);
		});
	}

	@Operation(summary = "タスクを削除する")
	@DeleteMapping("/{id}")
	void deleteById(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
