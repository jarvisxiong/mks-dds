package com.mks.utils.jms;

public interface MessageBeanFactory {

	public MessageBean createMessageBean(String sql, Object[][] argumentsList);

}
