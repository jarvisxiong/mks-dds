package com.mks.validate.factory;

import java.lang.annotation.Annotation;

import com.mks.validate.ValidateException;


public class ValidateAnnotationMisuseException extends ValidateException {

	private static final long serialVersionUID = -9076469730894079649L;

	private final Class<? extends Annotation> annoClass;

	public ValidateAnnotationMisuseException(Class<? extends Annotation> annoClass, String message) {
		super(message);
		this.annoClass = annoClass;
	}

	@Override
	public String getMessage() {
		return "ValidateAnnotation:" + getAnnoClass() + " " + super.getMessage();
	}

	public Class<? extends Annotation> getAnnoClass() {
		return annoClass;
	}
}
