package com.taskmanager.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.taskmanager.domain.Task;
import com.taskmanager.exception.TaskManagerException;
import com.taskmanager.service.ITaskService;
import com.taskmanager.service.impl.TaskService;
import com.taskmanager.session.Session;

@ManagedBean
@ViewScoped
public class PanelView implements Serializable {

	private static final long serialVersionUID = -5182320650506184972L;

	private ITaskService service = new TaskService();

	private Session session = Session.getInstance();

	private Task task;

	private String description;

	public PanelView() {
		reset();
	}

	public Collection<Task> listAll() {
		List<Task> lista = new ArrayList<>(service.listBySituation(true));
		Collections.reverse(lista);
		return lista;
	}

	public void taskSelect(Task task) {
		this.task = task;
	}

	public void taskClose() {

		if (task.getSolution().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("form-task-detail:btnClose", new FacesMessage("Informe a solução para o problema"));
		} else {
			this.task.setOpen(false);
			save();
		}
	}

	public void taskSave() {

		if (task.getSolution().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("form-task-detail:btnSave", new FacesMessage("Informe a solução para o problema"));
		} else {
			this.task.setOpen(false);
			taskSave();
		}
	}

	private void save() {

		try {

			service.update(this.task);
			reset();
			listAll();

		} catch (TaskManagerException e) {
			e.printStackTrace();
		}
	}

	public boolean assignedToLogged() {
		return session.getUserLogged().equals(this.task.getOwner());
	}

	public boolean withoutAttribution() {
		return this.task.getOwner() == null;
	}

	public boolean assignedToAnother() {
		return !withoutAttribution() && !assignedToLogged();
	}

	public void leaveTask() {
		this.task.setOwner(null);
		save();
	}

	public void getTaskWithoutAttribution() {
		this.task.setOwner(session.getUserLogged());
		save();
	}

	public void create() {
		try {

			Task newTask = new Task();
			newTask.setDescription(description);
			service.insert(newTask);
			reset();

		} catch (TaskManagerException e) {
			FacesContext.getCurrentInstance().addMessage("modal-new:btnCreate", new FacesMessage(e.getMessage()));
		}
	}

	public void closeWindowTaskDetail() {
		reset();
	}

	private void reset() {
		this.task = new Task();
		this.description = "";
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
