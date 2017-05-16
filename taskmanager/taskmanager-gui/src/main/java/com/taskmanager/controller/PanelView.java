package com.taskmanager.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.taskmanager.domain.Task;
import com.taskmanager.domain.User;
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

		teste();

		List<Task> lista = new ArrayList<>(service.listBySituation(true));
		Collections.reverse(lista);
		return lista;
	}
	
	public void taskSelect(Task task) {
		this.task = task;		
	}

	public void taskClose() {
		this.task.setOpen(false);
		taskSave();
	}
	
	public void taskSave() {
		
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
		taskSave();
	}
	
	public void getTaskWithoutAttribution() {		
		this.task.setOwner(session.getUserLogged());
		taskSave();
	}

	public void create() {
		try {
			
			Task newTask = new Task();
			newTask.setDescription(description);
			service.insert(newTask);
			reset();
			
		} catch (TaskManagerException e) {
			e.printStackTrace();
		}
	}
	
	private void reset() {
		this.task = new Task();
		this.description = "";
	}

	// TODO Remover após teste
	private void teste() {

		if (service.listAll().isEmpty()) {

			try {
				
				new TaskService().insert(new Task(Session.getInstance().getUserLogged(), "Ajustar processo de deploy na nuvem"));
				new TaskService().insert(new Task(null, "XXXXXXXXXX XXXXXXXXXXX asndja skdjh kasjdh kasjh dkjh as"));
				new TaskService().insert(new Task(new User("Hugo", "hugo", "000"), "WWW aksd kasj djagsh dgajsgjas"));
				
			} catch (TaskManagerException e) {
				e.printStackTrace();
			}
		}
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
