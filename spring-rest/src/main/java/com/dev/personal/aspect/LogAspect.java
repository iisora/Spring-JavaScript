package com.dev.personal.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LogAspect {

	@Before("execution(* com.dev.personal.controller.ColleagueController.*(..))")
	public void startLog(JoinPoint jp) {
		log.info("{}: を開始します。", jp.getSignature());
		System.out.println("****Method Start****👏:" + jp.getSignature());
	}

	@After("execution(* com.dev.personal.controller.ColleagueController.*(..))")
	public void endLog(JoinPoint jp) {
		log.info("{}: を終了します。", jp.getSignature());
		System.out.println("****Method End****👋:" + jp.getSignature());
	}

	@Around("execution(* com.dev.personal..*(..))")
	public Object startAndEndLog(ProceedingJoinPoint pjp) throws Throwable {
		log.info("{}: Around前処理", pjp.getSignature());
		Object result = pjp.proceed();
		log.info("{}: Around後処理", pjp.getSignature());

		return result;
	}

	@AfterReturning(pointcut = "within(com.dev.personal.controller.*Controller)", returning = "result")
	public void afterReturning(JoinPoint jp, Object result) {
		log.info("{}: return = {}", jp.getSignature(), result);
	}

	// beanには、先頭小文字で登録されている
	@AfterThrowing(pointcut = "bean(colleagueController)", throwing = "e")
	public void afterThrowing(JoinPoint jp, Throwable e) {
		log.error("{}: 処理中に例外が発生しました。: {}", jp.getSignature(), e);
	}

}
