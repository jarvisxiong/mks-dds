package com.mks.dpl.factory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mks.dpl.event.listener.LoggingServiceListener;
import com.mks.dpl.event.listener.ServiceListener;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

	String code();

	String name();

	String description() default "";

	boolean isRunning() default true;

	Class<? extends ServiceListener>[] listeners() default { LoggingServiceListener.class };

	/**
	 * Reserved.
	 * 
	 * @return
	 */
	ThreadModel threadModel() default @ThreadModel();
}
