package com.taskmanager.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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

	public Collection<Task> listAll() {

		teste();

		List<Task> lista = new ArrayList<>(service.listAll());
		Collections.reverse(lista);
		return lista;
	}

	// TODO Remover após teste
	private void teste() {

		if (service.listAll().isEmpty()) {

			for (int i = 0; i < 5; i++) {
				Task task = new Task(Session.getInstance().getUserLogged(), "Ajustar processo de deploy na nuvem");

				try {
					new TaskService().insert(task);
				} catch (TaskManagerException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
