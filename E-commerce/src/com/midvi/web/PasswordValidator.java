package com.midvi.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
	 private static final String PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";
	 private static final Pattern PATTERN = Pattern.compile(PASSWORD_PATTERN);
	
		/*
		 * (?=.*[0-9]) a digit must occur at least once
		 * (?=.*[a-z]) a lower case letter must occur at least once 
		 * (?=.*[A-Z]) an upper case letter must occur at least once 
		 * (?=.*[@#$%^&+=]) a special character must occur at least once
		 * (?=\\S+$) no whitespace allowed in the entire string .
		 * {8,} at least 8 characters
		 */
	 
	 public boolean isValid(final String password) {
	        return (validatePassword(password));
	    }

	    private boolean validatePassword(final String password) {
	        Matcher matcher = PATTERN.matcher(password);
	        return matcher.matches();
	    }
}
