package com.intertecintl.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intertecintl.utils.StringUtils;

/**
 * The Class UsernameValidador.
 */
@FacesValidator("com.intertecintl.validator.UsernameValidador")
public class UsernameValidador implements Validator {

	/** The Constant LOG. */
	static final Logger LOG = LoggerFactory.getLogger(UsernameValidador.class);
	
	/** The letter number msg. */
	private static String LETTER_NUMBER_MSG = "Username validation failed. Please use only letters (a-z) and numbers.";
	
	/** The null blank msg. */
	private static String NULL_BLANK_MSG = "Username validation failed. Please Username can't be null or blank.";
	
	/** The size msg. */
	private static String SIZE_MSG = "Username validation failed. Please Username must have size between 6 and 45 use letters/numbers.";
	
	/** The Constant USERNAME_PATTERN. */
	private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]*$";
	
	/** The pattern. */
	private Pattern pattern;
	
	/** The matcher. */
	private Matcher matcher;
	
	/**
	 * Instantiates a new username validador.
	 */
	public UsernameValidador(){
		  pattern = Pattern.compile(USERNAME_PATTERN);
	}
	
	/* (non-Javadoc)
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		matcher = pattern.matcher(String.valueOf(value));
		if(!matcher.matches()){
			
			LOG.error(LETTER_NUMBER_MSG);
			FacesMessage msg = new FacesMessage(LETTER_NUMBER_MSG);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		
		if (StringUtils.isNullOrBlank(String.valueOf(value))){
			LOG.error(NULL_BLANK_MSG);
			FacesMessage msg = new FacesMessage(NULL_BLANK_MSG);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		
		if (!StringUtils.isSizeCorrect(String.valueOf(value))){
			LOG.error(SIZE_MSG);
			FacesMessage msg = new FacesMessage(SIZE_MSG);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}

