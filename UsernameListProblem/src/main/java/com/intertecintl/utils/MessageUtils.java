package com.intertecintl.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * The Class MessageUtils.
 */
public class MessageUtils {
	
	/**
	 * Creates the message.
	 *
	 * @param severity the severity
	 * @param message the message
	 */
	public static void createMessage(FacesMessage.Severity severity, String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message ,null));
	}
}



