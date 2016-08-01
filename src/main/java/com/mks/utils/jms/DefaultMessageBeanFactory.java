package com.mks.utils.jms;

public class DefaultMessageBeanFactory implements MessageBeanFactory {

	@Override
	public MessageBean createMessageBean(String sql, Object[][] argumentsList) {
		return new DefaultMessageBean(sql, argumentsList);
	}

}
