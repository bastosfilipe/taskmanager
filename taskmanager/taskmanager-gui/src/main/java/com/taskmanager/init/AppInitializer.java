package com.taskmanager.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.taskmanager.domain.User;
import com.taskmanager.exception.TaskManagerException;
import com.taskmanager.service.IUserService;
import com.taskmanager.service.impl.UserService;

@WebListener
public class AppInitializer implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		IUserService userService = new UserService();
		User user = new User("Administrador", "admin", "admin");
		try {
			
			userService.insert(user);
			
		} catch (TaskManagerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
	
}
