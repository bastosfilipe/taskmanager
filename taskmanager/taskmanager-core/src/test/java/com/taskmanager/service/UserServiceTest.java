package com.taskmanager.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.taskmanager.domain.User;
import com.taskmanager.exception.TaskManagerException;
import com.taskmanager.service.impl.UserService;

public class UserServiceTest {

	private IUserService service;

	@Before
	public void setUp() {
		service = new UserService();
		Assert.assertNotNull(service);
		service.listAll().clear();
	}

	@Test
	public void insert() throws Exception {

		User user = new User("Filipe Bastos", "filipe", "123");
		int antes = service.listAll().size();
		service.insert(user);
		int depois = service.listAll().size();

		Assert.assertEquals(antes + 1, depois);
	}

	@Test(expected = TaskManagerException.class)
	public void insertError() throws Exception {

		User u1 = new User("Hugo Bastos", "hugo", "000");
		service.insert(u1);

		User u2 = new User("Hugo Bastos", "hugo", "000");
		service.insert(u2);
	}

	@Test
	public void authentication() throws Exception {

		User u1 = new User("Filipe Bastos", "filipe", "123");
		service.insert(u1);
		User u2 = new User("Hugo Bastos", "hugo", "000");
		service.insert(u2);
		User u3 = new User("Marta", "marta", "111");
		service.insert(u3);
		Assert.assertTrue(service.listAll().size() == 3);

		User located1 = service.authentication("hugo", "000");
		Assert.assertNotNull(located1);
		Assert.assertEquals(located1.getNome(), u2.getNome());
	}

	@Test(expected = TaskManagerException.class)
	public void authenticationError() throws Exception {

		User user = new User("Filipe Bastos", "filipe", "123");
		service.insert(user);
		service.authentication("hugo", "000");
	}

}
