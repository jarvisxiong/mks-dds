package com.mks.validate.factory;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.mks.validate.Validator;

public class PreGeneratedValidatorFactory implements ValidatorFactory {

	private final Map<Class<?>, Validator> validators;

	public PreGeneratedValidatorFactory(ValidatorFactory validatorFactory,
			List<Class<?>> modelClasses) {
		if (null == validatorFactory)
			throw new NullPointerException("validatorFactory");
		if (null == modelClasses || modelClasses.isEmpty())
			throw new IllegalArgumentException("Validate modelClasses is blank.");

		validators = Maps.newHashMap();
		for (Class<?> modelClass : modelClasses) {
			Validator validator = validatorFactory.create(modelClass);
			validators.put(modelClass, validator);
		}
	}

	@Override
	public Validator create(Class<?> beanClass) {
		if (null == beanClass)
			throw new NullPointerException("beanClass");
		return validators.get(beanClass);
	}
}
