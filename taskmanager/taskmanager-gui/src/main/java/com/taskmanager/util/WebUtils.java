package com.taskmanager.util;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class WebUtils {

	private WebUtils() {

	}

	private static FacesContext facesContext() {
		return FacesContext.getCurrentInstance();
	}

	private static ExternalContext externalContext() {
		return facesContext().getExternalContext();
	}

	public static void redirect(String url) throws IOException {
		externalContext().redirect(url);
	}

}
