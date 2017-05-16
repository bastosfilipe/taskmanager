package com.taskmanager.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.taskmanager.domain.User;

public class UserRepository {

	private static final UserRepository INSTANCE = new UserRepository();

	private List<User> list = new ArrayList<>();

	private UserRepository() {

	}

	public static UserRepository getInstance() {
		return INSTANCE;
	}

	public void add(User user) {
		list.add(user);
	}

	public void remove(User user) {
		list.remove(user);
	}

	public User get(User user) {
		int index = list.indexOf(user);
		return list.get(index);
	}

	public Collection<User> listAll() {
		return list;
	}

}
