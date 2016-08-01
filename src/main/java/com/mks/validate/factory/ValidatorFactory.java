package com.mks.validate.factory;

import com.mks.validate.Validator;

public interface ValidatorFactory {

	// TODO:
	Validator create(Class<?> beanClass);
}
