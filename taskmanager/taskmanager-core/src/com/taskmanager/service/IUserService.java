package com.taskmanager.service;

import java.util.Collection;

import com.taskmanager.domain.User;
import com.taskmanager.exception.TaskManagerException;

public interface IUserService {

	public void insert(User user) throws TaskManagerException;

	public User authentication(String username, String password) throws TaskManagerException;
	
	public Collection<User> listAll();
	
}