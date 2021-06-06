package com.example.demo.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/* This is happy set */
@Aspect
@Component // Define class as Bean to DI container
public class LogAspect {

	// AOPの実装
//	@Before("execution( * com.example.demo.login.controller.LoginController.getLogin(..))")
	// コントーラークラスの全てのメソッドを対象
	@Before("execution( * *..*.*Controller.*(..))")
	public void startLog(JoinPoint jp) {
		System.out.println("****Method Start****👏:" + jp.getSignature());
	}

//	@After("execution( * com.example.demo.login.controller.LoginController.getLogin(..))")
	@After("execution( * *..*.*.*Controller.*(..))")
	public void endLog(JoinPoint jp) {
		System.out.println("****Method End****👋:" + jp.getSignature());
	}

//	@Around("execution( * *..*.*.*Controller.*(..))")
//	@Around("bean(*Controller)") // Specify with Bean Name
//	@Around("@annotation(org.springframework.web.bind.annotation.GetMapping)") // Specify with @Annotation
	@Around("@within(org.springframework.stereotype.Controller)") // @withingは、指定したアノテーションがついてるクラスの全てのメソッドが対象
	public Object startLog(ProceedingJoinPoint jp) throws Throwable {

		System.out.println("****Around the World Start***🌎:" + jp.getSignature());

		try {
			// メソッド実行
			Object result = jp.proceed();
			System.out.println("****Around the World End***🌎🌳🍃:" + jp.getSignature());

			return result;

		} catch (Exception e) {
			System.out.println("****World End***🌎💥:" + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}

	@Around("execution( * *..*.*UserDao*.*(..))")
	public Object daoLog(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("****Dao Method Start****👶:" + jp.getSignature());

		try {
			Object result = jp.proceed();

			System.out.println("****Dao Method End****👶🍼:" + jp.getSignature());

			return result;
		} catch (Exception e) {
			System.out.println("****Dao Method Abnormal Termination*****💪👨:" + jp.getSignature());

			e.printStackTrace();
			throw e;
		}
	}
}