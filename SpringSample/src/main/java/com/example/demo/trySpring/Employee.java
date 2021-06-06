package com.example.demo.trySpring;

import lombok.Data;

/* DTO, モデルクラス、　ドメインクラス(domain)　呼び方は色々　リポシトリーとサービスの間の橋渡し) */
@Data
public class Employee {

	private int employeeId;
	private String employeeName;
	private int age;
}