package com.mks.utils;

 
public interface Transformer<FROM, TO> {

	public TO transform(FROM from);

}
