package com.mks.validate;

public interface Validator {

	void validate(Object input) throws ValidateException, ConstraintViolationException;

	String getConstraintDesc();
}
