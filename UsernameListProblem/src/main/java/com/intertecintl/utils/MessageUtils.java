package com.intertecintl.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageUtils {
	
	public static void createMessage(FacesMessage.Severity severity, String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message ,null));
	}
}



