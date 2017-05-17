package com.taskmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.taskmanager.domain.Task;
import com.taskmanager.domain.User;
import com.taskmanager.exception.TaskManagerException;
import com.taskmanager.service.impl.TaskService;

public class TaskServiceTest {

	private ITaskService service;

	@Before
	public void setUp() {
		service = new TaskService();
		Assert.assertNotNull(service);
		service.listAll().clear();
	}

	@Test
	public void insert() throws Exception {

		User user = new User("Filipe", "filipe", "123");

		Task task = new Task(user, "Ajustar processo de deploy na nuvem");
		int antes = service.listAll().size();
		service.insert(task);
		int depois = service.listAll().size();

		Assert.assertEquals(antes + 1, depois);
	}

	@Test
	public void update() throws Exception {

		User user = new User("Filipe", "filipe", "123");
		Task task = new Task(user, "Criar serviços de acesso a base de dados");
		service.insert(task);
		Assert.assertTrue(service.listAll().size() == 1);

		Task t1 = service.getById(task.getId());
		t1.setSolution("Implementação dos métodos necessário");
		service.update(t1);
		Assert.assertTrue(service.listAll().size() == 1);

		Assert.assertEquals(task, t1);
	}

	@Test(expected = TaskManagerException.class)
	public void getByIdError() throws Exception {
		service.getById(125);
	}

	@Test
	public void listAll() throws Exception {

		User user = new User("Filipe", "filipe", "123");

		service.insert(new Task(user, "desc1"));
		service.insert(new Task(user, "desc2"));
		service.insert(new Task(user, "desc3"));
		service.insert(new Task(user, "desc4"));

		List<Task> tasks = new ArrayList<>(service.listAll());
		Assert.assertEquals(4, tasks.size());
	}

}
