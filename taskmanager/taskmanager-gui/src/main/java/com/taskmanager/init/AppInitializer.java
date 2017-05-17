package com.taskmanager.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.taskmanager.domain.Task;
import com.taskmanager.domain.User;
import com.taskmanager.exception.TaskManagerException;
import com.taskmanager.service.ITaskService;
import com.taskmanager.service.IUserService;
import com.taskmanager.service.impl.TaskService;
import com.taskmanager.service.impl.UserService;

@WebListener
public class AppInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		try {

			IUserService userService = new UserService();

			User u1 = new User("Administrador", "admin", "admin");
			userService.insert(u1);
			User u2 = new User("Hugo", "hugo", "000");
			userService.insert(u2);

			ITaskService taskService = new TaskService();
			taskService.insert(new Task(u1, "Ajustar processo de deploy na nuvem"));
			taskService.insert(new Task(null, "Revisar infraestrutura da sala 4"));
			taskService.insert(new Task(u2, "Cria testes de aceitação para App X"));

		} catch (TaskManagerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
