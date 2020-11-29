package com.eamon.zhuiqiu.util.state;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
//最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {
	
	String value() default ADMIN_PRIVATE;
	
	/**
	 * 超级管理员私有字段
	 * {userId}
	 * 私有资源必须字段
	 * viewerId 必须等于 userId
	 * viewToken
	 */
	String SUPER_ADMIN_PRIVATE= "super_admin_private";
	String SUPER_ADMIN="super";
	
	/**
	 * 管理员私有字段
	 * {userId}
	 * 私有资源必须字段
	 * viewerId 必须等于 userId
	 * viewToken
	 */
	String ADMIN_PRIVATE = "admin_private";
	String ADMIN="admin";
	
	/**
	 * 教师私有字段
	 * {userId}
	 * 私有资源必须字段
	 * viewerId 必须等于 userId
	 * viewToken
	 */
	String TEACHER_PRIVATE = "teacher_private";
	String TEACHER="teacher";
	
	/**
	 * 家长私有字段
	 * {userId}
	 * 私有资源必须字段
	 * viewerId 必须等于 userId
	 * viewToken
	 */
	String PARENT_PRIVATE="parent_private";
	String PARENT="parent";
	/**
	 * 学生私有字段
	 * {userId}
	 * 私有资源必须字段
	 * viewerId 必须等于 userId
	 * viewToken
	 */
	String STUDENT_PRIVATE="student_private";
	String STUDENT="student";
	
	
	/**
	 * 用户私有资源
	 * {userId}
	 * 私有资源必须字段
	 * viewerId 必须等于 userId
	 * viewToken
	 */
	String USER_PRIVATE = "user_private";
	String USER="user";
//	/**
//	 * 用户保护资源
//	 * {userId}
//	 * 私有资源必须字段
//	 * viewerId 必须与userId为friend关系
//	 * viewToken
//	 */
//	String USER_PROTECTED = "user_protected";
//	
//	/**
//	 * 用户公开资源
//	 * {userId}
//	 */
//	String USER_PUBLIC = "user_public";
	
}
