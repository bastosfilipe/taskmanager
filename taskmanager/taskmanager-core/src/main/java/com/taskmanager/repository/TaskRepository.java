package com.taskmanager.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.taskmanager.domain.Task;

public class TaskRepository {

	private static final TaskRepository INSTANCE = new TaskRepository();

	private List<Task> list = new ArrayList<>();

	private TaskRepository() {

	}

	public static TaskRepository getInstance() {
		return INSTANCE;
	}

	public void add(Task task) {
		list.add(task);
	}

	public void remove(Task task) {
		list.remove(task);
	}

	public Task get(Task task) {
		int index = list.indexOf(task);
		return list.get(index);
	}

	public Collection<Task> listAll() {
		return list;
	}

}
