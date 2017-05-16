package com.taskmanager.session;

import com.taskmanager.domain.User;

public class Session {

	private static final Session INSTANCE = new Session();

	private static User logged;

	private Session() {

	}

	public static Session getInstance() {
		return INSTANCE;
	}

	public void addUserLogged(User user) {
		logged = user;
	}

	public User getUserLogged() {
		return logged;
	}

}
