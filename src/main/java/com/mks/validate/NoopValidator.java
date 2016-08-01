package com.mks.validate;

public class NoopValidator implements Validator {

	@Override
	public String getConstraintDesc() {
		return "Noop";
	}

	@Override
	public void validate(Object input) throws ValidateException, ConstraintViolationException {
		// Empty Handler
	}
}
