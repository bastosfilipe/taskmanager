package com.taskmanager.service;

import java.util.Collection;

import com.taskmanager.domain.Task;
import com.taskmanager.exception.TaskManagerException;

public interface ITaskService {

	public void insert(Task task) throws TaskManagerException;

	public void update(Task task) throws TaskManagerException;

	public Task getById(Integer id) throws TaskManagerException;

	public Collection<Task> listAll();

}
