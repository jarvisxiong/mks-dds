package com.mks.mq;


/**
 * 消费状态 
 */
public enum ConsumeStatus {

    /**
     * Success consumption
     */
    CONSUME_SUCCESS,
    /**
     * Failure consumption,later try to consume
     */
    RECONSUME_LATER;
}
