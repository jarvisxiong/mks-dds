package com.mks.utils;

public interface ObjectTransformer extends Transformer<Object, Object> {

	@Override
	public Object transform(Object from);

}
