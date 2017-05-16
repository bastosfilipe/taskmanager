package com.taskmanager.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.taskmanager.domain.Task;
import com.taskmanager.exception.TaskManagerException;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.service.ITaskService;

public class TaskService implements ITaskService {

	private TaskRepository repository = TaskRepository.getInstance();

	@Override
	public void insert(Task task) throws TaskManagerException {

		task.setId(TaskIdGenerator.getInstance().getNextId());
		task.setOpen(true);
		task.setCreated(new Date());
		repository.add(task);
	}

	@Override
	public void update(Task task) throws TaskManagerException {

		Task taskBase = getById(task.getId());
		List<Task> tasks = new ArrayList<>(repository.listAll());
		tasks.set(tasks.indexOf(taskBase), task);
	}

	@Override
	public Task getById(Integer id) throws TaskManagerException {

		try {
			return repository.get(new Task(id));
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new TaskManagerException("Tarefa não localizada.");
		}
	}

	@Override
	public Collection<Task> listAll() {
		return repository.listAll();
	}

	@Override
	public Collection<Task> listBySituation(boolean open) {
		return repository.listBySituation(open);
	}

}
