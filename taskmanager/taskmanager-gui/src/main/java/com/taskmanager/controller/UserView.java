package com.taskmanager.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.taskmanager.domain.User;
import com.taskmanager.exception.TaskManagerException;
import com.taskmanager.service.IUserService;
import com.taskmanager.service.impl.UserService;
import com.taskmanager.session.Session;
import com.taskmanager.util.WebUtils;

@ManagedBean
@ViewScoped
public class UserView implements Serializable {

	private static final long serialVersionUID = -1849895145290595389L;

	private String username;

	private String password;

	private IUserService service = new UserService();

	private Session session = Session.getInstance();

	public void login() {

		try {

			User logged = service.authentication(username, password);
			session.addUserLogged(logged);
			WebUtils.redirect("pages/main.jsf");

		} catch (TaskManagerException e) {
			FacesContext.getCurrentInstance().addMessage("login-form:btnLogar", new FacesMessage(e.getMessage()));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage("login-form:btnLogar", new FacesMessage("Erro ao redirecionar a página."));
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

	public Session getSession() {
		return session;
	}
}
