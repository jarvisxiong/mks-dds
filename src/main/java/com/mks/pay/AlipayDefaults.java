package com.mks.pay;


/**
 * 支付宝常量定义 
 */
public interface AlipayDefaults {
    
    String Y = "Y";
    String N = "N";
    
    interface Charset {
        
        String UTF8 = "utf-8";
    }
    
    interface Service {
        
        String CREATE_DIRECT_PAY_BY_USER = "create_direct_pay_by_user";
    }
    
    interface PaymentType {
        
        /** 商品购买 */
        String BUY = "1";
        
        /** 捐赠 */
        String DONATION = "4";
        
        /** 卡券 */
        String COUPON = "47";
    }
    
    /**
     * 支付渠道
     */
    interface Paymethod {
        /** 支付宝账户余额 */
        String DIRECTPAY = "voucher";
        /** 卡通 */
        String CARTOON = "cartoon";
        /** 网银 */
        String BANKPAY = "bankPay";
        /** 现金 */
        String CASH = "cash";
        /** 信用卡快捷 */
        String CREDITCARDEXPRESS = "creditCardExpress";
        /** 借记卡快捷 */
        String DEBITCARDEXPRESS = "debitCardExpress";
        /** 红包 */
        String COUPON = "coupon";
        /** 积分 */
        String POINT = "point";
        /** 购物券 */
        String VOUCHER = "voucher";
    }
    
    interface RefundStatus {
        /** 退款成功：
         * <p>全额退款情况：trade_status= TRADE_CLOSED，而refund_status=REFUND_SUCCESS</p>
         * <p>非全额退款情况：trade_status= TRADE_SUCCESS，而 refund_status=REFUND_SUCCESS</p>
         */
        String REFUND_SUCCESS = "REFUND_SUCCESS";
        
        String REFUND_CLOSED = "REFUND_CLOSED";	/** 退款关闭 */
    }
    
    
    /**
     * 交易状态 
     */
    interface TradeStatus {
        
        String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";/** 交易创建，等待买家付款。 */
       
        String TRADE_CLOSED = "TRADE_CLOSED";	 /** 在指定时间段内未支付时关闭的交易；在交易完成全额退款成功时关闭的交易。 */
        
        String TRADE_SUCCESS = "TRADE_SUCCESS";	 /** 交易成功，且可对该交易做操作，如：多级分润、退款等。 */
        
        String TRADE_PENDING = "TRADE_PENDING";	 /** 等待卖家收款（买家付款后，如果卖家账号被冻结）。 */
        
        String TRADE_FINISHED = "TRADE_FINISHED";	/** 交易成功且结束，即不可再做任何操作 */
    }
}
