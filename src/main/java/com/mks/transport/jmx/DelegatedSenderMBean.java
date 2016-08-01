package com.mks.transport.jmx;

import com.mks.jmx.model.GenericModelMBean;
import com.mks.transport.DelegatedSender;

 
public class DelegatedSenderMBean extends GenericModelMBean<DelegatedSender> {

    public DelegatedSenderMBean(DelegatedSender source) {
        super(source);
    }

    @Override
    protected boolean isAttribute(String attrName, Class<?> attrType) {
        if (attrName.matches("(pendingSendMessages|processors)"))
            return true;
        return false;
    }

    @Override
    protected boolean isOperation(String methodName, Class<?>[] paramTypes) {
        return false;
    }
}
