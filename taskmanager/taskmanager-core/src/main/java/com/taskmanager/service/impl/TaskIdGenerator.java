package com.taskmanager.service.impl;

public class TaskIdGenerator {

	private static final TaskIdGenerator INSTANCE = new TaskIdGenerator();

	private static int next = 0;

	private TaskIdGenerator() {

	}

	public static TaskIdGenerator getInstance() {
		return INSTANCE;
	}

	protected int getNextId() {
		next++;
		return next;
	}

}
