package com.taskmanager.service.impl;

import java.util.Collection;

import com.taskmanager.domain.User;
import com.taskmanager.exception.TaskManagerException;
import com.taskmanager.repository.UserRepository;
import com.taskmanager.service.IUserService;

public class UserService implements IUserService {

	private UserRepository repository = UserRepository.getInstance();

	@Override
	public void insert(User user) throws TaskManagerException {

		if (validate(user)) {
			repository.add(user);
		}
	}

	@Override
	public User authentication(String username, String password) throws TaskManagerException {

		for (User user : repository.listAll()) {

			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		
		throw new TaskManagerException("Falha na autenticação!");
	}

	@Override
	public Collection<User> listAll() {
		return repository.listAll();
	}

	private boolean validate(User user) throws TaskManagerException {

		for (User u : repository.listAll()) {

			if (u.getUsername().equals(user.getUsername())) {
				throw new TaskManagerException("Nome de usuário já existe!");
			}
		}
		return true;
	}

}
