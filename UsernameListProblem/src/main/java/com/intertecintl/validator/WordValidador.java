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

@FacesValidator("com.intertecintl.validator.WordValidador")
public class WordValidador implements Validator {
	
static final Logger LOG = LoggerFactory.getLogger(WordValidador.class);
	
	private static String LETTER_NUMBER_MSG = "Word validation failed. Please use only letters (a-z) and numbers.";
	private static String NULL_BLANK_MSG = "Word validation failed. Please Username can't be null or blank.";
	private static String SIZE_MSG = "Word validation failed. Please Username must have size between 6 and 45 use letters/numbers.";

	private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]*$";
	
	private Pattern pattern;
	private Matcher matcher;
	
	public WordValidador(){
		  pattern = Pattern.compile(USERNAME_PATTERN);
	}
	
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

