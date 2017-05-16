package com.taskmanager.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.taskmanager.exception.TaskManagerException;
import com.taskmanager.service.IUserService;
import com.taskmanager.service.impl.UserService;


@ManagedBean
@ViewScoped
public class UserView implements Serializable {

	private static final long serialVersionUID = -1849895145290595389L;

	private String username;

	private String password;

	private IUserService service = new UserService();
		
	public void login() {
		
		System.out.println(username);
		System.out.println(password);
		
		
		try {
			
			service.authentication(username, password);
			
		} catch (TaskManagerException e) {
			e.printStackTrace();
		}
	}	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
